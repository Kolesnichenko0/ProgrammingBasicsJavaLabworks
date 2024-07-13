package part1.labwork3.third.realisation1;

class CubicFunction extends AbstractFunction {

    @Override
    double someFunction(double x) {
        return Math.pow(x, 3) - 2 * Math.pow(x, 2);
    }
}

class SinFunction extends AbstractFunction {

    @Override
    double someFunction(double x) {
        return Math.sin(x);
    }
}

class MinimumFinder {
    static double findFunctionMin(AbstractFunction function, double intervalStart, double intervalEnd, double step) {
        double minValue = function.someFunction(intervalStart);
        double currentValue = 0;
        for (double x = intervalStart + step; x <= intervalEnd; x += step) {
            currentValue = function.someFunction(x);
            if (currentValue < minValue) {
                minValue = currentValue;
            }
        }

        return minValue;
    }

    public static void main(String[] args) {
        CubicFunction cubicFunction = new CubicFunction();
        SinFunction sinFunction = new SinFunction();
        System.out.println("The minimum value of the cubic function x^3-2x^2 on the interval [-1; 1] with a step 0.01: "
                + findFunctionMin(cubicFunction, -1.0, 1.0, 0.01));
        System.out.println("The minimum value of the sin function on the interval [0; 3pi/2] with a step 0.01: "
                + findFunctionMin(sinFunction, 0.0, 3 * Math.PI / 2, 0.01));
    }
}

