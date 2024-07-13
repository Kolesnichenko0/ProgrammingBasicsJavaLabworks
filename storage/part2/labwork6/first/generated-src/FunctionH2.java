public class FunctionH2 {
    private static double f(double x) {
        return Math.pow(x,2);
    }
    private static double g(double x) {
        return Math.sin(x) + 5;
    }
    public static double h(double a, double b, double x) {
        return a * f(x) - g(b * x);
    }
}
