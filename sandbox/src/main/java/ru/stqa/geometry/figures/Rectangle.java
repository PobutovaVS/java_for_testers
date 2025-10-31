package ru.stqa.geometry.figures;

public record Rectangle(double sideA, double sideB) {

    /*  private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }
*/
    public Rectangle {
        if (sideA < 0 || sideB < 0) {
            throw new IllegalArgumentException("Rectangle side should be non-negative");

        }
    }

    public static void printRectangleArea(Rectangle r) {
        String text = String.format("Плодащь прямоугольника со сторонами  %f и %f = %f", r.sideA, r.sideB, r.area());
        System.out.println(text);
    }

    public static void printRectanglePerimeter(Rectangle r) {
        var text = String.format("Периметр прямоугольника со сторонами  %f и %f = %f", r.sideA, r.sideB, r.perimeter());
        System.out.println(text);
    }

    public double area() {
        return this.sideA * this.sideB;
    }

    public double perimeter() {
        return 2 * (this.sideA + this.sideB);
    }
}
