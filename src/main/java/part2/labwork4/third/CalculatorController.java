package part2.labwork4.third;

import javafx.event.ActionEvent;

public class CalculatorController {
    private CalculatorView view;
    private CalculatorModel model;

    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;
    }

    public void handleCalculateButtonAction(ActionEvent event) {
        String firstNumberText = view.getFirstNumberFieldText();
        String secondNumberText = view.getSecondNumberFieldText();
        if (firstNumberText.isEmpty() || secondNumberText.isEmpty()) {
            view.showAlert("Input Error",
                    "Please enter numbers in both fields.");
        } else if (!view.isAddButtonSelected() && !view.isSubtractButtonSelected() && !view.isMultiplyButtonSelected() && !view.isDivideButtonSelected()) {
            view.showAlert("Operation Error",
                    "Please select an operation.");
        } else {
            try {
                double firstNumber = Double.parseDouble(firstNumberText);
                double secondNumber = Double.parseDouble(secondNumberText);
                String operator = "";

                if (view.isAddButtonSelected()) {
                    operator = "Add";
                } else if (view.isSubtractButtonSelected()) {
                    operator = "Subtract";
                } else if (view.isMultiplyButtonSelected()) {
                    operator = "Multiply";
                } else if (view.isDivideButtonSelected()) {
                    operator = "Divide";
                }

                double result = model.calculate(firstNumber, secondNumber, operator);
                view.setResultFieldText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                view.showAlert("Input Error",
                        "Please enter valid numbers without text in both fields.");
            } catch (IllegalArgumentException ex) {
                view.showAlert("Calculation Error", ex.getMessage());
            }
        }
    }
}
