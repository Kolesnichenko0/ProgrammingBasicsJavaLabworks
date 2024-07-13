package part2.labwork6.first;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import part2.labwork6.first.model.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class FunctionPlotterController implements Initializable {
    @FXML
    private TextField textFieldA;
    @FXML
    private TextField textFieldB;
    @FXML
    private TextField textFieldFunctionF;
    @FXML
    private TextField textFieldFunctionG;
    @FXML
    private TextField textFieldFrom;
    @FXML
    private TextField textFieldTo;
    @FXML
    private TextField textFieldStep;
    @FXML
    private LineChart<Number, Number> graphChart;
    @FXML
    private XYChart.Series<Number, Number> hSeries;
    private FunctionPlotter functionPlotter;
    private final FunctionDataFactory functionDataFactory = new FunctionDataFactory();

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        ((Stage) alert.getDialogPane().getScene().getWindow())
                .getIcons().add(new Image(FunctionPlotterApp.class
                        .getResourceAsStream(FunctionPlotterApp.ICON_ERROR_PATH)));
        alert.showAndWait();
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ((Stage) alert.getDialogPane().getScene().getWindow())
                .getIcons().add(new Image(FunctionPlotterApp.class
                        .getResourceAsStream(FunctionPlotterApp.ICON_ERROR_PATH)));
        alert.setTitle("Error");
        alert.setHeaderText(functionPlotter.getErrorMessage());
        alert.showAndWait();
    }

    private void displayOnGraph() {
        graphChart.setCreateSymbols(functionPlotter.getPoints().size() == 1);
        for (Map.Entry<Double, Double> entry : functionPlotter.getPoints().entrySet()) {
            hSeries.getData().add(
                    new XYChart.Data<>(
                            entry.getKey(),
                            entry.getValue()));
        }
    }

    private double parseFunctionParameter(String fieldText, String fieldName) {
        try {
            return Double.parseDouble(fieldText);
        } catch (NumberFormatException e) {
            throw new RuntimeException(fieldName + " must be a valid number");
        }
    }

    private void validateFieldNotEmpty(String fieldText, String fieldName) {
        if (fieldText.isEmpty()) {
            throw new RuntimeException(fieldName + " field must be filled");
        }
    }

    public String getFullExpression(double a, double b, String functionF, String functionG) {
        String functionG_bx = functionG.replaceAll("x", "(" + b + " * x)");
        String expression = "h(x) = " + a + " * (" + functionF + ") - (" + functionG_bx + ")";
        return expression.replace("*", "∙");
    }

    private void deleteDirectoryContents(Path path) throws IOException {
        if (Files.exists(path)) {
            try (Stream<Path> paths = Files.walk(path)) {
                paths.filter(Files::isRegularFile)
                        .forEach(p -> {
                            try {
                                Files.delete(p);
                            } catch (IOException e) {
                                throw new RuntimeException("Failed to delete file: " + e.getMessage());
                            }
                        });
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            deleteDirectoryContents(Paths.get(FunctionCodeCompiler.GENERATED_PATH));
            deleteDirectoryContents(Paths.get(FunctionCodeCompiler.COMPILED_PATH));
        } catch (Exception e) {
            showError("Couldn't delete directory contents: " + e.getMessage());
        }
        functionPlotter = new FunctionPlotter(
                this::showError,
                this::displayOnGraph);
        graphChart.setTitle("Graph of our function h(x)");
        graphChart.getXAxis().setLabel("x");
        graphChart.getYAxis().setLabel("y");
    }

    @FXML
    private void plotGraphClicked(ActionEvent event) {
        try {
            String aText = textFieldA.getText();
            validateFieldNotEmpty(aText, "Parameter a");
            String bText = textFieldB.getText();
            validateFieldNotEmpty(bText, "Parameter b");

            String functionF = textFieldFunctionF.getText();
            validateFieldNotEmpty(functionF, "Function f(x)");
            String functionG = textFieldFunctionG.getText();
            validateFieldNotEmpty(functionG, "Function g(x)");

            String fromText = textFieldFrom.getText();
            validateFieldNotEmpty(fromText, "Range from");
            String toText = textFieldTo.getText();
            validateFieldNotEmpty(toText, "Range to");
            String stepText = textFieldStep.getText();
            validateFieldNotEmpty(stepText, "Range step");

            double a = parseFunctionParameter(aText, "Parameter a");
            double b = parseFunctionParameter(bText, "Parameter b");

            double from = parseFunctionParameter(fromText, "Range from");
            double to = parseFunctionParameter(toText, "Range to");
            double step = parseFunctionParameter(stepText, "Range step");

            FunctionData functionData = functionDataFactory.getFunctionData(functionF, functionG);
            functionPlotter.setFunctionData(functionData);

            FunctionParameters functionParameters = new FunctionParameters(a, b, from, to, step);
            functionPlotter.setFunctionParameters(functionParameters);

            hSeries = new XYChart.Series<>();
            hSeries.setName(getFullExpression(functionParameters.getA(), functionParameters.getB(),
                    functionData.getFunctionF(), functionData.getFunctionG()));
            graphChart.getData().setAll(hSeries);

            functionPlotter.start();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    private void clearGraphClicked(ActionEvent event) {
        if (hSeries != null) {
            hSeries.getData().clear();
        }
        graphChart.getData().clear();
    }

    @FXML
    private void clearFieldsClicked(ActionEvent event) {
        textFieldA.clear();
        textFieldB.clear();

        textFieldFunctionF.clear();
        textFieldFunctionG.clear();

        textFieldFrom.clear();
        textFieldTo.clear();
        textFieldStep.clear();
    }
}
