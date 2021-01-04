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
    
    public void setRelativeConcentration(double relative) {
        this.concentration += relative;
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
        double relative = this.getConcentration() - device.getConcentration();
        if(relative<0) {
            device.setRelativeConcentration(relative * 0.5);
            this.setRelativeConcentration(-relative * 0.5);
            System.out.println("Change:\n\tFrom: "+device.getName()+"\n\tTo: "+ this.getName() +"\n\tValue: "+(-relative*0.5) + "\n\n");
        }
    }
    
    public void handleTimeout() {
        neighbours.forEach(
            neighbour -> {
                messageSender.send(this, neighbour);
            }
        );
    }
}
