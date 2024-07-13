package part2.labwork3.third;

import java.io.IOException;
import java.util.List;

public class FileProcessorProgram {
    private static final String INPUT_FILE_PATH = "storage/part2/labwork3/third/input.txt";
    private static final String OUTPUT_FILE_PATH = "storage/part2/labwork3/third/output.txt";
    private static final String FRAGMENT = "a";

    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor();
        try {
            System.out.println("\033[1;32m" + "Content of " + INPUT_FILE_PATH + ":" + "\033[0m");
            fileProcessor.printFileContent(INPUT_FILE_PATH);

            System.out.println("\n\033[1;32m" + "Working with files:" + "\033[0m");

            System.out.println("Reading lines from " + INPUT_FILE_PATH + "...");
            List<String> lines = fileProcessor.readLines(INPUT_FILE_PATH);

            System.out.println("Sorting lines...");
            List<String> sortedLines = fileProcessor.sortLinesByLength(lines);

            System.out.println("Filtering lines with '" + FRAGMENT + "'...");
            List<String> linesWithA = fileProcessor.filterLinesWithFragment(sortedLines, FRAGMENT);

            System.out.println("Writing lines to " + OUTPUT_FILE_PATH + "...");
            fileProcessor.writeLines(linesWithA, OUTPUT_FILE_PATH);

            System.out.println("Done.\n");

            System.out.println("\033[1;32m" + "Content of " + OUTPUT_FILE_PATH + ":" + "\033[0m");
            fileProcessor.printFileContent(OUTPUT_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
