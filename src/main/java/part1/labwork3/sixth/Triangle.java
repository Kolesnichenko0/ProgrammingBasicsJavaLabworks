package part1.labwork3.sixth;

class Triangle {
    private double sideA, sideB, sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public double calculateArea() {
        return 0.25 * Math.sqrt((getSideA() + getSideB() + getSideC()) * (getSideB() + getSideC() - getSideA())
                * (getSideA() + getSideC() - getSideB()) * (getSideA() + getSideB() - getSideC()));
    }
    @Override
    public String toString() {
        return "Triangle with sides " + getSideA() + "; " + getSideB() + "; " +getSideC() + ".";
    }
}
