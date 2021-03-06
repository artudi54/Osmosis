import config.Configuration;
import simulation.Simulator;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Wybierz, który symulator uruchomić: c - chmura, v - chmura 2, f- fabryka, h - fabryka 2");
        URL config;
        Scanner s = new Scanner(System.in);
        char key = s.next().charAt(0);
        switch(key){
            case 'f':
            System.out.println("Włączono symulację fabryki");
            config = Main.class.getClassLoader().getResource("factory.json");
            break;
            case 'c':
            System.out.println("Włączono symulację chmury");
            config = Main.class.getClassLoader().getResource("cloud.json");
            break;
            case 'v':
            System.out.println("Włączono symulację chmury 2");
            config = Main.class.getClassLoader().getResource("cloud2.json");
            break;
            case 'h':
            System.out.println("Włączono symulację fabryki 2");
            config = Main.class.getClassLoader().getResource("factory2.json");
            break;
            default:
            System.out.println("Włączono symulację fabryki");
            config = Main.class.getClassLoader().getResource("factory.json");
        }
        
        Configuration configuration = Configuration.loadFrom(config);
        System.out.println(configuration);
        
        Simulator simulator = new Simulator(configuration);
        simulator.run(100);
        System.out.println("Simulation finished");
    }
}
