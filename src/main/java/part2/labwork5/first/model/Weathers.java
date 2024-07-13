package part2.labwork5.first.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Weathers class is used to represent a collection of WeatherForDB objects.
 * It provides methods for adding and retrieving weather data.
 */
public class Weathers {
    private List<WeatherForDB> list = new ArrayList<>();

    public List<WeatherForDB> getList() {
        return list;
    }

    /**
     * Retrieves the number of WeatherForDB objects in the list.
     *
     * @return the number of WeatherForDB objects in the list.
     */
    public int weathersCount() {
        return list.size();
    }

    /**
     * This method is used to clear all weathers from the list.
     */
    public void clearWeathers() {
        list.clear();
    }

    /**
     * Adds a WeatherForDB object to the list.
     *
     * @param weather the WeatherForDB object to be added.
     */
    public void addWeather(WeatherForDB weather) {
        list.add(weather);
    }

    /**
     * This method is used to remove a specific weather from the list of weathers.
     *
     * @param weather The weather to be removed.
     */
    public boolean removeWeather(WeatherForDB weather) {
        return list.remove(weather);
    }

    /**
     * Returns a string representation of the Weathers object.
     *
     * @return a string representation of the Weathers object.
     */
    @Override
    public String toString() {
        return "Weathers:\n" + list.stream()
                .map(WeatherForDB::toString)
                .collect(Collectors.joining("\n"));
    }
}
