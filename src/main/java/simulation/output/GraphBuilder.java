package simulation.output;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.Graph;
import simulation.SimulationDevice;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphBuilder {
    private final Map<String, SimulationDevice> deviceMap;
    private final Path directory;
    
    public GraphBuilder(List<SimulationDevice> devices, Path directory) {
        this.deviceMap = devices.stream()
            .collect(Collectors.toMap(SimulationDevice::getName, x -> x));
        this.directory = directory;
    }
    
    public void render(double time) throws IOException {
        String name = "output-" + time + ".png";
        Path path = directory.resolve(name);
        Graphviz.fromGraph(buildGraph()).render(Format.PNG).toFile(path.toFile());
    }
    
    private Graph buildGraph() {
        return null;
    }
}
