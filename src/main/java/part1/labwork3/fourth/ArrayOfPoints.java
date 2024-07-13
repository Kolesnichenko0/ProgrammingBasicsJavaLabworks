package part1.labwork3.fourth;

public class ArrayOfPoints extends AbstractArrayOfPoints {
    private double[] points = {};

    @Override
    public void setPoint(int i, double x, double y) {
        if (i >= 0 && i * 2 + 1 < points.length) {
            points[i * 2] = x;
            points[i * 2 + 1] = y;
        }
    }

    @Override
    public double getX(int i) {
        if (i >= 0 && i * 2 + 1 < points.length) {
            return points[i * 2];
        }
        return 0;
    }

    @Override
    public double getY(int i) {
        if (i >= 0 && i * 2 + 1 < points.length) {
            return points[i * 2 + 1];
        }
        return 0;
    }

    @Override
    public int count() {
        return points.length / 2;
    }

    @Override
    public void addPoint(double x, double y) {
        double[] newPointsArray = new double[points.length + 2];
        System.arraycopy(points, 0, newPointsArray, 0, points.length);
        newPointsArray[points.length] = x;
        newPointsArray[points.length + 1] = y;
        points = newPointsArray;
    }

    @Override
    public void removeLast() {
        double[] newPointsArray = new double[points.length - 2];
        System.arraycopy(points, 0, newPointsArray, 0, points.length - 2);
        points = newPointsArray;
    }

    @Override
    public void test() {
        addPoint(22, 45);
        addPoint(4, 11);
        addPoint(30, 5.5);
        addPoint(-2, 48);
        addPoint(0, -8);
        System.out.println("The array of points after adding points:");
        System.out.print(this);

        removeLast();
        System.out.println("The array of points after remove last point:");
        System.out.print(this);

        setPoint(1, 7, 12);
        System.out.println("The array of points after writing new coordinates to the point with index 1:");
        System.out.print(this);

        sortByX();
        System.out.println("After sorting by X:");
        System.out.print(this);

        sortByY();
        System.out.println("After sorting by Y:");
        System.out.print(this);
    }
}
