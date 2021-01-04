package simulation;

import config.Configuration;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class Simulator {
    private final List<SimulationDevice> simulationDevices;
    private final MessageSender messageSender;
    private final Timer timer;
    private int iterationCount;
    
    private long startTime;
    
    public Simulator(Configuration configuration) {
        simulationDevices = configuration
            .getDevices()
            .stream()
            .map(confDev -> new SimulationDevice(
                confDev.getName(),
                confDev.getConcentration(),
                confDev.getDelay(),
                confDev.getNeighbours()
            ))
            .collect(Collectors.toList());
        messageSender = new MessageSender(simulationDevices);
        timer = new Timer();
    } 
    
    public void start() {
        startTime = System.nanoTime();
        simulationDevices.forEach(
            device -> {
                long timeout = (long)(device.getDelay() * 1000);
                TimerTask task = new TimerTask() {
                    @Override public void run() {
                        device.handleTimeout();
                        printStatus();
                    }
                };
                timer.scheduleAtFixedRate(task, timeout, timeout);
            }
        );
    }
    
    private void printStatus() {
        System.out.println("Time from start " + (double) (System.nanoTime()-startTime)/1000000000+ " s: ");
        simulationDevices.forEach(
            device -> System.out.println("Device: " + device.getName() + " Concentration: " + device.getConcentration())
        );
        System.out.println("\n");
    }
}
