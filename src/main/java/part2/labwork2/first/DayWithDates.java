package part2.labwork2.first;

import part1.labwork3.first.Day;

import java.text.NumberFormat;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The DayWithDates class extends the Day class and adds date and time information.
 * It represents a specific day with weather information, including temperature and comments,
 * along with the date and time of the day and the peak temperature time.
 * <p>
 * The class provides methods to get and set the date and time of the day and the peak temperature time.
 * It also provides methods to manipulate and query the comment string, such as splitting it into words,
 * checking if a word is in the comment, and checking if a fragment is at the start or end of a word in the comment.
 * <p>
 * The class overrides the equals and hashCode methods from the Object class to provide custom equality checks and hash code generation.
 * It also overrides the toString method to provide a custom string representation of the DayWithDates object.
 * <p>
 * The class also includes a test method for each of the comment manipulation and query methods.
 *
 * @see Day
 */
public class DayWithDates extends Day {
    private static final String RESOURCE_BUNDLE_PATH = "part2/labwork2/first/";
    private static final String RESOURCE_BUNDLE = "days";
    private ZonedDateTime zonedDateTime;
    private ZonedDateTime peakTemperatureTime;

    /**
     * The constructor initialises the object with the default values.
     */
    public DayWithDates() {
    }

    /**
     * The constructor initialises the object with the specified values.
     *
     * @param zonedDateTime       the date and time
     * @param temperature         the temperature
     * @param comment             the comment
     * @param peakTemperatureTime the peak temperature time
     */
    public DayWithDates(ZonedDateTime zonedDateTime, double temperature, String comment, ZonedDateTime peakTemperatureTime) {
        super(null, temperature, comment);
        this.zonedDateTime = zonedDateTime;
        this.peakTemperatureTime = peakTemperatureTime.withYear(zonedDateTime.getYear())
                .withMonth(zonedDateTime.getMonthValue())
                .withDayOfMonth(zonedDateTime.getDayOfMonth());
    }

    /**
     * Gets the {@code date and time} of this day.
     *
     * @return the {@code date and time}
     */
    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    /**
     * Gets the {@code peak temperature time} of this day.
     *
     * @return the {@code peak temperature time}
     */
    public ZonedDateTime getPeakTemperatureTime() {
        return peakTemperatureTime;
    }

    /**
     * Sets the {@code date and time} of this day.
     *
     * @param zonedDateTime the {@code date and time} to be set
     */
    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
        if (this.peakTemperatureTime != null) {
            this.peakTemperatureTime = this.peakTemperatureTime.withYear(zonedDateTime.getYear())
                    .withMonth(zonedDateTime.getMonthValue())
                    .withDayOfMonth(zonedDateTime.getDayOfMonth());
        }
    }

    /**
     * Sets the {@code peak temperature time} of this day.
     *
     * @param peakTemperatureTime the {@code peak temperature time} to be set
     */
    public void setPeakTemperatureTime(ZonedDateTime peakTemperatureTime) {
        if (this.zonedDateTime != null) {
            this.peakTemperatureTime = peakTemperatureTime.withYear(this.zonedDateTime.getYear())
                    .withMonth(this.zonedDateTime.getMonthValue())
                    .withDayOfMonth(this.zonedDateTime.getDayOfMonth());
        } else {
            this.peakTemperatureTime = peakTemperatureTime;
        }
    }

    /**
     * Gets the {@code comment} for this day.
     *
     * @return the {@code comment}
     */
    @Override
    public String getComment() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        return bundle.getString(super.getComment());
    }

    /**
     * Provides the string representing the DayWithDates object.
     *
     * @return the string representing the DayWithDates object
     */
    @Override
    public String toString() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(Locale.getDefault());
        ;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault());

        return String.format(Locale.getDefault(), "%s %s %s: %s.\n%s: %s %s. %s: %s.\n%s: %s.",
                bundle.getString("date"), bundle.getString("and"),
                bundle.getString("time"), zonedDateTime.format(dateTimeFormatter),
                bundle.getString("temperature"), numberFormat.format(getTemperature()),
                bundle.getString("degreesCelsius"),
                bundle.getString("comment"), getComment(),
                bundle.getString("peakTime"), peakTemperatureTime.format(timeFormatter));
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
        if (!(obj instanceof DayWithDates d)) {
            return false;
        }
        return getZonedDateTime().equals(d.getZonedDateTime()) && Double.compare(d.getTemperature(), getTemperature()) == 0
                && getComment().equals(d.getComment()) && getPeakTemperatureTime().equals(d.getPeakTemperatureTime());
    }

    /**
     * Generates a hash code for the DayWithDates object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return getZonedDateTime().hashCode() * getComment().hashCode() * getPeakTemperatureTime().hashCode()
                + Double.hashCode(getTemperature());
    }

    /**
     * Tests the functionality of the getWordsFromComment method.
     * This method retrieves the comment, splits it into words, and prints the resulting array.
     * It is used to verify that the getWordsFromComment method correctly splits the comment into individual words.
     */
    public void testGetWordsFromComment() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        System.out.println(bundle.getString("testingGetWordsFromComment") + ":");
        String[] words = getWordsFromComment();
        System.out.println(Arrays.toString(words));
        System.out.println();
    }

    /**
     * Tests the functionality of the isWordInComment method.
     * This method checks if specific words are present in the comment and prints the results.
     * It is used to verify that the isWordInComment method correctly identifies the presence of words in the comment.
     */
    public void testIsWordInComment() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        System.out.println(bundle.getString("testingIsWordInComment") + ":");
        String[] searchWords = {bundle.getString("sunny"), bundle.getString("cloudy"), bundle.getString("sun")};
        for (String word : searchWords) {
            System.out.println(word + ": " + (isWordInComment(word) ? bundle.getString("present") : bundle.getString("absent")) + ".");
        }
        System.out.println();
    }

    /**
     * Tests the functionality of the isFragmentAtStartOfWordInComment and isFragmentAtEndOfWordInComment methods.
     * This method checks if specific fragments are present at the start or end of words in the comment and prints the results.
     * It is used to verify that the isFragmentAtStartOfWordInComment and isFragmentAtEndOfWordInComment methods correctly identify the presence of fragments in the comment.
     */
    public void testIsFragmentOfWordInComment() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        System.out.println(bundle.getString("testingIsFragmentOfWordInComment") + ":");
        String[] searchFragmentsOfWordsInComment = {bundle.getString("fragmentWordSunny"), bundle.getString("fragmentWordSunny2"), bundle.getString("cloudy")};
        for (String fragment : searchFragmentsOfWordsInComment) {
            System.out.println(fragment + " " + bundle.getString("atStartOfWord") + ": " + (isFragmentAtStartOfWordInComment(fragment) ? bundle.getString("present") : bundle.getString("absent")) + ".");
            System.out.println(fragment + " " + bundle.getString("atEndOfWord") + ": " + (isFragmentAtEndOfWordInComment(fragment) ? bundle.getString("present") : bundle.getString("absent")) + ".");
        }
        System.out.println();
    }

    /**
     * Carries out showing of the functionality of the {@link DayWithDates}.
     */
    public void testDay() {
        setZonedDateTime(ZonedDateTime.of(2024, Month.JANUARY.getValue(), 10, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")));
        setTemperature(-7);
        setComment("sunny");
        setPeakTemperatureTime(ZonedDateTime.of(2024, Month.JANUARY.getValue(), 10, 15, 32, 10, 0, ZoneId.of("Europe/Kiev")));
        System.out.println(this);
        testGetWordsFromComment();
        testIsWordInComment();
        testIsFragmentOfWordInComment();
    }
}
