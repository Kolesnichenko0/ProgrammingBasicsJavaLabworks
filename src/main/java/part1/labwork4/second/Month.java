package part1.labwork4.second;

public enum Month {
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(31),
    SEPTEMBER(30),
    OCTOBER(31),
    NOVEMBER(30),
    DECEMBER(31);

    private final int numberOfDays;

    Month(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public String getUkrainianName() {
        return switch (this) {
            case JANUARY -> "Січень";
            case FEBRUARY -> "Лютий";
            case MARCH -> "Березень";
            case APRIL -> "Квітень";
            case MAY -> "Травень";
            case JUNE -> "Червень";
            case JULY -> "Липень";
            case AUGUST -> "Серпень";
            case SEPTEMBER -> "Вересень";
            case OCTOBER -> "Жовтень";
            case NOVEMBER -> "Листопад";
            case DECEMBER -> "Грудень";
            default -> "Невідомий";
        };
    }

    Month next() {
        Month[] months = values();
        return months[(ordinal() + 1) % months.length];
    }

    Month previous() {
        Month[] months = values();
        return months[(ordinal() + 11) % months.length];
    }

    public Season getSeason() {
        return switch (this) {
            case DECEMBER, JANUARY, FEBRUARY -> Season.WINTER;
            case MARCH, APRIL, MAY -> Season.SPRING;
            case JUNE, JULY, AUGUST -> Season.SUMMER;
            case SEPTEMBER, OCTOBER, NOVEMBER -> Season.AUTUMN;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Місяць: " + getUkrainianName() + ". Кількість днів: " + getNumberOfDays()
                + ". Номер місяця в переліку : " + ordinal();
    }

    public static void printMonths() {
        System.out.println("Всі місяці:");
        for (Month month : values()) {
            System.out.println(month);
        }
        System.out.println();
    }
}


