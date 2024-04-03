package part1.labwork1.third;

import java.util.Scanner;

/**
 * A class represents a console program for calculating the value of a certain function
 * The program defines the value of n (from 1) and x. Then the program calculates
 * the result of the expression and displays it on the console.
 * The value of n and x are entered from the keyboard. The value of n is checked for validity.
 * The label is used to move to the next iteration(continue) of the outer loop and to exit both loops(break)
 */
public class SomeFunction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of x:");
        double x = scanner.nextDouble();

        System.out.println("Enter the value of n (from 1):");
        int n = scanner.nextInt();
        scanner.close();
        if (n < 1) {
            System.err.println("Invalid value of n");
            return;
        }
        double result = 1.0;
        boolean errorFlag = false;
        loopLabel:
        for (int i = 1; i <= n - 1; i++) {
            double sum = 0.0;
            for (int j = 0; j <= n; j++) {
                double denominator = j + x;
                if (denominator == 0) {
                    errorFlag = true;
                    break loopLabel;
                } else if (denominator == i)
                    continue loopLabel;
                sum += i / denominator;
            }
            result *= sum;
        }
        if (!errorFlag)
            System.out.printf("Result:%ny = %.4f", result);
        else
            System.err.println("Division by 0");
    }
}
