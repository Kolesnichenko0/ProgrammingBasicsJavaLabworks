package part1.labwork3.fifth;

import java.util.Arrays;

class SortCircles {
    static void printCircles(Circle[] circles) {
        for(Circle circle : circles) {
            System.out.println(circle);
        }
    }
    public static void main(String[] args) {
        Circle[] circles = {new Circle(0.2),
                new Circle(5.0),
                new Circle(1.0),
                new Circle(23.1),
                new Circle(5.9)};
        System.out.println("The initial array stores the following circles:");
        printCircles(circles);
        System.out.println("After sorting by circle radius:");
        Arrays.sort(circles);
        printCircles(circles);
    }
}
