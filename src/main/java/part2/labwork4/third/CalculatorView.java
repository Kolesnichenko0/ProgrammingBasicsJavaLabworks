package part2.labwork4.third;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorView {
    private TextField firstNumberField, secondNumberField, resultField;
    private RadioButton addButton, subtractButton, multiplyButton, divideButton;
    private Button calculateButton;
    private Label firstNumberLabel = new Label("First Number:");
    private Label secondNumberLabel = new Label("Second Number:");
    private Label resultLabel = new Label("Result:");

    private static final String ICON_PATH = "icon.png";
    private static final String ICON_ERROR_PATH = "icon_error.png";

    private CalculatorController controller;

    public void setController(CalculatorController controller) {
        this.controller = controller;
    }

    public String getFirstNumberFieldText() {
        return firstNumberField.getText();
    }

    public String getSecondNumberFieldText() {
        return secondNumberField.getText();
    }

    public boolean isAddButtonSelected() {
        return addButton.isSelected();
    }

    public boolean isSubtractButtonSelected() {
        return subtractButton.isSelected();
    }

    public boolean isMultiplyButtonSelected() {
        return multiplyButton.isSelected();
    }

    public boolean isDivideButtonSelected() {
        return divideButton.isSelected();
    }

    public void setResultFieldText(String text) {
        resultField.setText(text);
    }

    public void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(
                new Image(MiniCalculator.class
                        .getResourceAsStream(ICON_ERROR_PATH)));
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void createUI(Stage primaryStage) {
        primaryStage.setTitle("Mini Calculator");
        primaryStage.getIcons().add(new Image(getClass()
                .getResourceAsStream(ICON_PATH)));

        firstNumberField = new TextField();
        secondNumberField = new TextField();
        resultField = new TextField();

        addButton = new RadioButton("Add");
        subtractButton = new RadioButton("Subtract");
        multiplyButton = new RadioButton("Multiply");
        divideButton = new RadioButton("Divide");

        ToggleGroup radioGroup = new ToggleGroup();
        addButton.setToggleGroup(radioGroup);
        subtractButton.setToggleGroup(radioGroup);
        multiplyButton.setToggleGroup(radioGroup);
        divideButton.setToggleGroup(radioGroup);

        calculateButton = new Button("Calculate");
        calculateButton.setOnAction(controller::handleCalculateButtonAction);
        HBox calculateButtonHbox = new HBox(calculateButton);
        calculateButtonHbox.setAlignment(Pos.CENTER);

        VBox leftVBox = new VBox(addButton, subtractButton);
        leftVBox.setSpacing(10);

        VBox rightVBox = new VBox(multiplyButton, divideButton);
        rightVBox.setSpacing(10);

        HBox operationButtonsHbox = new HBox(leftVBox, rightVBox);
        operationButtonsHbox.setSpacing(10);
        operationButtonsHbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(firstNumberLabel, firstNumberField, secondNumberLabel,
                secondNumberField, operationButtonsHbox, calculateButtonHbox,
                resultLabel, resultField);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(vbox, 270, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
