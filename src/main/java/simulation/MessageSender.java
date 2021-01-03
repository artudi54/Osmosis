package simulation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageSender {
    private final Map<String, SimulationDevice> deviceMap;
    
    public MessageSender(List<SimulationDevice> devices) {
        deviceMap = devices.stream()
            .collect(Collectors.toMap(SimulationDevice::getName, x -> x));
        devices.forEach(device -> device.setMessageSender(this));
    }
    
    public void send(SimulationDevice from, String to) {
        SimulationDevice toDevice = deviceMap.get(to);
        toDevice.handleMessage(from);
    }
}
