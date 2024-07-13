package part1.labwork3.seventh;

public class ImplementationsTester {
    public static void main(String[] args) {
        RectangularImplementation firstIntegrator = new RectangularImplementation();
        TrapezoidalImplementation secondIntegrator = new TrapezoidalImplementation();

        double intervalStart = -Math.PI;
        double intervalEnd = 0;

//        intervalStart = 1;
//        intervalEnd = Math.exp(1);

//        intervalStart = -2;
//        intervalEnd = 2;

        double eps = 0.001;

        System.out.println("The definite integral for the function sin on the interval [-PI; 0] "
                + "with calculation accuracy value(eps) = 0.001:");

//        System.out.println("The definite integral for the function ln(natural logarithm) on the interval [1; exp] "
//                + "with calculation accuracy value(eps) = 0.001:");

//        System.out.println("The definite integral for the function x^3 on the interval [-2; 2] "
//                + "with calculation accuracy value(eps) = 0.001:");
        System.out.println("The rectangle method: " + firstIntegrator.integral(intervalStart, intervalEnd, eps));
        System.out.println("The trapezoid method: " + secondIntegrator.integral(intervalStart, intervalEnd, eps));

        eps = 0.00001;
        System.out.println("The definite integral for the function sin on the interval [-PI; 0] "
                + "with calculation accuracy value(eps) = 0.00001:");

//        System.out.println("The definite integral for the function ln(natural logarithm) on the interval [1; exp] "
//                + "with calculation accuracy value(eps) = 0.00001:");

//        System.out.println("The definite integral for the function x^3 on the interval [-2; 2] "
//                + "with calculation accuracy value(eps) = 0.00001:");
        System.out.println("The rectangle method: " + firstIntegrator.integral(intervalStart, intervalEnd, eps));
        System.out.println("The trapezoid method: " + secondIntegrator.integral(intervalStart, intervalEnd, eps));
    }
}
