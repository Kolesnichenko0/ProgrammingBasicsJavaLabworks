package part2.labwork2.second;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DateInput {
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    private static Date createDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.parse(dateString);
    }

    private static GregorianCalendar createGregorianCalendar(String dateString) throws ParseException {
        Date date = createDate(dateString);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar;
    }

    private static LocalDate createLocalDate(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(dateString, formatter);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the date in the format dd.mm.yyyy:");
        String input = scanner.nextLine();

        DateValidator dateValidator = new DateValidator();

        if (!dateValidator.isValidDate(input)) {
            System.out.println("Error: the entered string does not match the format dd.mm.yyyy");
        } else {
            try {
                Date date = createDate(input);
                System.out.println("Date: " + date);

                GregorianCalendar gregorianCalendar = createGregorianCalendar(input);
                System.out.println("GregorianCalendar: " + gregorianCalendar.getTime());

                LocalDate localDate = createLocalDate(input);
                System.out.println("LocalDate: " + localDate);
            } catch (ParseException | DateTimeParseException e) {
                System.out.println("Error converting string to date: " + e.getMessage());
            }
        }
    }
}
