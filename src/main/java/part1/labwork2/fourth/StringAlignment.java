package part1.labwork2.fourth;

/**
 * The {@code StringAlignment} class provides a method to align a string
 * by adding spaces using method {@code addSpaces}.
 */
class StringAlignment {
    /**
     * Spaces are added evenly at the beginning and end of a line if it is a single word and,
     * if there are more words, evenly between words.
     * @param words       the array of words from the string
     * @param totalSpaces the total number of spaces to be added
     * @return the string with added spaces
     */
    private static String addSpaces(String[] words, int totalSpaces) {
        if (words.length == 1) {
            int beforeWordLength;
            if (totalSpaces % 2 == 0) {
                beforeWordLength = totalSpaces / 2;
            } else {
                beforeWordLength = totalSpaces / 2 + 1;
            }
            int afterWordLength = totalSpaces - beforeWordLength;
            return " ".repeat(beforeWordLength) + words[0] + " ".repeat(afterWordLength);
        } else {
            int spacesBetweenWords = totalSpaces / (words.length - 1);
            int remainingSpaces = totalSpaces % (words.length - 1);
            StringBuilder resultString = new StringBuilder();

            resultString.append(words[0]);
            for (int i = 1; i < words.length; i++) {
                resultString.append(" ".repeat(spacesBetweenWords));
                if (remainingSpaces != 0) {
                    resultString.append(" ");
                    remainingSpaces--;
                }
                resultString.append(words[i]);
            }
            return resultString.toString();
        }
    }

    /**
     * Aligns the inputString by adding spaces using method {@code addSpaces}.
     * It validates value of arguments: {@code inputString} and {@code requiredLength}.
     * The value of the {@code requiredLength} must be greater than the {@code inputString} length.
     * The {@code inputString} must not start and/or end with a space character.
     * There should be one space between words, if not, the {@code inputString} will be redone
     * with one space between each word,
     * but any spaces removed will be taken into account when adding spaces evenly.
     * @param inputString    the input string to be aligned
     * @param requiredLength the required length of the aligned string
     * @return the aligned string
     */
    static String alignString(String inputString, int requiredLength) {
        if (inputString.length() >= requiredLength) {
            System.err.println("The value of the required length is invalid.");
            System.exit(1);
        }
        if (inputString.startsWith(" ") || inputString.endsWith(" ")) {
            System.err.println("The string must not start and/or end with a space character.");
            System.exit(1);
        }
        while (inputString.indexOf("  ") >= 0) {
            inputString = inputString.replaceAll("  ", " ");
        }
        String[] words = inputString.split(" ");
        int totalSpaces = requiredLength - inputString.length() + words.length - 1;
        return addSpaces(words, totalSpaces);
    }
}
