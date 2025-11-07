package ru.stqa.geometry.figures;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(sideA, rectangle.sideA) == 0 && Double.compare(sideB, rectangle.sideB) == 0)
                || (Double.compare(sideA, rectangle.sideB) == 0 && Double.compare(sideB, rectangle.sideA) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB);
    }
}
