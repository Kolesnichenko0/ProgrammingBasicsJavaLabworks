package part2.labwork5.first.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The DbUtils class provides utility methods for database operations.
 * It includes methods for executing SQL queries and handling database transactions.
 * This class cannot be instantiated.
 */
public class DbUtils {
    public static final String DROP_TABLE_DAYS = "DROP TABLE IF EXISTS weathers_db.days";
    public static final String DROP_TABLE_WEATHERS = "DROP TABLE IF EXISTS weathers_db.weathers";
    public static final String DROP_DATABASE_WEATHERS = "DROP DATABASE IF EXISTS weathers_db";
    public static final String CREATE_DATABASE_WEATHERS = "CREATE DATABASE weathers_db";
    public static final String CREATE_TABLE_WEATHERS = """
            CREATE TABLE weathers_db.weathers (
              id INT NOT NULL AUTO_INCREMENT,
              season ENUM('winter', 'spring', 'summer', 'autumn') NOT NULL UNIQUE,
              comment VARCHAR(128) NULL,
              PRIMARY KEY (id));
            """;
    public static final String CREATE_TABLE_DAYS = """
            CREATE TABLE weathers_db.days (
              id INT NOT NULL AUTO_INCREMENT,
              date DATE NOT NULL UNIQUE,
              temperature DECIMAL(10,2) NULL,
              comment VARCHAR(128) NULL,
              weather_id INT NULL,
              PRIMARY KEY (id),
              INDEX weather_id_idx (weather_id ASC) VISIBLE,
              CONSTRAINT weather_id
                FOREIGN KEY (weather_id)
                REFERENCES weathers_db.weathers (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION);
            """;

    public static final String SELECT_BY_SEASON = "SELECT * FROM weathers_db.weathers WHERE season = ?";
    public static final String SELECT_ALL_WEATHERS = "SELECT * FROM weathers_db.weathers";
    public static final String SELECT_ALL_DAYS = "SELECT * FROM weathers_db.days";
    public static final String SELECT_FROM_DAYS = "SELECT * FROM weathers_db.days WHERE weather_id = ?";
    public static final String SELECT_FROM_DAYS_ORDER_BY_DECREASING_TEMPERATURE =
            "SELECT * FROM weathers_db.days WHERE weather_id = ? ORDER BY temperature DESC";
    public static final String SELECT_FROM_DAYS_ORDER_BY_COMMENT =
            "SELECT * FROM weathers_db.days WHERE weather_id = ? ORDER BY comment ASC";
    public static final String SELECT_FROM_DAYS_WHERE_WORD_FRAGMENT = """
                 SELECT d.*, w.season
                 FROM weathers_db.days d
                 INNER JOIN weathers_db.weathers w ON d.weather_id = w.id
                 WHERE d.comment LIKE '%key_word_fragment%';
            """;

    public static final String SELECT_FROM_DAYS_WHERE_WORD_FRAGMENT_AND_WEATHER_ID = """
                SELECT d.*, w.season
                FROM weathers_db.days d
                INNER JOIN weathers_db.weathers w ON d.weather_id = w.id
                WHERE d.comment LIKE '%key_word_fragment%' AND w.id = ?;
            """;

    public static final String SELECT_FROM_DAYS_WHERE_MAX_TEMPERATURE = """
                SELECT d.*, w.season
                FROM weathers_db.days d
                INNER JOIN weathers_db.weathers w ON d.weather_id = w.id
                WHERE d.temperature = (SELECT MAX(temperature) FROM weathers_db.days);
            """;


    public static final String SELECT_FROM_DAYS_WHERE_MAX_TEMPERATURE_AND_WEATHER_ID = """
                SELECT d.*, w.season
                FROM weathers_db.days d
                INNER JOIN weathers_db.weathers w ON d.weather_id = w.id
                WHERE w.id = ? AND d.temperature = (
                    SELECT MAX(temperature)
                    FROM weathers_db.days d2
                    WHERE d2.weather_id = ?
                );
            """;

    public static final String SELECT_FROM_DAYS_WHERE_MAX_COMMENT_LENGTH = """
                SELECT d.*, w.season
                FROM weathers_db.days d
                INNER JOIN weathers_db.weathers w ON d.weather_id = w.id
                WHERE LENGTH(d.comment) = (
                    SELECT MAX(LENGTH(comment))
                    FROM weathers_db.days
                );
            """;

    public static final String SELECT_FROM_DAYS_WHERE_MAX_COMMENT_LENGTH_AND_WEATHER_ID = """
                SELECT d.*, w.season
                FROM weathers_db.days d
                INNER JOIN weathers_db.weathers w ON d.weather_id = w.id
                WHERE w.id = ? AND LENGTH(d.comment) = (
                    SELECT MAX(LENGTH(comment))
                    FROM weathers_db.days d2
                    WHERE d2.weather_id = ?
                );
            """;

    public static final String INSERT_INTO_WEATHERS = """
            INSERT INTO weathers_db.weathers (season, comment) VALUES (?, ?);
            """;
    public static final String INSERT_INTO_DAYS = """
            INSERT INTO weathers_db.days (date, temperature, comment, weather_id) VALUES (?, ?, ?, ?);
            """;
    public static final String DELETE_BY_DATE = "DELETE FROM weathers_db.days WHERE weather_id=? AND date=?";

    public static final String DELETE_FROM_WEATHERS = "DELETE FROM weathers_db.weathers WHERE season = ?";

    public static final String DELETE_DAYS_BY_WEATHER_ID = "DELETE FROM weathers_db.days WHERE weather_id = ?";

    /**
     * Converts a String into a java.sql.Date.
     * The input string should be in the format "yyyy-MM-dd".
     *
     * @param dateString the String to be converted.
     * @return the converted java.sql.Date.
     */
    public static java.sql.Date convertStringToSqlDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(dateString);
            return new java.sql.Date(parsed.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
