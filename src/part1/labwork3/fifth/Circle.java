package part1.labwork3.fifth;

class Circle implements Comparable<Circle>{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public int compareTo(Circle other) {
        return Double.compare(this.getRadius(), other.getRadius());
    }

    @Override
    public String toString() {
        return "Circle with radius = " + this.getRadius() + "m.";
    }
}
