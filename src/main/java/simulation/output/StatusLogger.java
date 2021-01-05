package simulation.output;

import simulation.SimulationDevice;

import java.util.List;

public class StatusLogger {
    private final List<SimulationDevice> devices;

    public StatusLogger(List<SimulationDevice> devices) {
        this.devices = devices;
        this.devices.forEach(device -> device.setStatusLogger(this));
    }

    public void startReport(double time) {
        System.out.println("Time from start " + time + "s:");
    }
    
    public void logMove(SimulationDevice from, SimulationDevice to) {
        System.out.println("Task moved from '" + from.getName() + "' to '" + to.getName() + "'");
    }
    
    public void logAll() {
        devices.forEach(device -> System.out.println("Device: " + device.getName() + " Current tasks " + device.getTasks() + "/" + device.getCapacity() + " Concentration " + device.getConcentration()));
    }
    
    public void finishReport() {
        System.out.println("\n");
    }
}
