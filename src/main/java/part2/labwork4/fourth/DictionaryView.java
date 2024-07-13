package part2.labwork4.fourth;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class DictionaryView {
    private TextField englishInputField;
    private TextField ukrainianInputField;
    private TextArea resultArea;
    private Button findButton;
    private Button addButton;
    private Button showAllButton;
    Label englishInputLabel = new Label("English Word:");
    Label ukrainianInputLabel = new Label("Ukrainian Word:");
    Label resultLabel = new Label("Result:");
    public static final String ICON_PATH = "icon.png";
    public static final String ICON_ERROR_PATH = "icon_error.png";
    private DictionaryController controller;

    public void setController(DictionaryController controller) {
        this.controller = controller;
    }

    public String getEnglishInputFieldText() {
        return englishInputField.getText();
    }

    public String getUkrainianInputFieldText() {
        return ukrainianInputField.getText();
    }

    public void setResultAreaText(String text) {
        resultArea.setText(text);
    }

    public void showAlert(String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        ((Stage) alert.getDialogPane().getScene().getWindow())
                .getIcons().add(new Image(DictionaryApp.class
                        .getResourceAsStream(ICON_ERROR_PATH)));
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void createUI(Stage primaryStage) {
        primaryStage.setTitle("English-Ukrainian Dictionary");
        primaryStage.getIcons().add(new Image(getClass()
                .getResourceAsStream(ICON_PATH)));

        englishInputField = new TextField();
        ukrainianInputField = new TextField();
        resultArea = new TextArea();
        findButton = new Button("Find");
        addButton = new Button("Add");
        showAllButton = new Button("Show All");

        findButton.prefWidthProperty().bind(englishInputField.widthProperty());
        addButton.prefWidthProperty().bind(englishInputField.widthProperty());
        showAllButton.prefWidthProperty().bind(englishInputField.widthProperty());

        findButton.setOnAction(event -> controller.handleFindButtonAction());
        addButton.setOnAction(event -> controller.handleAddButtonAction());
        showAllButton.setOnAction(event -> controller.handleShowAllButtonAction());

        VBox vbox = new VBox(englishInputLabel, englishInputField, ukrainianInputLabel, ukrainianInputField,
                findButton, addButton, showAllButton, resultLabel, resultArea);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(vbox, 300, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
