package part2.labwork5.first.model;

import part1.labwork3.first.Day;
import part2.labwork1.first.WeatherWithStreams;

public class WeatherForDB extends WeatherWithStreams {
    private long id = -1;

    /**
     * Default constructor for the WeatherForDB class.
     * This constructor initializes a new instance of the WeatherForDB class with no parameters.
     * The fields of the class will be set to their default values.
     */
    public WeatherForDB() {
    }

    /**
     * Constructs a new WeatherForDB object with the specified season and comment.
     *
     * @param season  the season of the weather.
     * @param comment the comment for the weather.
     */
    public WeatherForDB(String season, String comment) {
        super(season, comment);
    }

    /**
     * Retrieves the ID of the weather.
     *
     * @return the ID of the weather.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the weather.
     *
     * @param id the ID to be set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the WeatherForDB object.
     *
     * @return a string representation of the WeatherForDB object.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Weather:\nId: ").append(getId()).append(". Season: ")
                .append(getSeason()).append(". Comment: ").append(getComment());
        if (daysCount() == 0) {
            result.append(".\nThere are no days.\n");
        } else {
            result.append(".\nDays:\n");
            for (Day day : getDays()) {
                result.append(day).append("\n");
            }
        }
        return result.toString();
    }
}
