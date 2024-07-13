package part2.labwork5.first.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.labwork5.first.util.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * The DatabaseConfig class is responsible for managing the database configuration.
 * It includes methods for creating and closing the database connection.
 * This class is crucial for the operation of the application
 * as it establishes the link between the application and the database.
 * It uses the JDBC API for database connectivity.
 * JDBC 4.0 includes automatic driver discovery, so the MySQL driver will be automatically registered when you start your application.
 * The connection is made to the mysql database, which is the MySQL system database. This is used to create a new database.
 */
public class DatabaseConfig {
    private static final Logger logger = LogManager.getLogger(DatabaseConfig.class);
    private static Connection connection;
    private static final String RESOURCE_BUNDLE_PATH = "part2/labwork5/first/config/";
    private static final String RESOURCE_BUNDLE = "db";

    /**
     * This static initializer block is executed when the DatabaseConfig class is loaded into memory.
     * It is used to perform initialization operations for the class, such as loading database drivers,
     * setting up connections, or other setup tasks that need to be performed once and prior to the instantiation of any objects.
     */
    static {
        try {
            ResourceBundle dbProperties = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH + RESOURCE_BUNDLE);
            String url = dbProperties.getString("url");
            String user = dbProperties.getString("user");
            String password = dbProperties.getString("password");
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Successfully connected to the database");
        } catch (SQLException e) {
            logger.error("Error connecting to the database", e);
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    /**
     * Returns the database connection object.
     *
     * @return the Connection object representing the database connection.
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Creates a new database and tables for the application.
     * If the database and tables already exist, they will be dropped before the new ones are created.
     *
     * @return true if the database and tables were created successfully, false if an SQLException occurred.
     */
    public static boolean createDatabase() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DbUtils.DROP_TABLE_DAYS);
            statement.executeUpdate(DbUtils.DROP_TABLE_WEATHERS);
            statement.executeUpdate(DbUtils.DROP_DATABASE_WEATHERS);
            logger.info("Database and tables dropped successfully if they existed");
            statement.executeUpdate(DbUtils.CREATE_DATABASE_WEATHERS);
            statement.executeUpdate(DbUtils.CREATE_TABLE_WEATHERS);
            statement.executeUpdate(DbUtils.CREATE_TABLE_DAYS);
            logger.info("Database and tables created successfully");
            return true;
        } catch (SQLException e) {
            logger.error("Error creating database and tables", e);
            return false;
        }
    }

    /**
     * Closes the database connection.
     * Logs the status of the operation: success or error.
     * In case of an error, it throws a RuntimeException.
     *
     * @throws RuntimeException if an SQLException occurs while closing the connection
     */
    public static void closeConnection() {
        try {
            connection.close();
            logger.info("Database connection closed successfully");
        } catch (SQLException e) {
            logger.error("Error closing database connection", e);
            throw new RuntimeException(e);
        }
    }

}
