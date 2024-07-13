package part2.labwork5.third;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import part2.labwork5.first.config.DatabaseConfig;
import part2.labwork5.first.dao.DAOManager;
import part2.labwork5.first.model.WeatherForDB;
import part2.labwork5.first.service.WeatherService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the NewWeather view.
 * This class is responsible for handling user interactions when adding a new weather.
 */
public class NewWeatherController implements Initializable {
    private WeatherService weatherService;
    @FXML
    private TextField textFieldSeason;
    @FXML
    private TextField textFieldComment;

    /**
     * Initializes the controller class.
     * This method is automatically called after the fxml file has been loaded.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WeathersFX.logger = LogManager.getLogger(WeathersFX.class);

        WeathersFX.logger.info("Creating DAOManager for NewWeatherController...");
        DAOManager daoManager = new DAOManager(DatabaseConfig.getConnection());

        WeathersFX.logger.info("Creating DayService and WeatherService for NewWeatherController...");
        weatherService = new WeatherService(daoManager);

        WeathersFX.logger.info("NewWeatherController initialized successfully");
    }

    /**
     * Handles the OK button click event.
     * This method is called when the user clicks the OK button to add a new weather.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doOK(ActionEvent event) {
        String season = textFieldSeason.getText().toLowerCase();
        String comment = textFieldComment.getText();

        if (season.isEmpty() || comment.isEmpty()) {
            WeathersFX.logger.warn("Attempted to add a weather with one or more empty fields");
            WeathersController.showError("All fields must be filled!");
            return;
        }

        if (!WeathersController.isValidSeason(season)) {
            WeathersFX.logger.warn("Attempted to add a weather with invalid season: " + season);
            WeathersController.showError(WeathersController.INVALID_SEASON_ERROR);
            return;
        }
        try {
            weatherService.addWeather(new WeatherForDB(season, comment));
            WeathersFX.logger.info("Weather added successfully: " + season + ", " + comment);
            WeathersController.showMessage("Weather added: " + season + ", " + comment);
            WeathersController.closeCurrentWindow(event);
        } catch (Exception e) {
            WeathersFX.logger.error("Failed to add weather to the database: " + season + ", " + comment, e);
            WeathersController.showError("Failed to add data to the database");
        }
    }

    /**
     * Handles the Cancel button click event.
     * This method is called when the user clicks the Cancel button to cancel the addition of a new weather.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doCancel(ActionEvent event) {
        WeathersFX.logger.info("User cancelled the addition of a new weather");
        WeathersController.closeCurrentWindow(event);
    }
}
