package part2.labwork5.third;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import part2.labwork5.first.config.DatabaseConfig;
import part2.labwork5.first.dao.DAOManager;
import part2.labwork5.first.model.DayForDB;
import part2.labwork5.first.service.DayService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the NewDay view.
 * This class is responsible for handling user interactions when adding a new day.
 */
public class NewDayController implements Initializable {
    private DayService dayService;
    @FXML
    private TextField textFieldSeason;
    @FXML
    private TextField textFieldDate;
    @FXML
    private TextField textFieldTemperature;
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
        dayService = new DayService(daoManager);

        WeathersFX.logger.info("NewDayController initialized successfully");
    }

    /**
     * Handles the OK button click event.
     * This method is called when the user clicks the OK button to add a new day.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doOK(ActionEvent event) {
        String season = textFieldSeason.getText().toLowerCase();
        String date = textFieldDate.getText();
        String temperatureText = textFieldTemperature.getText();
        String comment = textFieldComment.getText();

        if (season.isEmpty() || date.isEmpty() || temperatureText.isEmpty() || comment.isEmpty()) {
            WeathersFX.logger.warn("Attempted to add a day with one or more empty fields");
            WeathersController.showError("All fields must be filled!");
            return;
        }

        if (!WeathersController.isValidSeason(season)) {
            WeathersFX.logger.warn("Attempted to add a day with invalid season: " + season);
            WeathersController.showError(WeathersController.INVALID_SEASON_ERROR);
            return;
        }

        if (!WeathersController.isValidDate(date)) {
            WeathersFX.logger.warn("Attempted to add a day with invalid date: " + date);
            WeathersController.showError(WeathersController.INVALID_DATE_ERROR);
            return;
        }

        double temperature;
        try {
            temperature = Double.parseDouble(temperatureText);
        } catch (NumberFormatException e) {
            WeathersFX.logger.error("Attempted to add a day with non-numeric temperature: " + temperatureText, e);
            WeathersController.showError("Temperature must be a number");
            return;
        }
        try {
            dayService.addDay(season, new DayForDB(date, temperature, comment));
            WeathersFX.logger.info("Day added successfully: " + date + ", " + temperature + ", " + comment);
            WeathersController.showMessage("Day added: " + date + ", " + temperature + ", " + comment);
            WeathersController.closeCurrentWindow(event);
        } catch (Exception e) {
            WeathersFX.logger.error("Failed to add data to the database");
            WeathersController.showError("Failed to add data to the database");
        }
    }

    /**
     * Handles the Cancel button click event.
     * This method is called when the user clicks the Cancel button to cancel the addition of a new day.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doCancel(ActionEvent event) {
        WeathersFX.logger.info("User cancelled the addition of a new day");
        WeathersController.closeCurrentWindow(event);
    }
}
