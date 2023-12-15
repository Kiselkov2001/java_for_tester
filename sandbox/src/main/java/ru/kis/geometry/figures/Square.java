package ru.kis.geometry.figures;

public class Square {
    public static void printSquareArea(double a) {
        String text = String.format("площадь квадрата со стороной %.2f = %.2f", a, squareArea(a));
        System.out.println(text);
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
