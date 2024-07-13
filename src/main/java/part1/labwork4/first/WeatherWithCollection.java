package part1.labwork4.first;

import part1.labwork3.first.AbstractWeather;
import part1.labwork3.first.Day;

public abstract class WeatherWithCollection extends AbstractWeather {
    private static final long serialVersionUID = 176873029745254541L;
    private String season;
    private String comment;

    /**
     * The constructor initialises the object with the default values.
     */
    public WeatherWithCollection() {
    }

    /**
     * The constructor initialises the object with the specified values with {@code season}, {@code comment}.
     *
     * @param season  the season
     * @param comment the comment
     */
    public WeatherWithCollection(String season, String comment) {
        this.season = season;
        this.comment = comment;
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

    public void testFunctionality() {
        System.out.println(this);
        Day[] secondDays = {new Day("26.12.22", -2.0, "Sunny"),
                new Day("27.12.22", -0.2, "Cloudy"),
                new Day("25.12.22", -2.1, "Foggy"),
        };
        System.out.println("Use setDays:");
        setDays(secondDays);
        System.out.println(this);

        System.out.println("Use setDay(0, new Day(\"25.12.22\", -2.8, \"Snowy\")):");
        setDay(0, new Day("25.12.22", -2.8, "Snowy"));
        System.out.println("Days(using getDays()):");
        secondDays = getDays();
        for (Day day : secondDays) {
            System.out.println(day);
        }
        System.out.println("Use getDay(1):");
        System.out.println(getDay(1));
        System.out.println("Use daysCount(): " + daysCount());

        System.out.println("Testing getters:");
        System.out.println(getSeason());
        System.out.println(getComment());
    }

}
