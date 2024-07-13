package part1.labwork1.individual;

import java.util.Scanner;

/**
 * The class represents a console program for calculating
 * the values of a function at n = 8 on a certain interval.
 * The program defines the values of the beginning
 * of the interval, the end of the interval and the step size
 * with which the argument is changed. Values are entered from the keyboard.
 */
public class IndividualFunction {
    /**
     * Function calculates the value of individual function at a given argument value
     *
     * @param x argument value
     * @return individual function value
     */
    static double calculateY(double x) {
        final int n = 8;
        double y;
        if (x <= 8) {
            y = Math.sqrt(9 - x) + 2 * x;
        } else {
            y = 34;
            for (int i = 1; i <= n; i++)
                y *= Math.sin(x / 4 - i);
            y += 17;
        }
        return y;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the start of the interval:");
        double intervalStart = scanner.nextDouble();
        System.out.println("Enter the end of the interval:");
        double intervalEnd = scanner.nextDouble();
        if (intervalStart > intervalEnd) {
            System.err.println("Invalid data, because the start of the interval must be less than the end");
            return;
        }

        System.out.println("Enter step size with which the argument changes:");
        double step = scanner.nextDouble();
        scanner.close();
        if (step <= 0) {
            System.err.println("Invalid data, because the step size must be greater than 0");
            return;
        }

        for (double x = intervalStart; x <= intervalEnd; x += step) {
            double y = calculateY(x);
            System.out.printf("x = %.2f, y = %.2f%n", x, y);
        }
    }
}