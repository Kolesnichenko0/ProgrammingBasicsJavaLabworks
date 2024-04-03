package part1.labwork3.fourth;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Testing PointsArray class where points are stored"
                + " in a two-dimensional array of real numbers:");
        new PointsArray().test();
        System.out.println("Testing ArrayOfPoints class where points are stored"
                + " in a one-dimensional array of real numbers:");
        new ArrayOfPoints().test();
    }
}
