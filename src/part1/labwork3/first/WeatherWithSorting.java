package part1.labwork3.first;

import java.util.Arrays;

/**
 * Represents weather data with an array of days and overridden sorting methods.
 * This class is inherited from the abstract {@link WeatherWithArray}
 */
public class WeatherWithSorting extends WeatherWithArray {
    private static final long serialVersionUID = 176873029745254541L;
    /**
     * The constructor initialises the object with the default values.
     */
    public WeatherWithSorting() {
    }

    /**
     * The constructor initialises the object with the specified values with {@code season} and {@code comment}
     *
     * @param season  the season
     * @param comment the comment
     */
    public WeatherWithSorting(String season, String comment) {
        super(season, comment);
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment},  {@code days}.
     *
     * @param season  the season
     * @param comment the comment
     * @param days    the array of days
     */
    public WeatherWithSorting(String season, String comment, Day[] days) {
        super(season, comment, days);
    }

    /**
     * Overridden decreasing temperature sorting method using the standard sort function of class {@code Arrays}
     * Is provided by the implementation of the Comparable interface for the {@code Day} class
     */
    @Override
    public void sortByDecreasingTemperature() {
        if (getDays() == null)
            return;
        Arrays.sort(getDays());
    }

    /**
     * Overridden comment sorting method using the standard sort function of class {@code Arrays} and {@code Comparator}.
     * Is provided by creating a separate class {@code CommentComparator} that implements the {@code Comparator} interface.
     */
    @Override
    public void sortByComment() {
        if (getDays() == null)
            return;
        Arrays.sort(getDays(), new CommentComparator());
    }
    /**
     * Carries out testing of the default constructor
     *
     * @return the object of class {@code WeatherWithSorting}
     */
    private static WeatherWithSorting testDefaultConstructor() {
        return new WeatherWithSorting();
    }
    /**
     * Carries out testing of the specific constructor with {@code season}, {@code comment},  {@code days}.
     *
     * @return the object of class {@code WeatherWithSorting}
     */
    private static WeatherWithSorting testWithDaysConstructor(String season, String comment, Day[] days) {
        return new WeatherWithSorting(season, comment, days);
    }
    /**
     * Carries out testing of the specific constructor with {@code season}, {@code comment}.
     *
     * @return the object of class {@code WeatherWithSorting}
     */
    private static WeatherWithSorting testWithoutDaysConstructor(String season, String comment) {
        return new WeatherWithSorting(season, comment);
    }
    /**
     * Carries out testing of the functionality of the {@code WeatherWithSorting} class.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Day[] firstDays = {new Day("29.12.22", -4.2, "Windy"),
                new Day("30.12.22", -4.6, "Cloudy")
        };
        System.out.println("Use default constructor for create defaultWeather:");
        WeatherWithSorting defaultWeather = testDefaultConstructor();
        System.out.println(defaultWeather);
        System.out.println("Use with days constructor for create firstSpecificWeather:");
        WeatherWithSorting firstSpecificWeather = testWithDaysConstructor("Winter", "2 days are described", firstDays);
        System.out.println(firstSpecificWeather);
        System.out.println("Use without days constructor for create secondSpecificWeather:");
        WeatherWithSorting secondSpecificWeather = testWithoutDaysConstructor("Winter", "3 days are described");
        System.out.println(secondSpecificWeather);
        firstSpecificWeather.testSorting();
    }

}
