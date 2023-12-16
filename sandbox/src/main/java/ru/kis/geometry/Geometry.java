package ru.kis.geometry;

import ru.kis.geometry.figures.Rectangle;
import ru.kis.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        new Square(5.0).printSquareArea();
        Rectangle.printRectangleArea(5.0, 3.0);
    }
}
