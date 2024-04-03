package part1.labwork3.third.realisations2to5;

class CubicFunction implements Function {

    @Override
    public double someFunction(double x) {
        return Math.pow(x, 3) - 2 * Math.pow(x, 2);
    }
}

class SinFunction implements Function {

    @Override
    public double someFunction(double x) {
        return Math.sin(x);
    }
}

class MinimumFinder {
    static double findFunctionMin(Function function, double intervalStart, double intervalEnd, double step) {
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

    static void useOrdinaryClasses() {
        CubicFunction cubicFunction = new CubicFunction();
        SinFunction sinFunction = new SinFunction();
        System.out.println("First result value: "
                + findFunctionMin(cubicFunction, -1.0, 1.0, 0.01));
        System.out.println("Second result value: "
                + findFunctionMin(sinFunction, 0.0, 3 * Math.PI / 2, 0.01));
    }

    static void useAnonymousClasses() {
        System.out.println("First result value: "
                + findFunctionMin(new Function() {
            @Override
            public double someFunction(double x) {
                return Math.pow(x, 3) - 2 * Math.pow(x, 2);
            }
        }, -1.0, 1.0, 0.01));
        System.out.println("Second result value: "
                + findFunctionMin(new Function() {
            @Override
            public double someFunction(double x) {
                return Math.sin(x);
            }
        }, 0.0, 3 * Math.PI / 2, 0.01));
    }

    static void useLambdaExpressions() {
        System.out.println("First result value: "
                + findFunctionMin(x -> Math.pow(x, 3) - 2 * Math.pow(x, 2), -1.0,
                1.0, 0.01));
        System.out.println("Second result value: "
                + findFunctionMin(x -> Math.sin(x), 0.0,
                3 * Math.PI / 2, 0.01));
    }

    static double cubicFunction(double x) {
        return Math.pow(x, 3) - 2 * Math.pow(x, 2);
    }
    static void useMethodReferences() {
        System.out.println("First result value: "
                + findFunctionMin(MinimumFinder::cubicFunction, -1.0,
                1.0, 0.01));
        System.out.println("Second result value: "
                + findFunctionMin(Math::sin, 0.0,
                3 * Math.PI / 2, 0.01));
    }

    public static void main(String[] args) {
        System.out.println("The minimum value of the cubic function x^3-2x^2 on the interval [-1; 1] with a step 0.01 = first result value\n"
                + "The minimum value of the sin function on the interval [0; 3pi/2] with a step 0.01 = second result value\n\n"
                + "With the creation of separate classes that implement the interface:");
        useOrdinaryClasses();
        System.out.println("Using anonymous classes:");
        useAnonymousClasses();
        System.out.println("Using lambda expressions:");
        useLambdaExpressions();
        System.out.println("Using method references:");
        useMethodReferences();
    }
}
