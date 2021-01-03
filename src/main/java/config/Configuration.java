package config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class Configuration {
    private final List<Device> devices;

    @JsonCreator
    public Configuration(@JsonProperty("devices") List<Device> devices) {
        this.devices = devices;
    }

    public List<Device> getDevices() {
        return devices;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(devices, that.devices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(devices);
    }

    @Override
    public String toString() {
        return "Configuration{" +
            "devices=" + devices +
            '}';
    }

    public static Configuration loadFrom(URL source) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(source, Configuration.class);
    }
}
