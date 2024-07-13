package part1.labwork3.first;

/**
 * The {@code Main} class represents realisation of the main task.
 */
public class Main {
    /**
     * Finds the average temperature, the days with the maximum temperature,
     * the days with the longest comment and prints all
     * Sorts by decreasing temperature and comments alphabetically
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        WeatherWithArray weather = new WeatherWithArray();
        weather.testSearch();
        weather.testSorting();
    }
}
