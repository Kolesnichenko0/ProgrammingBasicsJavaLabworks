public class FunctionH1 {
    private static double f(double x) {
        return x;
    }
    private static double g(double x) {
        return x;
    }
    public static double h(double a, double b, double x) {
        return a * f(x) - g(b * x);
    }
}
