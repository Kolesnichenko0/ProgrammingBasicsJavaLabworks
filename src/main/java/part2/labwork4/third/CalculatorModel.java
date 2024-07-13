package part2.labwork4.third;

public class CalculatorModel {
    public double calculate(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "Add":
                return firstNumber + secondNumber;
            case "Subtract":
                return firstNumber - secondNumber;
            case "Multiply":
                return firstNumber * secondNumber;
            case "Divide":
                if (secondNumber != 0) {
                    return firstNumber / secondNumber;
                } else {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}
