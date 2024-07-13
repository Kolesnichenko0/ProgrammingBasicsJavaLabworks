package part2.labwork1.fourth;

import java.util.stream.IntStream;

/**
 * The {@code DivisorFinder} class provides a method for finding
 * all positive divisors of a number.
 * <p>
 * The {@link #findAllPositiveNumberDivisors(int)} method takes a positive integer as input and
 * returns an array of all its positive divisors.
 */
public class DivisorFinder {
    /**
     * Finds all positive divisors of a number.
     * It uses the Java 8 Stream API to generate a range of numbers from 1 to the input number (inclusive),
     * and then filters this stream to include only those numbers
     * that divide the input number without a remainder.
     * The resulting stream is then converted to an array.
     *
     * @param number the number to find the divisors of, must be positive
     * @return an array of all positive divisors of the number
     * @throws IllegalArgumentException if the number is not positive
     */
    public static int[] findAllPositiveNumberDivisors(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        return IntStream.range(1, number + 1)
                .filter(i -> number % i == 0)
                .toArray();
    }
}
