package part2.labwork2.sandbox;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class WorkWithDate {
//    public static void main(String[] args) {
//        Date date1 = new Date();
//        System.out.println(date1);
//        System.out.println(date1.getTime());
//
//        Calendar gregorianCalendar = Calendar.getInstance();
//        System.out.println(gregorianCalendar);
//        System.out.println(gregorianCalendar.getTime());
//
//        //boolean after(long when) – повертає true, якщо час when більше значення, що зберігається в об'єкті;
//        //true тогда и только тогда, когда момент, представленный этим объектом Date, строго позже момента, представленного when;
//        // false в противном случае.
////        if(new Date().before(new Date(3600L))) {
////            System.out.println("fd");
////        }
////        System.out.println(System.nanoTime());
//
//        // Установка часу з використанням екземпляра Date:
//        System.out.println();
//        Date date = new Date(System.currentTimeMillis());
//        Calendar calendar = GregorianCalendar.getInstance();
//        calendar.setTime(date);
//        System.out.println(calendar.getTime());
//
//        // Виклик методів getter і setter об'єкта Calendar:
//        calendar.set(Calendar.MONTH, Calendar.AUGUST);
//        calendar.set(Calendar.DAY_OF_MONTH, 24);
//        calendar.set(Calendar.YEAR, 1991);
//        calendar.set(Calendar.HOUR, 9);
//        calendar.set(Calendar.MINUTE, 00);
//        calendar.set(Calendar.SECOND, 01);
//        System.out.println(calendar.getTime());
//        System.out.println("The YEAR is: " + calendar.get(Calendar.YEAR));
//        System.out.println("The MONTH is: " + calendar.get(Calendar.MONTH));
//        System.out.println("The DAY is: " + calendar.get(Calendar.DATE));
//        System.out.println("The HOUR is: " + calendar.get(Calendar.HOUR));
//        System.out.println("The MINUTE is: " + calendar.get(Calendar.MINUTE));
//        System.out.println("The SECOND is: " + calendar.get(Calendar.SECOND));
//        System.out.println("The AM_PM indicator is: " + calendar.get(Calendar.AM_PM));
//
//
//        LocalDate date6 = LocalDate.now();
//        LocalTime time6 = LocalTime.now();
//        LocalDateTime dateTime6 = LocalDateTime.now();
//        System.out.println("\n"+time6);
//        System.out.println("\n"+dateTime6);
//        for (String zone: ZoneId.getAvailableZoneIds()) {
//            if (zone.startsWith("Europe")) {
//                System.out.println(zone);
//            }
//        }
//        ZoneId germanZone = ZoneId.of("Europe/Berlin");
//        ZonedDateTime germanTime = ZonedDateTime.now(germanZone);
//        System.out.println(germanTime);
//        ZoneId germanZone = ZoneId.of("Europe/Berlin");
//        LocalDateTime localDateTime = LocalDateTime.parse("2018-09-01T10:00:00");
//        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, germanZone);
//        System.out.println(zonedDateTime.getHour());
//        String block = """
//        Hello, block!
//        This is the same string.
//        We can add use "quotes" without backslash.
//            We can indent.""";
//        System.out.println(block);
//        System.out.println(locale.getCountry()); //код регіону
//        System.out.println(locale.getDisplayCountry()); //назва регіону
//        System.out.println(locale.getLanguage()); //код мови регіону
//        System.out.println(locale.getDisplayLanguage()); //назва мови регіону

    //    }
    public static void main(String[] args) throws UnsupportedEncodingException {
//        printInfo("", "");
//        printInfo("ru", "RU");
//        printInfo("en", "US");
//        printInfo("uk", "UA");
//        Locale locale = Locale.getDefault();
//        System.out.println("Default Locale: " + locale);
//        NumberFormat form = NumberFormat.getInstance(new Locale("ru", "RU"));
//        Scanner scan = new Scanner(System.in);
//        double x = scan.nextDouble();
//        System.out.println(form.format(x));
//        System.out.println(x);
//        Locale locale = new Locale("uk", "UA");
//        System.out.println(locale.getCountry()); //код регіону
//        System.out.println(locale.getDisplayCountry()); //назва регіону
//        System.out.println(locale.getLanguage()); //код мови регіону
//        System.out.println(locale.getDisplayLanguage()); //назва мови регіону
//        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("uk", "UA"));
//        Date today = new Date();
//        String formattedDate = dateFormatter.format(today);
//        System.out.println(formattedDate);
//        Date today = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        String formattedDate = formatter.format(today);
//        System.out.println(formattedDate);
//        DateFormatSymbols symbols = new DateFormatSymbols();
//        String[] oddMonthAbbreviations = new String[] {"Ja","Fe","Mh","Ap","My","Jn","Jy","Au","Se","Oc","No","De" };
//        symbols.setShortMonths(oddMonthAbbreviations);
//        formatter = new SimpleDateFormat("MMM dd, yyyy", symbols);
//        formattedDate = formatter.format(today);
//        System.out.println(formattedDate);
//        Date date = new Date();
//        System.out.printf("The date and time is: %tF", date);
//        Formatter f = new Formatter();
//        f.format("%s %c %nОснови розробки застосунків Java %S ", "Модуль",'2',"se");
//        System.out.print(f);
//        Formatter f = new Formatter();
//        f.format("|%10.2f|", 123.123);  // вирівнювання праворуч
//        System.out.println(f);
//        f = new Formatter();            // вирівнювання ліворуч
//        f.format("|%-10.2f|", 123.123); // застосування прапора '-'
//        System.out.println(f);
//        f = new Formatter();
//        f.format("%,.2f", 123456789.34);// застосування прапора ','
//        System.out.println(f);
//        f = new Formatter();
//        f.format("%.4f", 1111.1111111); // Завдання точності представлення для чисел
//        System.out.println(f);
//        f = new Formatter();
//        f.format("|%-67.6s|", "Робота з текстовими даними."); // Завдання точності представлення для рядків
//        System.out.println(f);
//        Formatter f = new Formatter();
//        Calendar calendar = Calendar.getInstance();
//        f.format("%tr", calendar);// виведення у 12-годинному часовому форматі
//        System.out.println(f);
//        f = new Formatter();
//        f.format("%tc", calendar);// повноформатне виведення часу та дати
//        System.out.println(f);
//        f = new Formatter();
//        f.format("%tl:%tM", calendar, calendar);// виведення поточної години та хвилини
//        System.out.println(f);
//        f = new Formatter();
//        f.format("%tB %tb %tm", calendar, calendar, calendar);// різні варіанти виведення місяця
//        System.out.println(f);
//
        Locale.setDefault(new Locale("uk", "UA"));
        ResourceBundle bundle = ResourceBundle.getBundle("days", Locale.getDefault());
        Duration duration = Duration.ofHours(1).plusMinutes(30).plusSeconds(15);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault());
        String formattedDuration = formatter.format(duration.addTo(LocalTime.MIDNIGHT));
        System.out.println(bundle.getString("shortestGap") + " " + formattedDuration + ".");

    }


    private static void printInfo(String language, String country)
            throws UnsupportedEncodingException {
        Locale current = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle("text", current);
        String s1 = rb.getString("str1");

        System.out.println(s1);
        String s2 = rb.getString("str2");
        System.out.println(s2);
        System.out.println();
    }
}
