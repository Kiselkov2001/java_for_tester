package ru.kis.geometry.figures;

public record Square(double side) {
    public Square {
        if (side < 0 )
            throw new IllegalArgumentException("Square side length < 0");
    }

    public void printSquareArea() {
        String text = String.format("площадь квадрата со стороной %.2f = %.2f", this.side, area());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return this.side * 4;
    }
}
