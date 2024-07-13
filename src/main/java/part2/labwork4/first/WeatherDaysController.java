package part2.labwork4.first;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.event.ActionEvent;

import part1.labwork3.first.Day;
import part2.labwork1.first.WeatherWithStreams;
import part2.labwork3.first.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The {@code WeatherDaysController} class is the controller for the WeatherDaysFX application.
 * It manages the user interface events and interacts with the {@code WeatherWithStreams} model.
 * It implements the {@code Initializable} interface,
 * which means it performs certain actions upon initialization.
 * <p>
 * This class provides methods to handle various user actions
 * such as adding a new day, removing the last row,
 * sorting by decreasing temperature, sorting by comment,
 * searching by word, word fragment, max temperature, and longest comment.
 * It also provides methods to update the source data,
 * update the table, and update the button states.
 * <p>
 * The class uses JavaFX annotations to link the controller
 * to the user interface elements defined in the FXML file.
 */
public class WeatherDaysController implements Initializable {

    /** Reference to the class model */
    private WeatherWithStreams weather = new WeatherWithStreams();

    /** A list whose contents will be displayed in the table */
    private ObservableList<Day> observableList;

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
                .getIcons().add(new Image(WeatherDaysController.class
                        .getResourceAsStream(WeatherDaysFX.ICON_PATH)));
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
                .getIcons().add(new Image(WeatherDaysController.class
                        .getResourceAsStream(WeatherDaysFX.ICON_ERROR_PATH)));
        alert.showAndWait();
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
                new FileChooser.ExtensionFilter("XML-files (*.xml)", "*.xml"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
        fileChooser.setTitle(title);
        return fileChooser;
    }

    /**
     * Updates the state of the search buttons based on the state of the observable list.
     * If the observable list is null or empty, the search buttons are disabled.
     * Otherwise, the search buttons are enabled.
     */
    private void updateButtonStates() {
        boolean isListEmptyOrNull = observableList == null || observableList.isEmpty();
        doSearchByWordButton.setDisable(isListEmptyOrNull);
        doSearchByWordFragmentButton.setDisable(isListEmptyOrNull);
        doSearchByMaxTemperatureButton.setDisable(isListEmptyOrNull);
        doSearchByLongestCommentButton.setDisable(isListEmptyOrNull);
    }

    /**
     * This method displays the search results in the text area.
     * It takes an array of Day objects as input and appends each
     * Day object's string representation to the text area.
     *
     * @param day An array of Day objects that represent the search results.
     */
    private void showResults(Day[] day) {
        if (day != null) {
            for (Day d : day) {
                textAreaResults.appendText(d.toString() + "\n\n");
            }
        }
    }

    /**
     * This method updates the source data by creating
     * a new WeatherWithStreams object and populating it with the data from the observable list.
     * It is used to ensure that the weather data in
     * the model is in sync with the data displayed in the table view.
     */
    private void updateSourceData() {
        weather = new WeatherWithStreams();
        if (observableList != null) {
            for (Day d : observableList) {
                weather.addDay(d);
            }
        }
        WeatherDaysFX.logger.info("Source data updated successfully");
    }


    /**
     * Handles the event triggered when the date cell in the table view is edited.
     * This method updates the date of the corresponding Day object in the weather data.
     * It retrieves the Day object from the table view, saves the old date value for logging,
     * and then sets the new date value entered by the user.
     *
     * @param t The CellEditEvent triggered by editing the date cell in the table view.
     */
    private void updateDate(CellEditEvent<Day, String> t) {
        Day d = t.getTableView().getItems().get(t.getTablePosition().getRow());
        String oldDate = d.getDate();
        d.setDate(t.getNewValue());
        WeatherDaysFX.logger.info("Day date updated successfully");
        WeatherDaysFX.logger.debug("Day date changed from: " +
                oldDate + " to: " + d.getDate());
    }

    /**
     * Handles the event triggered when the temperature cell in the table view is edited.
     * This method updates the temperature of the corresponding Day object in the weather data.
     * It retrieves the Day object from the table view, saves the old temperature value for logging,
     * and then sets the new temperature value entered by the user.
     *
     * @param t The CellEditEvent triggered by editing the temperature cell in the table view.
     */
    private void updateTemperature(CellEditEvent<Day, Double> t) {
        Day d = t.getTableView().getItems().get(t.getTablePosition().getRow());
        double oldTemperature = d.getTemperature();
        d.setTemperature(t.getNewValue());
        WeatherDaysFX.logger.info("Day temperature updated successfully");
        WeatherDaysFX.logger.debug("Day temperature changed from: " +
                oldTemperature + " to: " + d.getTemperature());
    }

    /**
     * Handles the event triggered when the comment cell in the table view is edited.
     * This method updates the comment of the corresponding Day object in the weather data.
     * It retrieves the Day object from the table view, saves the old comment value for logging,
     * and then sets the new comment value entered by the user.
     *
     * @param t The CellEditEvent triggered by editing the comment cell in the table view.
     */
    private void updateComment(CellEditEvent<Day, String> t) {
        Day d = t.getTableView().getItems().get(t.getTablePosition().getRow());
        String oldComment = d.getComment();
        d.setComment(t.getNewValue());
        WeatherDaysFX.logger.info("Day comment updated successfully");
        WeatherDaysFX.logger.debug("Day comment changed from: " +
                oldComment + " to: " + d.getComment());
    }

    /**
     * This method updates the table view with the current weather data.
     * It first creates a new list and observable list to hold the Day objects.
     * Then, it populates the list with the Day objects from the weather data.
     * After that, it sets the items of the table view to the observable list.
     * It also sets the cell value factories for the date, temperature,
     * and comment columns,
     * and sets the cell factories to TextFieldTableCells for editing.
     * Finally, it sets the on edit commit event handlers for the date,
     * temperature, and comment columns,
     * and updates the button states.
     */
    private void updateTable() {
        WeatherDaysFX.logger.info("Attempting to update the table...");
        List<Day> list = new ArrayList<>();
        observableList = FXCollections.observableList(list);
        for (int i = 0; i < weather.daysCount(); i++) {
            list.add(weather.getDay(i));
        }
        tableViewDays.setItems(observableList);

        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnDate.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnDate.setOnEditCommit(this::updateDate);

        tableColumnTemperature.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        tableColumnTemperature.setCellFactory(
                TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        tableColumnTemperature.setOnEditCommit(this::updateTemperature);

        tableColumnComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        tableColumnComment.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnComment.setOnEditCommit(this::updateComment);
        updateButtonStates();
        WeatherDaysFX.logger.info("Table updated successfully");
    }

    //Fields associated with visual elements
    @FXML
    private TextField textFieldSeason;
    @FXML
    private TextField textFieldComment;
    @FXML
    private TextField textFieldText;
    @FXML
    private TextArea textAreaResults;
    @FXML
    private TableView<Day> tableViewDays;
    @FXML
    private TableColumn<Day, String> tableColumnDate;
    @FXML
    private TableColumn<Day, Double> tableColumnTemperature;
    @FXML
    private TableColumn<Day, String> tableColumnComment;
    @FXML
    private Button doSearchByWordButton;
    @FXML
    private Button doSearchByWordFragmentButton;
    @FXML
    private Button doSearchByMaxTemperatureButton;
    @FXML
    private Button doSearchByLongestCommentButton;

    /**
     * Initializes the controller after its root element has been completely processed.
     * This method is called after the FXML file has been loaded and the JavaFX objects have been created.
     * It sets the placeholder for the tableViewDays,
     * sets the logger for FileUtils, and updates the button states.
     *
     * @param location  The location used to resolve relative paths
     *                  for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object,
     *                  or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewDays.setPlaceholder(new Label(""));
        FileUtils.setLogger(WeatherDaysFX.logger);
        updateButtonStates();
    }

    /**
     * Handles the event triggered when the "New" button is clicked.
     * This method resets the application to its initial state by creating a new session.
     * It clears the text fields, text area, and the table view.
     * It also updates the button states.
     *
     * @param event The action event triggered by clicking the "New" button.
     */
    @FXML
    private void doNew(ActionEvent event) {
        WeatherDaysFX.logger.info("Starting a new session...");
        weather = new WeatherWithStreams();
        observableList = FXCollections.observableArrayList();
        textFieldSeason.setText("");
        textFieldComment.setText("");
        textFieldText.setText("");
        textAreaResults.setText("");
        tableViewDays.setItems(observableList);
        tableViewDays.setPlaceholder(new Label(""));
        updateButtonStates();
        WeatherDaysFX.logger.info("New session started successfully");
    }

    /**
     * Handles the event triggered when the "Open" button is clicked.
     * This method opens a file chooser dialog for the user to select an XML file.
     * If a file is selected, it attempts to deserialize the file into a {@code WeatherWithStreams} object.
     * If successful, it updates the text fields, text area, and table view with the data from the file.
     * If an error occurs during this process, it displays an error message.
     *
     * @param event The action event triggered by clicking the "Open" button.
     */
    @FXML
    private void doOpen(ActionEvent event) {
        WeatherDaysFX.logger.info("Attempting to open a file...");
        FileChooser fileChooser = getFileChooser("Open XML-file");
        File file;
        if ((file = fileChooser.showOpenDialog(null)) != null) {
            try {
                String fileName = file.getCanonicalPath();
                weather = FileUtils.deserializeFromXML(fileName);

                textFieldSeason.setText(weather.getSeason());
                textFieldComment.setText(weather.getComment());
                textAreaResults.setText("");

                tableViewDays.setItems(null);
                updateTable();
                WeatherDaysFX.logger.info("File opened successfully");
                WeatherDaysFX.logger.debug("Opened file: " + fileName);
            } catch (IOException e) {
                WeatherDaysFX.logger.error("File not found");
                showError("File not found");
            } catch (Exception e) {
                WeatherDaysFX.logger.error("Incorrect file format");
                showError("Incorrect file format");
            }
        }
    }

    /**
     * Handles the event triggered when the "Save" button is clicked.
     * This method opens a file chooser dialog for the user to select a location to save an XML file.
     * If a location is selected, it attempts to serialize
     * the {@code WeatherWithStreams} object into the file.
     * If successful, it displays a success message.
     * If an error occurs during this process, it displays an error message.
     *
     * @param event The action event triggered by clicking the "Save" button.
     */
    @FXML
    private void doSave(ActionEvent event) {
        WeatherDaysFX.logger.info("Attempting to save a file...");
        FileChooser fileChooser = getFileChooser("Save XML-file");
        File file;
        if ((file = fileChooser.showSaveDialog(null)) != null) {
            try {
                updateSourceData();
                seasonChanged(event);
                commentChanged(event);
                String fileName = file.getCanonicalPath();
                FileUtils.serializeToXML(weather, fileName);
                showMessage("Results successfully saved");
                WeatherDaysFX.logger.info("File saved successfully");
                WeatherDaysFX.logger.debug("Saved file: " + fileName);
            } catch (Exception e) {
                WeatherDaysFX.logger.error("Error writing to file");
                showError("Error writing to file");
            }
        }
    }

    /**
     * Handles the event triggered when the "Exit" button is clicked.
     * This method closes the application.
     *
     * @param event The action event triggered by clicking the "Exit" button.
     */
    @FXML
    private void doExit(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Handles the event triggered when the "Add" button is clicked.
     * This method attempts to add a new day to the weather data.
     * If successful, it updates the table view with the new day.
     * If an error occurs during this process, it logs the error.
     *
     * @param event The action event triggered by clicking the "Add" button.
     */
    @FXML
    private void doAdd(ActionEvent event) {
        WeatherDaysFX.logger.info("Attempting to add a new day...");
        updateSourceData();
        if (weather.addDay("", 0.0, "")) {
            Day addedDay = weather.getDay(weather.daysCount() - 1);
            WeatherDaysFX.logger.info("New day(row) added successfully");
            WeatherDaysFX.logger.debug("Added day: " + addedDay);
            updateTable();
        } else {
            WeatherDaysFX.logger.info("Failed to add a new day(row)");
        }
    }

    /**
     * Handles the event triggered when the "Remove Last Row" button is clicked.
     * This method attempts to remove the last row from the weather data.
     * If successful, it updates the table view by removing the last day.
     * If an error occurs during this process, it logs the error.
     *
     * @param event The action event triggered by clicking the "Remove Last Row" button.
     */
    @FXML
    private void doRemoveLastRow(ActionEvent event) {
        WeatherDaysFX.logger.info("Attempting to remove the last row...");
        if (observableList == null) {
            WeatherDaysFX.logger.info("No rows to remove");
            return;
        }
        if (!observableList.isEmpty()) {
            Day removedDay = observableList.get(observableList.size() - 1);
            observableList.remove(observableList.size() - 1);
            WeatherDaysFX.logger.info("Last row removed successfully");
            WeatherDaysFX.logger.debug("Removed day: " + removedDay);
        }
        if (observableList.isEmpty()) {
            observableList = null;
        }
        updateButtonStates();
    }

    /**
     * Handles the event triggered when the "Sort By Decreasing Temperature" button is clicked.
     * This method sorts the weather data by decreasing temperature.
     * If successful, it updates the table view with the sorted data.
     *
     * @param event The action event triggered by clicking the "Sort By Decreasing Temperature" button.
     */
    @FXML
    private void doSortByDecreasingTemperature(ActionEvent event) {
        WeatherDaysFX.logger.info("Attempting to sort by decreasing temperature...");
        updateSourceData();
        weather.sortByDecreasingTemperature();
        updateTable();
        WeatherDaysFX.logger.info("Sorted by decreasing temperature successfully");
        WeatherDaysFX.logger.debug("Sorted weather: " + weather);
    }

    /**
     * Handles the event triggered when the "Sort By Comment" button is clicked.
     * This method sorts the weather data by comment.
     * If successful, it updates the table view with the sorted data.
     *
     * @param event The action event triggered by clicking the "Sort By Comment" button.
     */
    @FXML
    private void doSortByComment(ActionEvent event) {
        WeatherDaysFX.logger.info("Attempting to sort by comment...");
        updateSourceData();
        weather.sortByComment();
        updateTable();
        WeatherDaysFX.logger.info("Sorted by comment successfully");
        WeatherDaysFX.logger.debug("Sorted weather: " + weather);
    }

    /**
     * Handles the event triggered when the "About" button is clicked.
     * This method displays an information dialog with details about the application.
     *
     * @param event The action event triggered by clicking the "About" button.
     */
    @FXML
    private void doAbout(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About " + WeatherDaysFX.APP_TITLE);
        alert.setHeaderText("Data on weather days");
        alert.setContentText("Version 1.0");
        ((Stage) alert.getDialogPane().getScene().getWindow()).
                getIcons().add(new Image(getClass().
                        getResourceAsStream(WeatherDaysFX.ICON_PATH)));
        alert.showAndWait();
    }

    /**
     * Handles the event triggered when the season text field is changed.
     * This method updates the season in the weather data.
     *
     * @param event The action event triggered by changing the season text field.
     */
    @FXML
    private void seasonChanged(ActionEvent event) {
        String oldSeason = weather.getSeason();
        weather.setSeason(textFieldSeason.getText());
        WeatherDaysFX.logger.info("Season changed successfully");
        WeatherDaysFX.logger.debug("Season changed from: " +
                oldSeason + " to: " + weather.getSeason());
    }

    /**
     * Handles the event triggered when the comment text field is changed.
     * This method updates the comment in the weather data.
     *
     * @param event The action event triggered by changing the comment text field.
     */
    @FXML
    private void commentChanged(ActionEvent event) {
        String oldComment = weather.getComment();
        weather.setComment(textFieldComment.getText());
        WeatherDaysFX.logger.info("Comment changed successfully");
        WeatherDaysFX.logger.debug("Season changed from: " +
                oldComment + " to: " + weather.getComment());
    }

    /**
     * Handles the event triggered when the "Search By Word" button is clicked.
     * This method searches the weather data for days with comments containing a specific word.
     * If successful, it updates the text area with the search results.
     *
     * @param event The action event triggered by clicking the "Search By Word" button.
     */
    @FXML
    private void doSearchByWord(ActionEvent event) {
        WeatherDaysFX.logger.info("Attempting to search by word...");
        updateSourceData();
        textAreaResults.setText("");
        String searchWord = textFieldText.getText();
        Day[] foundDays = weather.findDaysWithCommentWord(searchWord);
        showResults(foundDays);
        WeatherDaysFX.logger.info("Search by word completed successfully");
        WeatherDaysFX.logger.debug("Searched word: " + searchWord + ". Found days: ");
        for (Day day : foundDays) {
            WeatherDaysFX.logger.debug(day.toString());
        }
    }

    /**
     * Handles the event triggered when the "Search By Word Fragment" button is clicked.
     * This method searches the weather data for days with comments containing a specific word fragment.
     * If successful, it updates the text area with the search results.
     *
     * @param event The action event triggered by clicking
     *              the "Search By Word Fragment" button.
     */
    @FXML
    private void doSearchByWordFragment(ActionEvent event) {
        WeatherDaysFX.logger.info("Attempting to search by word fragment...");
        updateSourceData();
        textAreaResults.setText("");
        String searchWordFragment = textFieldText.getText();
        Day[] foundDays = weather.findDaysWithCommentWordFragment(searchWordFragment);
        showResults(foundDays);
        WeatherDaysFX.logger.info("Search by word fragment completed successfully");
        WeatherDaysFX.logger.debug("Searched word fragment: " + searchWordFragment + ". Found days: ");
        for (Day day : foundDays) {
            WeatherDaysFX.logger.debug(day.toString());
        }
    }

    /**
     * Handles the event triggered when the "Search By Max Temperature" button is clicked.
     * This method searches the weather data for days with the maximum temperature.
     * If successful, it updates the text area with the search results.
     *
     * @param event The action event triggered by clicking
     *              the "Search By Max Temperature" button.
     */
    @FXML
    private void doSearchByMaxTemperature(ActionEvent event) {
        WeatherDaysFX.logger.info("Attempting to search by max temperature...");
        updateSourceData();
        textAreaResults.setText("");
        Day[] foundDays = weather.findMaxTemperatureDays();
        showResults(foundDays);
        WeatherDaysFX.logger.info("Search by max temperature completed successfully");
        WeatherDaysFX.logger.debug("Found days with max temperature: ");
        for (Day day : foundDays) {
            WeatherDaysFX.logger.debug(day.toString());
        }
    }

    /**
     * Handles the event triggered when the "Search By Longest Comment" button is clicked.
     * This method searches the weather data for days with the longest comment.
     * If successful, it updates the text area with the search results.
     *
     * @param event The action event triggered by clicking
     *              the "Search By Longest Comment" button.
     */
    @FXML
    private void doSearchByLongestComment(ActionEvent event) {
        WeatherDaysFX.logger.info("Attempting to search by longest comment...");
        updateSourceData();
        textAreaResults.setText("");
        Day[] foundDays = weather.findLongestCommentDays();
        showResults(foundDays);
        WeatherDaysFX.logger.info("Search by longest comment completed successfully");
        WeatherDaysFX.logger.debug("Found days with longest comment: ");
        for (Day day : foundDays) {
            WeatherDaysFX.logger.debug(day.toString());
        }
    }
}
