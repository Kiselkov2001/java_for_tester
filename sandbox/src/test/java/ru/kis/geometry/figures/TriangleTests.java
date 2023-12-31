package ru.kis.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-1.0, 4.0, 5.0);
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertTrue(e.getMessage().equals("one or more triangle side lengths < 0"));
        }
    }

    @Test
    void cannotCreateTriangleWithNonConsistentSides() {
        try {
            new Triangle(1.0, 4.0, 5.0);
            Assertions.fail();
        } catch(IllegalArgumentException e){
            Assertions.assertTrue(e.getMessage().equals("triangle side lengths are non-consistent"));
        }
    }

    @Test
    void testEquality() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(4.0, 5.0, 3.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality2() {
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(a, c, b);
        Assertions.assertEquals(triangle, triangle1);
    }

    @Test
    void testEquality3() {
        var t1 = new Triangle(3.0, 3.0, 2.0);
        var t2 = new Triangle(3.0, 2.0, 2.0);
        Assertions.assertNotEquals(t1, t2);
    }

    @Test
    void canCalculationArea() {
        var t = new Triangle(3.0, 4.0, 5.0);
        var result = t.area();
        Assertions.assertEquals(6.0, result);
    }

    @Test
    void canCalculationPerimeter() {
        var result = new Triangle(3.0, 4.0, 5.0).perimeter();
        Assertions.assertEquals(12.0, result);
    }

}
