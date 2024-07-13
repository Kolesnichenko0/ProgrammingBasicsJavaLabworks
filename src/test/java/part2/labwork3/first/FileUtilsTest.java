package part2.labwork3.first;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import part2.labwork1.first.WeatherWithStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the FileUtils class.
 * It tests all the methods in the FileUtils class,
 * including those for reading and writing text files,
 * serializing and deserializing objects to and from XML and JSON formats, and logging.
 * <p>
 * The tests are organized into nested classes,
 * each of which contains tests for a specific group of methods or functionality.
 * <p>
 * The LoggingTests class tests the logging functionality of the FileUtils class.
 * <p>
 * The WriteAndReadTxtTests class tests the methods
 * for writing to and reading from text files.
 * <p>
 * The SerializeAndDeserializeXMLTests class tests the methods
 * for serializing to and deserializing from XML files.
 * <p>
 * The SerializeAndDeserializeJSONTests class tests the methods
 * for serializing to and deserializing from JSON files.
 * <p>
 * The InvalidPathTests class tests the behavior
 * of the FileUtils methods when given invalid file paths.
 * <p>
 * Each test method in these classes is annotated with @Test and @DisplayName annotations.
 * The @DisplayName annotation provides a human-readable name for the test.
 * <p>
 * Before each test, the setUp method is called to create a new WeatherWithStreams object.
 * After each test, the tearDown method is called to delete
 * any files that were created during the test.
 */
class FileUtilsTest {

    private WeatherWithStreams weather;

    @BeforeEach
    public void setUp() {
        weather = (WeatherWithStreams) new WeatherWithStreams().createWeather();
    }

    @Nested
    @DisplayName("Tests for logging")
    class LoggingTests {
        private static final String TEST_FILE_NAME = "storage/part2/labwork3/first/tests/test.txt";

        @AfterEach
        public void tearDown() throws IOException {
            Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
        }

        @Test
        @DisplayName("Should log correct message when writeToTxt is called")
        public void testLoggerInWriteToTxt() throws IOException {
            Logger logger = LogManager.getLogger(FileUtilsProgram.class);
            FileUtils.setLogger(logger);
            FileUtils.writeToTxt(weather, TEST_FILE_NAME);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String currentDate = LocalDate.now().format(formatter);
            String logFileName = "storage/part2/labwork3/first/logs/weather-" + currentDate + ".log";

            List<String> logLines = Files.readAllLines(Paths.get(logFileName));

            String lastLogMessage = logLines.get(logLines.size() - 1);
            assertTrue(lastLogMessage.contains("Writing to text file"));
        }
    }

    @Nested
    @DisplayName("Tests for writeToTxt and readFromTxt methods")
    class WriteAndReadTxtTests {
        private static final String FILE_NAME = "storage/part2/labwork3/first/tests/test.txt";

        @AfterEach
        public void tearDown() throws IOException {
            Files.deleteIfExists(Paths.get(FILE_NAME));
        }

        @Test
        @DisplayName("Should write WeatherWithStreams object to text file")
        public void testWriteToTxt() {
            FileUtils.writeToTxt(weather, FILE_NAME);
            assertTrue(Files.exists(Paths.get(FILE_NAME)));

            // Check that the written content is the same as the original
            try {
                List<String> writtenContent = Files.readAllLines(Paths.get(FILE_NAME));
                List<String> originalContent = weather.toListOfStrings();
                assertEquals(originalContent, writtenContent);
            } catch (IOException e) {
                fail("Failed to read the written file", e);
            }
        }

        @Test
        @DisplayName("Should read WeatherWithStreams object from text file")
        public void testReadFromTxt() {
            FileUtils.writeToTxt(weather, FILE_NAME);
            WeatherWithStreams readWeather = FileUtils.readFromTxt(FILE_NAME);
            assertEquals(weather, readWeather);
        }
    }

    @Nested
    @DisplayName("Tests for serializeToXML and deserializeFromXML methods")
    class SerializeAndDeserializeXMLTests {
        private static final String FILE_NAME = "storage/part2/labwork3/first/tests/test.xml";

        @AfterEach
        public void tearDown() throws IOException {
            Files.deleteIfExists(Paths.get(FILE_NAME));
        }

        @Test
        @DisplayName("Should serialize WeatherWithStreams object to XML file")
        public void testSerializeToXML() {
            FileUtils.serializeToXML(weather, FILE_NAME);
            assertTrue(Files.exists(Paths.get(FILE_NAME)));

            // Check that the serialized content is the same as the original
            try {
                WeatherWithStreams deserializedWeather = FileUtils.deserializeFromXML(FILE_NAME);
                assertEquals(weather, deserializedWeather);
            } catch (Exception e) {
                fail("Failed to deserialize the XML file", e);
            }
        }

        @Test
        @DisplayName("Should deserialize WeatherWithStreams object from XML file")
        public void testDeserializeFromXML() {
            FileUtils.serializeToXML(weather, FILE_NAME);
            WeatherWithStreams deserializedWeather = FileUtils.deserializeFromXML(FILE_NAME);
            assertEquals(weather, deserializedWeather);
        }
    }

    @Nested
    @DisplayName("Tests for serializeToJSON and deserializeFromJSON methods")
    class SerializeAndDeserializeJSONTests {
        private static final String FILE_NAME = "storage/part2/labwork3/first/tests/test.json";

        @AfterEach
        public void tearDown() throws IOException {
            Files.deleteIfExists(Paths.get(FILE_NAME));
        }

        @Test
        @DisplayName("Should serialize WeatherWithStreams object to JSON file")
        public void testSerializeToJSON() {
            FileUtils.serializeToJSON(weather, FILE_NAME);
            assertTrue(Files.exists(Paths.get(FILE_NAME)));

            // Check that the serialized content is the same as the original
            try {
                WeatherWithStreams deserializedWeather = FileUtils.deserializeFromJSON(FILE_NAME);
                assertEquals(weather, deserializedWeather);
            } catch (Exception e) {
                fail("Failed to deserialize the JSON file", e);
            }
        }

        @Test
        @DisplayName("Should deserialize WeatherWithStreams object from JSON file")
        public void testDeserializeFromJSON() {
            FileUtils.serializeToJSON(weather, FILE_NAME);
            WeatherWithStreams deserializedWeather = FileUtils.deserializeFromJSON(FILE_NAME);
            assertEquals(weather, deserializedWeather);
        }
    }

    @Nested
    @DisplayName("Tests for invalid file paths")
    class InvalidPathTests {
        private static final String INVALID_FILE_NAME = "invalid/path/test.txt";

        @Test
        @DisplayName("Should throw RuntimeException " +
                "when writeToTxt is called with invalid file path")
        public void testWriteToTxtInvalidPath() {
            assertThrows(RuntimeException.class, () -> FileUtils.writeToTxt(weather, INVALID_FILE_NAME));
        }

        @Test
        @DisplayName("Should throw RuntimeException " +
                "when readFromTxt is called with invalid file path")
        public void testReadFromTxtInvalidPath() {
            assertThrows(RuntimeException.class, () -> FileUtils.readFromTxt(INVALID_FILE_NAME));
        }

        @Test
        @DisplayName("Should throw RuntimeException " +
                "when serializeToXML is called with invalid file path")
        public void testSerializeToXMLInvalidPath() {
            assertThrows(RuntimeException.class, () -> FileUtils.serializeToXML(weather, INVALID_FILE_NAME));
        }

        @Test
        @DisplayName("Should throw RuntimeException " +
                "when deserializeFromXML is called with invalid file path")
        public void testDeserializeFromXMLInvalidPath() {
            assertThrows(RuntimeException.class, () -> FileUtils.deserializeFromXML(INVALID_FILE_NAME));
        }

        @Test
        @DisplayName("Should throw RuntimeException " +
                "when serializeToJSON is called with invalid file path")
        public void testSerializeToJSONInvalidPath() {
            assertThrows(RuntimeException.class, () -> FileUtils.serializeToJSON(weather, INVALID_FILE_NAME));
        }

        @Test
        @DisplayName("Should throw RuntimeException when " +
                "deserializeFromJSON is called with invalid file path")
        public void testDeserializeFromJSONInvalidPath() {
            assertThrows(RuntimeException.class, () -> FileUtils.deserializeFromJSON(INVALID_FILE_NAME));
        }
    }

}