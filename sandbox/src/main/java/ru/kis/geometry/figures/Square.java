package ru.kis.geometry.figures;

public record Square(double side) {
    public Square {
        if (side < 0)
            throw new IllegalArgumentException("Square side length < 0");
    }

    public void printArea() {
        String text = String.format("площадь квадрата со стороной %.2f = %.2f", this.side, this.area());
        System.out.println(text);
    }

    public void printPerimeter() {
        String text = String.format("периметр квадрата со стороной %.2f = %.2f", this.side, this.perimeter());
        System.out.println(text);
    }

    public static void printAreaExt(Square s) {
        s.printArea();
    }

    public static void printPerimeterExt(Square s) {
        s.printPerimeter();
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return this.side * 4;
    }
}
