package part1.labwork4.fourth;

import java.util.List;
import java.util.Optional;

public class NumberListOperationUtilsTester {
    private static <T extends Number> void printList(List<T> list) {
        System.out.println(list);
    }

    private static <T extends Number> void printLastNegativeElement(Optional<T> lastNegativeElement) {
        if (lastNegativeElement.isPresent()) {
            System.out.println("Last negative element: " + lastNegativeElement.get());
        } else {
            System.out.println("No negative elements found.");
        }
    }

    private static <T extends Number> void testFunctionality(List<T> firstList, List<T> secondList) {
        System.out.println("FirstList:");
        printList(firstList);
        System.out.println("SecondList:");
        printList(secondList);
        System.out.println();

        System.out.println("Testing indexOfFirstZeroElement(firstList):");
        System.out.println(NumberListOperationUtils.indexOfFirstZeroElement(firstList));
        System.out.println("Testing indexOfFirstZeroElement(secondList):");
        System.out.println(NumberListOperationUtils.indexOfFirstZeroElement(secondList) + "\n");

        System.out.println("Testing countNegativeNumbers(firstList):");
        System.out.println(NumberListOperationUtils.countNegativeNumbers(firstList));
        System.out.println("Testing countNegativeNumbers(secondList):");
        System.out.println(NumberListOperationUtils.countNegativeNumbers(secondList) + "\n");

        System.out.println("Testing getLastNegativeElement(firstList):");
        Optional<T> lastNegativeElement = NumberListOperationUtils.getLastNegativeElement(firstList);
        printLastNegativeElement(lastNegativeElement);
        System.out.println("Testing getLastNegativeElement(secondList):");
        lastNegativeElement = NumberListOperationUtils.getLastNegativeElement(secondList);
        printLastNegativeElement(lastNegativeElement);
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> firstListOfIntegers = List.of(-3, 0, 1, -2, -4, 7);
        List<Integer> secondListOfIntegers = List.of(3, 6, 1, 2, 4, 7);
        System.out.println("Testing of NumberListOperationUtils class generic functions for Integer type:");
        testFunctionality(firstListOfIntegers, secondListOfIntegers);

        List<Double> firstListOfDoubles = List.of(-3.2, 0.0, 2.4, -2.9, -7.1, 7.1);
        List<Double> secondListOfDoubles = List.of(3.2, 4.2, 1.1, 3.7, 1.9, 7.2);
        System.out.println("Testing of NumberListOperationUtils class generic functions for Double type:");
        testFunctionality(firstListOfDoubles, secondListOfDoubles);
    }
}
