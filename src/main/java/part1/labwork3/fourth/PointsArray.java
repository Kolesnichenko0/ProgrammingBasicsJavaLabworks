package part1.labwork3.fourth;

public class PointsArray extends AbstractArrayOfPoints {
    private double[][] points = new double[0][2];

    @Override
    public void setPoint(int i, double x, double y) {
        if (i >= 0 && i < count()) {
            points[i][0] = x;
            points[i][1] = y;
        }
    }

    @Override
    public double getX(int i) {
        if (i >= 0 && i < count()) {
            return points[i][0];
        }
        return 0;
    }

    @Override
    public double getY(int i) {
        if (i >= 0 && i < count()) {
            return points[i][1];
        }
        return 0;
    }

    @Override
    public int count() {
        return points.length;
    }

    @Override
    public void addPoint(double x, double y) {
        double[][] newPointsArray = new double[count() + 1][2];
        System.arraycopy(points, 0, newPointsArray, 0, count());
        newPointsArray[count()][0] = x;
        newPointsArray[count()][1] = y;
        points = newPointsArray;
    }

    @Override
    public void removeLast() {
        double[][] newPointsArray = new double[count() - 1][2];
        System.arraycopy(points, 0, newPointsArray, 0, count() - 1);
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
