package part2.labwork1.third;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class implements the {@link BigDecimalProcessor} interface using collection functions.
 * It provides methods to fill a list with random BigDecimal values,
 * sort the list in descending order by absolute value, calculate the product
 * of positive numbers in the list, and print the list.
 */
public class BigDecimalProcessorWithCollectionFunctions implements BigDecimalProcessor {

    private final Random random = new Random();
    private final int bound;

    /**
     * Constructs a new BigDecimalProcessorWithCollectionFunctions
     * with the specified bound for random values.
     *
     * @param bound the bound for random values
     */
    BigDecimalProcessorWithCollectionFunctions(int bound) {
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
        list.addAll(
                IntStream.range(0, size)
                        .mapToObj(i ->
                                new BigDecimal(random.nextDouble() * 2 * bound - bound))
                        .collect(Collectors.toList())
        );
    }

    /**
     * Sorts the given list in descending order by absolute value
     * using collection functions.
     *
     * @param list the list to sort
     */
    public static void sortDescendingByAbsoluteValue(List<BigDecimal> list) {
        list.sort(Comparator.comparing((BigDecimal bd) ->
                bd.abs()).reversed());
    }

    /**
     * Calculates the product of positive numbers in the given list
     * using collection functions.
     *
     * @param list the list to process
     * @return the product of positive numbers
     */
    public static BigDecimal calculateProductOfPositiveNumbers(List<BigDecimal> list) {
        BigDecimal[] product = {BigDecimal.ONE};
        list.forEach(value -> {
            if (value.compareTo(BigDecimal.ZERO) == 1) {
                product[0] = product[0].multiply(value);
            }
        });
        return product[0];
    }

    /**
     * Prints the given list to the standard output.
     *
     * @param list the list to print
     */
    public static void printList(List<BigDecimal> list) {
        list.forEach(System.out::println);
    }
}
