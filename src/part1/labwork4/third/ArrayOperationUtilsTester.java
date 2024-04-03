package part1.labwork4.third;

import java.util.Arrays;

public class ArrayOperationUtilsTester {
    private static <T> void printArray(T[] array) {
        System.out.println(Arrays.toString(array));
    }

    private static <T> void testFunctionality(T[] firstArray, T[] secondArray, T[] thirdArray) {
        System.out.println("Initial firstArray:");
        printArray(firstArray);

        System.out.println("Testing swapGroups(firstArray, 0, -3, 1):");
        System.out.println(ArrayOperationUtils.swapGroups(firstArray, 0, -3, 1));
        System.out.println("FirstArray after:");
        printArray(firstArray);
        System.out.println("Testing swapGroups(firstArray, 0, 3, 2):");
        System.out.println(ArrayOperationUtils.swapGroups(firstArray, 0, 3, 2));
        System.out.println("FirstArray after:");
        printArray(firstArray);
        System.out.println();

        System.out.println("Initial secondArray:");
        printArray(secondArray);
        System.out.println("Testing swapAdjacentPairs(secondArray):");
        System.out.println(ArrayOperationUtils.swapAdjacentPairs(secondArray));
        System.out.println("SecondArray after:");
        printArray(secondArray);
        System.out.println("Testing swapAdjacentPairs(firstArray):");
        System.out.println(ArrayOperationUtils.swapAdjacentPairs(firstArray));
        System.out.println("FirstArray after:");
        printArray(firstArray);
        System.out.println("Initial thirdArray:");
        printArray(thirdArray);
        System.out.println("Testing swapAdjacentPairs(thirdArray):");
        System.out.println(ArrayOperationUtils.swapAdjacentPairs(thirdArray));
        System.out.println("ThirdArray after:");
        printArray(thirdArray);
        System.out.println();

        System.out.println("Testing replaceGroup(firstArray, 0, 2, secondArray, 1):");
        System.out.println(ArrayOperationUtils.replaceGroup(firstArray, 0, 2, secondArray, 1));
        System.out.println("FirstArray after:");
        printArray(firstArray);
        System.out.println("SecondArray after:");
        printArray(secondArray);
        System.out.println("Testing replaceGroup(firstArray, 0, 3, thirdArray, 0):");
        System.out.println(ArrayOperationUtils.replaceGroup(firstArray, 0, 3, thirdArray, 0));
        System.out.println("FirstArray after:");
        printArray(firstArray);
        System.out.println("ThirdArray after:");
        printArray(thirdArray);
        System.out.println();
    }

    public static void main(String[] args) {
        Double[] firstArrayOfDoubles = {-4.2, 2.3, 2.1, -3.0, 2.9, 5.1, -7.3};
        Double[] secondArrayOfDoubles = {2.9};
        Double[] thirdArrayOfDoubles = {2.7, 2.9, 2.5, 3.5};
        System.out.println("Testing of ArrayOperationUtil class generic functions for Double type:");
        testFunctionality(firstArrayOfDoubles, secondArrayOfDoubles, thirdArrayOfDoubles);

        Integer[] firstArrayOfIntegers = {4, 2, 1, -3, 8, 5, -7};
        Integer[] secondArrayOfIntegers = {3};
        Integer[] thirdArrayOfIntegers = {-4, -2, 4, 1};
        System.out.println("Testing of ArrayOperationUtil class generic functions for Integer type:");
        testFunctionality(firstArrayOfIntegers, secondArrayOfIntegers, thirdArrayOfIntegers);

        String[] firstArrayOfStrings = {"Cooking", "Painting ", "Drawing", "Baking", "Shopping", "Travelling", "Hiking"};
        String[] secondArrayOfStrings = {"Cycling"};
        String[] thirdArrayOfStrings = {"Walking", "Fishing", "Skating", "Skiing"};
        System.out.println("Testing of ArrayOperationUtil class generic functions for String type:");
        testFunctionality(firstArrayOfStrings, secondArrayOfStrings, thirdArrayOfStrings);
    }
}
