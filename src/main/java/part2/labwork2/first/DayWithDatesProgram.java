package part2.labwork2.first;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

/**
 * The {@code DayWithDatesProgram} class represents showing of the functionality
 * of the {@link DayWithDates}.
 */
public class DayWithDatesProgram {
    /**
     * Carries out showing of the functionality of the {@link DayWithDates}.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        System.out.println("With US locale:");
        new DayWithDates().testDay();
        Locale.setDefault(new Locale("uk", "UA"));
        System.out.println("\nWith UA locale:");
        DayWithDates dayWithSpecifiedConstructor = new DayWithDates(ZonedDateTime.of(2024, Month.JANUARY.getValue(), 10, 13, 0, 0, 0, ZoneId.of("Europe/Kiev")),
                -7, "sunny",
                ZonedDateTime.of(2024, Month.JANUARY.getValue(), 10, 15, 32, 10, 0, ZoneId.of("Europe/Kiev")));
        System.out.println(dayWithSpecifiedConstructor);
        dayWithSpecifiedConstructor.testGetWordsFromComment();
        dayWithSpecifiedConstructor.testIsWordInComment();
        dayWithSpecifiedConstructor.testIsFragmentOfWordInComment();
    }
}
