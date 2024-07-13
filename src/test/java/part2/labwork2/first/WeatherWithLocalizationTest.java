package part2.labwork2.first;

import org.junit.jupiter.api.Test;
import part1.labwork3.first.Day;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * A class for testing the WeatherWithLocalisation class.
 * It contains test that verify the functionality of the WeatherWithLocalisation constructor.
 *
 * @see WeatherWithLocalization
 */
class WeatherWithLocalizationTest {
    @Test
    public void testWeatherWithLocalizationSpecifiedConstructor() {
        Locale.setDefault(Locale.US);
        String season = "winter";
        String comment = "twoDaysDescribed";
        List<Day> days = new ArrayList<>();
        days.add(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 9, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                -5.2, "cloudy",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 9, 15, 39, 18, 0, ZoneId.of("Europe/Kiev"))));

        days.add(new DayWithDates(ZonedDateTime.of(2022, Month.DECEMBER.getValue(), 10, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                -3.1, "windy",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 10, 14, 3, 31, 0, ZoneId.of("Europe/Kiev"))));

        WeatherWithLocalization weather = new WeatherWithLocalization(season, comment, days);

        assertEquals("Winter", weather.getSeason());
        assertEquals("Two days are described", weather.getComment());
        assertIterableEquals(days, weather.getDaysList());
    }
}