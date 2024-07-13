package part1.labwork4.seventh;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;

public class BoundedIndexArray<T> extends AbstractList<T> {

    private final T[] array;
    private final int from;
    private final int to;

    public BoundedIndexArray(int from, int to, T[] array) {
        if (from > to) {
            System.err.println("Invalid value 'from' and(or) 'to'.\n"
                    + "The value 'from' must be less than or equal to the value 'to'");
            System.exit(1);
        }
        if (to - from + 1 != array.length) {
            System.err.println("Invalid value 'from' and(or) 'to' and(or) array length.\n"
                    + "The size of the array must be equal to 'to - from + 1'");
            System.exit(1);
        }
        this.from = from;
        this.to = to;
        this.array = Arrays.copyOf(array, array.length);
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    private boolean validateIndex(int index) {
        return index >= from && index <= to;
    }

    @Override
    public T get(int index) {
        if (!validateIndex(index)) {
            System.out.println("Invalid index. Null was returned.");
            return null;
        }
        return array[index - from];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public T set(int index, T element) {
        if (!validateIndex(index)) {
            System.out.println("Invalid index. Null was returned.");
            return null;
        }
        T oldValue = array[index - from];
        array[index - from] = element;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < array.length; i++) {
            if ((o == null && array[i] == null) || (o != null && o.equals(array[i]))) {
                return i + from;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = array.length - 1; i >= 0; i--) {
            if ((o == null && array[i] == null) || (o != null && o.equals(array[i]))) {
                return i + from;
            }
        }
        return -1;
    }

    private class BoundedIndexArrayIterator implements Iterator<T> {
        private int cursor = from;

        @Override
        public boolean hasNext() {
            return cursor <= to;
        }

        @Override
        public T next() {
            return array[cursor++ - from];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BoundedIndexArrayIterator();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("from = " + getFrom() + ". to = " + getTo() + ".\nElements:\n");

        for (T data : this) {
            result.append(data);
            result.append('\n');
        }

        return result.toString();
    }

    public static void main(String[] args) {
        BoundedIndexArray<Integer> boundedArrayOfIntegers = new BoundedIndexArray<>(5, 7, new Integer[]{2, 7, 9});
        System.out.println("boundedArrayOfIntegers:");
        System.out.println(boundedArrayOfIntegers);
        System.out.println("boundedArrayOfIntegers.validateIndex(2): " + boundedArrayOfIntegers.validateIndex(2));
        System.out.println("boundedArrayOfIntegers.validateIndex(5): " + boundedArrayOfIntegers.validateIndex(5));
    }
}
