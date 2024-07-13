package part1.labwork3.seventh;

public class TrapezoidalImplementation implements Integrable{
    @Override
    public double someFunction(double x) {
        return Math.sin(x);
//        return Math.log(x);
//        return Math.pow(x,3);
    }

    @Override
    public double integral(double intervalStart, double intervalEnd, double eps) {
        double step = (intervalEnd - intervalStart) * eps;
        double totalArea = 0.0;

        for (double x = intervalStart; x < intervalEnd; x += step) {
            totalArea += 0.5 * step * (someFunction(x) + someFunction(x+step));
        }
        return totalArea;
    }

}
