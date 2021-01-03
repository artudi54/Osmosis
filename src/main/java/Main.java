import config.Configuration;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        URL config = Main.class.getClassLoader().getResource("configuration.json");
        Configuration configuration = Configuration.loadFrom(config);
        System.out.println(configuration);
    }
}
