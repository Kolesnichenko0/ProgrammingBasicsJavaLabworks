package part1.labwork5.first;


import java.io.*;

public class WeatherWithBinarySerialization extends WeatherWithFile {


    /**
     * Saves object of {@code WeatherWithBinarySerialization} to a given file
     *
     * @param fileName file name
     * @throws IOException if some other I/O error occurs.
     */
    @Override
    public void writeToFile(String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
        }
    }

    /**
     * Reads object of {@code WeatherWithBinarySerialization} from a given file
     *
     * @param fileName file name
     * @throws IOException            if some other I/O error occurs.
     * @throws ClassNotFoundException Class definition of a serialized object cannot be found.
     */
    @Override
    public void readFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            WeatherWithBinarySerialization weather = (WeatherWithBinarySerialization) in.readObject();
            setSeason(weather.getSeason());
            setComment(weather.getComment());
            setDays(weather.getDays());
        }
    }

    /**
     * Carries out testing of the functionality of the necessary classes.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String path = "resources/part1/labwork5/first/objects/";
        new WeatherWithBinarySerialization().testWeather(path + "weather.dat",
                path + "byTemperature.dat",
                path + "byComments.dat",
                path + "noDaysWeather.dat");
    }
}
