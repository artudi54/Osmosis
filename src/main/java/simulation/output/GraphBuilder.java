package simulation.output;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;
import simulation.SimulationDevice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphBuilder {
    private final Map<String, SimulationDevice> deviceMap;
    private final Path directory;
    private int count;
    
    public GraphBuilder(List<SimulationDevice> devices, Path directory) throws IOException {
        this.deviceMap = devices.stream()
            .collect(Collectors.toMap(SimulationDevice::getName, x -> x));
        this.directory = directory;
        this.count = 0;
        recreateDirectory();
    }
    
    private void recreateDirectory() throws IOException {
        if (Files.exists(directory)) {
            Files.walk(directory)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        }
        Files.createDirectories(directory);
    }
    
    
    public void render(double time) throws IOException {
        String name = String.format("output %05d (%fs).png", count++, time);
        Path path = directory.resolve(name);
        Graphviz.fromGraph(buildGraph()).render(Format.PNG).toFile(path.toFile());
        System.gc();
    }
    
    private Graph buildGraph() {
        return Factory.graph()
            .directed()
            .with(makeLinks());
    }
    
    private List<LinkSource> makeLinks() {
        return deviceMap
            .values()
            .stream()
            .map(device -> {
                Node node = Factory.node(device.getName())
                    .with(makeColor(device), Style.FILLED)
                    .with(Label.html(makeLabel(device)));
                for (String neighbour : device.getNeighbours()) {
                    node = node.link(neighbour);
                }
                return node;
            })
            .collect(Collectors.toList());
    }
    
    private String makeLabel(SimulationDevice device) {
        return String.format("<b>%s</b><br/>%d/%d<br/>%f", device.getName(), device.getTasks(), device.getCapacity(), device.getConcentration());
    }
    
    private Color makeColor(SimulationDevice device) {
        int value = 255 - (int)(255 * device.getConcentration());
        return Color.rgb(255, value, value);
    }
}
