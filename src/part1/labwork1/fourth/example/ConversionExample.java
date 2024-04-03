package part1.labwork1.fourth.example;

import part1.labwork1.fourth.converter.BooleanConverter;

import static part1.labwork1.fourth.converter.BooleanConverter.intToBoolean;

/**
 * The class represents a console program for testing static functions of the BooleanConverter class.
 * The call to these functions is dependent on the import.
 */
public class ConversionExample {
    public static void main(String[] args) {
        System.out.println("Ordinary import:");
        boolean booleanArgument1 = true;
        System.out.println("First value of type boolean: " + booleanArgument1);
        System.out.println("Converted value of type int: " + BooleanConverter.booleanToInt(booleanArgument1));
        boolean booleanArgument2 = false;
        System.out.println("Second value of type boolean: " + booleanArgument2);
        System.out.println("Converted value of type int: " + BooleanConverter.booleanToInt(booleanArgument2) + "\n");

        System.out.println("Static import:");
        int intArgument1 = 1;
        System.out.println("First value of type int: " + intArgument1);
        System.out.println("Converted value of type boolean: " + intToBoolean(intArgument1));
        int intArgument2 = 0;
        System.out.println("Second value of type int: " + intArgument2);
        System.out.println("Converted value of type boolean: " + intToBoolean(intArgument2));
    }
}
