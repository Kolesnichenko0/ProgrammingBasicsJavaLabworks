package part1.labwork4.second;

public enum Season {
    WINTER,
    SPRING,
    SUMMER,
    AUTUMN;

    public String getUkrainianName() {
        return switch (this) {
            case WINTER -> "Зима";
            case SPRING -> "Весна";
            case SUMMER -> "Літо";
            case AUTUMN -> "Осінь";
            default -> "Невідомий";
        };
    }

    @Override
    public String toString() {
        return "Сезон: " + getUkrainianName() + ". Номер сезону в переліку : " + ordinal();
    }
}
