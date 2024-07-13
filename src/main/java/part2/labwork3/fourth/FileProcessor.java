package part2.labwork3.fourth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileProcessor {

    public static void writeToFile(String fileName, String data) throws IOException {
        Files.write(Paths.get(fileName), data.getBytes());
    }

    public static String readFromFile(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName)).collect(Collectors.joining("\n"));
    }
}