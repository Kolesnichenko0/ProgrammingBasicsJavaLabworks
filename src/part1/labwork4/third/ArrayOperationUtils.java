package part1.labwork4.third;

public class ArrayOperationUtils {
    private ArrayOperationUtils() {
    }

    private static <T> void swapElements(T[] array, int firstIndex, int secondIndex) {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }


    public static <T> boolean swapGroups(T[] array, int firstGroupStartIndex, int secondGroupStartIndex, int groupSize) {
        int arraySize = array.length;
        if (firstGroupStartIndex < 0 || secondGroupStartIndex < 0 || groupSize <= 0
                || arraySize < firstGroupStartIndex + groupSize || arraySize < secondGroupStartIndex + groupSize
                || firstGroupStartIndex == secondGroupStartIndex)
            return false;
        for (int i = 0; i < groupSize; i++) {
            swapElements(array, firstGroupStartIndex + i, secondGroupStartIndex + i);
        }
        return true;
    }

    public static <T> boolean swapAdjacentPairs(T[] array) {
        if (array.length <= 1)
            return false;
        for (int i = 0; i < array.length - 1; i += 2) {
            swapElements(array, i, i + 1);
        }
        return true;
    }

    public static <T> boolean replaceGroup(T[] dest, int groupStartIndex, int groupSize, T[] src, int srcStartIndex) {
        if (groupStartIndex < 0 || srcStartIndex < 0 || groupSize <= 0 || dest.length < groupStartIndex + groupSize
                || src.length < srcStartIndex + groupSize)
            return false;
        for (int i = 0; i < groupSize; i++) {
            dest[groupStartIndex + i] = src[srcStartIndex + i];
        }
        return true;
    }
}
