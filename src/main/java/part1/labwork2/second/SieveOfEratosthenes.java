package part1.labwork2.second;

import java.util.Arrays;

/**
 * The {@code SieveOfEratosthenes} class implements the Sieve of Eratosthenes algorithm
 * to find prime numbers without division and getting the remainder of the division.
 */
class SieveOfEratosthenes {
    /** Array size to search for primes in the range from 1 to this size */
    private static final int ARRAY_SIZE = 29;
    /** A value used to indicate non-prime numbers in an array */
    private static final int NEGATIVE_NUMBER = -1;
    /**
     * Finds prime numbers within the range of 1 to {@value #ARRAY_SIZE}
     * using the Sieve of Eratosthenes algorithm.
     * The {@code resultArray} is filled with consecutive positive integers starting from 1.
     * Numbers that are not prime are filled with {@value #NEGATIVE_NUMBER}
     *
     * @return the array of prime numbers and {@value #NEGATIVE_NUMBER} in the given range
     */
    static int[] findPrimeNumbers() {
        int[] resultArray = new int[ARRAY_SIZE];
        Arrays.setAll(resultArray, index -> index + 1);
        resultArray[0] = NEGATIVE_NUMBER;
        for (int i = 2; i * i <= ARRAY_SIZE; i++) {
            if (resultArray[i - 1] != NEGATIVE_NUMBER) {
                for (int j = 2 * i; j <= ARRAY_SIZE; j += i) {
                    if (resultArray[j - 1] == NEGATIVE_NUMBER) {
                        continue;
                    }
                    resultArray[j - 1] = NEGATIVE_NUMBER;
                }
            }
        }
        return resultArray;
    }

    /**
     * Prints the prime numbers in a formatted manner.
     * Values {@value #NEGATIVE_NUMBER} in the {@code resultArray} are not printed.
     *
     * @param resultArray the array of prime numbers
     */
    static void printPrimeNumbers(int[] resultArray) {
        int numberCount = 0;
        for (int element : resultArray) {
            if (element != NEGATIVE_NUMBER) {
                if (numberCount == 8) {
                    System.out.println();
                    numberCount = 0;
                }
                System.out.print(element + "\t");
                numberCount++;
            }
        }
    }
}
