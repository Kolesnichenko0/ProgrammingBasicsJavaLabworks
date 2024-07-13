package part1.labwork2.third;

/**
 * The {@code FibonacciNumbersTester} class is used to test the functionality
 * of the {@link FibonacciNumberFinder} class.
 */
class FibonacciNumbersTester {
    /**
     * It calls static methods of the {@link FibonacciNumberFinder} class
     * to find the nth Fibonacci number and prints it along with the {@code fibonacciArray}
     * for different values of n. {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        long tempResult = FibonacciNumberFinder.findFibonacci(5);
        System.out.println("The 5th number of Fibonacci is " + tempResult);
        FibonacciNumberFinder.printFibonacciArray();
        System.out.println();

        tempResult = FibonacciNumberFinder.findFibonacci(3);
        System.out.println("The 3th number of Fibonacci is " + tempResult);
        FibonacciNumberFinder.printFibonacciArray();
        System.out.println();

        tempResult = FibonacciNumberFinder.findFibonacci(7);
        System.out.println("The 7th number of Fibonacci is " + tempResult);
        FibonacciNumberFinder.printFibonacciArray();
        System.out.println();

        tempResult = FibonacciNumberFinder.findFibonacci(92);
        System.out.println("The 92th number of Fibonacci is " + tempResult);
        FibonacciNumberFinder.printFibonacciArray();
        System.out.println();

        System.out.println("Trying to find the 93rd Fibonacci number:");
        tempResult = FibonacciNumberFinder.findFibonacci(93);
    }
}
