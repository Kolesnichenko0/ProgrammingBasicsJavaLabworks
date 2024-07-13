package part1.labwork3.seventh;

@FunctionalInterface
public interface Integrable {
    double someFunction(double x);

    default double integral(double intervalStart, double intervalEnd, double eps) {
        double step = (intervalEnd - intervalStart) * eps;
        double totalArea = 0.0;

        for (double x = intervalStart; x < intervalEnd; x += step) {
            totalArea += someFunction(x) * step;
        }
        return totalArea;

    }
}
