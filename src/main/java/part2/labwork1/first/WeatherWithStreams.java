package part2.labwork1.first;

import part1.labwork3.first.AbstractWeather;
import part1.labwork3.first.Day;
import part1.labwork4.first.WeatherWithList;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
     * Converts the weather data to a list of strings.
     * The list of strings will follow a specific format:
     * - The first line will be "Weather:"
     * - The second line will be "Season: {season}. Comment: {comment}."
     * - The third line will be either "Days:" or "There are no days."
     * - If there are days, each day will be represented by two lines:
     *   - The first line will be "Date: {date}. Temperature: {temperature} degrees Celsius."
     *   - The second line will be "Comment: {comment}"
     *
     * @return a list of strings representing the weather data
     */
    public List<String> toListOfStrings() {
        ArrayList<String> list = new ArrayList<>();

        list.add("Weather:");
        list.add("Season: " + getSeason() + ". Comment: " + getComment() + ".");
        if (daysCount() == 0) {
            list.add("There are no days.");
        } else {
            list.add("Days:");
            Arrays.stream(getDays()).forEach(day -> {
                list.add(
                        "Date: " + day.getDate() + ". Temperature: " + day.getTemperature() + " degrees Celsius.");
                list.add("Comment: " + day.getComment());
            });
        }

        return list;
    }
    /**
     * Parses a list of strings and sets the season, comment, and days of the weather accordingly.
     * The list of strings should follow a specific format:
     * - The first line should be "Weather:"
     * - The second line should be "Season: {season}. Comment: {comment}."
     * - The third line should be either "Days:" or "There are no days."
     * - If there are days, each day should be represented by two lines:
     *   - The first line should be "Date: {date}. Temperature: {temperature} degrees Celsius."
     *   - The second line should be "Comment: {comment}"
     * If the list of strings does not follow this format, an IllegalArgumentException will be thrown.
     *
     * @param list the list of strings to parse
     * @throws IllegalArgumentException if the list of strings does not follow the expected format
     */
    public void fromListOfStrings(List<String> list) {
        String season = null;
        String comment = null;
        List<Day> days = new ArrayList<>();

        Pattern weatherPattern = Pattern.compile("^Weather:$");
        Pattern seasonAndCommentPattern = Pattern.compile("^Season: (.+). Comment: (.+).$");
        Pattern daysPattern = Pattern.compile("^Days:$");
        Pattern noDaysPattern = Pattern.compile("^There are no days.$");
        Pattern dayPattern = Pattern.compile("^Date: (.+). Temperature: (.+) degrees Celsius.$");
        Pattern commentOfDayPattern = Pattern.compile("^Comment: (.+)$");

        for (int i = 0; i < list.size(); i++) {
            String line = list.get(i);

            if (weatherPattern.matcher(line).matches()) {
                continue;
            } else if (seasonAndCommentPattern.matcher(line).matches()) {
                Matcher seasonAndCommentMatcher = seasonAndCommentPattern.matcher(line);
                if (seasonAndCommentMatcher.find()) {
                    season = seasonAndCommentMatcher.group(1);
                    comment = seasonAndCommentMatcher.group(2);
                }
            } else if (daysPattern.matcher(line).matches() || noDaysPattern.matcher(line).matches()) {
                continue;
            } else if (dayPattern.matcher(line).matches()) {
                Matcher dayMatcher = dayPattern.matcher(line);
                if (dayMatcher.find()) {
                    String date = dayMatcher.group(1);
                    double temperature = Double.parseDouble(dayMatcher.group(2));

                    i++;
                    if (i < list.size()) {
                        line = list.get(i);
                        Matcher commentOfDayMatcher = commentOfDayPattern.matcher(line);
                        if (commentOfDayMatcher.find()) {
                            String dayComment = commentOfDayMatcher.group(1);
                            days.add(new Day(date, temperature, dayComment));
                        } else {
                            throw new IllegalArgumentException("Expected a comment line after the date and temperature line");
                        }
                    } else {
                        throw new IllegalArgumentException("Unexpected end of list. Expected a comment line after the date and temperature line");
                    }
                }
            } else {
                throw new IllegalArgumentException("Unexpected line: " + line);
            }
        }

        setSeason(season);
        setComment(comment);
        setDays(days);
    }
    /**
     * Finds and returns an array of Day objects where the comment contains the specified word.
     * If there are no days or no comments contain the word, returns null.
     *
     * @param word the word to search for in the comments
     * @return an array of Day objects where the comment contains the word, or null if no such days exist
     */
    public Day[] findDaysWithCommentWord(String word) {
        if (daysCount() == 0)
            return null;
        return getDaysList().stream()
                .filter(day -> day.isWordInComment(word))
                .toArray(Day[]::new);
    }
    /**
     * Finds and returns an array of Day objects where the comment contains the specified word fragment.
     * If there are no days or no comments contain the word fragment, returns null.
     *
     * @param wordFragment the word fragment to search for in the comments
     * @return an array of Day objects where the comment contains the word fragment, or null if no such days exist
     */
    public Day[] findDaysWithCommentWordFragment(String wordFragment) {
        if (daysCount() == 0)
            return null;
        return getDaysList().stream()
                .filter(day -> day.isFragmentOfWordInComment(wordFragment))
                .toArray(Day[]::new);
    }

    /**
     * Splits the comment into words.
     * This method retrieves the comment and splits it into an array of words using whitespace as the delimiter.
     *
     * @return An array of words from the comment.
     */
    public String[] getWordsFromComment() {
        return getComment().split("\\s+");
    }

    /**
     * Checks if a word is present in the comment.
     * This method retrieves the comment and checks if the specified word is present in it.
     *
     * @param word The word to search for in the comment.
     * @return {@code true} if the word is present in the comment, {@code false} otherwise.
     */
    public boolean isWordInComment(String word) {
        Pattern pattern = Pattern.compile("\\b" + word + "\\b");
        Matcher matcher = pattern.matcher(getComment());
        return matcher.find();
    }

    /**
     * Checks if a fragment is at the start or end of a word in the comment.
     * This method retrieves the comment and checks if the specified fragment is at the start or end of a word in it.
     *
     * @param fragment The fragment to search for in the comment.
     * @return {@code true} if the fragment is at the start or end of a word in the comment, {@code false} otherwise.
     */
    public boolean isFragmentAtStartOfWordInComment(String fragment) {
        Pattern pattern = Pattern.compile("\\b" + fragment + "\\p{L}*\\b");
        Matcher matcher = pattern.matcher(getComment());
        return matcher.find();
    }

    /**
     * Checks if a fragment is at the end of a word in the comment.
     * This method retrieves the comment and checks if the specified fragment is at the end of a word in it.
     *
     * @param fragment The fragment to search for in the comment.
     * @return {@code true} if the fragment is at the end of a word in the comment, {@code false} otherwise.
     */

    public boolean isFragmentAtEndOfWordInComment(String fragment) {
        Pattern pattern = Pattern.compile("\\b\\p{L}*" + fragment + "\\b");
        Matcher matcher = pattern.matcher(getComment());
        return matcher.find();
    }
    /**
     * Creates a new instance of {@code WeatherWithStreams} with predefined values.
     *
     * @return the object of class {@code WeatherWithStreams}
     */
    @Override
    public AbstractWeather createWeather() {
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
