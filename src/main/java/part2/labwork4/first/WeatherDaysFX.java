package part2.labwork4.first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The WeatherDaysFX class is the main entry point for the Weather Days application.
 * It extends the JavaFX Application class and overrides
 * the start method to set up the primary stage.
 * The class also includes a main method that launches the application.
 * <p>
 * The application displays weather data for different days,
 * allowing the user to edit the date, temperature, and comment for each day.
 * The WeatherDaysFX class uses a logger to log information and error messages.
 */
public class WeatherDaysFX extends Application {

    public static final String APP_TITLE = "Weather days";
    public static final String ICON_PATH = "icon.png";
    public static final String ICON_ERROR_PATH = "icon_error.png";
    public static Logger logger;

    /**
     * The start method is the main entry point for any JavaFX application.
     * It is called after the init method has returned, and after the system
     * is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     *                     The primary stage will be embedded in the browser
     *                     if the application was launched from a web page.
     */
    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting application...");
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().
                    getResource("WeatherDaysForm.fxml"));
            Scene scene = new Scene(root, 700, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle(APP_TITLE);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(ICON_PATH)));
            primaryStage.show();
        } catch (Exception e) {
            if (logger != null) {
                logger.error("Error during application start", e);
            }
            e.printStackTrace();
        }
        logger.info("Application started successfully");
    }

    /**
     * The main method is the entry point for the Java application.
     * It initializes the logger and then launches the JavaFX application.
     * After the JavaFX application has exited, it logs that the application has closed.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger = LogManager.getLogger(WeatherDaysFX.class);
        launch(args);
        logger.info("Application closed");
    }
}
