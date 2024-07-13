package part2.labwork4.third;

import javafx.application.Application;
import javafx.stage.Stage;

public class MiniCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        CalculatorView view = new CalculatorView();
        CalculatorModel model = new CalculatorModel();
        CalculatorController controller = new CalculatorController(view, model);

        view.setController(controller);
        view.createUI(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
