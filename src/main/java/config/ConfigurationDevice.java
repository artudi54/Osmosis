package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class ConfigurationDevice {
    private final String name;
    private final double concentration;
    private final double delay;
    private final List<String> neighbours;

    @JsonCreator
    public ConfigurationDevice(@JsonProperty("name") String name,
                               @JsonProperty("concentration") double concentration,
                               @JsonProperty("delay") double delay,
                               @JsonProperty("neighbours") List<String> neighbours) {
        this.name = name;
        this.concentration = concentration;
        this.delay = delay;
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }

    public double getConcentration() {
        return concentration;
    }

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
        return Double.compare(device.concentration, concentration) == 0 && Double.compare(device.delay, delay) == 0 && Objects.equals(name, device.name) && Objects.equals(neighbours, device.neighbours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, concentration, delay, neighbours);
    }

    @Override
    public String toString() {
        return "ConfigurationDevice{" +
            "name='" + name + '\'' +
            ", concentration=" + concentration +
            ", delay=" + delay +
            ", neighbours=" + neighbours +
            "}\r\n" ;
    }
}
