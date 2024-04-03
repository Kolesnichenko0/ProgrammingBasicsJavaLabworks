package part1.labwork5.second;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;

public class IntegerNumbers {
    private static int calculateDigitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    private static class IncreasingDigitSumComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer firstNumber, Integer secondNumber) {
            return Integer.compare(calculateDigitSum(firstNumber), calculateDigitSum(secondNumber));
        }
    }

    private static class DecreasingDigitSumComparator extends IncreasingDigitSumComparator {
        @Override
        public int compare(Integer firstNumber, Integer secondNumber) {
            return super.compare(secondNumber, firstNumber);
        }
    }

    private static Integer[] readFromFile(String inFileName) throws NonPositiveIntegerException, IOException {
        Integer[] resultArray = {};
        try (BufferedReader reader = new BufferedReader(new FileReader(inFileName));
             Scanner scanner = new Scanner(reader)) {
            while (scanner.hasNext()) {
                int number = scanner.nextInt();
                if (number <= 0) {
                    throw new NonPositiveIntegerException(number);
//                    throw new NonPositiveIntegerException("Wrong value: " + number);
//                    throw new NonPositiveIntegerException();
                }
                Integer[] array = new Integer[resultArray.length + 1];
                System.arraycopy(resultArray, 0, array, 0, resultArray.length);
                array[array.length - 1] = number;
                resultArray = array;
            }
        }
        return resultArray;
    }

    private static void writeInFile(String outFileName, Integer[] numbers) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)))) {
            for (Integer number : numbers) {
                writer.print(number + " ");
            }
        }
    }

    public static void sortIntegers(String inFileName, String firstOutFileName,
                                    String secondOutFileName) throws NonPositiveIntegerException, IOException {
        Integer[] numbers = readFromFile(inFileName);
        Arrays.sort(numbers, new IncreasingDigitSumComparator());
        writeInFile(firstOutFileName, numbers);
        Arrays.sort(numbers, new DecreasingDigitSumComparator());
        writeInFile(secondOutFileName, numbers);
    }

    public static void main(String[] args) {
        String path = "resources/second/";
        try {
            sortIntegers(path + "validData.txt",
                    path + "validResult1.txt", path + "validResult2.txt");
            sortIntegers(path + "invalidNonPositiveData.txt",
                    path + "invalidResult1.txt", path + "invalidResult2.txt");
            sortIntegers(path + "invalidStringData.txt",
                    path + "invalidResult1.txt", path + "invalidResult2.txt");
            sortIntegers(path + "nonExistentFile.txt",
                    path + "invalidResult1.txt", path + "invalidResult2.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Read failed or no path found");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Write failed");
            e.printStackTrace();
        } catch (NonPositiveIntegerException e) {
            System.err.println("Wrong value");
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.err.println("Wrong format");
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
