package part2.labwork5.first.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.labwork5.first.model.DayForDB;
import part2.labwork5.first.model.WeatherForDB;
import part2.labwork5.first.util.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The DayDAO class is responsible for managing the Data Access Object (DAO) operations for the DayForDB model.
 * It provides methods for interacting with the 'days' table in the database.
 * This includes operations like adding a day, removing a day, and finding days based on certain conditions.
 * <p>
 * Each instance of DayDAO is associated with a specific database connection, which is used to execute the SQL queries.
 * This class uses the JDBC API for database connectivity.
 * <p>
 * The DayDAO class is crucial for the operation of the application as it provides the link between the DayForDB model and the 'days' table in the database.
 */
public class DayDAO {
    private static final Logger logger = LogManager.getLogger(DayDAO.class);
    private Connection connection;
    private WeatherDAO weatherDAO;

    /**
     * Constructs a new DayDAO object.
     *
     * @param connection the connection to the database.
     */
    public DayDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Sets the WeatherDAO object.
     *
     * @param weatherDAO the WeatherDAO object to be set.
     */
    public void setWeatherDAO(WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
    }

    /**
     * Retrieves all DayForDB objects from the database.
     *
     * @return a List of DayForDB objects.
     */
    public List<DayForDB> getAllDays() {
        List<DayForDB> days = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.SELECT_ALL_DAYS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            days = getDaysData(resultSet);
        } catch (SQLException e) {
            logger.error("Error occurred while retrieving all days", e);
            throw new RuntimeException(e);
        }
        return days;
    }

    /**
     * This method is used to add a day to the database.
     *
     * @param weatherSeason The season of the weather.
     * @param day           The day object to be added.
     */
    public void addDay(String weatherSeason, DayForDB day) {
        logger.info("Attempting to add day for season: " + weatherSeason);
        WeatherForDB weather = weatherDAO.getWeatherBySeason(weatherSeason);
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.INSERT_INTO_DAYS)) {
            preparedStatement.setDate(1, DbUtils.convertStringToSqlDate(day.getDate()));
            preparedStatement.setDouble(2, day.getTemperature());
            preparedStatement.setString(3, day.getComment());
            preparedStatement.setInt(4, weatherDAO.getIdBySeason(weather.getSeason()));
            preparedStatement.executeUpdate();
            logger.info("Day added successfully for season: " + weatherSeason);
        } catch (SQLException e) {
            logger.error("Error occurred while adding day for season: " + weatherSeason, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to remove a day from the database.
     *
     * @param weatherSeason The season of the weather.
     * @param date          The date of the day to be removed.
     */
    public void removeDay(String weatherSeason, String date) {
        logger.info("Attempting to remove day for season: " + weatherSeason + " and date: " + date);
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.DELETE_BY_DATE)) {
            preparedStatement.setInt(1, weatherDAO.getIdBySeason(weatherSeason));
            preparedStatement.setDate(2, DbUtils.convertStringToSqlDate(date));
            preparedStatement.execute();
            logger.info("Day removed successfully for season: " + weatherSeason + " and date: " + date);
        } catch (SQLException e) {
            logger.error("Error occurred while removing day for season: " + weatherSeason + " and date: " + date, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes days associated with a specific weather ID.
     *
     * @param weatherId the ID of the weather whose associated days are to be removed.
     */
    public void removeDaysByWeatherId(int weatherId) {
        logger.info("Attempting to remove days for weatherId: " + weatherId);
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbUtils.DELETE_DAYS_BY_WEATHER_ID)) {
            preparedStatement.setInt(1, weatherId);
            preparedStatement.execute();
            logger.info("Days removed successfully for weatherId: " + weatherId);
        } catch (SQLException e) {
            logger.error("Error occurred while removing days for weatherId: " + weatherId, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves data of days from the database.
     *
     * @return a list of DayForDB objects representing the days data.
     */
    private List<DayForDB> getDaysData(ResultSet resultSet) throws SQLException {
        logger.info("Attempting to get days data...");
        List<DayForDB> days = new ArrayList<>();
        while (resultSet.next()) {
            DayForDB day = new DayForDB(resultSet.getDate("date").toString(),
                    resultSet.getDouble("temperature"), resultSet.getString("comment"));
            day.setId(resultSet.getInt("id"));
            if (resultSet.getObject("weather_id") != null) {
                day.setWeatherId(resultSet.getInt("weather_id"));
            }
            days.add(day);
        }
        logger.info("Days data retrieved successfully");
        return days;
    }

    /**
     * Finds days based on a specific condition or criteria.
     *
     * @return a list of DayForDB objects that meet the specified condition or criteria.
     */
    private List<DayForDB> findDays(String query, Integer... weatherId) {
        logger.info("Attempting to find days with query: " + query + " and weatherId: " + (weatherId.length > 0 ? weatherId[0] : "none"));
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < weatherId.length; i++) {
                preparedStatement.setInt(i + 1, weatherId[i]);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<DayForDB> days = getDaysData(resultSet);
                logger.info("Days found successfully with query: " + query + " and weatherId: " + (weatherId.length > 0 ? weatherId[0] : "none"));
                return days;
            }
        } catch (SQLException e) {
            logger.error("Error occurred while finding days with query: " + query + " and weatherId: " + (weatherId.length > 0 ? weatherId[0] : "none"), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to find days with a word fragment in the comment.
     *
     * @param wordFragment The word fragment to be searched in the comment.
     * @return List of days that contain the word fragment in the comment.
     */
    public List<DayForDB> findDaysWithWordFragment(String wordFragment) {
        logger.info("Attempting to find days with word fragment: " + wordFragment);
        List<DayForDB> days = findDays(DbUtils.SELECT_FROM_DAYS_WHERE_WORD_FRAGMENT.replace("key_word_fragment", wordFragment));
        logger.info("Days found successfully with word fragment: " + wordFragment);
        return days;
    }

    /**
     * This method is used to find days with a word fragment in the comment for a specific season.
     *
     * @param wordFragment  The word fragment to be searched in the comment.
     * @param weatherSeason The season of the weather.
     * @return List of days that contain the word fragment in the comment for the specified season.
     */
    public List<DayForDB> findDaysWithWordFragment(String wordFragment, String weatherSeason) {
        logger.info("Attempting to find days with word fragment: " + wordFragment + " and weather season: " + weatherSeason);
        int weatherId = weatherDAO.getIdBySeason(weatherSeason);
        List<DayForDB> days = findDays(DbUtils.SELECT_FROM_DAYS_WHERE_WORD_FRAGMENT_AND_WEATHER_ID.replace("key_word_fragment", wordFragment), weatherId);
        logger.info("Days found successfully with word fragment: " + wordFragment + " and weather season: " + weatherSeason);
        return days;
    }

    /**
     * This method is used to find days with the maximum temperature.
     *
     * @return List of days with the maximum temperature.
     */
    public List<DayForDB> findMaxTemperatureDays() {
        logger.info("Attempting to find days with max temperature...");
        List<DayForDB> days = findDays(DbUtils.SELECT_FROM_DAYS_WHERE_MAX_TEMPERATURE);
        logger.info("Days with max temperature found successfully");
        return days;
    }

    /**
     * This method is used to find days with the maximum temperature for a specific season.
     *
     * @param weatherSeason The season of the weather.
     * @return List of days with the maximum temperature for the specified season.
     */
    public List<DayForDB> findMaxTemperatureDays(String weatherSeason) {
        logger.info("Attempting to find days with max temperature for weather season: " + weatherSeason);
        int weatherId = weatherDAO.getIdBySeason(weatherSeason);
        List<DayForDB> days = findDays(DbUtils.SELECT_FROM_DAYS_WHERE_MAX_TEMPERATURE_AND_WEATHER_ID, weatherId, weatherId);
        logger.info("Days with max temperature for weather season: " + weatherSeason + " found successfully");
        return days;
    }

    /**
     * This method is used to find days with the longest comment.
     *
     * @return List of days with the longest comment.
     */
    public List<DayForDB> findLongestCommentDays() {
        logger.info("Attempting to find days with longest comment...");
        List<DayForDB> days = findDays(DbUtils.SELECT_FROM_DAYS_WHERE_MAX_COMMENT_LENGTH);
        logger.info("Days with longest comment found successfully");
        return days;
    }

    /**
     * This method is used to find days with the longest comment for a specific season.
     *
     * @param weatherSeason The season of the weather.
     * @return List of days with the longest comment for the specified season.
     */
    public List<DayForDB> findLongestCommentDays(String weatherSeason) {
        logger.info("Attempting to find days with longest comment for weather season: " + weatherSeason);
        int weatherId = weatherDAO.getIdBySeason(weatherSeason);
        List<DayForDB> days = findDays(DbUtils.SELECT_FROM_DAYS_WHERE_MAX_COMMENT_LENGTH_AND_WEATHER_ID, weatherId, weatherId);
        logger.info("Days with longest comment for weather season: " + weatherSeason + " found successfully");
        return days;
    }
}
