package part1.labwork5.first;

import part1.labwork4.first.WeatherWithList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public abstract class WeatherWithFile extends WeatherWithList {
    private static final long serialVersionUID = 176873029745254541L;
    /**
     * Saves weather and day data to a given file
     *
     * @param fileName file name
     * @throws Exception if an error occurs while writing or processing data.
     */
    public abstract void writeToFile(String fileName) throws Exception;

    /**
     * Reads weather and day data from a given file
     *
     * @param fileName file name
     * @throws Exception if an error occurs while reading or processing data from a file.
     *                   if some other I/O error occurs.
     */
    public abstract void readFromFile(String fileName) throws Exception;

    /**
     * Performs testing of class methods
     */
    public void testWeather(String weatherFile, String toByDecreasingTemperature,
                            String toByComment, String noDaysFile) {
        createWeather();
        try {
            writeToFile(weatherFile);

            testSorting();
            System.out.println("Clear days:");
            setComment("No days");
            clearDays();
            System.out.println(this);

            writeToFile(noDaysFile);
            readFromFile(noDaysFile);
            System.out.println("Weather without day:");
            System.out.println(this);
            readFromFile(weatherFile);

            sortByDecreasingTemperature();
            writeToFile(toByDecreasingTemperature);
            sortByComment();
            writeToFile(toByComment);
        } catch (FileNotFoundException e) {
            System.err.println("Read failed or no path found");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Write failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class definition of a serialized object cannot be found");
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.err.println("Wrong format");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        testSorting();
    }
}
