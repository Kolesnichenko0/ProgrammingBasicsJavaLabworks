package part2.labwork1.second;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The {@code BigIntegerPowerTest} class provides unit tests for the {@link BigIntegerPower} class.
 * It tests the generation of random BigIntegers, the calculation of powers using two different methods,
 * and the formatting of BigIntegers for display.
 */
class BigIntegerPowerTest {
    /**
     * A constant representing the base BigInteger used in the tests.
     */
    private static final BigInteger BASE =
            new BigInteger("141649035980713671023139756527");

    /**
     * A constant representing the fourth power of {@code BASE}.
     */
    private static final BigInteger FOURTH_POWER_BASE =
            new BigInteger("40258212949504183936133506542112496493053792713202188"
                    + "2916738339062967684956617311379510385980910713393362335610789441");
    ;

//    private static Stream<Arguments> provideDataForPowerCalculationTests() {
//        return Stream.of(
//                Arguments.of(-1, null),
//                Arguments.of(0, BigInteger.ONE),
//                Arguments.of(4, FOURTH_POWER_BASE)
//        );
//    }

    /**
     * Provides data for the power calculation tests.
     *
     * @return a stream of arguments for the power calculation tests.
     */
    private static Stream<Arguments> provideDataForPowerCalculationTests() {
        BiFunction<BigInteger, Integer, BigInteger> powFunction = BigIntegerPower::powerUsingPowFunction;
        BiFunction<BigInteger, Integer, BigInteger> multiplicationFunction = BigIntegerPower::powerUsingMultiplication;
        return Stream.of(
                Arguments.of(powFunction, -1, null),
                Arguments.of(powFunction, 0, BigInteger.ONE),
                Arguments.of(powFunction, 4, FOURTH_POWER_BASE),
                Arguments.of(multiplicationFunction, -1, null),
                Arguments.of(multiplicationFunction, 0, BigInteger.ONE),
                Arguments.of(multiplicationFunction, 4, FOURTH_POWER_BASE)
        );
    }

    /**
     * Tests the {@link BigIntegerPower#generateRandomBigInteger()} method.
     * Checks if the generated BigInteger has the expected number of digits.
     */
    @Test
    @DisplayName("Should generate BigInteger with the expected number of digits")
    void generateRandomBigInteger() {
        BigInteger result = BigIntegerPower.generateRandomBigInteger();
        int expectedNumDigits = BigIntegerPower.NUM_DIGITS;
        int actualNumDigits = result.toString().length();

        assertEquals(expectedNumDigits, actualNumDigits);
    }


//    @ParameterizedTest
//    @MethodSource("provideDataForPowerCalculationTests")
//    void powerUsingPowFunction(int exponent, BigInteger expected) {
//        if (exponent < 0) {
//            assertThrows(ArithmeticException.class,
//                    () -> BigIntegerPower.powerUsingPowFunction(BASE, exponent));
//        } else {
//            BigInteger result = BigIntegerPower.powerUsingPowFunction(BASE, exponent);
//            assertEquals(expected, result);
//        }
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideDataForPowerCalculationTests")
//    void powerUsingMultiplication(int exponent, BigInteger expected) {
//        if (exponent < 0) {
//            assertThrows(ArithmeticException.class,
//                    () -> BigIntegerPower.powerUsingMultiplication(BASE, exponent));
//        } else {
//            BigInteger result = BigIntegerPower.powerUsingMultiplication(BASE, exponent);
//            assertEquals(expected, result);
//        }
//    }

    /**
     * Tests the calculation of powers using two different methods.
     */
    @ParameterizedTest(name = "Test {index}: exponent = {1}, expected = {2}")
    @DisplayName("Should calculate power. First 3 tests using function powerUsingPowFunction, second 3 tests - powerUsingMultiplication")
    @MethodSource("provideDataForPowerCalculationTests")
    void testPowerCalculation(BiFunction<BigInteger, Integer, BigInteger> function,
                              int exponent, BigInteger expected) {
        if (exponent < 0) {
            assertThrows(ArithmeticException.class,
                    () -> function.apply(BASE, exponent));
        } else {
            BigInteger result = function.apply(BASE, exponent);
            assertEquals(expected, result);
        }
    }

    /**
     * Tests the {@link BigIntegerPower#formatBigInteger(BigInteger)} method.
     */
    @Test
    @DisplayName("Should format BigInteger correctly")
    void formatBigInteger() {
        String actualString = BigIntegerPower.formatBigInteger(BASE);
        int actualLength = actualString.length();
        int expectedLength = BASE.toString().length();
        if (BASE.toString().length() > BigIntegerPower.MAX_DISPLAY_DIGITS) {
            expectedLength = BigIntegerPower.MAX_DISPLAY_DIGITS + 3;
        }
        assertEquals(expectedLength, actualLength);
    }
}