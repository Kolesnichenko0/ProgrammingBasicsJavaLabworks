package part2.labwork3.first;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import org.apache.logging.log4j.Logger;
import part1.labwork3.first.Day;
import part2.labwork1.first.WeatherWithStreams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The {@code FileUtils} class provides utility methods for file operations.
 * It includes methods for writing to and reading from text files,
 * serializing and deserializing objects to and from XML and JSON formats, and logging.
 * <p>
 * The class uses the Apache Log4j logging library for logging operations.
 * The logger can be set using the {@code setLogger} method.
 * <p>
 * The serialization and deserialization methods use the XStream library.
 * The XStream library is configured to use the JettisonMappedXmlDriver for JSON operations.
 * <p>
 * The {@code writeToTxt} and {@code readFromTxt} methods are used for writing to
 * and reading from text files. They use the Java NIO Files class for file operations.
 * <p>
 * The {@code serializeToXML} and {@code deserializeFromXML} methods are used for serializing
 * to and deserializing from XML files.
 * <p>
 * The {@code serializeToJSON} and {@code deserializeFromJSON} methods are used for serializing
 * to and deserializing from JSON files.
 */
public class FileUtils {
    private static Logger logger = null;

    /**
     * Returns the logger used by the FileUtils class.
     *
     * @return the logger
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * Sets the logger to be used by the FileUtils class.
     *
     * @param logger the logger to be set
     */
    public static void setLogger(Logger logger) {
        FileUtils.logger = logger;
    }

    /**
     * Writes the given {@code WeatherWithStreams} object to a text file.
     * The file is specified by the {@code fileName} parameter.
     * If the file already exists, it will be overwritten.
     *
     * @param weather  the {@code WeatherWithStreams} object to be written to the file
     * @param fileName the name of the file
     * @throws RuntimeException if an I/O error occurs
     */
    public static void writeToTxt(WeatherWithStreams weather, String fileName) {
        if (logger != null) {
            logger.info("Writing to text file");
        }
        try {
            Files.write(Path.of(fileName), weather.toListOfStrings());
        } catch (IOException e) {
            if (logger != null) {
                logger.error(e.toString());
            }
            throw new RuntimeException(e);
        }
        logger.info("Writing to text file was successful");
    }

    /**
     * Reads a {@code WeatherWithStreams} object from a text file.
     * The file is specified by the {@code fileName} parameter.
     *
     * @param fileName the name of the file
     * @return the {@code WeatherWithStreams} object read from the file
     * @throws RuntimeException if an I/O error occurs
     */
    public static WeatherWithStreams readFromTxt(String fileName) {
        WeatherWithStreams weather = new WeatherWithStreams();
        if (logger != null) {
            logger.info("Reading from text file");
        }
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            weather.fromListOfStrings(lines.collect(Collectors.toList()));
        } catch (IOException e) {
            if (logger != null) {
                logger.error(e.toString());
            }
            throw new RuntimeException(e);
        }
        logger.info("Reading from text file was successful");
        return weather;
    }

    /**
     * Serializes the given {@code WeatherWithStreams} object to an XML file.
     * The file is specified by the {@code fileName} parameter.
     * If the file already exists, it will be overwritten.
     *
     * @param weather  the {@code WeatherWithStreams} object to be serialized to the file
     * @param fileName the name of the file
     * @throws RuntimeException if an I/O error occurs
     */
    public static void serializeToXML(WeatherWithStreams weather, String fileName) {
        if (logger != null) {
            logger.info("Serializing to XML");
        }
        XStream xStream = new XStream();
        xStream.alias("weather", WeatherWithStreams.class);
        xStream.alias("day", Day.class);
        String xml = xStream.toXML(weather);
        try {
            Files.write(Path.of(fileName), xml.getBytes());
        } catch (IOException e) {
            if (logger != null) {
                logger.error(e.toString());
            }
            throw new RuntimeException(e);
        }
        logger.info("Writing to XML file was successful");
    }

    /**
     * Deserializes a {@code WeatherWithStreams} object from an XML file.
     * The file is specified by the {@code fileName} parameter.
     *
     * @param fileName the name of the file
     * @return the {@code WeatherWithStreams} object deserialized from the file
     * @throws RuntimeException if an I/O error occurs
     */
    public static WeatherWithStreams deserializeFromXML(String fileName) {
        if (logger != null) {
            logger.info("Deserializing from XML");
        }
        try {
            XStream xStream = new XStream();
            xStream.addPermission(NoTypePermission.NONE);
            xStream.allowTypes(new Class[]{WeatherWithStreams.class, Day.class});
            xStream.alias("weather", WeatherWithStreams.class);
            xStream.alias("day", Day.class);
            WeatherWithStreams weather = (WeatherWithStreams) xStream.fromXML(new File(fileName));
            logger.info("Deserializing from XML file was successful");
            return weather;
        } catch (Exception e) {
            if (logger != null) {
                logger.error(e.toString());
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * Serializes the given {@code WeatherWithStreams} object to a JSON file.
     * The file is specified by the {@code fileName} parameter.
     * If the file already exists, it will be overwritten.
     *
     * @param weather  the {@code WeatherWithStreams} object to be serialized to the file
     * @param fileName the name of the file
     * @throws RuntimeException if an I/O error occurs
     */
    public static void serializeToJSON(WeatherWithStreams weather, String fileName) {
        if (logger != null) {
            logger.info("Serializing to JSON");
        }
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("weather", WeatherWithStreams.class);
        xStream.alias("day", Day.class);
        String xml = xStream.toXML(weather);
        try {
            Files.write(Path.of(fileName), xml.getBytes());
        } catch (IOException e) {
            if (logger != null) {
                logger.error(e.toString());
            }
            throw new RuntimeException(e);
        }
        logger.info("Writing to JSON file was successful");
    }

    /**
     * Deserializes a {@code WeatherWithStreams} object from a JSON file.
     * The file is specified by the {@code fileName} parameter.
     *
     * @param fileName the name of the file
     * @return the {@code WeatherWithStreams} object deserialized from the file
     * @throws RuntimeException if an I/O error occurs
     */
    public static WeatherWithStreams deserializeFromJSON(String fileName) {
        if (logger != null) {
            logger.info("Deserializing from JSON");
        }
        try {
            XStream xStream = new XStream(new JettisonMappedXmlDriver());
            xStream.addPermission(NoTypePermission.NONE);
            xStream.allowTypes(new Class[]{WeatherWithStreams.class, Day.class});
            xStream.alias("weather", WeatherWithStreams.class);
            xStream.alias("day", Day.class);
            WeatherWithStreams weather = (WeatherWithStreams) xStream.fromXML(new File(fileName));
            logger.info("Deserializing from JSON file was successful");
            return weather;
        } catch (Exception e) {
            if (logger != null) {
                logger.error(e.toString());
            }
            throw new RuntimeException(e);
        }
    }
}
