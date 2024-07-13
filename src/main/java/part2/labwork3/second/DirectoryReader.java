package part2.labwork3.second;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class DirectoryReader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a directory name:");
        String directoryName = scanner.nextLine();

        File directory = new File(directoryName);
        if (directory.exists() && directory.isDirectory()) {
            FileLister lister = new FileLister();
            List<File> filesByIO = lister.listFilesByIOFile(directory);
            List<Path> filesByNIO = lister.listFilesByNIO(directory.toPath());

            if (filesByIO.isEmpty() && filesByNIO.isEmpty()) {
                System.out.println("The directory does not contain any files.");
            } else {
                System.out.println("\033[1;32m" + "Files listed by IO:" + "\033[0m");
                filesByIO.forEach(file -> System.out.println(file.getName()));

                System.out.println("\033[1;32m" + "Files listed by NIO:" + "\033[0m");
                filesByNIO.forEach(file -> System.out.println(file.getFileName()));
            }
        } else {
            System.out.println("The directory does not exist.");
        }
    }
}
