package part2.labwork1.third;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * This class implements the {@link BigDecimalProcessor} interface using loops.
 * It provides methods to fill a list with random BigDecimal values,
 * sort the list in descending order by absolute value, calculate the product
 * of positive numbers in the list, and print the list.
 */
public class BigDecimalProcessorWithLoops implements BigDecimalProcessor {
    private final Random random = new Random();
    private final int bound;

    /**
     * Constructs a new BigDecimalProcessorWithLoops
     * with the specified bound for random values.
     *
     * @param bound the bound for random values
     */
    BigDecimalProcessorWithLoops(int bound) {
        this.bound = bound;
    }

    /**
     * Returns the bound for random values.
     *
     * @return the bound for random values
     */
    public int getBound() {
        return bound;
    }

    /**
     * Fills the given list with random BigDecimal values within the bound.
     *
     * @param list the list to fill
     * @param size the number of elements
     */
    public void fillWithRandomValues(List<BigDecimal> list, int size) {
        list.clear();
        for (int i = 0; i < size; i++) {
//            BigDecimal value = BigDecimal.valueOf(random.nextDouble() * 500 - 250);
            BigDecimal value = new BigDecimal(random.nextDouble() * 2 * bound - bound);
            list.add(value);
        }
    }

    /**
     * Sorts the given list in descending order by absolute value
     * using a bubble sort algorithm.
     *
     * @param list the list to sort
     */
    public static void sortDescendingByAbsoluteValue(List<BigDecimal> list) {
        boolean mustSorted = true;
        while (mustSorted) {
            mustSorted = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).abs().compareTo(list.get(i + 1).abs()) == -1) {
                    BigDecimal temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    mustSorted = true;
                }
            }
        }
    }

    /**
     * Calculates the product of positive numbers in the given list.
     *
     * @param list the list to process
     * @return the product of positive numbers
     */
    public static BigDecimal calculateProductOfPositiveNumbers(List<BigDecimal> list) {
        BigDecimal product = BigDecimal.ONE;
        BigDecimal value;
        for (int i = 0; i < list.size(); i++) {
            value = list.get(i);
            if (value.compareTo(BigDecimal.ZERO) == 1) {
                product = product.multiply(value);
            }
        }
        return product;
    }

    /**
     * Prints the given list to the standard output.
     *
     * @param list the list to print
     */
    public static void printList(List<BigDecimal> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
