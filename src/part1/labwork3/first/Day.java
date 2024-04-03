package part1.labwork3.first;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The {@code Day} class performs day with {@code date}, {@code temperature},  {@code comment}
 */
public class Day implements Comparable<Day>, Serializable {

    private String date;
    private double temperature;
    private String comment;

    /**
     * The constructor initialises the object with the default values.
     */
    public Day() {
    }

    /**
     * The constructor initialises the object with the specified values.
     *
     * @param date        the date
     * @param temperature the temperature
     * @param comment     the comment
     */
    public Day(String date, double temperature, String comment) {
        this.date = date;
        this.temperature = temperature;
        this.comment = comment;
    }

    /**
     * Gets the {@code date} of this day.
     *
     * @return the {@code date}
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the {@code date} of this day.
     *
     * @param date the {@code date} to be set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the {@code temperature} of this day.
     *
     * @return the {@code temperature}
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets the {@code temperature} of this day.
     *
     * @param temperature the {@code temperature} to be set
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the {@code comment} for this day.
     *
     * @return the {@code comment}
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the {@code comment} for this day.
     *
     * @param comment the {@code comment} to be set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Calculates the length of a comment on this day
     *
     * @return the length of a comment
     */
    public int calculateLengthComment() {
        return getComment().length();
    }

    /**
     * Provides the string representing the Day object.
     *
     * @return the string representing the Day object
     */
    @Override
    public String toString() {
        return "Date: " + getDate() + ". Temperature: " + getTemperature()
                + " degrees Celsius.\nComment: " + getComment();
    }

    /**
     * Checks whether this day is equivalent to another
     *
     * @param obj the day with which we check the equivalence
     * @return {@code true}, if two days are the same
     * {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Day d)) {
            return false;
        }
        return getDate().equals(d.getDate()) && Double.compare(d.getTemperature(), getTemperature()) == 0
                && getComment().equals(d.getComment());
    }

    /**
     * Calculates the hash code of the day.
     * If two objects are equal, they must have the same hash code.
     * If this method is called multiple times on the same object, it must return the same number each time.
     *
     * @return the hash code of the day
     */
    @Override
    public int hashCode() {
        return getDate().hashCode() * getComment().hashCode() + Double.hashCode(getTemperature());
    }

    /**
     * Compares this Day object with another Day object based on temperature.
     *
     * @param o the object to be compared.
     * @return negative number, if this object is smaller
     * zero, if they are equal
     * positive number, if this object is larger
     */
    @Override
    public int compareTo(Day o) {
        return Double.compare(o.getTemperature(), getTemperature());
    }

    /**
     * Carries out testing of the default constructor
     *
     * @return the object of class {@code Day}
     */
    private static Day testDefaultConstructor() {
        return new Day();
    }

    /**
     * Carries out testing of the specific constructor with {@code date}, {@code temperature},  {@code comment}.
     *
     * @return the object of class {@code Day}
     */
    private static Day testSpecificConstructor(String date, double temperature, String comment) {
        return new Day(date, temperature, comment);
    }

    /**
     * Carries out testing of setters
     */
    private void testSetters() {
        this.setDate("21.12.22");
        this.setTemperature(2.4);
        this.setComment("Cloudy");
    }

    /**
     * Carries out testing of getters
     */
    private void testGetters() {
        System.out.println(getDate());
        System.out.println(getTemperature());
        System.out.println(getComment());
    }

    /**
     * Prints the array of days
     */
    private static void printDays(Day[] days) {
        System.out.println("Array of days:");
        for (Day day : days) {
            System.out.println(day);
        }
    }

    /**
     * Carries out testing of the functionality of the {@code Day} class.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Use default constructor for create day:");
        Day day = testDefaultConstructor();
        System.out.println(day);
        System.out.println("Use specific constructor for create day:");
        day = testSpecificConstructor("02.12.22", -3.2, "Windy");
        System.out.println(day);
        System.out.println("Testing the setters:");
        day.testSetters();
        System.out.println(day);
        System.out.println("Length comment: " + day.calculateLengthComment());
        System.out.println("Testing the getters:");
        day.testGetters();
        Day[] days = {day,
                new Day("22.12.22", -4.1, "Snowy"),
                new Day("23.12.22", -2.5, "Sunny"),
                new Day("23.12.22", -2.5, "Sunny")
        };
        printDays(days);
        System.out.println("Comparing days with index 0 and 1: " + days[0].equals(days[1]));
        System.out.println("Hashcode for the day with index 0: " + days[0].hashCode());
        System.out.println("Hashcode for the day with index 1: " + days[1].hashCode());

        System.out.println("Comparing days with index 2 and 3: " + days[2].equals(days[3]));
        System.out.println("Hashcode for the day with index 2: " + days[2].hashCode());
        System.out.println("Hashcode for the day with index 3: " + days[3].hashCode());

        System.out.println("Sorting the array by decreasing temperature.");
        Arrays.sort(days);
        printDays(days);
    }
}
