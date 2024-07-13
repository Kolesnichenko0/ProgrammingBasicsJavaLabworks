package part1.labwork4.eighth;

import java.util.Iterator;

public class DoublyLinkedList<E> implements Iterable<E> {

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.data = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> first = null;
    private Node<E> last = null;
    private int size = 0;

    public void addFirst(E element) {
        Node<E> linkToFirst = first;
        Node<E> newNode = new Node<>(null, element, first);
        first = newNode;
        if (linkToFirst == null)
            last = newNode;
        else
            linkToFirst.prev = newNode;
        size++;
    }

    public void addLast(E element) {
        Node<E> linkToLast = last;
        Node<E> newNode = new Node<>(linkToLast, element, null);
        last = newNode;
        if (linkToLast == null)
            first = newNode;
        else
            linkToLast.next = newNode;
        size++;
    }

    private boolean validateIndex(int index) {
        return index >= 0 && index < size;
    }

    private Node<E> findNodeAt(int index) {
        Node<E> current;
        if (index < (size >> 1)) {
            current = first;
            for (int i = 0; i < index; i++)
                current = current.next;
            return current;
        } else {
            current = last;
            for (int i = size - 1; i > index; i--)
                current = current.prev;
            return current;
        }
    }

    public boolean add(int index, E element) {
        if (index == size) {
            addLast(element);
            return true;
        }
        if (!validateIndex(index)) {
            return false;
        }
        Node<E> current = findNodeAt(index);
        Node<E> prevCurrent = current.prev;
        Node<E> newNode = new Node<>(prevCurrent, element, current);
        current.prev = newNode;
        if (prevCurrent == null)
            first = newNode;
        else
            prevCurrent.next = newNode;
        size++;
        return true;
    }

    public E removeFirst() {
        Node<E> linkToFirst = first;
        if (linkToFirst == null) {
            System.out.println("The list is empty. Null was returned.");
            return null;
        }
        E element = linkToFirst.data;
        Node<E> next = linkToFirst.next;
        linkToFirst.data = null;
        linkToFirst.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    public E removeLast() {
        Node<E> linkToLast = last;
        if (linkToLast == null) {
            System.out.println("The list is empty. Null was returned.");
            return null;
        }
        E element = linkToLast.data;
        Node<E> prev = linkToLast.prev;
        linkToLast.data = null;
        linkToLast.prev = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    private void removeNode(Node<E> current) {
        Node<E> next = current.next;
        Node<E> prev = current.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            current.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            current.next = null;
        }

        current.data = null;
        size--;
    }

    public E remove(int index) {
        if (!validateIndex(index)) {
            System.out.println("Invalid index. Null was returned.");
            return null;
        }
        Node<E> current = findNodeAt(index);
        E element = current.data;
        removeNode(current);
        return element;
    }

    public int indexOf(E element) {
        int index = 0;
        if (element == null) {
            for (Node<E> i = first; i != null; i = i.next) {
                if (i.data == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> i = first; i != null; i = i.next) {
                if (element.equals(i.data))
                    return index;
                index++;
            }
        }
        return -1;
    }

    public boolean removeFirstOccurrence(E element) {
        int index = indexOf(element);

        if (index >= 0) {
            Node<E> current = findNodeAt(index);
            removeNode(current);
            return true;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        if (!validateIndex(index)) {
            System.out.println("Invalid index. Null was returned.");
            return null;
        }
        return findNodeAt(index).data;
    }

    public void clear() {
        for (Node<E> i = first; i != null; ) {
            Node<E> next = i.next;
            i.data = null;
            i.next = null;
            i.prev = null;
            i = next;
        }
        first = last = null;
        size = 0;
    }

    private class DoublyLinkedListIterator implements Iterator<E> {
        private Node<E> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E data = current.data;
            current = current.next;
            return data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("size = " + size() + "\n");

        if (size() > 0) {
            result.append("Elements:\n");

            for (E data : this) {
                result.append(data);
                result.append('\n');
            }
        } else {
            result.append("No elements.\n");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(10);
        list.addLast(15);
        list.addLast(23);
        list.addLast(9);

        System.out.println("Initial list:\n" + list);
        System.out.println("list.removeNode(list.findNodeAt(1)):");
        list.removeNode(list.findNodeAt(1));
        System.out.println(list);

        System.out.println("list.validateIndex(2): " + list.validateIndex(2));
        System.out.println("list.validateIndex(5): " + list.validateIndex(5));
    }
}
