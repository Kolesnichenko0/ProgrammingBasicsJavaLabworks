package part1.labwork1.second;

import java.util.Scanner;

/**
 * The class represents a console program for calculating the powers of 8.
 * The program defines the value of n (from 0 to 10) and prints the powers of 8 up to and including n.
 * The value of n is entered from the keyboard. The value is checked for validity.
 * Two approaches have been implemented - using arithmetic and bitwise operations.
 */
public class PowersOfEight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of n (from 0 to 10):");
        int n = scanner.nextInt();
        scanner.close();
        if (n < 0 || n > 10) {
            System.err.println("Invalid value of n");
            return;
        }
        System.out.println("Powers from 8 to n, calculated using arithmetic operations:");
        int degreeBase = 8;
        int result = 1;
        for (int i = 0; i <= n; i++) {
            System.out.printf("%d ^ %d = %d%n", degreeBase, i, result);
            result *= degreeBase;
        }
        System.out.println("Powers from 8 to n, calculated using bitwise operations:");
        for (int i = 0; i <= n; i++) {
            System.out.printf("%d ^ %d = %d%n", degreeBase, i, 1 << 3 * i);
        }
    }
}
