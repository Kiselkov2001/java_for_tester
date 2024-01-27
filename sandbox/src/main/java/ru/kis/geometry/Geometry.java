package ru.kis.geometry;

import ru.kis.geometry.figures.Rectangle;
import ru.kis.geometry.figures.Square;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.Random;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        new Square(5.0).printArea();
        Rectangle.printRectangleArea(5.0, 3.0);

        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(10);

//        Consumer<Square> print = square -> {
//            Square.printAreaExt(square);
//            Square.printPerimeterExt(square);
//        };
//        squares.forEach(print);

        squares.peek(Square::printArea).forEach(Square::printPerimeter);
    }
}
