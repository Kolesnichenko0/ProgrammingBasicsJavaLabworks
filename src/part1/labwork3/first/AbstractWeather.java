package part1.labwork3.first;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Abstract class representing weather with {@code season}, {@code comment} and days data.
 * Access to the sequence of days, {@code season} and {@code comment} is represented by abstract methods.
 */
public abstract class AbstractWeather implements Serializable {
    private static final long serialVersionUID = 176873029745254541L;
    /**
     * Gets the {@code comment} for the weather.
     * <p> The derived class must provide an implementation of this method
     *
     * @return the {@code comment}
     */
    public abstract String getComment();

    /**
     * Sets the {@code comment} for the weather.
     * <p> The derived class must provide an implementation of this method
     *
     * @param comment the {@code comment} to be set
     */
    public abstract void setComment(String comment);

    /**
     * Gets the {@code season} for the weather.
     * <p> The derived class must provide an implementation of this method
     *
     * @return the {@code season}
     */
    public abstract String getSeason();

    /**
     * Sets the {@code season} for the weather.
     * <p> The derived class must provide an implementation of this method
     *
     * @param season the {@code season} to be set
     */
    public abstract void setSeason(String season);

    /**
     * Gets the array of days for the weather.
     * <p> The derived class must provide an implementation of this method
     *
     * @return the array of days
     */
    public abstract Day[] getDays();

    /**
     * Sets the array of days for the weather.
     * <p> The derived class must provide an implementation of this method
     *
     * @param days the array of days to be set
     */
    public abstract void setDays(Day[] days);

    /**
     * Gets the {@code day} with index {@code i}.
     * <p> The derived class must provide an implementation of this method
     *
     * @return the object of class {@code Day} with index {@code i}
     */
    public abstract Day getDay(int i);

    /**
     * Sets the {@code day} with index {@code i}.
     * <p> The derived class must provide an implementation of this method
     *
     * @param i   index of {@code day}
     * @param day the object of class {@code Day} with index {@code i} to be set
     */
    public abstract void setDay(int i, Day day);

    /**
     * Adds a link to the new {@code day} at the end of the sequence.
     * <p> The derived class must provide an implementation of this method
     *
     * @param day the object of class {@code Day} to be added
     * @return {@code true}, if the link was added successfully
     * {@code false} otherwise
     */
    public abstract boolean addDay(Day day);

    /**
     * Creates a new {@code day} and adds a link to it at the end of the sequence
     * <p> The derived class must provide an implementation of this method
     *
     * @param Date        the date
     * @param temperature the temperature
     * @param comment     the comment
     * @return {@code true}, if the link was added successfully
     * {@code false} otherwise
     */
    public abstract boolean addDay(String Date, double temperature, String comment);

    /**
     * Counts the number of days in the sequence.
     * <p> The derived class must provide an implementation of this method
     *
     * @return the number of days
     */
    public abstract int daysCount();

    /**
     * Clears the sequence of days.
     * <p> The derived class must provide an implementation of this method
     */
    public abstract void clearDays();

    /**
     * Provides the string representing the object that is inherited from this abstract class
     *
     * @return the string representing the object that is inherited from this abstract class
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Weather:\nSeason: ").append(getSeason()).append(". Comment: ").append(getComment());
        if (daysCount() == 0) {
            result.append(".\nThere are no days.\n");
        } else {
            result.append(".\nDays:\n");
            for (Day day : getDays()) {
                result.append(day).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Checks whether this weather is equivalent to another
     *
     * @param obj the weather with which we check the equivalence
     * @return {@code true}, if two weathers are the same
     * {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractWeather w)) {
            return false;
        }
        if (!w.getSeason().equals(getSeason()) || !w.getComment().equals(getComment())) {
            return false;
        }
        return Arrays.equals(getDays(), w.getDays());
    }

    /**
     * Calculates the hash code of the weather.
     * If two objects are equal, they must have the same hash code.
     * If this method is called multiple times on the same object, it must return the same number each time.
     *
     * @return the hash code of the weather.
     */
    @Override
    public int hashCode() {
        return getSeason().hashCode() * getComment().hashCode() + Arrays.hashCode(getDays());
    }

    /**
     * An additional static function for adding a day reference to the provided array of days
     *
     * @param days the array to which the day is added
     * @param item the link that is added
     * @return updated array of days
     */
    public static Day[] addToArray(Day[] days, Day item) {
        Day[] result;
        if (days != null) {
            result = new Day[days.length + 1];
            System.arraycopy(days, 0, result, 0, days.length);
        } else {
            result = new Day[1];
        }
        result[result.length - 1] = item;
        return result;
    }

    /**
     * Calculates the average temperature for an array of weather days.
     *
     * @return null, if there is no pointer to the days array or it is empty
     * the average temperature otherwise
     */
    public Double calculateAverageTemperature() {
        if (daysCount() == 0)
            return null;
        double totalTemperature = 0;
        for (Day day : getDays()) {
            totalTemperature += day.getTemperature();
        }
        return totalTemperature / getDays().length;
    }

    /**
     * Finds the days with the maximum temperature in the array of weather days.
     *
     * @return null, if there is no pointer to the days array or it is empty
     * array of days with maximum temperature otherwise
     */
    public Day[] findMaxTemperatureDays() {
        if (daysCount() == 0)
            return null;
        Day maxTemperatureDay = getDays()[0];
        for (Day day : getDays()) {
            if (maxTemperatureDay.getTemperature() < day.getTemperature()) {
                maxTemperatureDay = day;
            }
        }
        Day[] result = null;
        for (Day day : getDays()) {
            if (maxTemperatureDay.getTemperature() == day.getTemperature()) {
                result = addToArray(result, day);
            }
        }
        return result;
    }

    /**
     * Finds the days with the longest comment in the array of weather days.
     *
     * @return null, if there is no pointer to the days array or it is empty
     * array of days with the longest comment otherwise
     */
    public Day[] findLongestCommentDays() {
        if (daysCount() == 0)
            return null;
        Day LongestCommentDay = getDays()[0];
        for (Day day : getDays()) {
            if (LongestCommentDay.calculateLengthComment() < day.calculateLengthComment()) {
                LongestCommentDay = day;
            }
        }
        Day[] result = null;
        for (Day day : getDays()) {
            if (LongestCommentDay.calculateLengthComment() == day.calculateLengthComment()) {
                result = addToArray(result, day);
            }
        }
        return result;
    }

    /**
     * Finds the average temperature and prints the result to the console.
     */
    private void printAverageTemperature() {
        Double result = calculateAverageTemperature();
        if (result == null) {
            System.out.println("There is no pointer to the days array or it is empty");
        } else {
            System.out.println("Average temperature : " + result);
        }
    }

    /**
     * Prints the array of days.
     *
     * @param days the array of days to be printed
     */
    private void printDays(Day[] days) {
        for (Day day : days) {
            System.out.println(day);
        }
    }

    /**
     * Finds the days with maximum temperature and prints the result to the console.
     */
    private void printMaxTemperatureDays() {
        Day[] result = findMaxTemperatureDays();
        if (result == null) {
            System.out.println("There is no pointer to the days array or it is empty");
        } else {
            System.out.println("Days with maximum temperature:");
            printDays(result);
        }
    }

    /**
     * Finds the days with the longest comment and prints the result to the console.
     */
    private void printLongestCommentDays() {
        Day[] result = findLongestCommentDays();
        if (result == null) {
            System.out.println("There is no pointer to the days array or it is empty");
        } else {
            System.out.println("Days with the longest comment:");
            printDays(result);
        }
    }

    /**
     * Sorts a sequence of days by decreasing temperature using bubble sorting.
     */
    public void sortByDecreasingTemperature() {
        if (daysCount() == 0)
            return;
        boolean mustSort = true;
        while (mustSort) {
            mustSort = false;
            for (int i = 0; i < getDays().length - 1; i++) {
                if (getDays()[i].getTemperature() < getDays()[i + 1].getTemperature()) {
                    Day tempDay = getDays()[i];
                    getDays()[i] = getDays()[i + 1];
                    getDays()[i + 1] = tempDay;
                    mustSort = true;
                }
            }
        }
    }
    /**
     * Sorts a sequence of days by comment(in alphabetical order) using insertion sorting.
     */
    public void sortByComment() {
        if (daysCount() == 0)
            return;
        for (int i = 1; i < getDays().length; i++) {
            Day key = getDays()[i];
            int j = i - 1;

            while (j >= 0 && getDays()[j].getComment().compareTo(key.getComment()) > 0) {
                getDays()[j + 1] = getDays()[j];
                j--;
            }
            getDays()[j + 1] = key;
        }
    }

    /**
     * An additional function for adding days to a sequence of days.
     * @return The object is inherited from this abstract class
     */
    public AbstractWeather createWeather() {
        setSeason("Winter");
        setComment("7 days are described");
        System.out.println("Adding 7 days to the array:");
        System.out.println(addDay("09.12.22", -5.2, "Cloudy"));
        Day day = new Day("10.12.22", -3.1, "Windy");
        System.out.println(addDay(day));
        System.out.println(addDay("11.12.22", -6.0, "Sunny"));
        System.out.println(addDay("12.12.22", -7.6, "Drizzly"));
        System.out.println(addDay("13.12.22", -3.2, "Foggy"));
        System.out.println(addDay("14.12.22", 0, "Foggy and cloudy"));
        System.out.println(addDay("15.12.22", 1, "Windy and sunny"));
        System.out.println("Adding the last day again:");
        System.out.println(addDay("15.12.22", 1, "Windy and sunny"));
        return this;
    }

    /**
     * Calls up search methods.
     */
    private void callSearch() {
        printAverageTemperature();
        printMaxTemperatureDays();
        printLongestCommentDays();
    }
    /**
     * Carries out testing of search methods
     */
    public void testSearch() {
        System.out.println("Search test:");
        setSeason("Winter");
        setComment("7 days are described");
        System.out.println("Clear the array of days and call the search functions.");
        clearDays();
        callSearch();

        System.out.println("Creating the weather:");
        createWeather();
        System.out.println(this);
        callSearch();

        System.out.println("Add a day with the same maximum temperature and a day with the same longest comment.");
        System.out.println(addDay("16.12.22", -1.2, "Windy and cloudy"));
        System.out.println(addDay("17.12.22", 1, "Sunny"));
        System.out.println(this);
        callSearch();
    }
    /**
     * Carries out testing of sorting methods
     */
    public void testSorting() {
        System.out.println("Sorting test:");
        System.out.println("Before sorting:");
        System.out.println(this);
        System.out.println("Sorting by decreasing temperature.");
        sortByDecreasingTemperature();
        System.out.println(this);
        System.out.println("Sorting comments by alphabetical order.");
        sortByComment();
        System.out.println(this);
    }

}
