package part1.labwork4.first;

import part1.labwork3.first.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Set;
import java.util.LinkedHashSet;


/**
 * Represents weather data with an ArrayList of days.
 * This class is inherited from the abstract {@link WeatherWithCollection}
 */
public class WeatherWithList extends WeatherWithCollection {
    private static final long serialVersionUID = 176873029745254541L;
    private List<Day> days = new ArrayList<>();

    /**
     * The constructor initialises the object with the default values.
     */
    public WeatherWithList() {
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment},  {@code days}.
     *
     * @param season  the season
     * @param comment the comment
     * @param days    the ArrayList of days
     */
    public WeatherWithList(String season, String comment, List<Day> days) {
        super(season, comment);
        Set<Day> uniqueSet = new LinkedHashSet<>(days);
        this.days = new ArrayList<>(uniqueSet);
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment}.
     *
     * @param season  the season
     * @param comment the comment
     */
    public WeatherWithList(String season, String comment) {
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
     * Gets the list of days for the weather.
     *
     * @return the list of days
     */
    protected List<Day> getDaysList() {
        return days;
    }

    /**
     * Sets the list of days for the weather.
     * But if there are equal days in List, all will be recorded.
     *
     * @param days the list of days
     */
    protected void setDays(List<Day> days) {
        this.days = days;
    }

    /**
     * Sets the list of days for the weather.
     *
     * @param days the array of days
     */
    @Override
    public void setDays(Day[] days) {
        Set<Day> uniqueSet = new LinkedHashSet<>(Arrays.asList(days));
        this.days = new ArrayList<>(uniqueSet);
    }

    /**
     * Gets the {@code day} with index {@code i}.
     *
     * @return the object of class {@code Day} with index {@code i}
     */
    @Override
    public Day getDay(int i) {
        return days.get(i);
    }

    /**
     * Sets the {@code day} with index {@code i}.
     *
     * @param i   index of {@code day}
     * @param day the object of class {@code Day} with index {@code i} to be set
     */
    @Override
    public void setDay(int i, Day day) {
        if (days.contains(day)) {
            return;
        }
        days.set(i, day);
    }



    /**
     * Adds a link to the new {@code day} at the end of the sequence.
     *
     * @param day the object of class {@code Day} to be added
     * @return {@code true}, if the link was added successfully
     * {@code false} otherwise
     */
    @Override
    public boolean addDay(Day day) {
        if (days.contains(day)) {
            return false;
        }
        return days.add(day);
    }

    /**
     * Creates a new {@code day} and adds a link to it at the end of the sequence
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
     * Overridden decreasing temperature sorting method using the standard sort function of class {@code Collections}
     * Is provided by the implementation of the Comparable interface for the {@code Day} class
     */
    @Override
    public void sortByDecreasingTemperature() {
        Collections.sort(days);
    }

    /**
     * Overridden comment sorting method using the default sort function of interface {@code List}.
     * Is provided by {@code Comparator}.
     */
    @Override
    public void sortByComment() {
        days.sort(Comparator.comparing(Day::getComment));
    }
}
