package part1.labwork2.third;

/**
 * The {@code FibonacciNumberFinder} class provides a function for computing Fibonacci numbers up to the 92nd integer
 * using a supporting array. The Fibonacci numbers are calculated according to the rule:
 * F(1) = F(2) = 1; F(n) = F(n - 2) + F(n - 1)
 * <p>
 * The class maintains a static {@code fibonacciArray} to store the Fibonacci numbers. At the first call, the array is filled
 * until the required number. At subsequent calls, the number is either returned from the array or calculated
 * using the last two numbers stored in the array.
 * <p>
 * The class also provides a function to print the Fibonacci array.
 */
class FibonacciNumberFinder {
    /** The size of the {@code fibonacciArray} array and the maximum possible value to find */
    private static final int ARRAY_SIZE = 92;
    /** The static array to store the Fibonacci numbers */
    private static final long[] fibonacciArray = new long[ARRAY_SIZE];
    /** The number of the last calculated Fibonacci number in the {@code fibonacciArray} */
    private static int lastNumber;

    static {
        fibonacciArray[0] = 1;
        fibonacciArray[1] = 1;
        lastNumber = 2;
    }

    /**
     * Finds the Fibonacci number at a given {@code number}.
     *
     * @param number the index of the Fibonacci number
     * @return fibonacci number at the given index
     */
    static long findFibonacci(int number) {
        if (number <= 0 || number > 92) {
            System.err.println("Invalid value of index");
            System.exit(1);
        }
        if (lastNumber < number) {
            for (int i = lastNumber; i < number; i++) {
                fibonacciArray[i] = fibonacciArray[i - 2] + fibonacciArray[i - 1];
            }
            System.out.println("Fibonacci numbers from " + (lastNumber + 1) + " to " +
                    number + "(inclusive) were calculated.");
            lastNumber = number;
        } else {
            System.out.println("The Fibonacci number was taken from the array.");
        }
        return fibonacciArray[number - 1];
    }

    /**
     * Prints the {@code fibonacciArray} up to the {@code lastNumber}.
     */
    static void printFibonacciArray() {
        System.out.println("The Fibonacci array from 1 to " + lastNumber + "(inclusive):");
        int numberCount = 0;
        int width = String.valueOf(fibonacciArray[lastNumber - 1]).length();
        for (int i = 0; i < lastNumber; i++) {
            if (numberCount == 5) {
                System.out.println("|");
                numberCount = 0;
            }
            numberCount++;
            System.out.printf("| %-" + width + "d ", fibonacciArray[i]);
        }
        System.out.println("|");
    }
}
