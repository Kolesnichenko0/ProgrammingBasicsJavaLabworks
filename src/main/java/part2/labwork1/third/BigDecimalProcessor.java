package part2.labwork1.third;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for processing lists of BigDecimal values.
 *
 * @see BigDecimalProcessorWithLoops
 * @see BigDecimalProcessorWithCollectionFunctions
 * @see BigDecimalProcessorWithStreams
 */
public interface BigDecimalProcessor {
    /**
     * Fills the given list with random BigDecimal values.
     *
     * @param list the list to fill
     * @param size the number of elements
     */
    void fillWithRandomValues(List<BigDecimal> list, int size);

    /**
     * Sorts the given list in descending order by absolute value.
     *
     * @param list the list to sort
     * @throws UnsupportedOperationException if the method is not implemented
     */
    static void sortDescendingByAbsoluteValue(List<BigDecimal> list) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Calculates the product of positive numbers in the given list.
     *
     * @param list the list to process
     * @return the product of positive numbers
     * @throws UnsupportedOperationException if the method is not implemented
     */
    static BigDecimal calculateProductOfPositiveNumbers(List<BigDecimal> list) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Prints the given list.
     *
     * @param list the list to print
     * @throws UnsupportedOperationException if the method is not implemented
     */
    static void printList(List<BigDecimal> list) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
