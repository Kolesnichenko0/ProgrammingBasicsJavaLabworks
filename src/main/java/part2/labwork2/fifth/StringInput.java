package part2.labwork2.fifth;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringInput {
    public static final String STRING_PATTERN = "^[a-zA-Z0-9]{21,}$";

    private static boolean isValidInput(String input) {
        Pattern pattern = Pattern.compile(STRING_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the string that is more than 20 characters " +
                "long and contains letters and numbers: ");
        String str = scan.nextLine();

        if (!isValidInput(str)) {
            System.out.println("Error: The input string does not meet the requirements.");
            return;
        }

        StringProcessor processor = new StringProcessor();
        String[] substrings = processor.getSubstrings(str);
        if (substrings.length == 0) {
            System.out.println("No substrings found.");
            return;
        }
        System.out.println("Result:");
        for (String substring : substrings) {
            System.out.println("Substring: " + substring);
        }
    }
}