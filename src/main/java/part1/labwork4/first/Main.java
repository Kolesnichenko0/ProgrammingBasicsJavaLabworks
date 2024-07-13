package part1.labwork4.first;

import part1.labwork3.first.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.List;

/**
 * The {@code Main} class represents testing of the functionality
 * of the {@link WeatherWithCollection}, {@link WeatherWithList} and {@link WeatherWithSortedSet} classes.
 *
 */
public class Main {
    private static void testWeather(WeatherWithCollection weather) {
        weather.testFunctionality();
        weather.testSorting();
    }

    /**
     * Carries out testing of the functionality of the necessary classes.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Day[] days = {new Day("22.12.22", -4.1, "Snowy"),
                new Day("24.12.22", -2.9, "Windy"),
                new Day("23.12.22", -2.5, "Sunny"),
                new Day("24.12.22", -2.9, "Windy")
        };
        System.out.println("Test functionality of class WeatherWithList:");
        WeatherWithList weatherList = new WeatherWithList("Winter", "3 days are described",
                new ArrayList<>(Arrays.asList(days)));
        testWeather(weatherList);

        System.out.println("Test functionality of class WeatherWithSortedSet:");
        SortedSet<Day> daysSet = new TreeSet<>(Comparator.comparing(Day::hashCode));
        daysSet.addAll(Arrays.asList(days));
        WeatherWithSortedSet weatherSortedSet = new WeatherWithSortedSet("Winter", "3 days are described", daysSet);
        System.out.println("Testing the getDaysSortedSet() function:");
        SortedSet<Day> daysSortedSet = weatherSortedSet.getDaysSortedSet();
        daysSortedSet.forEach(System.out::println);
        testWeather(weatherSortedSet);
    }
}
