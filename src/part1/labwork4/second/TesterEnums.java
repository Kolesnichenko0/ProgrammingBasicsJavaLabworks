package part1.labwork4.second;

public class TesterEnums {
    private static void testMonth(Month month) {
        System.out.println("Працюємо з місяцем: ");
        System.out.println(month);
        System.out.println(month.getSeason());
        System.out.println("Попередній місяць:\n" + month.previous());
        System.out.println("Наступний місяць:\n" + month.next() + "\n");
    }

    public static void main(String[] args) {
        System.out.println("Тестування переліку Season:");
        Season season = Season.WINTER;
        System.out.println(season);
        System.out.println("Тестування переліку Month:");
        Month.printMonths();
        testMonth(Month.JANUARY);
        testMonth(Month.DECEMBER);
    }
}
