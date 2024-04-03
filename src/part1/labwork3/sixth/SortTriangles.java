package part1.labwork3.sixth;

import java.util.Arrays;
import java.util.Comparator;

public class SortTriangles {
    static void printTriangles(Triangle[] triangles) {
        for(Triangle triangle : triangles) {
            System.out.printf("%s Area = %.2f sq.m.%n", triangle, triangle.calculateArea());
        }
    }
    public static void main(String[] args) {
        Triangle[] triangles = {new Triangle(4.0, 5.0, 7.0),
                new Triangle(14.0, 13.0, 15.0),
                new Triangle(3.2,5.2, 5.0),
                new Triangle(9.9, 10.2, 12.3)};
        System.out.println("The initial array contains the following triangles:");
        printTriangles(triangles);
        System.out.println("After sorting by decreasing triangle area:");
        Arrays.sort(triangles, new Comparator<Triangle>() {
            @Override
            public int compare(Triangle t1, Triangle t2) {
                return Double.compare(t2.calculateArea(), t1.calculateArea());
            }
        });
        printTriangles(triangles);
    }
}
