package part1.labwork5.second;

public class NonPositiveIntegerException extends Exception {
    private static final long serialVersionUID = 234122996006267687L;

    public NonPositiveIntegerException() {
        super();
    }

    public NonPositiveIntegerException(String s) {
        super(s);
    }

    public NonPositiveIntegerException(int wrongValue) {
        super("Wrong value: " + wrongValue + ". The value must be positive.");
    }
}