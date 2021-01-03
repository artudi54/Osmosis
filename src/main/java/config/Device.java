package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Device {
    private final String name;
    private final double concentration;
    private final List<String> neighbours;

    @JsonCreator
    public Device(@JsonProperty("name") String name,
                  @JsonProperty("concentration") double concentration,
                  @JsonProperty("neighbours") List<String> neighbours) {
        this.name = name;
        this.concentration = concentration;
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }

    public double getConcentration() {
        return concentration;
    }
    
    public List<String> getNeighbours() {
        return neighbours;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Double.compare(device.concentration, concentration) == 0 && Objects.equals(name, device.name) && Objects.equals(neighbours, device.neighbours);
    }

    @Override
    public String toString() {
        return "Device{" +
            "name='" + name + '\'' +
            ", concentration=" + concentration +
            ", neighbours=" + neighbours +
            '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, concentration, neighbours);
    }
}
