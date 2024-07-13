package part2.labwork5.first;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part2.labwork5.first.config.DatabaseConfig;
import part2.labwork5.first.dao.DAOManager;
import part2.labwork5.first.model.DayForDB;
import part2.labwork5.first.model.WeatherForDB;
import part2.labwork5.first.model.Weathers;
import part2.labwork5.first.service.DayService;
import part2.labwork5.first.service.WeatherService;
import part2.labwork5.first.util.SortOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * The DbConsoleApplication class is the main entry point of the application.
 * It is responsible for initializing and starting the application.
 * This class interacts with the service layer to perform operations on the database.
 * It also handles user input and output through the console.
 */
public class DbConsoleApplication {
    private static final Logger logger = LogManager.getLogger(DbConsoleApplication.class);
    private final DayService dayService;
    private final WeatherService weatherService;

    private static final String FILE_PATH = "storage/part2/labwork5/first/";
    private static final String ALL_WEATHERS_FILE_NAME = "weathers.json";

    private static final String CHANGED_WEATHERS_FILE_NAME = "changed_weathers.json";

    /**
     * Constructor for the DbConsoleApplication class.
     * Initializes a new instance of the DbConsoleApplication.
     */
    public DbConsoleApplication(DayService dayService, WeatherService weatherService) {
        this.dayService = dayService;
        this.weatherService = weatherService;
    }

    /**
     * Prints the details of the days.
     * This method retrieves and prints the details of the days from the database.
     */
    public static void printDays(List<DayForDB> days) {
        days.forEach(System.out::println);
    }

    /**
     * Prints the weather data.
     * This method retrieves and prints the weather data from the database.
     */
    public static void printWeatherData(WeatherForDB weather) {
        System.out.println(weather);
    }

    /**
     * Prints all weathers.
     * This method retrieves and prints all the weather data from the database.
     */
    public static void printAllWeathers(Weathers weathers) {
        System.out.println(weathers);
    }

    /**
     * Creates test data.
     * This method generates and inserts test data into the database.
     */
    private void createTestData() {
        List<DayForDB> summerDays = new ArrayList<>();
        summerDays.add(new DayForDB("2024-06-01", 30.5, "Hot day"));
        summerDays.add(new DayForDB("2024-06-02", 30.5, "Very hot day"));
        summerDays.add(new DayForDB("2024-06-03", 29.0, "Warm day"));

        List<DayForDB> winterDays = new ArrayList<>();
        winterDays.add(new DayForDB("2024-12-01", -5.0, "Cold day"));
        winterDays.add(new DayForDB("2024-12-02", -10.0, "Very cold day"));

        List<DayForDB> springDays = new ArrayList<>();
        springDays.add(new DayForDB("2024-03-21", 15.0, "Warm day"));
        springDays.add(new DayForDB("2024-03-22", 18.0, "Very warm day"));
        springDays.add(new DayForDB("2024-03-23", 18.0, "Very warm day"));

        WeatherForDB summerWeather = new WeatherForDB("summer", "Hot and sunny");
        summerDays.forEach(summerWeather::addDay);
        weatherService.addWeather(summerWeather);

        WeatherForDB winterWeather = new WeatherForDB("winter", "Cold and snowy");
        winterDays.forEach(winterWeather::addDay);
        weatherService.addWeather(winterWeather);

        WeatherForDB springWeather = new WeatherForDB("spring", "Mild and rainy");
        springDays.forEach(springWeather::addDay);
        weatherService.addWeather(springWeather);
    }

    /**
     * Executes a series of operations related to the DayService.
     * This may include actions such as adding, removing, or finding days.
     * The specific operations executed are determined by the implementation.
     */
    private void runDayServiceOperations() {
        logger.info("Running DayService operations");
        System.out.println("\033[1;33m" + "=== DayService ===" + "\033[0m");
        List<DayForDB> allDays = new ArrayList<>();

        logger.info("Finding all days");
        allDays.addAll(dayService.getAllDays());
        System.out.println("\033[1;32m" + "\nAll days:" + "\033[0m");
        printDays(allDays);

        logger.info("Finding days with word fragment 'Warm'");
        List<DayForDB> daysWithFragment = dayService.findDaysWithWordFragment("Warm");
        System.out.println("\033[1;32m" + "\nDays with word fragment 'Warm':" + "\033[0m");
        printDays(daysWithFragment);

        logger.info("Finding days with word fragment 'Warm' in Summer");
        daysWithFragment = dayService.findDaysWithWordFragment("Warm", "summer");
        System.out.println("\033[1;32m" + "\nDays with word fragment 'Warm' in Summer:" + "\033[0m");
        printDays(daysWithFragment);

        logger.info("Finding days with max temperature");
        List<DayForDB> maxTempDays = dayService.findMaxTemperatureDays();
        System.out.println("\033[1;32m" + "\nDays with max temperature:" + "\033[0m");
        printDays(maxTempDays);

        logger.info("Finding days with max temperature in Spring");
        maxTempDays = dayService.findMaxTemperatureDays("spring");
        System.out.println("\033[1;32m" + "\nDays with max temperature in Spring:" + "\033[0m");
        printDays(maxTempDays);

        logger.info("Finding days with longest comments");
        List<DayForDB> longestCommentDays = dayService.findLongestCommentDays();
        System.out.println("\033[1;32m" + "\nDays with longest comments:" + "\033[0m");
        printDays(longestCommentDays);

        logger.info("Finding days with longest comments in Summer");
        longestCommentDays = dayService.findLongestCommentDays("summer");
        System.out.println("\033[1;32m" + "\nDays with longest comments in Summer:" + "\033[0m");
        printDays(longestCommentDays);

        logger.info("Removing day with date 2024-06-01 in Summer");
        dayService.removeDay("summer", "2024-06-01");
        System.out.println("\033[1;32m" + "\nRemoved day with date 2024-06-01 in Summer." + "\033[0m");
    }

    /**
     * Executes a series of operations related to the WeatherService.
     * This may include actions such as adding, removing, or finding weathers.
     * The specific operations executed are determined by the implementation.
     */
    private void runWeatherServiceOperations() {
        logger.info("Running WeatherService operations");

        logger.info("Creating sample weather for Autumn");
        System.out.println("\033[1;33m" + "\n=== WeatherService ===" + "\033[0m");
        WeatherForDB sampleWeather = new WeatherForDB("autumn", "Cool and windy");
        sampleWeather.addDay(new DayForDB("2024-09-21", 10.0, "Cool day"));
        sampleWeather.addDay(new DayForDB("2024-09-22", 8.0, "Very cool day"));

        logger.info("Adding sample weather to Weathers object");
        Weathers weathersToAdd = new Weathers();
        weathersToAdd.addWeather(sampleWeather);

        logger.info("Adding all weathers from Weathers object to WeatherService");
        weatherService.addAll(weathersToAdd);
        System.out.println("\033[1;32m" + "\nAdded weather:" + "\033[0m");
        printWeatherData(sampleWeather);

        logger.info("Exporting all weathers to JSON");
        Weathers weathers = weatherService.getAllWeathers();
        weatherService.exportToJSON(weathers, FILE_PATH + ALL_WEATHERS_FILE_NAME);
        System.out.println("\033[1;32m" + "\nExported all weathers(after getting all weathers) to "
                + FILE_PATH + ALL_WEATHERS_FILE_NAME + "\033[0m");

        logger.info("Importing weathers from JSON");
        Weathers importedWeathers = weatherService.importFromJSON(FILE_PATH + ALL_WEATHERS_FILE_NAME);
        System.out.println("\033[1;32m" + "\nImported weathers from " + FILE_PATH + ALL_WEATHERS_FILE_NAME + ":" + "\033[0m");
        printAllWeathers(importedWeathers);

        logger.info("Getting all weathers");
        Weathers allWeathers = weatherService.getAllWeathers();
        System.out.println("\033[1;32m" + "\nAll weathers:" + "\033[0m");
        printAllWeathers(allWeathers);

        logger.info("Getting weather data for Spring sorted by decreasing temperature");
        WeatherForDB sortedWeatherByTemp = weatherService.getWeatherDataSortedByDecreasingTemperature("spring");
        System.out.println("\033[1;32m" + "\nWeather data for Spring sorted by decreasing temperature:" + "\033[0m");
        printWeatherData(sortedWeatherByTemp);

        logger.info("Getting weather data for Spring sorted by comment");
        WeatherForDB sortedWeatherByComment = weatherService.getWeatherDataSortedByComment("spring");
        System.out.println("\033[1;32m" + "\nWeather data for Spring sorted by comment:" + "\033[0m");
        printWeatherData(sortedWeatherByComment);

        logger.info("Getting unsorted weather data for Spring");
        WeatherForDB weatherData = weatherService.getWeatherData("spring", SortOrder.UNSORTED);
        System.out.println("\033[1;32m" + "\nWeather data for Spring unsorted:" + "\033[0m");
        printWeatherData(weatherData);

        logger.info("Getting unsorted weather data for Spring");
        weatherService.removeWeatherAndDays("spring");
        System.out.println("\033[1;32m" + "\nRemoved weather and days for Spring." + "\033[0m");

        logger.info("Exporting all weathers to JSON");
        weatherService.exportToJSON(FILE_PATH + CHANGED_WEATHERS_FILE_NAME);
        System.out.println("\033[1;32m" + "\nExported all weathers to " + FILE_PATH + CHANGED_WEATHERS_FILE_NAME + "\033[0m");
    }

    /**
     * This is the main execution method for the application.
     * It is responsible for starting the application and managing its lifecycle.
     */
    public void run() {
        logger.info("Filling database tables with days and weathers using DayService and WeatherService");
        System.out.println("\033[1;32m" + "Filling database tables with days and weathers using DayService and WeatherService" + "\033[0m");
        createTestData();
        runDayServiceOperations();
        runWeatherServiceOperations();
    }

    /**
     * The main entry point for the application.
     * This method is responsible for initializing the necessary services and running the application.
     *
     * @param args the command-line arguments passed to the application. This parameter is not currently used.
     */
    public static void main(String[] args) {
        logger.info("Connecting to MySQL system database and creating database 'weather_db' with 2 tables:'days','weathers'...");
        System.out.println("\033[1;32m" + "Connecting to MySQL system database " +
                "and creating database 'weather_db' with 2 tables:'days','weathers'..." + "\033[0m");
        System.out.println("\033[1;33m" + "\nNote: If the database and tables exist, recreate them." + "\033[0m");
        DatabaseConfig.createDatabase();
        logger.info("Creating DAOManager...");
        System.out.println("\033[1;32m" + "Creating DAOManager..." + "\033[0m");
        DAOManager daoManager = new DAOManager(DatabaseConfig.getConnection());
        logger.info("Creating DayService and WeatherService...");
        System.out.println("\033[1;32m" + "Creating DayService and WeatherService..." + "\033[0m");
        DayService dayService = new DayService(daoManager);
        WeatherService weatherService = new WeatherService(daoManager);
        logger.info("Running DbConsoleApplication...");
        System.out.println("\033[1;32m" + "Running DbConsoleApplication..." + "\033[0m");
        new DbConsoleApplication(dayService, weatherService).run();
        logger.info("Closing database connection...");
        System.out.println("\033[1;32m" + "Closing database connection..." + "\033[0m");
        DatabaseConfig.closeConnection();
        logger.info("Done.");
        System.out.println("\033[1;32m" + "Done." + "\033[0m");
    }
}
