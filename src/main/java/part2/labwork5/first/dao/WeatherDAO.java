package part2.labwork5.first.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.labwork5.first.model.DayForDB;
import part2.labwork5.first.model.WeatherForDB;
import part2.labwork5.first.model.Weathers;
import part2.labwork5.first.util.DbUtils;
import part2.labwork5.first.util.SortOrder;

import java.sql.*;

/**
 * The WeatherDAO class is responsible for managing the Data Access Object (DAO) operations for the WeatherForDB model.
 * It provides methods for interacting with the 'weathers' table in the database.
 * This includes operations like adding a weather, removing a weather, and finding weathers based on certain conditions.
 * <p>
 * Each instance of WeatherDAO is associated with a specific database connection, which is used to execute the SQL queries.
 * This class uses the JDBC API for database connectivity.
 * <p>
 * The WeatherDAO class is crucial for the operation of the application as it provides the link between the WeatherForDB model and the 'weathers' table in the database.
 */
public class WeatherDAO {
    private static final Logger logger = LogManager.getLogger(WeatherDAO.class);
    private Connection connection;

    private DayDAO dayDAO;

    /**
     * Constructs a new WeatherDAO object.
     *
     * @param connection the connection to the database.
     */
    public WeatherDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Sets the DayDAO object.
     *
     * @param dayDAO the DayDAO object to be set.
     */
    public void setDayDAO(DayDAO dayDAO) {
        this.dayDAO = dayDAO;
    }

    /**
     * Retrieves a WeatherForDB object from the database using the provided ResultSet.
     * This method is used to map the data from the ResultSet to a WeatherForDB object.
     *
     * @param setOfWeathers the ResultSet obtained from a SQL query, which contains the weather data.
     * @return a WeatherForDB object that represents the weather data retrieved from the database.
     * @throws SQLException if a database access error occurs or this method is called on a closed result set.
     */
    public WeatherForDB getWeatherFromDB(ResultSet setOfWeathers) throws SQLException {
        logger.info("Attempting to get weather data from DB...");
        WeatherForDB weather = new WeatherForDB(setOfWeathers.getString("season"), setOfWeathers.getString("comment"));
        int id = setOfWeathers.getInt("id");
        weather.setId(id);

        try (PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.SELECT_FROM_DAYS)) {
            preparedStatement.setInt(1, id);
            try (ResultSet setOfDays = preparedStatement.executeQuery()) {
                while (setOfDays.next()) {
                    DayForDB day = new DayForDB(setOfDays.getDate("date").toString(),
                            setOfDays.getDouble("temperature"), setOfDays.getString("comment"));
                    day.setId(setOfDays.getInt("id"));
                    if (setOfDays.getObject("weather_id") != null) {
                        day.setWeatherId(setOfDays.getInt("weather_id"));
                    }
                    weather.addDay(day);
                }
            }
        }
        logger.info("Weather data retrieved successfully from DB");
        return weather;
    }

    /**
     * Retrieves all WeatherForDB objects from the database.
     *
     * @return a Weathers object containing all the WeatherForDB objects.
     */
    public Weathers getWeathersFromDB() {
        logger.info("Attempting to get all weathers data from DB...");
        try {
            Weathers weathers = new Weathers();
            try (Statement statement = connection.createStatement();
                 ResultSet setOfWeathers = statement.executeQuery(DbUtils.SELECT_ALL_WEATHERS)) {
                while (setOfWeathers.next()) {
                    weathers.addWeather(getWeatherFromDB(setOfWeathers));
                }
            }
            logger.info("All weathers data retrieved successfully from DB");
            return weathers;
        } catch (SQLException e) {
            logger.error("Error occurred while getting all weathers data from DB", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the ID of a weather based on the season.
     *
     * @param weatherSeason the season of the weather.
     * @return the ID of the weather for the specified season.
     */
    public int getIdBySeason(String weatherSeason) {
        logger.info("Attempting to get ID by season: " + weatherSeason);
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.SELECT_BY_SEASON)) {
            preparedStatement.setString(1, weatherSeason);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    logger.info("ID retrieved successfully for season: " + weatherSeason);
                    return id;
                } else {
                    logger.error("No result found for season: " + weatherSeason);
                    throw new RuntimeException("No result found for season: " + weatherSeason);
                }
            }
        } catch (SQLException e) {
            logger.error("Error occurred while getting ID by season: " + weatherSeason, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to get a weather from the database by its season.
     *
     * @param weatherSeason The season of the weather to be retrieved.
     * @return The weather object that was retrieved.
     */
    public WeatherForDB getWeatherBySeason(String weatherSeason) {
        logger.info("Attempting to get weather by season: " + weatherSeason);
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.SELECT_BY_SEASON)) {
            preparedStatement.setString(1, weatherSeason);
            try (ResultSet setOfWeathers = preparedStatement.executeQuery()) {
                if (setOfWeathers.next()) {
                    WeatherForDB weather = getWeatherFromDB(setOfWeathers);
                    logger.info("Weather retrieved successfully for season: " + weatherSeason);
                    return weather;
                } else {
                    logger.error("No result found for season: " + weatherSeason);
                    throw new RuntimeException("No result found for season: " + weatherSeason);
                }
            }
        } catch (SQLException e) {
            logger.error("Error occurred while getting weather by season: " + weatherSeason, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to add a weather to the database.
     *
     * @param weather The weather object to be added.
     */
    public void addWeather(WeatherForDB weather) {
        logger.info("Attempting to add weather: " + weather.getSeason());
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.INSERT_INTO_WEATHERS)) {
            preparedStatement.setString(1, weather.getSeason());
            preparedStatement.setString(2, weather.getComment());
            preparedStatement.executeUpdate();
            for (int i = 0; i < weather.daysCount(); i++) {
                dayDAO.addDay(weather.getSeason(), (DayForDB) weather.getDay(i));
            }
            logger.info("Weather added successfully: " + weather.getSeason());
        } catch (SQLException e) {
            logger.error("Error occurred while adding weather: " + weather.getSeason(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds all WeatherForDB objects from a Weathers object to the database.
     *
     * @param weathers the Weathers object containing the WeatherForDB objects to be added.
     */
    public void addAll(Weathers weathers) {
        logger.info("Attempting to add all weathers...");
        for (WeatherForDB w : weathers.getList()) {
            addWeather(w);
        }
        logger.info("All weathers added successfully");
    }

    /**
     * Retrieves a WeatherForDB object from the database based on the season and sort order.
     *
     * @param weatherSeason the season of the weather.
     * @param sortOrder     the order in which the days should be sorted.
     * @return the WeatherForDB object representing the weather data for the specified season.
     */
    public WeatherForDB getWeatherData(String weatherSeason, SortOrder sortOrder) {
        logger.info("Attempting to get weather data for season: " + weatherSeason + " with sort order: " + sortOrder);
        WeatherForDB weather = new WeatherForDB();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.SELECT_BY_SEASON);
                PreparedStatement anotherStatement = switch (sortOrder) {
                    case BY_DECREASING_TEMPERATURE ->
                            connection.prepareStatement(DbUtils.SELECT_FROM_DAYS_ORDER_BY_DECREASING_TEMPERATURE);
                    case BY_COMMENT -> connection.prepareStatement(DbUtils.SELECT_FROM_DAYS_ORDER_BY_COMMENT);
                    case UNSORTED -> connection.prepareStatement(DbUtils.SELECT_FROM_DAYS);
                }
        ) {
            preparedStatement.setString(1, weatherSeason);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    weather.setSeason(resultSet.getString("season"));
                    weather.setComment(resultSet.getString("comment"));
                    weather.setId(resultSet.getInt("id"));
                }
            }

            anotherStatement.setInt(1, getIdBySeason(weatherSeason));
            try (ResultSet anotherSet = anotherStatement.executeQuery()) {
                while (anotherSet.next()) {
                    DayForDB day = new DayForDB(anotherSet.getDate("date").toString(),
                            anotherSet.getDouble("temperature"), anotherSet.getString("comment"));
                    day.setId(anotherSet.getInt("id"));
                    if (anotherSet.getObject("weather_id") != null) {
                        day.setWeatherId(anotherSet.getInt("weather_id"));
                    }
                    weather.addDay(day);
                }
            }
            logger.info("Weather data retrieved successfully for season: " + weatherSeason + " with sort order: " + sortOrder);
            return weather;
        } catch (SQLException e) {
            logger.error("Error occurred while getting weather data for season: " + weatherSeason + " with sort order: " + sortOrder, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves all WeatherForDB objects from the database.
     *
     * @return a Weathers object containing all the WeatherForDB objects.
     */
    public Weathers getAllWeathers() {
        logger.info("Attempting to get all weathers...");
        Weathers weathers = new Weathers();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.SELECT_ALL_WEATHERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String season = resultSet.getString("season");
                WeatherForDB weather = getWeatherData(season, SortOrder.UNSORTED);
                weathers.addWeather(weather);
            }
            logger.info("All weathers retrieved successfully");
            return weathers;
        } catch (Exception e) {
            logger.error("Error occurred while getting all weathers", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a WeatherForDB object from the database based on the season, with the days sorted by decreasing temperature.
     *
     * @param weatherSeason the season of the weather.
     * @return the WeatherForDB object representing the weather data for the specified season.
     */
    public WeatherForDB getWeatherDataSortedByDecreasingTemperature(String weatherSeason) {
        logger.info("Attempting to get weather data for season: " + weatherSeason + " sorted by decreasing temperature");
        WeatherForDB weather = getWeatherData(weatherSeason, SortOrder.BY_DECREASING_TEMPERATURE);
        logger.info("Weather data retrieved successfully for season: " + weatherSeason + " sorted by decreasing temperature");
        return weather;
    }

    /**
     * Retrieves a WeatherForDB object from the database based on the season, with the days sorted by comment.
     *
     * @param weatherSeason the season of the weather.
     * @return the WeatherForDB object representing the weather data for the specified season.
     */
    public WeatherForDB getWeatherDataSortedByComment(String weatherSeason) {
        logger.info("Attempting to get weather data for season: " + weatherSeason + " sorted by comment");
        WeatherForDB weather = getWeatherData(weatherSeason, SortOrder.BY_COMMENT);
        logger.info("Weather data retrieved successfully for season: " + weatherSeason + " sorted by comment");
        return weather;
    }

    /**
     * This method is used to remove a weather from the database.
     *
     * @param weatherSeason The season of the weather to be removed.
     */
    private void removeWeather(String weatherSeason) {
        logger.info("Attempting to remove weather: " + weatherSeason);
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.DELETE_FROM_WEATHERS)) {
            preparedStatement.setString(1, weatherSeason);
            preparedStatement.executeUpdate();
            logger.info("Weather removed successfully: " + weatherSeason);
        } catch (SQLException e) {
            logger.error("Error occurred while removing weather: " + weatherSeason, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes a WeatherForDB object and its associated days from the database based on the season.
     *
     * @param weatherSeason the season of the weather to be removed.
     */
    public void removeWeatherAndDays(String weatherSeason) {
        logger.info("Attempting to remove weather and days for season: " + weatherSeason);
        WeatherForDB weather = getWeatherBySeason(weatherSeason);
        dayDAO.removeDaysByWeatherId((int) weather.getId());
        removeWeather(weatherSeason);
        logger.info("Weather and days removed successfully for season: " + weatherSeason);
    }

}
