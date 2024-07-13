package part2.labwork3.third;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileProcessor {
    public void printFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.lines(path).forEach(System.out::println);
    }

    public List<String> readLines(String inputFilePath) throws IOException {
        Path path = Paths.get(inputFilePath);
        return Files.lines(path).collect(Collectors.toList());
    }

    public List<String> sortLinesByLength(List<String> lines) {
        return lines.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
    }

    public List<String> filterLinesWithFragment(List<String> lines, String fragment) {
        return lines.stream()
                .filter(line -> line.contains(fragment))
                .collect(Collectors.toList());
    }

    public void writeLines(List<String> lines, String outputFilePath) throws IOException {
        Path path = Paths.get(outputFilePath);
        String content = String.join("\n", lines);
        Files.write(path, content.getBytes());
    }
}
