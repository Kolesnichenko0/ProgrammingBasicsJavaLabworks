package part1.labwork1.fourth.converter;

/**
 * A class that contains static functions for converting between types integer and boolean
 */
public class BooleanConverter {
    /**
     * Converts an integer to a boolean value.
     *
     * @param intArgument Value of type int (0 or 1)
     * @return Value of type boolean (false or true)
     */
    public static boolean intToBoolean(int intArgument) { return intArgument != 0; }

    /**
     * Converts a boolean to an integer value.
     *
     * @param booleanArgument Value of type boolean (false or true)
     * @return Value of type int (0 or 1)
     */
    public static int booleanToInt(boolean booleanArgument) {
        return booleanArgument ? 1 : 0;
    }
}
