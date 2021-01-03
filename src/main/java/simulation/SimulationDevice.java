package simulation;

import java.util.List;

public class SimulationDevice {
    private final String name;
    private double concentration;
    private final double delay;
    private final List<String> neighbours;
    private MessageSender messageSender;


    public SimulationDevice(String name, double concentration, double delay, List<String> neighbours) {
        this.name = name;
        this.concentration = concentration;
        this.delay = delay;
        this.neighbours = neighbours;
        this.messageSender = null;
    }

    public String getName() {
        return name;
    }

    public double getConcentration() {
        return concentration;
    }

    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }

    public double getDelay() {
        return delay;
    }

    public List<String> getNeighbours() {
        return neighbours;
    }

    public MessageSender getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void handleMessage(SimulationDevice device) {
        
    }
    
    
    public void handleTimeout() {
        
    }
}
