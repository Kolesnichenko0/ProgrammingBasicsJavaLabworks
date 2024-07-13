package part2.labwork5.third;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import part2.labwork5.first.config.DatabaseConfig;
import part2.labwork5.first.dao.DAOManager;
import part2.labwork5.first.model.DayForDB;
import part2.labwork5.first.model.WeatherForDB;
import part2.labwork5.first.service.DayService;
import part2.labwork5.first.service.WeatherService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Controller for the Weathers view.
 * This class is responsible for handling user interactions when managing weathers and days.
 */
public class WeathersController implements Initializable {
    /**
     * Modal dialogue window for message
     *
     * @param message - message text
     */
    public static void showMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(message);
        ((Stage) alert.getDialogPane().getScene().getWindow())
                .getIcons().add(new Image(WeathersController.class
                        .getResourceAsStream(WeathersFX.ICON_PATH)));
        alert.showAndWait();
    }

    /**
     * Modal dialogue window for error
     *
     * @param message - message text
     */
    public static void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        ((Stage) alert.getDialogPane().getScene().getWindow())
                .getIcons().add(new Image(WeathersController.class
                        .getResourceAsStream(WeathersFX.ICON_ERROR_PATH)));
        alert.showAndWait();
    }
    /**
     * Displays the results of a search operation in the text area.
     * If the provided WeatherForDB object is null, it displays "No results found".
     *
     * @param weather the WeatherForDB object containing the search results.
     */
    private void showResults(WeatherForDB weather) {
        if (weather != null) {
            textAreaResults.setText(weather.toString());
        } else {
            textAreaResults.setText("No results found");
        }
    }
    /**
     * Displays the results of a search operation in the text area.
     * If the provided list of DayForDB objects is null or empty, it displays "No results found".
     *
     * @param days the list of DayForDB objects containing the search results.
     */
    private void showResults(List<DayForDB> days) {
        if (days != null && !days.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (DayForDB day : days) {
                sb.append(day).append("\n");
            }
            textAreaResults.setText(sb.toString());
        } else {
            textAreaResults.setText("No results found");
        }
    }
    /**
     * Closes the current window.
     *
     * @param event the action event that triggered the method call.
     */
    public static void closeCurrentWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    /**
     * Checks if the provided season string is valid.
     * A valid season is one of: "winter", "spring", "summer", "autumn".
     *
     * @param season the season string to check.
     * @return true if the season is valid, false otherwise.
     */
    public static boolean isValidSeason(String season) {
        List<String> validSeasons = Arrays.asList("winter", "spring", "summer", "autumn");
        if (!validSeasons.contains(season.toLowerCase())) {
            return false;
        }
        return true;
    }
    /**
     * Checks if the provided date string is valid.
     * A valid date is in the format: "YYYY-MM-DD".
     *
     * @param date the date string to check.
     * @return true if the date is valid, false otherwise.
     */
    public static boolean isValidDate(String date) {
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }
        return true;
    }

    /**
     * Create a modal file selection dialogue box
     *
     * @param title - the text of the window title
     */
    public static FileChooser getFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON-files (*.json)", "*.json"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        fileChooser.setTitle(title);
        return fileChooser;
    }

    public static final String INVALID_SEASON_ERROR = "Season must be one of: winter, spring, summer, autumn";
    public static final String INVALID_DATE_ERROR = "Date must be in format: YYYY-MM-DD";
    private DayService dayService;
    private WeatherService weatherService;
    private ObservableList<DayForDB> daysData = FXCollections.observableArrayList();
    private ObservableList<WeatherForDB> weathersData = FXCollections.observableArrayList();

    //Fields associated with visual elements
    @FXML
    private TextField textFieldSeason;
    @FXML
    private TextField textFieldDate;
    @FXML
    private TextField textFieldWordFragment;
    @FXML
    private TextArea textAreaResults;
    @FXML
    private TableView<DayForDB> daysTableView;
    @FXML
    private TableColumn<DayForDB, Long> daysTableColumnId;
    @FXML
    private TableColumn<DayForDB, Long> daysTableColumnWeatherId;
    @FXML
    private TableColumn<DayForDB, String> daysTableColumnDate;
    @FXML
    private TableColumn<DayForDB, Double> daysTableColumnTemperature;
    @FXML
    private TableColumn<DayForDB, String> daysTableColumnComment;
    @FXML
    private TableView<WeatherForDB> weathersTableView;
    @FXML
    private TableColumn<WeatherForDB, Long> weathersTableColumnId;
    @FXML
    private TableColumn<WeatherForDB, String> weathersTableColumnSeason;
    @FXML
    private TableColumn<WeatherForDB, String> weathersTableColumnComment;
    /**
     * Sets up the Days table.
     * This method initializes the Days table and sets the cell value factories for each column.
     */
    private void setupDaysTable() {
        daysTableView.setItems(daysData);
        daysTableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        daysTableColumnWeatherId.setCellValueFactory(new PropertyValueFactory<>("weatherId"));
        daysTableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        daysTableColumnTemperature.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        daysTableColumnComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
    }
    /**
     * Sets up the Weathers table.
     * This method initializes the Weathers table and sets the cell value factories for each column.
     */
    private void setupWeathersTable() {
        weathersTableView.setItems(weathersData);
        weathersTableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        weathersTableColumnSeason.setCellValueFactory(new PropertyValueFactory<>("season"));
        weathersTableColumnComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
    }
    /**
     * Updates the Days table with the provided list of DayForDB objects.
     * This method clears the existing data in the Days table and adds the new data.
     *
     * @param days the list of DayForDB objects to display in the Days table.
     */
    private void updateDaysTable(List<DayForDB> days) {
        WeathersFX.logger.info("Attempting to update the days table...");
        daysData.clear();
        daysData.addAll(days);
        WeathersFX.logger.info("Days table updated successfully");
    }
    /**
     * Updates the Weathers table with the provided list of WeatherForDB objects.
     * This method clears the existing data in the Weathers table and adds the new data.
     *
     * @param weathers the list of WeatherForDB objects to display in the Weathers table.
     */
    private void updateWeathersTable(List<WeatherForDB> weathers) {
        WeathersFX.logger.info("Attempting to update the weathers table...");
        weathersData.clear();
        weathersData.addAll(weathers);
        WeathersFX.logger.info("Weathers table updated successfully");
    }
    /**
     * Initializes the controller class.
     * This method is automatically called after the fxml file has been loaded.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WeathersFX.logger.info("Connecting to MySQL system database and creating database 'weather_db' with 2 tables:'days','weathers'...");
        DatabaseConfig.createDatabase();

        WeathersFX.logger.info("Creating DAOManager for WeathersController...");
        DAOManager daoManager = new DAOManager(DatabaseConfig.getConnection());

        WeathersFX.logger.info("Creating DayService and WeatherService for WeathersController...");
        dayService = new DayService(daoManager);
        weatherService = new WeatherService(daoManager);

        WeathersFX.logger.info("Setting up Days and Weathers tables...");
        setupDaysTable();
        setupWeathersTable();

        daysTableView.setPlaceholder(new Label(""));
        weathersTableView.setPlaceholder(new Label(""));
        WeathersFX.logger.info("WeathersController initialized successfully");
    }
    /**
     * Handles the New button click event.
     * This method is called when the user clicks the New button to start a new session.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doNew(ActionEvent event) {
        WeathersFX.logger.info("Starting a new session...");
        DatabaseConfig.createDatabase();
        daysData.clear();
        weathersData.clear();
        setupDaysTable();
        setupWeathersTable();
        daysTableView.setPlaceholder(new Label(""));
        weathersTableView.setPlaceholder(new Label(""));
        textFieldWordFragment.setText("");
        textFieldDate.setText("");
        textFieldSeason.setText("");
        textAreaResults.setText("");
        WeathersFX.logger.info("New session started successfully");
    }
    /**
     * Handles the Open menu item click event.
     * This method is called when the user clicks the Open menu item to open a file.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doOpen(ActionEvent event) {
        WeathersFX.logger.info("Attempting to open a file...");
        FileChooser fileChooser = getFileChooser("Open JSON-file");
        File file;
        if ((file = fileChooser.showOpenDialog(null)) != null) {
            try {
                String fileName = file.getCanonicalPath();
                weatherService.addAll(weatherService.importFromJSON(fileName));

                textFieldSeason.setText("");
                textFieldDate.setText("");
                textFieldWordFragment.setText("");
                textAreaResults.setText("");

                updateDaysTable(dayService.getAllDays());

                updateWeathersTable(weatherService.getAllWeathers().getList());

                WeathersFX.logger.info("File opened successfully");
                WeathersFX.logger.debug("Opened file: " + fileName);
            } catch (IOException e) {
                WeathersFX.logger.error("File not found");
                showError("File not found");
            } catch (RuntimeException e) {
                WeathersFX.logger.error("Failed to add data to the database");
                showError("Failed to add data to the database");
            } catch (Exception e) {
                WeathersFX.logger.error("Incorrect file format");
                showError("Incorrect file format");
            }
        }
    }
    /**
     * Handles the Save menu item click event.
     * This method is called when the user clicks the Save menu item to save a file.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doSave(ActionEvent event) {
        WeathersFX.logger.info("Attempting to save a file...");
        FileChooser fileChooser = getFileChooser("Save JSON-file");
        File file;
        if ((file = fileChooser.showSaveDialog(null)) != null) {
            try {
                String fileName = file.getCanonicalPath();
                weatherService.exportToJSON(fileName);
                showMessage("Results successfully saved");
                WeathersFX.logger.info("File saved successfully");
                WeathersFX.logger.debug("Saved file: " + fileName);
            } catch (Exception e) {
                WeathersFX.logger.error("Error writing to file");
                showError("Error writing to file");
            }
        }
    }
    /**
     * Handles the Exit menu item click event.
     * This method is called when the user clicks the Exit menu item to exit the application.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doExit(ActionEvent event) {
        WeathersFX.logger.info("Closing database connection...");
        DatabaseConfig.closeConnection();
        WeathersFX.logger.info("Done.");
        Platform.exit();
    }
    /**
     * Handles the About menu item click event.
     * This method is called when the user clicks the About menu item to display information about the application.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doAbout(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About " + WeathersFX.APP_TITLE);
        alert.setHeaderText("Data on weathers");
        alert.setContentText("Version 1.0");
        ((Stage) alert.getDialogPane().getScene().getWindow()).
                getIcons().add(new Image(getClass().
                        getResourceAsStream(WeathersFX.ICON_PATH)));
        alert.showAndWait();
    }
    /**
     * Handles the Add Day button click event.
     * This method is called when the user clicks the Add Day button to add a new day.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doAddDay(ActionEvent event) {
        WeathersFX.logger.info("Attempting to add a new day...");
        try {
            Stage stage = new Stage();
            stage.setTitle("New Day");
            stage.getIcons().add(new Image(WeathersController.class
                    .getResourceAsStream(WeathersFX.DAY_ICON_PATH)));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(daysTableView.getScene().getWindow());

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().
                    getResource("NewDayForm.fxml"));
            Scene scene = new Scene(root, 350, 220);
            stage.setScene(scene);
            stage.showAndWait();

            updateDaysTable(dayService.getAllDays());
            WeathersFX.logger.info("New day added successfully");
        } catch (IOException e) {
            WeathersFX.logger.error(e.toString());
            throw new RuntimeException(e);
        }
    }
    /**
     * Handles the Add Weather button click event.
     * This method is called when the user clicks the Add Weather button to add a new weather.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doAddWeather(ActionEvent event) {
        WeathersFX.logger.info("Attempting to add a new weather...");
        try {
            Stage stage = new Stage();
            stage.setTitle("New Weather");
            stage.getIcons().add(new Image(WeathersController.class
                    .getResourceAsStream(WeathersFX.ICON_PATH)));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(weathersTableView.getScene().getWindow());

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().
                    getResource("NewWeatherForm.fxml"));
            Scene scene = new Scene(root, 350, 140);
            stage.setScene(scene);
            stage.showAndWait();

            updateWeathersTable(weatherService.getAllWeathers().getList());
            WeathersFX.logger.info("New weather added successfully");
        } catch (IOException e) {
            WeathersFX.logger.error(e.toString());
            throw new RuntimeException(e);
        }
    }
    /**
     * Handles the Remove Weather and Days button click event.
     * This method is called when the user clicks the Remove Weather and Days button to remove a weather and its associated days.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doRemoveWeatherAndDays(ActionEvent event) {
        String season = textFieldSeason.getText().toLowerCase();

        if (!isValidSeason(season)) {
            showError(INVALID_SEASON_ERROR);
            return;
        }
        try {
            WeathersFX.logger.info("Attempting to remove weather and associated days for season: " + season);
            weatherService.removeWeatherAndDays(season);
            WeathersFX.logger.info("Weather and associated days removed successfully for season: " + season);
        } catch (RuntimeException e) {
            WeathersFX.logger.error("Incorrect weather");
            WeathersController.showError("Incorrect weather");
        }

        updateDaysTable(dayService.getAllDays());
        updateWeathersTable(weatherService.getAllWeathers().getList());
    }
    /**
     * Handles the Remove Day button click event.
     * This method is called when the user clicks the Remove Day button to remove a day.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doRemoveDay(ActionEvent event) {
        String season = textFieldSeason.getText().toLowerCase();
        String date = textFieldDate.getText();

        if (!isValidSeason(season)) {
            showError(INVALID_SEASON_ERROR);
            return;
        }

        if (!isValidDate(date)) {
            showError(INVALID_DATE_ERROR);
            return;
        }

        try {
            WeathersFX.logger.info("Attempting to remove day for season: " + season + " and date: " + date);
            dayService.removeDay(season, date);
            WeathersFX.logger.info("Day removed successfully for season: " + season + " and date: " + date);
        } catch (RuntimeException e) {
            WeathersFX.logger.error("Incorrect weather or date");
            WeathersController.showError("Incorrect weather or date");
        }

        updateDaysTable(dayService.getAllDays());
    }
    /**
     * Handles the Sort by Decreasing Temperature button click event.
     * This method is called when the user clicks the Sort by Decreasing Temperature button to sort the weather data by decreasing temperature.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doSortByDecreasingTemperature(ActionEvent event) {
        String season = textFieldSeason.getText().toLowerCase();
        if (!isValidSeason(season)) {
            showError(INVALID_SEASON_ERROR);
            return;
        }
        try {
            WeathersFX.logger.info("Attempting to sort by decreasing temperature for season: " + season);
            WeatherForDB weather = weatherService.getWeatherDataSortedByDecreasingTemperature(season);
            showResults(weather);
            WeathersFX.logger.info("Sorted by decreasing temperature successfully for season: " + season);
        } catch (RuntimeException e) {
            WeathersFX.logger.error("Incorrect weather");
            WeathersController.showError("Incorrect weather");
        }
    }
    /**
     * Handles the Sort by Comment button click event.
     * This method is called when the user clicks the Sort by Comment button to sort the weather data by comment.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doSortByComment(ActionEvent event) {
        String season = textFieldSeason.getText().toLowerCase();
        if (!isValidSeason(season)) {
            showError(INVALID_SEASON_ERROR);
            return;
        }

        try {
            WeathersFX.logger.info("Attempting to sort by comment for season: " + season);
            WeatherForDB weather = weatherService.getWeatherDataSortedByComment(season);
            showResults(weather);
            WeathersFX.logger.info("Sorted by comment successfully for season: " + season);
        } catch (RuntimeException e) {
            WeathersFX.logger.error("Incorrect weather");
            WeathersController.showError("Incorrect weather");
        }
    }

    /**
     * Handles the Search by Word Fragment button click event.
     * This method is called when the user clicks the Search by Word Fragment button to find days with a specific word fragment.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doSearchByWordFragment(ActionEvent event) {
        String wordFragment = textFieldWordFragment.getText().toLowerCase();
        try {
            WeathersFX.logger.info("Attempting to search by word fragment: " + wordFragment);
            List<DayForDB> days = dayService.findDaysWithWordFragment(wordFragment);
            showResults(days);
            WeathersFX.logger.info("Search by word fragment successfully for word fragment: " + wordFragment);
        } catch (RuntimeException e) {
            WeathersFX.logger.error("Incorrect weather");
            WeathersController.showError("Incorrect weather");
        }

    }
    /**
     * Handles the Search by Word Fragment in Specific Weather button click event.
     * This method is called when the user clicks the Search by Word Fragment in Specific Weather button to find days with a specific word fragment in a specific season.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doSearchByWordFragmentInSpecificWeather(ActionEvent event) {
        String wordFragment = textFieldWordFragment.getText().toLowerCase();
        String season = textFieldSeason.getText().toLowerCase();
        if (!isValidSeason(season)) {
            showError(INVALID_SEASON_ERROR);
            return;
        }
        try {
            WeathersFX.logger.info("Attempting to search by word fragment: " + wordFragment + " in season: " + season);
            List<DayForDB> days = dayService.findDaysWithWordFragment(wordFragment, season);
            showResults(days);
            WeathersFX.logger.info("Search by word fragment successfully for word fragment: " + wordFragment + " in season: " + season);
        } catch (RuntimeException e) {
            WeathersFX.logger.error("Incorrect weather");
            WeathersController.showError("Incorrect weather");
        }

    }
    /**
     * Handles the Search by Maximum Temperature button click event.
     * This method is called when the user clicks the Search by Maximum Temperature button to find days with the maximum temperature.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doSearchByMaximumTemperature(ActionEvent event) {
        try {
            WeathersFX.logger.info("Attempting to search by maximum temperature");
            List<DayForDB> days = dayService.findMaxTemperatureDays();
            showResults(days);
            WeathersFX.logger.info("Search by maximum temperature successfully");
        } catch (RuntimeException e) {
            WeathersFX.logger.error("Incorrect weather");
            WeathersController.showError("Incorrect weather");
        }
    }
    /**
     * Handles the Search by Maximum Temperature in Specific Weather button click event.
     * This method is called when the user clicks the Search by Maximum Temperature in Specific Weather button to find days with the maximum temperature in a specific season.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doSearchByMaximumTemperatureInSpecificWeather(ActionEvent event) {
        String season = textFieldSeason.getText().toLowerCase();
        if (!isValidSeason(season)) {
            showError(INVALID_SEASON_ERROR);
            return;
        }
        try {
            WeathersFX.logger.info("Attempting to search by maximum temperature in season: " + season);
            List<DayForDB> days = dayService.findMaxTemperatureDays(season);
            showResults(days);
            WeathersFX.logger.info("Search by maximum temperature successfully in season: " + season);
        } catch (RuntimeException e) {
            WeathersFX.logger.error("Incorrect weather");
            WeathersController.showError("Incorrect weather");
        }
    }
    /**
     * Handles the Search by Longest Comment button click event.
     * This method is called when the user clicks the Search by Longest Comment button to find days with the longest comment.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doSearchByLongestComment(ActionEvent event) {
        try {
            WeathersFX.logger.info("Attempting to search by longest comment");
            List<DayForDB> days = dayService.findLongestCommentDays();
            showResults(days);
            WeathersFX.logger.info("Search by longest comment successfully");
        } catch (RuntimeException e) {
            WeathersFX.logger.error("Incorrect weather");
            WeathersController.showError("Incorrect weather");
        }
    }
    /**
     * Handles the Search by Longest Comment in Specific Weather button click event.
     * This method is called when the user clicks the Search by Longest Comment in Specific Weather button to find days with the longest comment in a specific season.
     *
     * @param event The event that has occurred. If this event is not consumed here, it will be passed on to the node's parent.
     */
    @FXML
    private void doSearchByLongestCommentInSpecificWeather(ActionEvent event) {
        String season = textFieldSeason.getText().toLowerCase();
        if (!isValidSeason(season)) {
            showError(INVALID_SEASON_ERROR);
            return;
        }
        try {
            WeathersFX.logger.info("Attempting to search by longest comment in season: " + season);
            List<DayForDB> days = dayService.findLongestCommentDays(season);
            showResults(days);
            WeathersFX.logger.info("Search by longest comment successfully in season: " + season);
        } catch (RuntimeException e) {
            WeathersFX.logger.error("Incorrect weather");
            WeathersController.showError("Incorrect weather");
        }
    }
}
