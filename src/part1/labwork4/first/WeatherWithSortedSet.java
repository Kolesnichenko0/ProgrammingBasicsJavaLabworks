package part1.labwork4.first;

import part1.labwork3.first.Day;

import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represents weather data with an SortedSet of days.
 * This class is inherited from the abstract {@link WeatherWithCollection}
 */
public class WeatherWithSortedSet extends WeatherWithCollection {
    private static final long serialVersionUID = 176873029745254541L;
    private SortedSet<Day> days = new TreeSet<>(Comparator.comparing(Day::hashCode));

    /**
     * The constructor initialises the object with the default values.
     */
    public WeatherWithSortedSet() {
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment},  {@code days}.
     *
     * @param season  the season
     * @param comment the comment
     * @param days    the SortedSet of days
     */
    public WeatherWithSortedSet(String season, String comment, SortedSet<Day> days) {
        super(season, comment);
        this.days = days;
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment}.
     *
     * @param season  the season
     * @param comment the comment
     */
    public WeatherWithSortedSet(String season, String comment) {
        super(season, comment);
    }

    /**
     * Gets the array of days for the weather.
     *
     * @return the array of days
     */
    @Override
    public Day[] getDays() {
        return days.toArray(new Day[0]);
    }

    /**
     * Gets the SortedSet of days for the weather.
     *
     * @return the SortedSet of days
     */
    public SortedSet<Day> getDaysSortedSet() {
        return days;
    }

    /**
     * Sets the SortedSet of days for the weather.
     *
     * @param days the array of days
     */
    @Override
    public void setDays(Day[] days) {
        this.days = new TreeSet<>(Comparator.comparing(Day::hashCode));
        this.days.addAll(Arrays.asList(days));
    }

    /**
     * Gets the {@code day} with index {@code i}.
     *
     * @return the object of class {@code Day} with index {@code i}
     */
    @Override
    public Day getDay(int i) {
        return days.toArray(new Day[0])[i];
    }

    /**
     * Sets the {@code day} with index {@code i}.
     *
     * @param i   index of {@code day}
     * @param day the object of class {@code Day} with index {@code i} to be set
     */
    @Override
    public void setDay(int i, Day day) {
        days.remove(getDay(i));
        days.add(day);
    }

    /**
     * Adds a link to the new {@code day}.
     *
     * @param day the object of class {@code Day} to be added
     * @return {@code true}, if the link was added successfully
     * {@code false} otherwise
     */
    @Override
    public boolean addDay(Day day) {
        return days.add(day);
    }

    /**
     * Creates a new {@code day} and adds a link to it.
     *
     * @param Date        the date
     * @param temperature the temperature
     * @param comment     the comment
     * @return {@code true}, if the link was added successfully
     * {@code false} otherwise
     */
    @Override
    public boolean addDay(String Date, double temperature, String comment) {
        return addDay(new Day(Date, temperature, comment));
    }

    /**
     * Counts the number of days in the sequence.
     *
     * @return the number of days
     */
    @Override
    public int daysCount() {
        return days.size();
    }

    /**
     * Clears the sequence of days.
     */
    @Override
    public void clearDays() {
        days.clear();
    }

    /**
     * Overridden decreasing temperature sorting method using {@code TreeSet}
     * Is provided by the implementation of the Comparable interface for the {@code Day} class
     */
    @Override
    public void sortByDecreasingTemperature() {
        SortedSet<Day> newSet = new TreeSet<>();
        newSet.addAll(days);
        days = newSet;
    }

    /**
     * Overridden comment sorting method using the {@code TreeSet}.
     * Is provided by {@code Comparator}.
     */
    @Override
    public void sortByComment() {
        SortedSet<Day> newSet = new TreeSet<>(Comparator.comparing(Day::getComment));
        newSet.addAll(days);
        days = newSet;
    }
}
