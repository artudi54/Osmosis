package simulation;

import simulation.output.StatusLogger;

import java.util.List;

public class SimulationDevice {
    private final String name;
    private int tasks;
    private final int capacity;
    private final double delay;
    private final List<String> neighbours;
    private StatusLogger statusLogger;
    private MessageSender messageSender;


    public SimulationDevice(String name, int tasks, int capacity, double delay, List<String> neighbours) {
        this.name = name;
        this.tasks = tasks;
        this.capacity = capacity;
        this.delay = delay;
        this.neighbours = neighbours;
        this.statusLogger = null;
        this.messageSender = null;
    }

    public String getName() {
        return name;
    }

    public int getTasks() { return tasks; }

    public void setTasks(int tasks) { this.tasks = tasks; }

    private void addTask(){
        tasks++;
    }

    private void removeTask(){
        tasks--;
    }

    public int getCapacity() { return capacity; }

    public double getConcentration(){
        return (double) this.tasks/this.capacity;
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

    public StatusLogger getStatusLogger() {
        return statusLogger;
    }

    public void setStatusLogger(StatusLogger statusLogger) {
        this.statusLogger = statusLogger;
    }
    

    public void handleMessage(SimulationDevice device) {
        double relative = this.getConcentration() - device.getConcentration();
        if (relative < 0) {
            device.removeTask();
            this.addTask();
            statusLogger.logMove(device, this);
        }
    }
    
    public void handleTimeout() {
        neighbours.forEach(neighbour -> messageSender.send(this, neighbour));
    }
}
