package part2.labwork1.first;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import part1.labwork3.first.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code WeatherWithStreamsTest} class provides unit tests for the {@link WeatherWithStreams} class.
 * It includes tests for setting days, sorting, temperature calculations, and finding days based on certain conditions.
 * Each test method in this class corresponds to a method in the {@link WeatherWithStreams} class.
 */
class WeatherWithStreamsTest {
    private WeatherWithStreams weatherWithStreams;

    /**
     * Helper method to get temperatures from a list of days.
     *
     * @param days the list of days
     * @return an array of temperatures
     */
    private static double[] getTemperatures(List<Day> days) {
        return days.stream()
                .mapToDouble(Day::getTemperature)
                .toArray();
    }

    /**
     * Helper method to get temperatures from an array of days.
     *
     * @param days the array of days
     * @return an array of temperatures
     */
    private static double[] getTemperatures(Day[] days) {
        return Arrays.stream(days)
                .mapToDouble(Day::getTemperature)
                .toArray();
    }

    @BeforeEach
    public void setup() {
        weatherWithStreams = new WeatherWithStreams("Winter", "7 days are described with streams",
                Arrays.asList(
                        new Day("09.12.22", -5.2, "Cloudy"),
                        new Day("10.12.22", -3.1, "Windy"),
                        new Day("11.12.22", -6.0, "Sunny"),
                        new Day("12.12.22", -7.6, "Drizzly"),
                        new Day("13.12.22", -3.2, "Foggy"),
                        new Day("14.12.22", 0.0, "Foggy and cloudy"),
                        new Day("15.12.22", 1.0, "Windy and sunny")
                ));
    }

    @Nested
    class TestSettingDays {
        /**
         * Tests the {@link WeatherWithStreams#setDays(List)} method.
         */
        @Test
        @DisplayName("Should Set Days")
        public void setDays() {
            List<Day> days = new ArrayList<>(weatherWithStreams.getDaysList());
            days.add(new Day("16.12.22", 1.3, "Windy"));
            days.add(new Day("17.12.22", 1.0, "Cloudy"));
            weatherWithStreams.setDays(days);
            assertEquals(days.size(), weatherWithStreams.getDaysList().size());
            days.forEach(day -> assertTrue(weatherWithStreams.getDaysList().contains(day)));
        }

        /**
         * Tests the {@link WeatherWithStreams#setDays(List)} method.
         * Checks if the method handles duplicate days correctly.
         */
        @Test
        @DisplayName("Should Handle Duplicate Days When Setting")
        public void setDaysWithDuplicate() {
            List<Day> days = new ArrayList<>(weatherWithStreams.getDaysList());
            Day duplicateDay = new Day("15.12.22", 1.0, "Windy and sunny");
            days.add(duplicateDay);
            weatherWithStreams.setDays(days);
            long count = weatherWithStreams.getDaysList().stream()
                    .filter(day -> day.equals(duplicateDay))
                    .count();
            assertEquals(1, count);
        }
    }

    @Nested
    class TestSorting {
        /**
         * Tests the {@link WeatherWithStreams#sortByDecreasingTemperature()} method.
         */
        @Test
        @DisplayName("Should Sort By Decreasing Temperature")
        public void sortByDecreasingTemperature() {
            weatherWithStreams.sortByDecreasingTemperature();
            double[] expected = new double[]{1.0, 0.0, -3.1, -3.2, -5.2, -6.0, -7.6};
            double[] actual = getTemperatures(weatherWithStreams.getDaysList());
            assertArrayEquals(expected, actual);
        }


        /**
         * Tests the {@link WeatherWithStreams#sortByComment()} method.
         */
        @Test
        @DisplayName("Should Sort By Comment")
        public void sortByComment() {
            weatherWithStreams.sortByComment();
            double[] expected = new double[]{-5.2, -7.6, -3.2, 0.0, -6.0, -3.1, 1.0};
            double[] actual = getTemperatures(weatherWithStreams.getDaysList());
            assertArrayEquals(expected, actual);
        }

    }

    @Nested
    class TestTemperatureCalculations {
        /**
         * Tests the {@link WeatherWithStreams#calculateAverageTemperature()} method.
         */
        @Test
        @DisplayName("Should Calculate Average Temperature")
        public void calculateAverageTemperature() {
            assertEquals(-3.44, weatherWithStreams.calculateAverageTemperature(), 0.005);
        }

        /**
         * Tests the {@link WeatherWithStreams#calculateAverageTemperature()} method.
         * Checks if the method returns null when there are no days.
         */
        @ParameterizedTest
        @DisplayName("Should Return Null When Calculating Average Temperature Without Days")
        @EmptySource
        public void calculateAverageTemperatureWithoutDays(List<Day> emptyList) {
            weatherWithStreams.setDays(emptyList);
            assertNull(weatherWithStreams.calculateAverageTemperature());
        }
    }

    @Nested
    class TestFindingDays {
        /**
         * Tests the {@link WeatherWithStreams#findMaxTemperatureDays()} method.
         * Checks if the method finds the one day with the maximum temperature correctly.
         */
        @Test
        @DisplayName("Should Find One Day With Max Temperature")
        public void findMaxTemperatureDaysWithOneMaxTemperature() {
            double[] expected = new double[]{1.0};
            double[] actual = getTemperatures(weatherWithStreams.findMaxTemperatureDays());
            assertArrayEquals(expected, actual);
        }

        /**
         * Tests the {@link WeatherWithStreams#findMaxTemperatureDays()} method.
         * Checks if the method finds multiple days with the maximum temperature correctly.
         */
        @Test
        @DisplayName("Should Find Two Days With Max Temperature")
        public void findMaxTemperatureDaysWithMultipleMaxTemperature() {
            List<Day> days = new ArrayList<>(weatherWithStreams.getDaysList());
            Day duplicateMaxTemperatureDay = new Day("16.12.22", 1.0, "Windy");
            days.add(duplicateMaxTemperatureDay);
            weatherWithStreams.setDays(days);

            double[] expected = new double[]{1.0, 1.0};
            double[] actual = getTemperatures(weatherWithStreams.findMaxTemperatureDays());
            assertArrayEquals(expected, actual);
        }

        /**
         * Tests the {@link WeatherWithStreams#findMaxTemperatureDays()} method.
         * Checks if the method returns null when there are no days.
         */
        @ParameterizedTest
        @DisplayName("Should Return Null When Finding Max Temperature Days Without Days")
        @EmptySource
        public void findMaxTemperatureDaysWithoutDays(List<Day> emptyList) {
            weatherWithStreams.setDays(emptyList);
            assertNull(weatherWithStreams.findMaxTemperatureDays());
        }

        /**
         * Tests the {@link WeatherWithStreams#findLongestCommentDays()} method.
         * Checks if the method finds the one day with the longest comment correctly.
         */
        @Test
        @DisplayName("Should Find One Day With Longest Comment")
        public void findLongestCommentDaysWithOneMaxTemperature() {
            double[] expected = new double[]{0.0};
            double[] actual = getTemperatures(weatherWithStreams.findLongestCommentDays());
            assertArrayEquals(expected, actual);
        }

        /**
         * Tests the {@link WeatherWithStreams#findLongestCommentDays()} method.
         * Checks if the method finds multiple days with the longest comment correctly.
         */
        @Test
        @DisplayName("Should Find Two Days With Longest Comment")
        public void findLongestCommentDaysWithMultipleMaxTemperature() {
            List<Day> days = new ArrayList<>(weatherWithStreams.getDaysList());
            Day duplicateLongestCommentDay = new Day("16.12.22", 2.0, "Rainy and cloudy");
            days.add(duplicateLongestCommentDay);
            weatherWithStreams.setDays(days);

            double[] expected = new double[]{0.0, 2.0};
            double[] actual = getTemperatures(weatherWithStreams.findLongestCommentDays());
            assertArrayEquals(expected, actual);
        }

        /**
         * Tests the {@link WeatherWithStreams#findLongestCommentDays()} method.
         * Checks if the method returns null when there are no days.
         */
        @ParameterizedTest
        @DisplayName("Should Return Null When Finding Longest Comment Days Without Days")
        @EmptySource
        public void findLongestCommentDaysWithoutDays(List<Day> emptyList) {
            weatherWithStreams.setDays(emptyList);
            assertNull(weatherWithStreams.findLongestCommentDays());
        }
    }
}