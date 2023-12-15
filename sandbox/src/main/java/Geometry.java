public class Geometry {
    public static void main(String[] args) {
        printSquareArea(5.0);
        printRectangleArea(5.0, 3.0);
    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("площадь прямоугольника со сторонами " + a + " и " + b + " = " + rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double a) {
        System.out.println("площадь квадрата со стороной " + a + " = " + squareArea(a));
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
