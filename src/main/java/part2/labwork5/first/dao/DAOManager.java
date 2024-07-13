package part2.labwork5.first.dao;

import java.sql.Connection;

/**
 * This class is responsible for managing the Data Access Objects (DAOs).
 * It provides a centralized point of control, allowing you to manage all DAOs in one place.
 * This can be particularly useful when you have multiple DAOs interacting with each other.
 * <p>
 * DAOManager ensures that all DAOs use the same database connection and manage their lifecycle,
 * making it easier to handle transactions and maintain the integrity of your data.
 */
public class DAOManager {
    private DayDAO dayDAO;
    private WeatherDAO weatherDAO;

    /**
     * Constructor for DAOManager.
     *
     * @param connection the connection to the database.
     */
    public DAOManager(Connection connection) {
        this.dayDAO = new DayDAO(connection);
        this.weatherDAO = new WeatherDAO(connection);

        this.dayDAO.setWeatherDAO(weatherDAO);
        this.weatherDAO.setDayDAO(dayDAO);
    }

    /**
     * Returns an instance of DayDAO.
     *
     * @return an instance of DayDAO.
     */
    public DayDAO getDayDAO() {
        return dayDAO;
    }

    /**
     * Returns an instance of WeatherDAO.
     *
     * @return an instance of WeatherDAO.
     */
    public WeatherDAO getWeatherDAO() {
        return weatherDAO;
    }
}
