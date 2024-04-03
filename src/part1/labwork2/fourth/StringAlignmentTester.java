package part1.labwork2.fourth;

/**
 * The {@code StringAlignmentTester} class presents a command-line program
 * that demonstrates the usage method {@link StringAlignment#alignString}.
 */
public class StringAlignmentTester {
    /**
     * It takes two arguments from the command line: a string and the required length.
     * It validates the command-line arguments.
     * It aligns the input string to the required length.
     * The input string and aligned string are then printed to the console.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("You must pass two arguments: a string and the required length.");
            System.exit(1);
        }
        String inputString = args[0];
        int requiredLength = Integer.parseInt(args[1]);
        String result = StringAlignment.alignString(inputString, requiredLength);

        System.out.println("String before alignment:\n" + "\"" + inputString + "\"");
        System.out.println("String after alignment:\n" + "\"" + result + "\"");
    }
}
