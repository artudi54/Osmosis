package simulation.output;

import simulation.output.external.GifSequenceWriter;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class GifCreator {
    public static Path generate(Path directory) throws IOException {
        Path outputPath = directory.resolve("output.gif");
        List<BufferedImage> images = Files.list(directory)
            .sorted()
            .map(Path::toFile)
            .map(file -> {
                try {
                    return ImageIO.read(file);
                } catch (IOException exception) {
                    exception.printStackTrace();
                    return null;
                }
            })
            .collect(Collectors.toList());
        
        ImageOutputStream output = new FileImageOutputStream(outputPath.toFile());
        int type = images.get(0).getType();
        GifSequenceWriter writer = new GifSequenceWriter(output, type, 100, true);
        images.forEach(image -> {
            try {
                writer.writeToSequence(image);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        writer.close();
        output.close();
        return outputPath;
    }
}
