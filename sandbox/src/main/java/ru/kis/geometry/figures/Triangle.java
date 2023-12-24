package ru.kis.geometry.figures;

import java.util.Collection;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0)
            throw new IllegalArgumentException("one or more triangle side lengths < 0");
        if (a + b <= c || a + c <= b || b + c <= a)
            throw new IllegalArgumentException("triangle side lengths are non-consistent");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        ArrayList<Double> list1 = new ArrayList<>(List.of(a, b, c));
        ArrayList<Double> list2 = new ArrayList<>(List.of(triangle.a, triangle.b, triangle.c));
        return (list1.containsAll(list2));
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    public double area() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public double perimeter() {
        return a + b + c;
    }
}
