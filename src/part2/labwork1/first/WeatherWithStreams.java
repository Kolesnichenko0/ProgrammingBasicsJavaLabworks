package part2.labwork1.first;

import part1.labwork3.first.Day;
import part1.labwork4.first.WeatherWithList;

import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Represents weather data with a List of days.
 * Stream API tools are used to process sequences of elements.
 * This class is inherited from the {@link WeatherWithList}
 */
public class WeatherWithStreams extends WeatherWithList {
    /**
     * The constructor initialises the object with the default values.
     */
    public WeatherWithStreams() {
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment},  {@code days}.
     *
     * @param season  the season
     * @param comment the comment
     * @param days    the list of days
     */
    public WeatherWithStreams(String season, String comment, List<Day> days) {
        super(season, comment);
        setDays(days);
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment}.
     *
     * @param season  the season
     * @param comment the comment
     */
    public WeatherWithStreams(String season, String comment) {
        super(season, comment);
    }

    /**
     * Sets the list of days for the weather.
     * But if there are equal days in List, only one of them will be recorded.
     *
     * @param days the list of days
     */
    @Override
    public void setDays(List<Day> days) {
        super.setDays(days.stream()
                .distinct()
                .collect(Collectors.toList()));
    }

    /**
     * Sets the list of days for the weather.
     *
     * @param days the array of days
     */
    @Override
    public void setDays(Day[] days) {
        setDays(Arrays.asList(days));
    }

    /**
     * Overridden decreasing temperature sorting method using Stream API
     * with the help of the {@link Comparator} interface
     */
    @Override
    public void sortByDecreasingTemperature() {
        setDays(getDaysList().stream()
                .sorted(Comparator.comparing(Day::getTemperature).reversed())
                .collect(Collectors.toList()));
    }

    /**
     * Overridden comment sorting method using Stream API
     * with the help of the {@link Comparator} interface
     */
    @Override
    public void sortByComment() {
        setDays(getDaysList().stream()
                .sorted(Comparator.comparing(Day::getComment))
                .collect(Collectors.toList()));
    }

    /**
     * Calculates the average temperature for weather days.
     *
     * @return null - if the list of days is empty,
     * Double.NaN - if there will be problems with the calculation
     * the average temperature otherwise.
     */
    @Override
    public Double calculateAverageTemperature() {
        if (getDaysList().isEmpty())
            return null;
        return getDaysList().stream()
                .mapToDouble(Day::getTemperature)
                .average()
                .orElse(Double.NaN);
    }
    /**
     * Finds the days with the maximum temperature.
     * The temperature of the day can be recorded not just as a numerical literal,
     * but as a calculation. So, the method uses a precision of 0.0001 to compare temperatures.
     *
     * @return An array of {@code Day} objects with the maximum temperature.
     * If the list of days is empty, returns null.
     */
    @Override
    public Day[] findMaxTemperatureDays() {
        Day maxTemperatureDay = getDaysList().stream()
                .max(Comparator.comparing(Day::getTemperature))
                .orElse(null);

        if (maxTemperatureDay == null)
            return null;

        double epsilon = 0.0001; // precision
        return getDaysList().stream()
                .filter(day -> Math.abs(day.getTemperature() - maxTemperatureDay.getTemperature()) < epsilon)
                .toArray(Day[]::new);
    }

    /**
     * Finds the days with the longest comments.
     *
     * @return An array of {@code Day} objects with the longest comments.
     * If the list of days is empty, returns null.
     */
    @Override
    public Day[] findLongestCommentDays() {
        Day LongestCommentDay = getDaysList().stream()
                .max(Comparator.comparing(Day::calculateLengthComment))
                .orElse(null);

        if (LongestCommentDay == null)
            return null;

        return getDaysList().stream()
                .filter(day -> day.calculateLengthComment() == LongestCommentDay.calculateLengthComment())
                .toArray(Day[]::new);
    }
    /**
     * Demonstrates the functionality of the {@code WeatherWithStreams}  class.
     * Prints the created weather, performs a search test, and a sorting test.
     */
    public void showFunctionality() {
        System.out.println("Created the weather:\n" + this);
        this.testSearch();
        this.testSorting();
    }
    /**
     * Creates a new instance of {@code WeatherWithStreams} with predefined values.
     * @return the object of class {@code WeatherWithStreams}
     */
    public static WeatherWithStreams createWeatherWithStreams() {
        return new WeatherWithStreams("Winter", "7 days are described with streams",
                Arrays.asList(
                        new Day("09.12.22", -5.2, "Cloudy"),
                        new Day("10.12.22", -3.1, "Windy"),
                        new Day("11.12.22", -6.0, "Sunny"),
                        new Day("12.12.22", -7.6, "Drizzly"),
                        new Day("13.12.22", -3.2, "Foggy"),
                        new Day("14.12.22", 0, "Foggy and cloudy"),
                        new Day("15.12.22", 1, "Windy and sunny"),
                        new Day("15.12.22", 1, "Windy and sunny")
                ));
    }

}
