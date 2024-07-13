package part2.labwork3.first;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.labwork1.first.WeatherWithStreams;

import static part2.labwork3.first.FileUtils.*;

/**
 * The {@code FileUtilsProgram} class demonstrates the usage of the {@code FileUtils} class methods.
 * It includes serialization and deserialization of {@code WeatherWithStreams} objects to and from text, XML, and JSON files.
 * It also demonstrates the usage of a logger within the {@code FileUtils} class.
 */
public class FileUtilsProgram {
    private static final String FILE_NAME = "storage/part2/labwork3/first/weather";

    /**
     * The main method of the {@code FileUtilsProgram} class.
     * It creates a {@code WeatherWithStreams} object, serializes it to a text file, XML file, and JSON file,
     * and then deserializes it back from these files.
     * The method also demonstrates sorting of the {@code WeatherWithStreams} object and usage of a logger.
     *
     * @param args the command-line arguments, not used in this method
     */
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(FileUtilsProgram.class);
        FileUtils.setLogger(logger);
        logger.info("Program started");

        WeatherWithStreams weather = (WeatherWithStreams) new WeatherWithStreams().createWeather();
        logger.info("Initial WeatherWithStreams object created");
        logger.debug(weather);
        System.out.println("\033[1;32m" + "Initial WeatherWithStreams object:" + "\033[0m");
        System.out.println(weather);

        writeToTxt(weather, FILE_NAME + ".txt");
        weather = readFromTxt(FILE_NAME + ".txt");
        logger.debug(weather);
        System.out.println("\033[1;32m" + "WeatherWithStreams object after reading from text file:" + "\033[0m");
        System.out.println(weather);

        logger.info("Sorting by comment...");
        System.out.println("\033[1;32m" + "Sorting by comment..." + "\033[0m");
        weather.sortByComment();
        serializeToXML(weather, FILE_NAME + ".xml");
        weather = deserializeFromXML(FILE_NAME + ".xml");
        logger.debug(weather);
        System.out.println("\033[1;32m" + "WeatherWithStreams object after deserializing from XML:" + "\033[0m");
        System.out.println(weather);

        logger.info("Sorting by decreasing temperature...");
        System.out.println("\033[1;32m" + "Sorting by decreasing temperature..." + "\033[0m");
        weather.sortByDecreasingTemperature();
        serializeToJSON(weather, FILE_NAME + ".json");
        weather = deserializeFromJSON(FILE_NAME + ".json");
        logger.debug(weather);
        System.out.println("\033[1;32m" + "WeatherWithStreams object after deserializing from JSON:" + "\033[0m");
        System.out.println(weather);

        logger.info("Program finished");
    }
}
