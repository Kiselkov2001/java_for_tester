package ru.kis.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    void canCalculationArea() {
        var s = new Square(5.0);
        var result = s.area();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculationPerimeter() {
        var result = new Square(5.).perimeter();
        Assertions.assertEquals(20.0, result);
    }

}
