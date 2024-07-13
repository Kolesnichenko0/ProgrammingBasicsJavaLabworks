package part2.labwork5.first.service;

import part2.labwork5.first.dao.DAOManager;
import part2.labwork5.first.dao.WeatherDAO;
import part2.labwork5.first.model.WeatherForDB;
import part2.labwork5.first.model.Weathers;
import part2.labwork5.first.util.JsonUtil;
import part2.labwork5.first.util.SortOrder;

/**
 * The WeatherService class provides services related to the WeatherForDB model.
 * It uses the DAOManager to interact with the database and perform CRUD operations on the 'weathers' table.
 * This class is part of the Service layer in the application architecture, which acts as a bridge between the Controller layer and the DAO layer.
 */
public class WeatherService {
    private WeatherDAO weatherDAO;

    /**
     * Constructs a new WeatherService object.
     *
     * @param daoManager the DAOManager object that provides access to the DAO layer.
     */
    public WeatherService(DAOManager daoManager) {
        this.weatherDAO = daoManager.getWeatherDAO();
    }

    /**
     * Imports weather data from a JSON file.
     *
     * @param fileName the name of the JSON file from which to import the data.
     * @return a Weathers object containing the imported weather data.
     */
    public Weathers importFromJSON(String fileName) {
        return JsonUtil.importFromJSON(fileName);
    }

    /**
     * Exports weather data to a JSON file.
     *
     * @param weathers the Weathers object containing the weather data to be exported.
     * @param fileName the name of the JSON file to which the data will be exported.
     */
    public void exportToJSON(Weathers weathers, String fileName) {
        JsonUtil.exportToJSON(weathers, fileName);
    }

    /**
     * Exports all weather data from the database to a JSON file.
     *
     * @param fileName the name of the JSON file to which the data will be exported.
     */
    public void exportToJSON(String fileName) {
        Weathers weathers = weatherDAO.getWeathersFromDB();
        JsonUtil.exportToJSON(weathers, fileName);
    }

    /**
     * This method is used to add a weather to the database.
     *
     * @param weather The WeatherForDB object to be added.
     */
    public void addWeather(WeatherForDB weather) {
        weatherDAO.addWeather(weather);
    }

    /**
     * Adds all WeatherForDB objects from a Weathers object to the database.
     *
     * @param weathers the Weathers object containing the WeatherForDB objects to be added.
     */
    public void addAll(Weathers weathers) {
        weatherDAO.addAll(weathers);
    }

    /**
     * Retrieves a WeatherForDB object from the database based on the season and sort order.
     *
     * @param weatherSeason the season of the weather.
     * @param sortOrder     the order in which the days should be sorted.
     * @return the WeatherForDB object representing the weather data for the specified season.
     */
    public WeatherForDB getWeatherData(String weatherSeason, SortOrder sortOrder) {
        return weatherDAO.getWeatherData(weatherSeason, sortOrder);
    }

    /**
     * Retrieves all WeatherForDB objects from the database.
     *
     * @return a Weathers object containing all the WeatherForDB objects.
     */
    public Weathers getAllWeathers() {
        return weatherDAO.getAllWeathers();
    }

    /**
     * Retrieves a WeatherForDB object from the database based on the season, with the days sorted by decreasing temperature.
     *
     * @param weatherSeason the season of the weather.
     * @return the WeatherForDB object representing the weather data for the specified season.
     */
    public WeatherForDB getWeatherDataSortedByDecreasingTemperature(String weatherSeason) {
        return weatherDAO.getWeatherDataSortedByDecreasingTemperature(weatherSeason);
    }

    /**
     * Retrieves a WeatherForDB object from the database based on the season, with the days sorted by comment.
     *
     * @param weatherSeason the season of the weather.
     * @return the WeatherForDB object representing the weather data for the specified season.
     */
    public WeatherForDB getWeatherDataSortedByComment(String weatherSeason) {
        return weatherDAO.getWeatherDataSortedByComment(weatherSeason);
    }

    /**
     * Removes a WeatherForDB object and its associated days from the database based on the season.
     *
     * @param weatherSeason the season of the weather to be removed.
     */
    public void removeWeatherAndDays(String weatherSeason) {
        weatherDAO.removeWeatherAndDays(weatherSeason);
    }
}
