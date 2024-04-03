package part1.labwork3.first;

/**
 * Represents weather data with an array of days.
 * This class is inherited from the abstract {@link AbstractWeather}
 */
public class WeatherWithArray extends AbstractWeather {
    private static final long serialVersionUID = 176873029745254541L;
    private String season;
    private String comment;
    private Day[] days;

    /**
     * The constructor initialises the object with the default values.
     */
    public WeatherWithArray() {
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment},  {@code days}.
     *
     * @param season  the season
     * @param comment the comment
     * @param days    the array of days
     */
    public WeatherWithArray(String season, String comment, Day[] days) {
        this.season = season;
        this.comment = comment;
        this.days = days;
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment}.
     *
     * @param season  the season
     * @param comment the comment
     */
    public WeatherWithArray(String season, String comment) {
        this(season, comment, new Day[0]);
    }

    /**
     * Gets the {@code comment} for the weather.
     *
     * @return the {@code comment}
     */
    @Override
    public String getComment() {
        return comment;
    }

    /**
     * Sets the {@code comment} for the weather.
     *
     * @param comment the {@code comment} to be set
     */
    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the {@code season} for the weather.
     *
     * @return the {@code season}
     */
    @Override
    public String getSeason() {
        return season;
    }

    /**
     * Sets the {@code season} for the weather.
     *
     * @param season the {@code season} to be set
     */
    @Override
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * Gets the array of days for the weather.
     *
     * @return the array of days
     */
    @Override
    public Day[] getDays() {
        return days;
    }

    /**
     * Sets the array of days for the weather.
     *
     * @param days the array of days to be set
     */
    @Override
    public void setDays(Day[] days) {
        this.days = days;
    }

    /**
     * Gets the {@code day} with index {@code i}.
     *
     * @return the object of class {@code Day} with index {@code i}
     */
    @Override
    public Day getDay(int i) {
        if (getDays() == null || getDays().length == 0 || i < 0 || i >= getDays().length) {
            return null;
        }
        return getDays()[i];
    }

    /**
     * Sets the {@code day} with index {@code i}.
     *
     * @param i   index of {@code day}
     * @param day the object of class {@code Day} with index {@code i} to be set
     */
    @Override
    public void setDay(int i, Day day) {
        if (getDays() == null || getDays().length == 0 || i < 0 || i >= getDays().length) {
            return;
        }
        getDays()[i] = day;
    }

    /**
     * Adds a link to the new {@code day} at the end of the sequence.
     *
     * @param day the object of class {@code Day} to be added
     * @return {@code true}, if the link was added successfully
     * {@code false} otherwise
     */
    @Override
    public boolean addDay(Day day) {
        if (getDays() != null) {
            for (Day elem : getDays()) {
                if (elem.equals(day)) {
                    return false;
                }
            }
        }
        setDays(addToArray(getDays(), day));
        return true;
    }

    /**
     * Creates a new {@code day} and adds a link to it at the end of the sequence
     *
     * @param Date        the date
     * @param temperature the temperature
     * @param comment     the comment
     * @return {@code true}, if the link was added successfully
     * {@code false} otherwise
     */
    @Override
    public boolean addDay(String Date, double temperature, String comment) {
        Day newDay = new Day(Date, temperature, comment);
        return addDay(newDay);
    }

    /**
     * Counts the number of days in the sequence.
     *
     * @return the number of days
     */
    @Override
    public int daysCount() {
        if (getDays() == null)
            return 0;
        return getDays().length;
    }

    /**
     * Clears the sequence of days.
     */
    @Override
    public void clearDays() {
        setDays(null);
    }

    /**
     * Carries out testing of the default constructor
     *
     * @return the object of class {@code WeatherWithArray}
     */
    private static WeatherWithArray testDefaultConstructor() {
        return new WeatherWithArray();
    }

    /**
     * Carries out testing of the specific constructor with {@code season}, {@code comment},  {@code days}.
     *
     * @return the object of class {@code WeatherWithArray}
     */
    private static WeatherWithArray testWithDaysConstructor(String season, String comment, Day[] days) {
        return new WeatherWithArray(season, comment, days);
    }

    /**
     * Carries out testing of the specific constructor with {@code season}, {@code comment}.
     *
     * @return the object of class {@code WeatherWithArray}
     */
    private static WeatherWithArray testWithoutDaysConstructor(String season, String comment) {
        return new WeatherWithArray(season, comment);
    }

    /**
     * Carries out testing of the functionality of the {@code WeatherWithArray} class.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Day[] firstDays = {new Day("22.12.22", -4.1, "Snowy"),
                new Day("23.12.22", -2.5, "Sunny"),
                new Day("24.12.22", -2.9, "Windy")
        };
        System.out.println("Use default constructor for create defaultWeather:");
        WeatherWithArray defaultWeather = testDefaultConstructor();
        System.out.println(defaultWeather);
        System.out.println("Use with days constructor for create firstSpecificWeather:");
        WeatherWithArray firstSpecificWeather = testWithDaysConstructor("Winter", "3 days are described", firstDays);
        System.out.println(firstSpecificWeather);
        System.out.println("Use without days constructor for create secondSpecificWeather:");
        WeatherWithArray secondSpecificWeather = testWithoutDaysConstructor("Winter", "3 days are described");
        System.out.println(secondSpecificWeather);
        Day[] secondDays = {new Day("25.12.22", -2.1, "Foggy"),
                new Day("26.12.22", -2.0, "Sunny"),
                new Day("27.12.22", -0.2, "Cloudy")
        };
        System.out.println("Use setDays for secondSpecificWeather:");
        secondSpecificWeather.setDays(secondDays);
        System.out.println(secondSpecificWeather);
        System.out.println("Use setDay(0, new Day(\"25.12.22\", -2.8, \"Snowy\")) for secondSpecificWeather:");
        secondSpecificWeather.setDay(0, new Day("25.12.22", -2.8, "Snowy"));
        System.out.println("Days of secondSpecificWeather:");
        secondDays = secondSpecificWeather.getDays();
        for (Day day : secondDays) {
            System.out.println(day);
        }
        System.out.println("Use getDay(1) for secondSpecificWeather:");
        System.out.println(secondSpecificWeather.getDay(1));
        System.out.println("Use daysCount() for secondSpecificWeather: " + secondSpecificWeather.daysCount());

        System.out.println("Comparing firstSpecificWeather with secondSpecificWeather: "
                + firstSpecificWeather.equals(secondSpecificWeather));
        System.out.println("Hashcode for the firstSpecificWeather: " + firstSpecificWeather.hashCode());
        System.out.println("Hashcode for the secondSpecificWeather: " + secondSpecificWeather.hashCode());

        System.out.println("firstSpecificWeather.setDays(secondSpecificWeather.getDays())");
        firstSpecificWeather.setDays(secondSpecificWeather.getDays());
        System.out.println("Comparing firstSpecificWeather with secondSpecificWeather: "
                + firstSpecificWeather.equals(secondSpecificWeather));
        System.out.println("Hashcode for the firstSpecificWeather: " + firstSpecificWeather.hashCode());
        System.out.println("Hashcode for the secondSpecificWeather: " + secondSpecificWeather.hashCode() + "\n");

        System.out.println("Testing getters for secondSpecificWeather:");
        System.out.println(secondSpecificWeather.getSeason());
        System.out.println(secondSpecificWeather.getComment());

    }
}
