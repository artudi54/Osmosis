import config.Configuration;
import simulation.Simulator;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        URL config = Main.class.getClassLoader().getResource("configuration.json");
        Configuration configuration = Configuration.loadFrom(config);
        System.out.println(configuration);
        
        Simulator simulator = new Simulator(configuration);
        simulator.run(100);
        System.out.println("Simulation finished");
    }
}
