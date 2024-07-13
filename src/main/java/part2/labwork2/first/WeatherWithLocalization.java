package part2.labwork2.first;

import part1.labwork3.first.AbstractWeather;
import part1.labwork3.first.Day;
import part2.labwork1.first.WeatherWithStreams;

import java.text.Collator;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The WeatherWithLocalization class extends the WeatherWithStreams class, adding localization.
 * It represents weather data with additional date and time information, as well as peak temperature time.
 * <p>
 * The class provides methods to get and set the date and time of the day and the peak temperature time.
 * It also provides methods for manipulating and querying the comment string, such as splitting it into words,
 * checking if a word is in the comment, and checking if a fragment is at the start or end of a word in the comment.
 * <p>
 * The class overrides the equals and hashCode methods from the Object class to provide custom equality checks and hash code generation.
 * It also overrides the toString method to provide a custom string representation of the WeatherWithLocalization object.
 *
 * @see WeatherWithStreams
 */
public class WeatherWithLocalization extends WeatherWithStreams {
    private static final String RESOURCE_BUNDLE_PATH = "part2/labwork2/first/";
    private static final String RESOURCE_BUNDLE = "days";
    private static final String RESOURCE_ERRORS_BUNDLE = "days_errors";
    /**
     * The constructor initialises the object with the default values.
     */
    public WeatherWithLocalization() {
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment},  {@code days}.
     *
     * @param season  the season
     * @param comment the comment
     * @param days    the list of days
     */
    public WeatherWithLocalization(String season, String comment, List<Day> days) {
        super(season, comment);
        setDays(days);
    }

    /**
     * Retrieves the localized comment for the weather.
     * The method uses the current default locale to fetch the appropriate translation from the resource bundle.
     *
     * @return The localized comment string.
     */
    @Override
    public String getComment() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        return bundle.getString(super.getComment());
    }

    /**
     * Retrieves the localized season for the weather.
     * The method uses the current default locale to fetch the appropriate translation from the resource bundle.
     *
     * @return The localized season string.
     */
    @Override
    public String getSeason() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        return bundle.getString(super.getSeason());
    }

    /**
     * Formats the given duration into a string representation.
     * The method takes a Duration object and converts it into a string that represents the duration in days, hours, minutes, and seconds.
     * The resulting string is localized according to the current default locale.
     *
     * @param duration The duration to format.
     * @return A string representation of the duration, formatted in days, hours, minutes, and seconds.
     */
    public static String formatDuration(Duration duration) {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        return String.format(Locale.getDefault(), "%d %s %d %s %d %s %d %s",
                duration.toDaysPart(), bundle.getString("duration_days"),
                duration.toHoursPart(), bundle.getString("duration_hours"),
                duration.toMinutesPart(), bundle.getString("duration_minutes"),
                duration.toSecondsPart(), bundle.getString("duration_seconds"));
    }

    /**
     * Filters the list of days to include only instances of DayWithDates.
     * This method retrieves the list of days and filters out any instances that are not of type DayWithDates.
     *
     * @return A list of DayWithDates instances from the original list of days.
     */
    private List<DayWithDates> filterDaysWithDates() {
        return getDaysList().stream()
                .filter(day -> day instanceof DayWithDates)
                .map(day -> (DayWithDates) day)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the time gaps between the peak temperature times of each pair of days.
     * This method retrieves a list of DayWithDates instances and calculates the absolute difference between the peak temperature times of each pair of days.
     *
     * @param daysWithDates A list of DayWithDates instances.
     * @return A list of Duration instances representing the time gaps between the peak temperature times of each pair of days.
     */
    private List<Duration> calculateTimeGaps(List<DayWithDates> daysWithDates) {
        return IntStream.range(0, daysWithDates.size() - 1)
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, daysWithDates.size())
                        .mapToObj(j -> Duration.between(daysWithDates.get(i).getPeakTemperatureTime(), daysWithDates.get(j).getPeakTemperatureTime()).abs()))
                .collect(Collectors.toList());
    }

    /**
     * Checks if there are enough DayWithDates instances for further processing.
     * This method checks if the size of the provided list of DayWithDates instances is less than 2.
     * If it is, it prints an error message and returns false. Otherwise, it returns true.
     *
     * @param daysWithDates A list of DayWithDates instances.
     * @return {@code true} if the list contains at least two instances, {@code false} otherwise.
     */
    private boolean checkEnoughData(List<DayWithDates> daysWithDates) {
        ResourceBundle errorsBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_ERRORS_BUNDLE, Locale.getDefault());
        if (daysWithDates.size() < 2) {
            System.out.println(errorsBundle.getString("notEnoughData") + ".");
            return false;
        }
        return true;
    }

    /**
     * Finds the shortest duration in the list of durations.
     * This method takes a list of Duration instances and returns the shortest duration.
     * If the list is empty, it returns an empty Optional.
     *
     * @param timeGaps A list of Duration instances.
     * @return An Optional containing the shortest duration if the list is not empty, an empty Optional otherwise.
     */
    private Optional<Duration> findShortestGap(List<Duration> timeGaps) {
        return timeGaps.stream().min(Duration::compareTo);
    }

    /**
     * Sorts the list of days by their comments in alphabetical order.
     * This method retrieves the list of days and sorts them based on their comments using the Collator instance for the current default locale.
     */
    @Override
    public void sortByComment() {
        Collator collator = Collator.getInstance(Locale.getDefault());
        setDays(getDaysList().stream()
                .sorted((day1, day2) -> collator.compare(day1.getComment(), day2.getComment()))
                .collect(Collectors.toList()));
    }

    /**
     * Provides a string representation of the WeatherWithLocalization object.
     * This method retrieves the localized season and comment, as well as the list of days, and formats them into a string.
     * If the list of days is empty, a specific message is added to the string.
     * Otherwise, the details of each day are added to the string.
     *
     * @return A string representation of the WeatherWithLocalization object.
     */
    @Override
    public String toString() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE);
        StringBuilder result = new StringBuilder();
        result.append(bundle.getString("weather"))
                .append(":\n")
                .append(bundle.getString("season"))
                .append(": ")
                .append(getSeason())
                .append(". ")
                .append(bundle.getString("comment"))
                .append(": ")
                .append(getComment())
                .append(".\n");
        if (daysCount() == 0) {
            result.append(bundle.getString("noDays"))
                    .append(".\n");
        } else {
            result.append(bundle.getString("days"))
                    .append(":\n");
            for (Day day : getDays()) {
                result.append(day).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Creates a new instance of {@code WeatherWithLocalization} with predefined values.
     * This method initializes a new {@code WeatherWithLocalization} object with a predefined season, comment, and list of days.
     * The list of days includes instances of {@code DayWithDates} with specific dates, temperatures, comments, and peak temperature times.
     * The method also prints messages to the console indicating the progress of the weather creation.
     *
     * @return The newly created {@code WeatherWithLocalization} object.
     */
    @Override
    public AbstractWeather createWeather() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        setSeason("winter");
        setComment("sevenDaysDescribed");
        System.out.println(bundle.getString("addingSevenDays") + ".");
        addDay(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 9, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                -5.2, "cloudy",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 9, 15, 39, 18, 0, ZoneId.of("Europe/Kiev"))));

        addDay(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 10, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                -3.1, "windy",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 10, 14, 3, 31, 0, ZoneId.of("Europe/Kiev"))));

        addDay(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 11, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                -6.0, "sunny",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 11, 11, 12, 29, 0, ZoneId.of("Europe/Kiev"))));

        addDay(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 12, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                -7.6, "drizzly",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 12, 12, 42, 56, 0, ZoneId.of("Europe/Kiev"))));

        addDay(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 13, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                -3.2, "foggy",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 13, 14, 43, 34, 0, ZoneId.of("Europe/Kiev"))));

        addDay(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 14, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                0, "foggyAndCloudy",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 14, 15, 27, 45, 0, ZoneId.of("Europe/Kiev"))));

        addDay(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 15, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                1, "windyAndSunny",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 15, 13, 21, 10, 0, ZoneId.of("Europe/Kiev"))));

        System.out.println(bundle.getString("addingLastDayAgain") + ".");

        addDay(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 15, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                1, "windyAndSunny",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 15, 13, 21, 10, 0, ZoneId.of("Europe/Kiev"))));
        return this;
    }

    /**
     * Finds the average temperature and prints the result to the console.
     */
    @Override
    public void printAverageTemperature() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        ResourceBundle errorsBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_ERRORS_BUNDLE, Locale.getDefault());
        Double result = calculateAverageTemperature();
        if (result == null) {
            System.out.println(errorsBundle.getString("noPointerOrEmpty"));
        } else {
            System.out.println(bundle.getString("averageTemperature") + " : " + result);
        }
    }

    /**
     * Finds the days with maximum temperature and prints the result to the console.
     */
    @Override
    public void printMaxTemperatureDays() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        ResourceBundle errorsBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_ERRORS_BUNDLE, Locale.getDefault());
        Day[] result = findMaxTemperatureDays();
        if (result == null) {
            System.out.println(errorsBundle.getString("noPointerOrEmpty"));
        } else {
            System.out.println(bundle.getString("maxTemperatureDays") + ":");
            printDays(result);
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
            System.out.println(bundle.getString("maxTemperature") + ": " + numberFormat.format(result[0].getTemperature()) + " " + bundle.getString("degreesCelsius") + ".");
        }
    }

    /**
     * Finds the days with the longest comment and prints the result to the console.
     */
    @Override
    public void printLongestCommentDays() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        ResourceBundle errorsBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_ERRORS_BUNDLE, Locale.getDefault());
        Day[] result = findLongestCommentDays();
        if (result == null) {
            System.out.println(errorsBundle.getString("noPointerOrEmpty"));
        } else {
            System.out.println(bundle.getString("maxLongestCommentDays") + ":");
            printDays(result);
        }
    }

    /**
     * Carries out testing of the search methods.
     * This method first clears the list of days and calls the search methods, which should result in specific error messages.
     * Then, it creates a new weather instance and calls the search methods again, which should now return valid results.
     * Finally, it adds a day with the same maximum temperature and longest comment as the existing days and calls the search methods again.
     */
    @Override
    public void testSearch() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());

        System.out.println(bundle.getString("searchTest") + ":");
        System.out.println(bundle.getString("clearArrayAndCallSearch") + ".");
        clearDays();
        callSearch();

        System.out.println(bundle.getString("creatingWeather") + ":");
        createWeather();
        System.out.println(this);
        callSearch();

        System.out.println(bundle.getString("addDayWithSameMaxTempAndLongestComment") + ".");
        addDay(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 16, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                -1.2, "windyAndCloudy",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 16, 12, 27, 40, 0, ZoneId.of("Europe/Kiev"))));

        addDay(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 17, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                1, "sunny",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 17, 11, 33, 11, 0, ZoneId.of("Europe/Kiev"))));

        System.out.println(this);
        callSearch();

    }

    /**
     * Carries out testing of sorting methods.
     * This method first prints the state of the weather object before any sorting.
     * Then, it calls the method to sort the days by decreasing temperature and prints the state of the weather object after sorting.
     * Finally, it calls the method to sort the days by comment and prints the state of the weather object after sorting.
     */
    @Override
    public void testSorting() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        System.out.println(bundle.getString("sortingTest"));
        System.out.println(bundle.getString("beforeSorting") + ":");
        System.out.println(this);
        System.out.println(bundle.getString("sortingByDecreasingTemperature") + ".");
        sortByDecreasingTemperature();
        System.out.println(this);
        System.out.println(bundle.getString("sortingCommentsByAlphabeticalOrder") + ".");
        sortByComment();
        System.out.println(this);
    }

    /**
     * Calculates and prints the shortest time gap between peak temperatures of days.
     * This method first filters the list of days to include only instances of DayWithDates.
     * If there are not enough DayWithDates instances for further processing, it returns.
     * Then, it calculates the time gaps between the peak temperature times of each pair of days.
     * Finally, it finds the shortest duration in the list of durations and prints it.
     */
    public void calculateAndPrintShortestPeakTimeGap() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        ResourceBundle errorsBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_ERRORS_BUNDLE, Locale.getDefault());
        List<DayWithDates> daysWithDates = filterDaysWithDates();
        if (!checkEnoughData(daysWithDates)) {
            return;
        }

        List<Duration> timeGaps = calculateTimeGaps(daysWithDates);
        Optional<Duration> shortestGap = findShortestGap(timeGaps);

        if (shortestGap.isPresent()) {
            System.out.println(bundle.getString("shortestGap") + " " + formatDuration(shortestGap.get()) + ".");
        } else {
            System.out.println(errorsBundle.getString("cannotFindGap") + ".");
        }
    }

    /**
     * Calculates and prints the time gaps between peak temperatures of days.
     * This method first filters the list of days to include only instances of DayWithDates.
     * If there are not enough DayWithDates instances for further processing, it returns.
     * Then, it calculates the time gaps between the peak temperature times of each pair of days.
     * Finally, it prints each pair of days and their corresponding time gap.
     */
    public void calculateAndPrintTimeGaps() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        List<DayWithDates> daysWithDates = filterDaysWithDates();
        if (!checkEnoughData(daysWithDates)) {
            return;
        }
        IntStream.range(0, daysWithDates.size() - 1)
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, daysWithDates.size())
                        .mapToObj(j -> {
                            Duration gap = Duration.between(daysWithDates.get(i).getPeakTemperatureTime(), daysWithDates.get(j).getPeakTemperatureTime()).abs();
                            return new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(daysWithDates.get(i), daysWithDates.get(j)), gap);
                        }))
                .forEach(entry -> {
                    AbstractMap.SimpleEntry<DayWithDates, DayWithDates> days = entry.getKey();
                    Duration gap = entry.getValue();
                    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.getDefault());
                    String date1 = days.getKey().getZonedDateTime().format(formatter);
                    String date2 = days.getValue().getZonedDateTime().format(formatter);
                    System.out.println(bundle.getString("timeGapBetween") + " " + bundle.getString("peakTimeOf") + " " + date1 + " " + bundle.getString("and")
                            + " " + date2 + " " + bundle.getString("equals") + " " + formatDuration(gap) + ".");
                });
    }

    /**
     * Carries out testing of the peak time gaps calculation with and without data.
     * This method first clears the list of days and calls the method to calculate and print time gaps, which should result in a specific error message.
     * Then, it creates a new weather instance and calls the method to calculate and print time gaps again, which should now return valid results.
     */
    public void testPeakTimeGapsWithAndWithoutData() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        System.out.println("\n" + bundle.getString("testingPeakTimeGaps") + ":");
        System.out.println(bundle.getString("withNotEnoughData") + ":");
        clearDays();
        calculateAndPrintTimeGaps();

        System.out.println(bundle.getString("creatingWeather") + ":");
        createWeather();
        System.out.println(bundle.getString("withData") + ":");
        calculateAndPrintTimeGaps();
        System.out.println();
    }

    /**
     * Carries out testing of the shortest peak time gaps calculation with and without data.
     * This method first clears the list of days and calls the method to calculate and print the shortest time gap, which should result in a specific error message.
     * Then, it creates a new weather instance and calls the method to calculate and print the shortest time gap again, which should now return valid results.
     */
    public void testShortestPeakTimeGapWithAndWithoutData() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        System.out.println("\n" + bundle.getString("testingShortestPeakTimeGap") + ":");
        System.out.println(bundle.getString("withNotEnoughData") + ":");
        clearDays();
        calculateAndPrintShortestPeakTimeGap();

        System.out.println(bundle.getString("creatingWeather") + ":");
        createWeather();
        System.out.println(bundle.getString("withData") + ":");
        calculateAndPrintShortestPeakTimeGap();
        System.out.println();
    }

    /**
     * Carries out testing of the getWordsFromComment method.
     * This method retrieves the words from the localized comment and prints them.
     */
    public void testGetWordsFromComment() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        System.out.println(bundle.getString("testingGetWordsFromComment") + ":");
        String[] words = getWordsFromComment();
        System.out.println(Arrays.toString(words));
        System.out.println();
    }

    /**
     * Carries out testing of the isWordInComment method.
     * This method retrieves a set of predefined words and checks if they are present in the localized comment.
     * The result for each word is printed to the console.
     */
    public void testIsWordInComment() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        System.out.println(bundle.getString("testingIsWordInComment") + ":");
        String[] searchWords = {bundle.getString("described"), bundle.getString("day"), bundle.getString("six")};
        for (String word : searchWords) {
            System.out.println(word + ": " + (isWordInComment(word) ? bundle.getString("present") : bundle.getString("absent")) + ".");
        }
        System.out.println();
    }

    /**
     * Carries out testing of the isFragmentOfWordInComment method.
     * This method retrieves a set of predefined fragments and checks if they are present at the start or end of any word in the localized comment.
     * The result for each fragment is printed to the console.
     */
    public void testIsFragmentOfWordInComment() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        System.out.println(bundle.getString("testingIsFragmentOfWordInComment") + ":");
        String[] searchFragmentsOfWordsInComment = {bundle.getString("fragmentWordDescribed"), bundle.getString("fragmentWordDescribed2"), bundle.getString("six")};
        for (String fragment : searchFragmentsOfWordsInComment) {
            System.out.println(fragment + " " + bundle.getString("atStartOfWord") + ": " + (isFragmentAtStartOfWordInComment(fragment) ? bundle.getString("present") : bundle.getString("absent")) + ".");
            System.out.println(fragment + " " + bundle.getString("atEndOfWord") + ": " + (isFragmentAtEndOfWordInComment(fragment) ? bundle.getString("present") : bundle.getString("absent")) + ".");
        }
        System.out.println();
    }

    /**
     * Demonstrates the functionality of the WeatherWithLocalization class.
     * This method first prints the state of the weather object.
     * Then, it carries out testing of the sorting methods, search methods, peak time gaps calculation, shortest peak time gaps calculation,
     * getWordsFromComment method, isWordInComment method, and isFragmentOfWordInComment method.
     */
    @Override
    public void showFunctionality() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE, Locale.getDefault());
        System.out.println(bundle.getString("createdWeather") + ":\n" + this);
        testSorting();
        testSearch();
        testPeakTimeGapsWithAndWithoutData();
        testShortestPeakTimeGapWithAndWithoutData();
        testGetWordsFromComment();
        testIsWordInComment();
        testIsFragmentOfWordInComment();
    }
}
