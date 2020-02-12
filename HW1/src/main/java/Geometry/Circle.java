package Geometry;

public class Circle {
    private double Radius;
    private Point Center;

    public Circle(double R, Point C) {
        Radius = R;
        Center = C;
    }

    public double ComputeSquare() {
        return Math.PI * Radius * Radius;
    }
}
