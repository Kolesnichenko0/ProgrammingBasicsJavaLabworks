package part2.labwork2.first;

import java.util.Locale;

/**
 * The {@code WeatherWithLocalizationProgram} class represents showing of the functionality
 * of the {@link WeatherWithLocalization}.
 */
public class WeatherWithLocalizationProgram {
    /**
     * Carries out showing of the functionality of the {@link WeatherWithLocalization}.
     * {@code args} are not used.
     *
     * <p>
     * In this class, ASCII escape sequences are used to format console output. Specifically,
     * the ASCII escape sequence "\033[1;32m" is used to make the following text bold and green,
     * and the sequence "\033[0m" is used to reset the text formatting.
     * </p>
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        System.out.println("\033[1;32m" + "With US locale:" + "\033[0m");
        new WeatherWithLocalization().createWeather().showFunctionality();
        Locale.setDefault(new Locale("uk", "UA"));
        System.out.println("\n\n\n\033[1;32m" + "With UA locale:" + "\033[0m");
        new WeatherWithLocalization().createWeather().showFunctionality();
    }
}
