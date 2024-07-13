package part2.labwork6.second;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import part2.labwork6.second.model.PrimeFactorCalculator;
import part2.labwork6.second.model.PrimeFactors;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimeFactorController implements Initializable {
    @FXML
    private TextField textFieldFrom;
    @FXML
    private TextField textFieldTo;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonSuspend;
    @FXML
    private Button buttonResume;
    @FXML
    private Button buttonStop;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TextArea textAreaResults;

    private PrimeFactorCalculator primeFactorCalculator;

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        ((Stage) alert.getDialogPane().getScene().getWindow())
                .getIcons().add(new Image(PrimeFactorController.class
                        .getResourceAsStream(PrimeFactorFinderApp.ICON_ERROR_PATH)));
        alert.showAndWait();
    }

    private Integer parseInput(String input, String fieldName) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showError("Invalid input: '" + fieldName + "' field must be a integer number.");
            return null;
        }
    }

    private int[] validateAndParseInput(String fromText, String toText) {
        if (fromText.isEmpty() || toText.isEmpty()) {
            showError("Invalid input: both fields must be filled");
            return null;
        }

        Integer from = parseInput(fromText, "From");
        if (from == null) {
            return null;
        }

        Integer to = parseInput(toText, "To");
        if (to == null) {
            return null;
        }

        return new int[]{from, to};
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonStart.setDisable(false);
        buttonSuspend.setDisable(true);
        buttonResume.setDisable(true);
        buttonStop.setDisable(true);

        primeFactorCalculator = new PrimeFactorCalculator(
                this::addToTextArea,
                this::setProgress,
                this::finish);
    }

    @FXML
    private void startClicked() {
        String fromText = textFieldFrom.getText();
        String toText = textFieldTo.getText();

        int[] inputs = validateAndParseInput(fromText, toText);
        if (inputs == null) {
            return;
        }

        int from = inputs[0];
        int to = inputs[1];

        try {
            PrimeFactors primeFactors = new PrimeFactors(from, to);
            primeFactorCalculator.setPrimeFactors(primeFactors);
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
            return;
        }

        textAreaResults.setText("");
        progressBar.setProgress(0);

        buttonStart.setDisable(true);
        buttonSuspend.setDisable(false);
        buttonResume.setDisable(true);
        buttonStop.setDisable(false);

        primeFactorCalculator.start();
    }

    @FXML
    private void suspendClicked(ActionEvent actionEvent) {
        primeFactorCalculator.suspend();

        buttonStart.setDisable(true);
        buttonSuspend.setDisable(true);
        buttonResume.setDisable(false);
        buttonStop.setDisable(false);
    }

    @FXML
    private void resumeClicked(ActionEvent actionEvent) {
        primeFactorCalculator.resume();

        buttonStart.setDisable(true);
        buttonSuspend.setDisable(false);
        buttonResume.setDisable(true);
        buttonStop.setDisable(false);
    }

    @FXML
    private void stopClicked(ActionEvent actionEvent) {
        primeFactorCalculator.stop();
    }

    private void addToTextArea() {
        PrimeFactors primeFactors = primeFactorCalculator.getPrimeFactors();
        Integer lastFound = primeFactors.getLastFound();
        String formattedPrimeFactors = primeFactors.getFormattedPrimeFactors(lastFound);

        textAreaResults.appendText(formattedPrimeFactors + "\n");
    }

    private void setProgress() {
        progressBar.setProgress(primeFactorCalculator.getPercentage());
    }

    private void finish() {
        buttonStart.setDisable(false);
        buttonSuspend.setDisable(true);
        buttonResume.setDisable(true);
        buttonStop.setDisable(true);
    }
}
