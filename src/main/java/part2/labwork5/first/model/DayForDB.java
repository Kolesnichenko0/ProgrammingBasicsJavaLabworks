package part2.labwork5.first.model;

import part1.labwork3.first.Day;

/**
 * The DayForDB class extends the Day class and is used to represent a day's data for the database.
 * It includes additional properties and methods to handle database-specific operations.
 */
public class DayForDB extends Day {
    private long id = -1;

    private long weatherId = -1;

    /**
     * Default constructor for the DayForDB class.
     * This constructor initializes a new instance of the DayForDB class with no parameters.
     * The fields of the class will be set to their default values.
     */
    public DayForDB() {
    }

    /**
     * Constructs a new DayForDB object with the specified date, temperature, and comment.
     *
     * @param date        the date of the day.
     * @param temperature the temperature of the day.
     * @param comment     the comment for the day.
     */
    public DayForDB(String date, double temperature, String comment) {
        super(date, temperature, comment);
    }

    /**
     * Retrieves the ID of the day.
     *
     * @return the ID of the day.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the day.
     *
     * @param id the ID to be set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retrieves the weather ID of the day.
     *
     * @return the weather ID of the day.
     */
    public long getWeatherId() {
        return weatherId;
    }

    /**
     * Sets the weather ID of the day.
     *
     * @param weatherId the weather ID to be set.
     */
    public void setWeatherId(long weatherId) {
        this.weatherId = weatherId;
    }

    /**
     * Returns a string representation of the DayForDB object.
     *
     * @return a string representation of the DayForDB object.
     */
    @Override
    public String toString() {
        String weatherIdInfo = (weatherId == -1) ? "WeatherId is absent." : "WeatherId: " + weatherId;
        return "Id: " + getId() + ". Date: " + getDate() + ". Temperature: " + getTemperature()
                + " degrees Celsius.\nComment: " + getComment() + ". " + weatherIdInfo;
    }
}
