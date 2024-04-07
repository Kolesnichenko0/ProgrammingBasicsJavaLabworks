package part2.labwork1.third;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Supplier;


/**
 * This class implements the {@link BigDecimalProcessor} interface using Java Streams API.
 * It provides methods to fill a list with random BigDecimal values
 within the bound using {@link Stream#generate(Supplier)}
 * sort the list in descending order by absolute value using the sorted method of the Stream interface,
 * calculate the product of positive numbers in the list using the filter and reduce methods of the Stream interface, and print the list to the standard output using the forEach method of the Stream interface.
 */
public class BigDecimalProcessorWithStreams implements BigDecimalProcessor {
    private final Random random = new Random();
    private final int bound;

    /**
     * Constructs a new BigDecimalProcessorWithStreams
     * with the specified bound for random values.
     *
     * @param bound the bound for random values
     */
    BigDecimalProcessorWithStreams(int bound) {
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
     * Fills the given list with random BigDecimal values
     * within the bound using Stream.generate.
     *
     * @param list the list to fill
     * @param size the number of elements
     */
    public void fillWithRandomValues(List<BigDecimal> list, int size) {
        list.clear();
        list.addAll(Stream.generate(() ->
                        new BigDecimal(random.nextDouble() * 2 * bound - bound))
                .limit(size)
                .collect(Collectors.toList()));
    }

    /**
     * Sorts the given list in descending order by absolute value
     * using the sorted method of the Stream interface.
     *
     * @param list the list to sort
     */
    public static void sortDescendingByAbsoluteValue(List<BigDecimal> list) {
        List<BigDecimal> sortedList = list.stream()
                .sorted(Comparator.comparing((BigDecimal bd) ->
                        bd.abs()).reversed())
                .collect(Collectors.toList());
        list.clear();
        list.addAll(sortedList);
    }

    /**
     * Calculates the product of positive numbers in the given list
     * using the filter and reduce methods of the Stream interface.
     *
     * @param list the list to process
     * @return the product of positive numbers
     */
    public static BigDecimal calculateProductOfPositiveNumbers(List<BigDecimal> list) {
        return list.stream()
                .filter(value -> value.compareTo(BigDecimal.ZERO) == 1)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
    }

    /**
     * Prints the given list to the standard output
     * using the forEach method of the Stream interface.
     *
     * @param list the list to print
     */
    public static void printList(List<BigDecimal> list) {
        list.stream().forEach(System.out::println);
    }
}
