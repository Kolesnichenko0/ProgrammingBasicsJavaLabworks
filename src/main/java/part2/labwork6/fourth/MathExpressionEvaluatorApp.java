package part2.labwork6.fourth;

import java.util.Scanner;

public class MathExpressionEvaluatorApp implements AutoCloseable {

    private final MathExpressionEvaluator evaluator;

    public MathExpressionEvaluatorApp() {
        this.evaluator = new MathExpressionEvaluator();
    }

    public void run() {
        System.out.println("Welcome! This is a console application " +
                "for evaluating mathematical expressions.");
        System.out.println("Type 'exit' to quit.\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a mathematical expression: ");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input.trim())) {
                break;
            }

            evaluator.evaluateExpression(input);
        }

        System.out.println("Thank you for using the application!");
    }

    @Override
    public void close() {
        evaluator.close();
    }

    public static void main(String[] args) {
        try (MathExpressionEvaluatorApp app = new MathExpressionEvaluatorApp()) {
            app.run();
        }
    }
}