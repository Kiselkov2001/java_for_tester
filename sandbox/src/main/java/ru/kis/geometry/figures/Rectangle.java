package ru.kis.geometry.figures;

public record Rectangle(double a, double b) {
    public static void printRectangleArea(double a, double b) {
        String text = String.format("площадь прямоугольника со сторонами %.2f, %.2f = %.2f", a, b, area(a, b));
        System.out.println(text);
    }

    public static double area(double a, double b) {
        return a * b;
    }
}
