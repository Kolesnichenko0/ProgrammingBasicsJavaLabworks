package part2.labwork1.fourth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The {@code DivisorFinderTest} class provides unit tests for the {@link DivisorFinder} class.
 */
class DivisorFinderTest {

    /**
     * Tests the {@link DivisorFinder#findAllPositiveNumberDivisors(int)} method
     * with positive numbers.
     * <p>
     * It uses a {@link CsvSource} to provide a set of numbers
     * and their expected divisors.
     *
     * @param number         the number to find divisors for
     * @param expectedString a string representation of the expected divisors of the number
     */
    @ParameterizedTest(name = "number = {0}, expectedDivisors = {1}")
    @DisplayName("Should find all positive number divisors")
    @CsvSource({
            "34, '1,2,17,34'",
            "1, 1"
    })
    public void testFindAllPositiveNumberDivisors(int number, String expectedString) {
        int[] expectedDivisors = Arrays.stream(expectedString.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] actualDivisors = DivisorFinder.findAllPositiveNumberDivisors(number);
        assertArrayEquals(expectedDivisors, actualDivisors);
    }

    /**
     * Tests the {@link DivisorFinder#findAllPositiveNumberDivisors(int)} method with non-positive numbers.
     * <p>
     * This test checks if the method correctly throws an {@link IllegalArgumentException}
     * when the input number is not positive.
     * It uses a {@link ValueSource} to provide a set of non-positive numbers.
     *
     * @param number the non-positive number to find the divisors for
     */
    @ParameterizedTest(name = "number = {0}")
    @DisplayName("Should throw exception for non-positive numbers")
    @ValueSource(ints = {-4, 0})
    public void testFindAllPositiveNumberDivisorsThrowsException(int number) {
        assertThrows(IllegalArgumentException.class,
                () -> DivisorFinder.findAllPositiveNumberDivisors(number));
    }
}