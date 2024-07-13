package part2.labwork1.third;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This is the main class for demonstrating the functionality of {@link BigDecimalProcessor} implementations.
 * It provides methods to demonstrate the functionality of {@link BigDecimalProcessor} implementations such as
 * filling a list with random BigDecimal values, sorting the list in descending order by absolute value,
 * calculating the product of positive numbers in the list, and printing the list.
 * <p>
 * The class uses Java's functional interfaces, Consumer and Function, to accept the methods as parameters
 * and execute them. This allows for a high degree of flexibility, as any method that fits the signature
 * of the functional interfaces can be passed in and executed.
 */
public class BigDecimalProcessorProgram {
    /**
     * The upper limit for generating random BigDecimal values.
     */
    public static int BOUND = 250;
    /**
     * The scale to be used for the BigDecimal product calculation,
     * the number of digits to the right of the decimal point.
     */
    public static int PRODUCT_SCALE = 10;
    /**
     * The size of the list to be filled with random BigDecimal values.
     */
    public static int LIST_SIZE = 5;

    /**
     * Demonstrates the methods of a {@link BigDecimalProcessor} implementation.
     *
     * @param processor         the {@link BigDecimalProcessor} implementation
     * @param ListModifier      a Consumer that modifies a list of BigDecimal values
     * @param ProductCalculator a Function that calculates the product of positive numbers in a list
     * @param ListPrinter       a Consumer that prints a list of BigDecimal values
     */
    public static void demonstrateMethods(BigDecimalProcessor processor,
                                          Consumer<List<BigDecimal>> ListModifier,
                                          Function<List<BigDecimal>, BigDecimal> ProductCalculator,
                                          Consumer<List<BigDecimal>> ListPrinter) {
        List<BigDecimal> list = new ArrayList<>();
        processor.fillWithRandomValues(list, LIST_SIZE);
        System.out.println("List after filling with random values:");
        ListPrinter.accept(list);

        ListModifier.accept(list);
        System.out.println("List after sorting by absolute value in descending order:");
        ListPrinter.accept(list);

        BigDecimal product = ProductCalculator.apply(list);
        product = product.setScale(PRODUCT_SCALE, RoundingMode.HALF_UP);
        System.out.println("Product of positive numbers: " + product);
    }

    /**
     * The main method for this class.
     * Creates instances of {@link BigDecimalProcessor} implementations and demonstrates their methods.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        BigDecimalProcessorWithLoops processor1 = new BigDecimalProcessorWithLoops(BOUND);
        BigDecimalProcessorWithCollectionFunctions processor2 = new BigDecimalProcessorWithCollectionFunctions(BOUND);
        BigDecimalProcessorWithStreams processor3 = new BigDecimalProcessorWithStreams(BOUND);

        System.out.println("Demonstrating methods for BigDecimalProcessorWithLoops:");
        demonstrateMethods(processor1,
                BigDecimalProcessorWithLoops::sortDescendingByAbsoluteValue,
                BigDecimalProcessorWithLoops::calculateProductOfPositiveNumbers,
                BigDecimalProcessorWithLoops::printList);
        System.out.println("\nDemonstrating methods for BigDecimalProcessorWithCollectionFunctions:");
        demonstrateMethods(processor2,
                BigDecimalProcessorWithCollectionFunctions::sortDescendingByAbsoluteValue,
                BigDecimalProcessorWithCollectionFunctions::calculateProductOfPositiveNumbers,
                BigDecimalProcessorWithCollectionFunctions::printList);

        System.out.println("\nDemonstrating methods for BigDecimalProcessorWithStreams:");
        demonstrateMethods(processor3,
                BigDecimalProcessorWithStreams::sortDescendingByAbsoluteValue,
                BigDecimalProcessorWithStreams::calculateProductOfPositiveNumbers,
                BigDecimalProcessorWithStreams::printList);
    }
}