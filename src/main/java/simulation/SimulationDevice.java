package simulation;

import java.util.List;

public class SimulationDevice {
    private final String name;
    private int tasks;
    private final int capacity;
    private final double delay;
    private final List<String> neighbours;
    private MessageSender messageSender;


    public SimulationDevice(String name, int tasks, int capacity, double delay, List<String> neighbours) {
        this.name = name;
        this.tasks = tasks;
        this.capacity = capacity;
        this.delay = delay;
        this.neighbours = neighbours;
        this.messageSender = null;
    }

    public String getName() {
        return name;
    }

    public int getTasks() { return tasks; }

    public void setTasks(int tasks) { this.tasks = tasks; }

    public int getCapacity() { return capacity; }
    
    private void addTask(){
        tasks++;
    }
    
    private void removeTask(){
        tasks--;
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
    
    private double getConcentration(){
        return (double) this.tasks/this.capacity;
    }

    
    public void handleMessage(SimulationDevice device) {
        double relative = this.getConcentration() - device.getConcentration();
        if(relative<0) {
            device.removeTask();
            this.addTask();
            System.out.println("Task moved:\n\tFrom: "+device.getName()+"\n\tTo: "+ this.getName() +"\n");
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
