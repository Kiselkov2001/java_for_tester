package ru.kis.geometry.figures;

import java.util.Objects;

public record Rectangle(double a, double b) {

    public Rectangle {
        if (a < 0 || b < 0)
            throw new IllegalArgumentException("one or more rectangle side length < 0");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(a, rectangle.a) == 0 && Double.compare(b, rectangle.b) == 0)
                || (Double.compare(b, rectangle.a) == 0 && Double.compare(a, rectangle.b) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    public static void printRectangleArea(double a, double b) {
        String text = String.format("площадь прямоугольника со сторонами %.2f, %.2f = %.2f", a, b, area(a, b));
        System.out.println(text);
    }

    public static double area(double a, double b) {
        return a * b;
    }
}
