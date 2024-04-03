package part1.labwork4.seventh;

public class BoundedIndexArrayTester {
    private static <T> void testFunctionality(BoundedIndexArray<T> array, T element) {
        System.out.println("Initial array:\n" + array);
        System.out.println("array.get(9):");
        T temp = array.get(9);
        System.out.println(temp);
        System.out.println("array.get(-9):");
        temp = array.get(-9);
        System.out.println(temp);

        System.out.println("array.set(9, element):");
        temp = array.set(9, element);
        System.out.println(temp);
        System.out.println("array.set(-9, element):");
        temp = array.set(-9, element);
        System.out.println(temp);

        System.out.println("Array after:\n" + array);

        System.out.println("array.size(): " + array.size());
        System.out.println("array.indexOf(element): " + array.indexOf(element));
        System.out.println("array.lastIndexOf(element): " + array.lastIndexOf(element));
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] arrayOfIntegers = {11, 13, 15, 17, 19, 53, 23};
        BoundedIndexArray<Integer> boundedArrayOfIntegers = new BoundedIndexArray<>(8, 14, arrayOfIntegers);
        System.out.println("Testing with the Integer type and positive new indices:");
        testFunctionality(boundedArrayOfIntegers, 53);

        BoundedIndexArray<Integer> boundedNegativeIndexArray = new BoundedIndexArray<>(-14, -8, arrayOfIntegers);
        System.out.println("Testing with the Integer type and negative new indices:");
        testFunctionality(boundedNegativeIndexArray, 53);

        String[] arrayOfStrings = {"Cooking", "Painting", "Skiing", "Skating", "Shopping", "Shopping", "Hiking"};
        BoundedIndexArray<String> boundedArrayOfStrings = new BoundedIndexArray<>(8, 14, arrayOfStrings);
        System.out.println("Testing with the String type and positive new indices:");
        testFunctionality(boundedArrayOfStrings, "Cycling");

        System.out.println("Trying to create firstTestArray with from = 4, to = 1:");
        BoundedIndexArray<String> firstTestArray = new BoundedIndexArray<>(4, 1, arrayOfStrings);

        System.out.println("Trying to create testArrayOfStrings with from = 8, to = 15:");
        BoundedIndexArray<String> secondTestArray = new BoundedIndexArray<>(8, 15, arrayOfStrings);
    }
}
