package part2.labwork1.first;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import part1.labwork3.first.Day;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherWithStreamsTest {
    private WeatherWithStreams weatherWithStreams;

    @BeforeEach
    void setup() {
        weatherWithStreams = new WeatherWithStreams("Winter", "7 days are described with streams",
                Arrays.asList(
                        new Day("09.12.22", -5.2, "Cloudy"),
                        new Day("10.12.22", -3.1, "Windy"),
                        new Day("11.12.22", -6.0, "Sunny"),
                        new Day("12.12.22", -7.6, "Drizzly"),
                        new Day("13.12.22", -3.2, "Foggy"),
                        new Day("14.12.22", 0, "Foggy and cloudy"),
                        new Day("15.12.22", 1, "Windy and sunny")
                ));
    }


    @Test
    void setDays() { //TODO
        List<Day> days = weatherWithStreams.getDaysList()
                .add(new Day("15.12.22", 1, "Windy and sunny"));
    }


    @Test
    void sortByDecreasingTemperature() {
    }

    @Test
    void sortByComment() {
    }

    @Test
    void calculateAverageTemperature() {
    }

    @Test
    void findMaxTemperatureDays() {
    }

    @Test
    void findLongestCommentDays() {
    }

    @AfterEach
    void tearDown() {
    }
}