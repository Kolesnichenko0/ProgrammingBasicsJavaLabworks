package part1.labwork2.first;

import java.util.Arrays;
import java.util.Random;

/**
 * The {@code IndividualArrayOperations} class performs various operations
 * on a two-dimensional integer array and an array of strings.
 * <p> It provides two approaches: a traditional approach based on loops and working with individual elements
 * and a functional approach based on functions of the Arrays class (without loops).
 */
class IndividualArrayOperations {
    /** The number of rows in the array and in the string */
    private static final int NUM_ROWS = 5;
    /** The number of columns in the array */
    private static final int NUM_COLS = 4;
    /** The symbol used for string filling */
    private static final char SYMBOL = 'N';

    /**
     * Fills the given two-dimensional integer array with random even numbers between 4 and 30.
     *
     * @param intArray the two-dimensional integer array
     */
    private static void fillIntArray(int[][] intArray) {
        Random random = new Random();
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[i].length; j++) {
                intArray[i][j] = random.nextInt(14) * 2 + 4;
            }
        }
    }

    /**
     * Prints the given two-dimensional integer array.
     *
     * @param intArray the two-dimensional integer array
     */
    private static void printIntArray(int[][] intArray) {
        for (int[] row : intArray) {
            System.out.print('[');
            for (int j = 0; j < row.length - 1; j++) {
                System.out.print(row[j] + ", ");
            }
            System.out.println(row[row.length - 1] + "]");
        }
    }

    /**
     * Finds the minimum value in a given integer array row.
     *
     * @param intArrayRow the integer array row
     * @return the minimum value in the given integer array row
     */
    private static int findMinValueInRow(int[] intArrayRow) {
        int minValue = intArrayRow[0];
        for (int i = 1; i < intArrayRow.length; i++) {
            if (intArrayRow[i] < minValue) {
                minValue = intArrayRow[i];
            }
        }
        return minValue;
    }

    /**
     * Fills the given string array with strings consisting of the '{@value SYMBOL}' repeated a number of times
     * based on the minimum value in each row of the integer array.
     *
     * @param stringArray the string array
     * @param intArray    the two-dimensional integer array
     */
    private static void fillStringArray(String[] stringArray, int[][] intArray) {
        String symbolString = String.valueOf(SYMBOL);
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = symbolString.repeat(findMinValueInRow(intArray[i]));
        }
    }

    /**
     * Sorts the given string array in ascending order based on the length of the strings.
     * The bubble sorting is used.
     *
     * @param stringArray the string array
     */
    private static void sortStringArrayByLength(String[] stringArray) {
        boolean isSortingNeeded = true;
        while (isSortingNeeded) {
            isSortingNeeded = false;
            for (int i = 0; i < stringArray.length - 1; i++) {
                if (stringArray[i].length() > stringArray[i + 1].length()) {
                    String tempString = stringArray[i];
                    stringArray[i] = stringArray[i + 1];
                    stringArray[i + 1] = tempString;
                    isSortingNeeded = true;
                }
            }
        }
    }

    /**
     * Prints the given string array.
     *
     * @param stringArray the string array
     */
    private static void printStringArray(String[] stringArray) {
        for (String s : stringArray) {
            System.out.println(s);
        }
    }

    /**
     * Performs the array operations using the traditional approach.
     * <p> Fills the two-dimensional integer array with random even numbers.
     * Prints the integer array.
     * Fills the string array with symbols based on the minimum value in each row of the integer array.
     * Prints the string array.
     * Sorts the string array by length and prints it.
     */
    static void useTraditionalApproach() {
        int[][] intArray = new int[NUM_ROWS][NUM_COLS];
        fillIntArray(intArray);
        intArray = ArrayOperationsTester.testArray;
        System.out.println("Two-dimensional int array:");
        printIntArray(intArray);
        String[] stringArray = new String[NUM_ROWS];
        fillStringArray(stringArray, intArray);
        System.out.println("Array of strings:");
        printStringArray(stringArray);
        sortStringArrayByLength(stringArray);
        System.out.println("Array of strings after sorting:");
        printStringArray(stringArray);
    }

    /**
     * Performs the array operations using the functional approach.
     * <p> Fills the two-dimensional integer array with random even numbers.
     * Prints the integer array.
     * Fills the string array with symbols based on the minimum value in each row of the integer array
     * using functional programming constructs.
     * Prints the string array.
     * Sorts the string array by length using functional programming constructs and prints it.
     */
    static void useFunctionalApproach() {
        Random random = new Random();
//        int[][] intArray = new int[NUM_ROWS][NUM_COLS];
//        Arrays.stream(intArray)
//                .forEach(row -> Arrays.setAll(row, i -> random.nextInt(14) * 2 + 4));
        int[][] intArray = ArrayOperationsTester.testArray;
        System.out.println("Two-dimensional int array:");
        Arrays.stream(intArray)
                .forEach(row -> System.out.println(Arrays.toString(row)));
        String[] stringArray = new String[NUM_ROWS];
        String symbolString = String.valueOf(SYMBOL);
        Arrays.setAll(stringArray, j -> {
            int minValueInRow = Arrays.stream(intArray[j])
                    .min()
                    .orElse(0);
            return symbolString.repeat(minValueInRow);
        });
        System.out.println("Array of strings:");
        Arrays.stream(stringArray).forEach(System.out::println);
        Arrays.sort(stringArray, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("Array of strings after sorting:");
        Arrays.stream(stringArray).forEach(System.out::println);

    }

}
