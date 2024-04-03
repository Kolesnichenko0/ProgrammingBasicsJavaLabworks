package part1.labwork5.first;

import part1.labwork3.first.Day;

import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileInputStream;

public class WeatherWithBinaryFile extends WeatherWithFile {
    private static final long serialVersionUID = 176873029745254541L;
    /**
     * Saves weather and day data to a given file
     *
     * @param fileName file name
     * @throws IOException if some other I/O error occurs.
     */
    @Override
    public void writeToFile(String fileName) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))) {
            out.writeUTF(getSeason());
            out.writeUTF(getComment());
            out.writeInt(daysCount());
            for (Day day : getDays()) {
                out.writeUTF(day.getDate());
                out.writeDouble(day.getTemperature());
                out.writeUTF(day.getComment());
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
        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            setSeason(in.readUTF());
            setComment(in.readUTF());
            int daysCount = in.readInt();
            for (int i = 0; i < daysCount; i++) {
                String date = in.readUTF();
                double temperature = in.readDouble();
                String comment = in.readUTF();
                addDay(new Day(date, temperature, comment));
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
        String path = "resources/first/binaryFiles/";
        new WeatherWithBinaryFile().testWeather(path + "weather.dat",
                path + "byTemperature.dat",
                path + "byComments.dat",
                path + "noDaysWeather.dat");
    }
}
