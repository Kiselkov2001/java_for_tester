package ru.kis.geometry.figures;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0)
            throw new IllegalArgumentException("one or more triangle side lengths < 0");
        if (a + b >= c || a + c >= b || b + c >= a)
            throw new IllegalArgumentException("triangle side lengths are non-consistent");
    }

    public double area() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public double perimeter() {
        return a + b + c;
    }
}
