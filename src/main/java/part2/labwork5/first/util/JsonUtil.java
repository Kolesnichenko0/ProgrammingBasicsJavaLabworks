package part2.labwork5.first.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import part2.labwork5.first.model.DayForDB;
import part2.labwork5.first.model.WeatherForDB;
import part2.labwork5.first.model.Weathers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The JsonUtil class provides utility methods for handling JSON data.
 * It includes methods for importing and exporting data from and to JSON format.
 * This class cannot be instantiated.
 */
public class JsonUtil {
    /**
     * Imports weather data from a JSON file.
     *
     * @param fileName the name of the JSON file from which to import the data.
     * @return a Weathers object containing the imported weather data.
     */
    public static Weathers importFromJSON(String fileName) {
        try {
            XStream xStream = new XStream(new JettisonMappedXmlDriver());
            xStream.addPermission(NoTypePermission.NONE);
            xStream.allowTypes(new Class[]{Weathers.class, WeatherForDB.class, DayForDB.class});
            xStream.alias("weathers", Weathers.class);
            xStream.alias("weather", WeatherForDB.class);
            xStream.alias("day", DayForDB.class);
            Weathers weathers = (Weathers) xStream.fromXML(new File(fileName));
            return weathers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Exports weather data to a JSON file.
     *
     * @param weathers the Weathers object containing the weather data to be exported.
     * @param fileName the name of the JSON file to which the data will be exported.
     */
    public static void exportToJSON(Weathers weathers, String fileName) {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("weathers", Weathers.class);
        xStream.alias("weather", WeatherForDB.class);
        xStream.alias("day", DayForDB.class);
        String xml = xStream.toXML(weathers);
        try (FileWriter fw = new FileWriter(fileName); PrintWriter out = new PrintWriter(fw)) {
            out.println(xml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
