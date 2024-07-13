package part2.labwork6.first.model;

public class FunctionParameters {
    private final double a;
    private final double b;
    private final double from;
    private final double to;
    private final double step;

    public FunctionParameters(double a, double b, double from, double to, double step) {
        if (from > to) {
            throw new IllegalArgumentException("Invalid input: 'From' must be less than 'To'.");
        }
        if (step <= 0) {
            throw new IllegalArgumentException("Invalid input: 'Step' must be greater than 0.");
        }
        this.a = a;
        this.b = b;
        this.from = from;
        this.to = to;
        this.step = step;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }
    public double getStep() {
        return step;
    }
}
