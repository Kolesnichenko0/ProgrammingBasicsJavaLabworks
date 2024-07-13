package part2.labwork2.fifth;

public class StringProcessor {
    public static final String DIGITS_PATTERN = "\\d+";

    public String[] getSubstrings(String inputString) {
        return inputString.split(DIGITS_PATTERN);
    }
}
