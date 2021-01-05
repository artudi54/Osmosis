package simulation;

import config.Configuration;
import simulation.output.GraphBuilder;
import simulation.output.StatusLogger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class Simulator {
    private final List<SimulationDevice> simulationDevices;
    private final MessageSender messageSender;
    private final StatusLogger statusLogger;
    private final GraphBuilder graphBuilder;
    private final Timer timer;
    private long startTime;
    private double speed;
    private int iterationCount;
    
    public Simulator(Configuration configuration) throws IOException {
        simulationDevices = configuration
            .getDevices()
            .stream()
            .map(confDev -> new SimulationDevice(
                confDev.getName(),
                confDev.getTasks(),
                confDev.getCapacity(),
                confDev.getDelay(),
                confDev.getNeighbours()
            ))
            .collect(Collectors.toList());
        messageSender = new MessageSender(simulationDevices);
        statusLogger = new StatusLogger(simulationDevices);
        graphBuilder = new GraphBuilder(simulationDevices, Path.of("output"));
        timer = new Timer();
    } 
    
    public void run(double speed) throws InterruptedException {
        this.startTime = System.nanoTime();
        this.speed = speed;
        startTimers();
        renderGraph(0);
        awaitFinish();
    }
    
    private void startTimers() {
        simulationDevices.forEach(
            device -> {
                long timeout = (long)(device.getDelay() * 1000 / speed);
                TimerTask task = new TimerTask() {
                    @Override public void run() { handleDevice(device); }
                };
                timer.scheduleAtFixedRate(task, timeout, timeout);
            }
        );
    }
    
    private void handleDevice(SimulationDevice device) {
        ++iterationCount;
        double time = (double)(System.nanoTime() - startTime) / 1000000000 * speed;
        
        statusLogger.startReport(time);
        device.handleTimeout();
        statusLogger.logAll();
        statusLogger.finishReport();
        
        if (iterationCount % 20 == 0) {
            renderGraph(time);
        }
        
        if (checkStability()) {
            stopSimulation(time);
        }
    }
    
    private void renderGraph(double time) {
        try {
            graphBuilder.render(time);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
    
    private boolean checkStability() {
        double epsilonDiff = simulationDevices.stream()
            .map(SimulationDevice::getCapacity)
            .mapToDouble(x -> 2.0 / x)
            .min()
            .getAsDouble();
        return simulationDevices.stream()
            .allMatch(device -> simulationDevices
                .stream()
                .allMatch(secondDevice -> Math.abs(device.getConcentration() - secondDevice.getConcentration()) <= epsilonDiff));

    }
    
    private void stopSimulation(double time) {
        timer.cancel();
        renderGraph(time);
        synchronized (this) {
            notify();
        }
    }
    
    private void awaitFinish() throws InterruptedException {
        synchronized (this) {
            wait();
        }
    }
}
