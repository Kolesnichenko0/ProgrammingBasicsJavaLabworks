package part1.labwork5.first;

import part1.labwork3.first.Day;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WeatherWithTextFile extends WeatherWithFile {
    private static final long serialVersionUID = 176873029745254541L;

    /**
     * Saves weather and day data to a given file
     *
     * @param fileName file name
     * @throws IOException if some other I/O error occurs.
     */
    @Override
    public void writeToFile(String fileName) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println("Season: " + getSeason() + ". Comment: " + getComment());
            for (Day day : getDays()) {
                out.println("Date: " + day.getDate() + ". Temperature: " + day.getTemperature() + " degrees Celsius.");
                out.println("Comment: " + day.getComment());
            }
        }
    }

    /**
     * Reads weather and day data from a given file
     *
     * @param fileName file name
     * @throws IOException if some other I/O error occurs.
     */
    @Override
    public void readFromFile(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            String line = scanner.nextLine();
            setSeason(line.split(": ")[1].split(". ")[0]);
            setComment(line.split("Comment: ")[1]);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.startsWith("Date: ")) {
                    String date = line.split(": ")[1].split(". ")[0];
                    double temperature = Double.parseDouble(line.split("Temperature: ")[1].split(" degrees Celsius.")[0]);
                    String comment = scanner.nextLine().split(": ")[1];
                    addDay(new Day(date, temperature, comment));
                }
            }
        }
    }

    /**
     * Carries out testing of the functionality of the necessary classes.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String path = "resources/first/textFiles/";
        new WeatherWithTextFile().testWeather(path + "weather.txt",
                path + "byTemperature.txt",
                path + "byComments.txt",
                path + "noDaysWeather.txt");
    }
}
