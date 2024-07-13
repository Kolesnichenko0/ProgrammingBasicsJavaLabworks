package part2.labwork1.fourth;

import java.util.Arrays;

/**
 * The {@code DivisorFinderProgram} class is the main entry point
 * for the Divisor Finder application.
 * <p>
 * This class demonstrates the usage of the {@link DivisorFinder#findAllPositiveNumberDivisors(int)} method
 * by finding all positive divisors of a valid number
 * and handling an exception for an invalid number.
 * <p>
 * The {@link #main(String[])} method first prints all positive divisors of a valid number.
 * It then attempts to find divisors of an invalid number,
 * catching and printing the resulting IllegalArgumentException.
 */
public class DivisorFinderProgram {
    /**
     * A valid number for which to find all positive divisors.
     */
    public static final int VALID_NUMBER = 34;
    /**
     * An invalid number for which the DivisorFinder
     * should throw an IllegalArgumentException.
     */
    public static final int INVALID_NUMBER = -5;

    /**
     * Carries out showing of the functionality of the {@link DivisorFinder}.
     * {@code args} are not used.
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Divisors of " + VALID_NUMBER + ":");
        int[] result = DivisorFinder.findAllPositiveNumberDivisors(VALID_NUMBER);
        System.out.println(Arrays.toString(result));

        try {
            System.out.println("\nTrying to find divisors of " + INVALID_NUMBER + ":");
            DivisorFinder.findAllPositiveNumberDivisors(INVALID_NUMBER);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
