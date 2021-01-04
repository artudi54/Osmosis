package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class ConfigurationDevice {
    private final String name;
    private final int tasks;
    private final int capacity;
    private final double delay;
    private final List<String> neighbours;

    @JsonCreator
    public ConfigurationDevice(@JsonProperty("name") String name,
                               @JsonProperty("tasks") int tasks,
                               @JsonProperty("capacity") int capacity,
                               @JsonProperty("delay") double delay,
                               @JsonProperty("neighbours") List<String> neighbours) {
        this.name = name;
        this.tasks = tasks;
        this.capacity = capacity;
        this.delay = delay;
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() { return capacity; }

    public int getTasks() { return tasks; }

    public double getDelay() {
        return delay;
    }

    public List<String> getNeighbours() {
        return neighbours;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationDevice device = (ConfigurationDevice) o;
        return tasks == device.tasks && capacity==device.capacity && Double.compare(device.delay, delay) == 0 && Objects.equals(name, device.name) && Objects.equals(neighbours, device.neighbours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tasks, capacity, delay, neighbours);
    }

    @Override
    public String toString() {
        return "ConfigurationDevice{" +
            "name='" + name + '\'' +
            ", tasks=" + tasks +
            ", capacity=" + capacity+
            ", delay=" + delay +
            ", neighbours=" + neighbours +
            "}\r\n" ;
    }
}
