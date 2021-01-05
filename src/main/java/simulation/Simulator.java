package simulation;

import config.Configuration;
import simulation.output.StatusLogger;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class Simulator {
    private final List<SimulationDevice> simulationDevices;
    private final MessageSender messageSender;
    private final StatusLogger statusLogger;
    private final Timer timer;
    private long startTime;
    
    public Simulator(Configuration configuration) {
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
        timer = new Timer();
    } 
    
    public void start() {
        startTime = System.nanoTime();
        simulationDevices.forEach(
            device -> {
                long timeout = (long)(device.getDelay()*100);
                TimerTask task = new TimerTask() {
                    @Override public void run() { handleDevice(device); }
                };
                timer.scheduleAtFixedRate(task, timeout, timeout);
            }
        );
    }
    
    private void handleDevice(SimulationDevice device) {
        double time = (double)(System.nanoTime()-startTime)/1000000000;
        statusLogger.startReport(time);
        device.handleTimeout();
        statusLogger.logAll();
        statusLogger.finishReport();
    }
}
