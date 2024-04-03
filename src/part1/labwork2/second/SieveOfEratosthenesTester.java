package part1.labwork2.second;
/**
 * The {@code SieveOfEratosthenesTester} class is responsible for testing the functionality
 * of the {@link SieveOfEratosthenes} class.
 */
class SieveOfEratosthenesTester {
    /**
     * It calls static methods of the {@link SieveOfEratosthenes} class to find and print prime numbers.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int[] resultArray = SieveOfEratosthenes.findPrimeNumbers();
        System.out.println("Prime numbers:");
        SieveOfEratosthenes.printPrimeNumbers(resultArray);
    }
}
