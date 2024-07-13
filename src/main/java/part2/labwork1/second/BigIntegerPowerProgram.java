package part2.labwork1.second;

import part2.labwork1.first.WeatherWithStreams;

import java.math.BigInteger;
import java.util.Scanner;

import static part2.labwork1.second.BigIntegerPower.*;

/**
 * The {@code BigIntegerPowerProgram} class represents showing of the functionality
 * of the {@link WeatherWithStreams}.
 * It generates a random BigInteger, asks the user for an exponent,
 * raises the BigInteger to the power of the exponent using two different methods,
 * and compares the results and the time taken by each method.
 */
public class BigIntegerPowerProgram {
    /**
     * Carries out showing of the functionality of the {@link BigIntegerPower}.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        BigInteger base = generateRandomBigInteger();
        System.out.println("The random generated number with " + NUM_DIGITS + " digits to be raised to a power is:\n" + base);
        System.out.print("Please enter the exponent: ");
        int exponent = new Scanner(System.in).nextInt();

        System.out.println("\nResults:");
        try {
            ResultAndDurationPair resultUsingPow = calculateAndMeasureTime(base, exponent, BigIntegerPower::powerUsingPowFunction);
            System.out.println("Result using pow function: " + formatBigInteger(resultUsingPow.getResult()));
            System.out.println("Time taken using pow function: " + resultUsingPow.getDuration() + " nanoseconds");

            ResultAndDurationPair resultUsingMultiplication = calculateAndMeasureTime(base, exponent, BigIntegerPower::powerUsingMultiplication);
            System.out.println("Result using multiplication: " + formatBigInteger(resultUsingMultiplication.getResult()));
            System.out.println("Time taken using multiplication: " + resultUsingMultiplication.getDuration() + " nanoseconds");

            System.out.println("\nConclusions:");
            printComparingResults(resultUsingPow.getResult(), resultUsingMultiplication.getResult());
            printComparingDurations(resultUsingPow.getDuration(), resultUsingMultiplication.getDuration());
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        }
    }
}
