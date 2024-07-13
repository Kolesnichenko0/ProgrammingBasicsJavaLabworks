package part2.labwork1.third;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The {@code BigDecimalProcessorTest} class provides unit tests
 * for the implementations of {@link BigDecimalProcessor} interface.
 * It tests the generation of random BigDecimal values, sorting,
 * the calculation of products positive values using different methods,
 * and the formatting of BigDecimal values for display.
 *
 * @see BigDecimalProcessorWithLoops
 * @see BigDecimalProcessorWithCollectionFunctions
 * @see BigDecimalProcessorWithStreams
 */
class BigDecimalProcessorTest {
    private static final BigDecimal POSITIVE_FIRST_VALUE = new BigDecimal("1.5");
    private static final BigDecimal NEGATIVE_SECOND_VALUE = new BigDecimal("-2.5");
    private static final BigDecimal POSITIVE_THIRD_VALUE = new BigDecimal("3.5");
    private static final BigDecimal NEGATIVE_FOURTH_VALUE = new BigDecimal("-4.5");
    private static final BigDecimal NEGATIVE_FIFTH_VALUE = new BigDecimal("-5.5");
    private static final BigDecimal PRODUCT_OF_FIRST_AND_THIRD = new BigDecimal("5.25");
    /**
     * The size of the list to be filled with random BigDecimal values.
     */
    private static final int SIZE = 10;
    /**
     * The bound for the random BigDecimal values.
     */
    private static final int BOUND = 100;

    private List<BigDecimal> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
    }

    private static Stream<Consumer<List<BigDecimal>>> sortDescendingByAbsoluteValueProvider() {
        return Stream.of(BigDecimalProcessorWithLoops::sortDescendingByAbsoluteValue,
                BigDecimalProcessorWithCollectionFunctions::sortDescendingByAbsoluteValue,
                BigDecimalProcessorWithStreams::sortDescendingByAbsoluteValue);
    }

    /**
     * The {@code FillWithRandomValuesTests} class provides unit
     * tests for the implementations {@link BigDecimalProcessor#fillWithRandomValues} method.
     * It tests the generation of random BigDecimal values and checks
     * if the list is filled with the correct number of elements.
     */
    @Nested
    class FillWithRandomValuesTests {
        private static Stream<BigDecimalProcessor> processorProvider() {
            return Stream.of(new BigDecimalProcessorWithLoops(BOUND),
                    new BigDecimalProcessorWithCollectionFunctions(BOUND),
                    new BigDecimalProcessorWithStreams(BOUND));

        }

        /**
         * Tests the implementations of {@link BigDecimalProcessor#fillWithRandomValues} method.
         * Checks if the list is filled with the correct number of elements.
         *
         * @param processor the BigDecimalProcessor to be tested
         */
        @ParameterizedTest(name = "Test #{index}.")
        @DisplayName("Should ensure that the list is filled" +
                "with the correct number of elements with 3 different implementations")
        @MethodSource("processorProvider")
        public void testFillWithRandomValuesSize(BigDecimalProcessor processor) {
            processor.fillWithRandomValues(list, SIZE);
            assertEquals(SIZE, list.size());
        }

        /**
         * Tests the implementations of {@link BigDecimalProcessor#fillWithRandomValues method.
         * Checks if the range of values is correct.
         *
         * @param processor the BigDecimalProcessor to be tested
         */
        @ParameterizedTest(name = "Test #{index}.")
        @DisplayName("Should ensure that range of values is correct " +
                "with 3 different implementations")
        @MethodSource("processorProvider")
        public void testFillWithRandomValuesRangeReplacesOldValues(BigDecimalProcessor processor) {
            int repeatTimes = 3;
            for (int i = 0; i < repeatTimes; i++) {
                processor.fillWithRandomValues(list, SIZE);
                BigDecimal LOWER_BOUND = BigDecimal.valueOf(-BOUND);
                BigDecimal UPPER_BOUND = BigDecimal.valueOf(BOUND);
                assertTrue(list.stream().allMatch(item ->
                        item.compareTo(LOWER_BOUND) == 1 && item.compareTo(UPPER_BOUND) == -1));
            }
        }
    }

    /**
     * Tests the implementations of {@link BigDecimalProcessor#sortDescendingByAbsoluteValue} method.
     * Checks if the list is sorted in descending order by absolute value.
     *
     * @param sortDescendingByAbsoluteValue the method to be tested
     */
    @ParameterizedTest(name = "Test #{index}.")
    @DisplayName("Should sort descending by absolute value" +
            " with 3 different implementations")
    @MethodSource("sortDescendingByAbsoluteValueProvider")
    public void testSortDescendingByAbsoluteValue(
            Consumer<List<BigDecimal>> sortDescendingByAbsoluteValue) {
        list.add(POSITIVE_FIRST_VALUE);
        list.add(NEGATIVE_SECOND_VALUE);
        list.add(POSITIVE_THIRD_VALUE);
        sortDescendingByAbsoluteValue.accept(list);
        assertEquals(POSITIVE_THIRD_VALUE, list.get(0));
        assertEquals(NEGATIVE_SECOND_VALUE, list.get(1));
        assertEquals(POSITIVE_FIRST_VALUE, list.get(2));
    }

    /**
     * The {@code CalculateProductOfPositiveNumbersTests} class provides
     * unit tests for the implementations of {@link BigDecimalProcessor#calculateProductOfPositiveNumbers} method.
     * It tests the calculation of the product of positive numbers
     * and checks if the result is correct.
     */
    @Nested
    class CalculateProductOfPositiveNumbersTests {
        private static Stream<Function<List<BigDecimal>, BigDecimal>> calculateProductOfPositiveNumbersProvider() {
            return Stream.of(BigDecimalProcessorWithLoops::calculateProductOfPositiveNumbers,
                    BigDecimalProcessorWithCollectionFunctions::calculateProductOfPositiveNumbers,
                    BigDecimalProcessorWithStreams::calculateProductOfPositiveNumbers);
        }

        /**
         * Tests the implementations of {@link BigDecimalProcessor#calculateProductOfPositiveNumbers} method.
         * Checks if the product of positive numbers is calculated correctly.
         *
         * @param calculateProductOfPositiveNumbers the method to be tested
         */
        @ParameterizedTest(name = "Test #{index}.")
        @DisplayName("Should calculate product of positive numbers " +
                "with 3 different implementations")
        @MethodSource("calculateProductOfPositiveNumbersProvider")
        public void testCalculateProductOfPositiveNumbers(
                Function<List<BigDecimal>, BigDecimal> calculateProductOfPositiveNumbers) {
            list.add(POSITIVE_FIRST_VALUE);
            list.add(NEGATIVE_SECOND_VALUE);
            list.add(POSITIVE_THIRD_VALUE);
            BigDecimal product = calculateProductOfPositiveNumbers.apply(list);
            assertEquals(PRODUCT_OF_FIRST_AND_THIRD, product);
        }

        /**
         * Tests the implementations of {@link BigDecimalProcessor#calculateProductOfPositiveNumbers} method.
         * Checks if the product of positive numbers is calculated correctly
         * when there are no positive numbers.
         *
         * @param calculateProductOfPositiveNumbers the method to be tested
         */
        @ParameterizedTest(name = "Test #{index}.")
        @DisplayName("Should calculate product of positive numbers" +
                "without them with 3 different implementations")
        @MethodSource("calculateProductOfPositiveNumbersProvider")
        public void testCalculateProductWithoutPositiveNumbers(
                Function<List<BigDecimal>, BigDecimal> calculateProductOfPositiveNumbers) {
            list.add(NEGATIVE_SECOND_VALUE);
            list.add(NEGATIVE_FOURTH_VALUE);
            list.add(NEGATIVE_FIFTH_VALUE);
            BigDecimal product = calculateProductOfPositiveNumbers.apply(list);
            assertEquals(BigDecimal.ONE, product);
        }
    }

    /**
     * The {@code PrintListTests} class provides unit tests
     * for the implementations of {@link BigDecimalProcessor#printList} method.
     * It tests the printing of the list and checks if the output is correct.
     * <p>
     * ByteArrayOutputStream and PrintStream are used to capture console output.
     * ByteArrayOutputStream stores the output in a byte array,
     * which allows easy checking of the output content later.
     * PrintStream is used with System.setOut to redirect output,
     * which usually goes to the console, to ByteArrayOutputStream.
     */
    @Nested
    class PrintListTests {
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final PrintStream originalOut = System.out;

        /**
         * Sets up the streams before each test.
         * Redirect output, which usually goes to the console, to ByteArrayOutputStream
         */
        @BeforeEach
        public void setUpStreams() {
            System.setOut(new PrintStream(outContent));
        }

        /**
         * Provides a stream of Consumers that print a list of BigDecimal values.
         * Each Consumer is a method reference to a different implementation
         * of the printList method.
         *
         * @return a stream of Consumers
         */
        private static Stream<Consumer<List<BigDecimal>>> printListProvider() {
            return Stream.of(BigDecimalProcessorWithLoops::printList,
                    BigDecimalProcessorWithCollectionFunctions::printList,
                    BigDecimalProcessorWithStreams::printList);
        }

        /**
         * Tests the printList method of different BigDecimalProcessor implementations.
         * Checks if the list is printed correctly.
         * The split(System.lineSeparator()) method is used to split this string
         * into an array of strings (lines),
         * using the system's line separator as the delimiter.
         * This allows the lines to be printed properly on any OS.
         *
         * @param printList a Consumer that prints a list of BigDecimal values
         */
        @ParameterizedTest(name = "Test #{index}.")
        @DisplayName("Should print list with 3 different implementations")
        @MethodSource("printListProvider")
        public void testPrintList(Consumer<List<BigDecimal>> printList) {
            List<BigDecimal> list = new ArrayList<>();
            list.add(POSITIVE_FIRST_VALUE);
            list.add(NEGATIVE_SECOND_VALUE);
            list.add(POSITIVE_THIRD_VALUE);

            printList.accept(list);
            String[] lines = outContent.toString().split(System.lineSeparator());
            assertEquals(POSITIVE_FIRST_VALUE.toString(), lines[0]);
            assertEquals(NEGATIVE_SECOND_VALUE.toString(), lines[1]);
            assertEquals(POSITIVE_THIRD_VALUE.toString(), lines[2]);
        }

        /**
         * Restores the standard output to the console after each test.
         * <p>
         * Note: ByteArrayOutputStream does not require closing.
         * It does not use any system resources that require closing,
         * so it can be safely left open.
         * It differs from other I/O streams, such as FileOutputStream or PrintStream,
         * which use system resources, such as files or network connections,
         * and therefore require closing to free these resources.
         */
        @AfterEach
        public void restoreStreams() {
            System.setOut(originalOut);
        }
    }
}