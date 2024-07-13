package part2.labwork5.third;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class for the WeathersFX application.
 * This class is responsible for starting the application and setting up the primary stage.
 */
public class WeathersFX extends Application {
    /**
     * Logger for logging application events.
     */
    public static Logger logger;
    /**
     * Constants for application title and icon paths.
     */
    public static final String APP_TITLE = "Weathers";
    public static final String ICON_PATH = "icon.png";
    public static final String DAY_ICON_PATH = "day_icon.png";
    public static final String ICON_ERROR_PATH = "icon_error.png";

    /**
     * Starts the application.
     * This method is called after the init() method has returned, and after the system is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting application...");
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass()
                    .getResource("WeathersForm.fxml"));
            Scene scene = new Scene(root, 1220, 760);
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
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned, and after the system is ready for the application to begin running.
     *
     * @param args the command line arguments passed to the application. An application may get these parameters and either ignore or process them.
     */
    public static void main(String[] args) {
        logger = LogManager.getLogger(WeathersFX.class);
        launch(args);
        logger.info("Application closed");
    }
}
