package part2.labwork1.second;

import java.math.BigInteger;
import java.util.Random;
import java.util.function.BiFunction;

/**
 * This class provides methods for working with BigInteger numbers,
 * including generating a random BigInteger, raising a BigInteger to a power,
 * and comparing the results and durations of different methods of raising a BigInteger to a power.
 */
public class BigIntegerPower {
    /** The number of digits of our number. */
    public static final int NUM_DIGITS = 30;
    /** The maximum number of digits to be displayed in the console. */
    public static final int MAX_DISPLAY_DIGITS = 4;

    /**
     * Generates a random BigInteger with a '{@value NUM_DIGITS}' of digits.
     *
     * @return a random BigInteger with '{@value NUM_DIGITS}' digits.
     */
    public static BigInteger generateRandomBigInteger() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(NUM_DIGITS);
        sb.append(random.nextInt(9) + 1);
        for (int i = 1; i < NUM_DIGITS; i++) {
            sb.append(random.nextInt(10));
        }
        return new BigInteger(sb.toString());
    }

    /**
     * Raises a BigInteger to a power using the pow function {@link BigInteger#pow(int)}.
     *
     * @param base     the base BigInteger.
     * @param exponent the exponent to raise the base to.
     * @return the result of raising the base to the exponent.
     */
    public static BigInteger powerUsingPowFunction(BigInteger base, int exponent) {
        return base.pow(exponent);
    }

    /**
     * Raises a BigInteger to a power using method {@link BigInteger#multiply(BigInteger)}.
     *
     * @param base     the base BigInteger.
     * @param exponent the exponent to raise the base to.
     * @return the result of raising the base to the exponent.
     * @throws ArithmeticException {@code exponent} is negative.  (This would
     *                             cause the operation to yield a non-integer value.)
     */
    public static BigInteger powerUsingMultiplication(BigInteger base, int exponent) {
        if (exponent < 0) {
            throw new ArithmeticException("Negative exponent");
        }
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < exponent; i++) {
            result = result.multiply(base);
        }
        return result;
    }

    /**
     * A class to hold a result and the duration it took to compute that result.
     */
    public static class ResultAndDurationPair {
        private final BigInteger result;
        private final long duration;

        public ResultAndDurationPair(BigInteger result, long duration) {
            this.result = result;
            this.duration = duration;
        }

        public BigInteger getResult() {
            return result;
        }

        public long getDuration() {
            return duration;
        }
    }

    /**
     * Calculates the result of a function and measures the time it took to compute the result.
     *
     * @param base     the base BigInteger.
     * @param exponent the exponent to raise the base to.
     * @param function {@code BiFunction} - the function to apply to the base and exponent.
     * @return a ResultAndDurationPair containing the result and the duration it took to compute the result.
     */
    public static ResultAndDurationPair calculateAndMeasureTime(BigInteger base,
                                                                int exponent,
                                                                BiFunction<BigInteger, Integer, BigInteger> function) {
        long startTime = System.nanoTime();
        BigInteger result = function.apply(base, exponent);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        return new ResultAndDurationPair(result, duration);
    }

    /**
     * Formats a BigInteger for display, truncating it if it has more than '{@value MAX_DISPLAY_DIGITS}' digits.
     *
     * @param number the BigInteger to format.
     * @return a string representation of the BigInteger, truncated if necessary.
     */
    public static String formatBigInteger(BigInteger number) {
        String str = number.toString();
        if (str.length() > MAX_DISPLAY_DIGITS) {
            str = str.substring(0, MAX_DISPLAY_DIGITS) + "...";
        }
        return str;
    }

    /**
     * Compares two BigIntegers and prints a message indicating whether they are equal.
     *
     * @param resultUsingPow            the first BigInteger to compare.
     * @param resultUsingMultiplication the second BigInteger to compare.
     */
    public static void printComparingResults(BigInteger resultUsingPow,
                                             BigInteger resultUsingMultiplication) {
        if (resultUsingPow.equals(resultUsingMultiplication)) {
            System.out.println("The results are equal.");
        } else {
            System.out.println("The results are not equal.");
        }
    }

    /**
     * Compares two durations and prints a message indicating which is shorter.
     *
     * @param durationUsingPow            the first duration to compare.
     * @param durationUsingMultiplication the second duration to compare.
     */
    public static void printComparingDurations(long durationUsingPow,
                                               long durationUsingMultiplication) {
        if (durationUsingPow < durationUsingMultiplication) {
            System.out.println("Pow function is faster.");
        } else if (durationUsingPow > durationUsingMultiplication) {
            System.out.println("Multiplication is faster.");
        } else {
            System.out.println("Both methods took the same amount of time.");
        }
    }

}
