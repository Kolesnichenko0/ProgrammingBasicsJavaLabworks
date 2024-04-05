package part2.labwork1.first;

import part1.labwork3.first.Day;
import part1.labwork4.first.WeatherWithList;

import java.util.*;
import java.util.stream.Collectors;

public class WeatherWithStreams extends WeatherWithList {
    public WeatherWithStreams() {
    }

    public WeatherWithStreams(String season, String comment, List<Day> days) {
        super(season, comment);
        setDays(days);
    }

    public WeatherWithStreams(String season, String comment) {
        super(season, comment);
    }

    @Override
    public void setDays(List<Day> days) {
        super.setDays(days.stream()
                .distinct()
                .collect(Collectors.toList()));
    }

    @Override
    public void setDays(Day[] days) {
        setDays(Arrays.asList(days));
    }

    @Override
    public void sortByDecreasingTemperature() {
        setDays(getDaysList().stream()
                .sorted(Comparator.comparing(Day::getTemperature).reversed())
                .collect(Collectors.toList()));
    }

    @Override
    public void sortByComment() {
        setDays(getDaysList().stream()
                .sorted(Comparator.comparing(Day::getComment))
                .collect(Collectors.toList()));
    }

    @Override
    public Double calculateAverageTemperature() {
        return getDaysList().stream()
                .mapToDouble(Day::getTemperature)
                .average()
                .orElse(Double.NaN);
    }

    //The temperature of the day can be recorded not just as a numerical literal, but as a calculation
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

    public void showFunctionality() {
        System.out.println("Created the weather:\n" + this);
        this.testSearch();
        this.testSorting();
    }

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
