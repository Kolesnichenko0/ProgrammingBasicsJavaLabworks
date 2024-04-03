package part1.labwork4.eighth;

public class DoublyLinkedListTester {
    private static <T> void testFunctionality(DoublyLinkedList<T> list, T element) {
        System.out.println("Initial list:\n" + list);
        System.out.println("list.add(1, element):" + list.add(1, element));
        System.out.println("list.add(3, element):" + list.add(3, element));
        System.out.println("list.add(-4, element):" + list.add(-4, element));
        System.out.println("List after:\n" + list);

        System.out.println("list.removeFirst(): " + list.removeFirst());
        System.out.println("list.removeLast(): " + list.removeLast());
        System.out.println("List after:\n" + list);

        System.out.println("list.remove(1):\n" + list.remove(1));
        System.out.println("List after:\n" + list);

        System.out.println("list.indexOf(element): " + list.indexOf(element));
        System.out.println("list.indexOf(null): " + list.indexOf(null));

        System.out.println("list.removeFirstOccurrence(element): " + list.removeFirstOccurrence(element));
        System.out.println("list.removeFirstOccurrence(null): " + list.removeFirstOccurrence(null));
        System.out.println("List after:\n" + list);

        System.out.println("list.get(1): " + list.get(1));
        System.out.println("list.get(43):");
        list.get(43);

        System.out.println("list.clear()");
        list.clear();
        System.out.println("List after:\n" + list);
    }

    public static void main(String[] args) {
        System.out.println("Testing the DoublyLinkedList class with type Integer:");
        DoublyLinkedList<Integer> listOfIntegers = new DoublyLinkedList<>();
        listOfIntegers.addFirst(2);
        listOfIntegers.addFirst(3);
        listOfIntegers.addFirst(-7);
        listOfIntegers.addFirst(9);
        listOfIntegers.addFirst(-8);
        testFunctionality(listOfIntegers, 53);

        System.out.println("Testing the DoublyLinkedList class with type String:");
        DoublyLinkedList<String> listOfStrings = new DoublyLinkedList<>();
        listOfStrings.addLast("Cooking");
        listOfStrings.addLast("Painting");
        listOfStrings.addLast("Skiing");
        listOfStrings.addLast("Skating");
        testFunctionality(listOfStrings, "Cycling");
    }
}
