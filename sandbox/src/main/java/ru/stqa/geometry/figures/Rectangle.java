package ru.stqa.geometry.figures;

public class Rectangle {

    public static void printRectangleArea(double a, double b) {
        var text = String.format("Плодащь прямоугольника со сторонами  %f и %f = %f", a, b, area(a, b));
        System.out.println(text);
    }

    public static void printRectanglePerimeter(double a, double b) {
        var text = String.format("Периметр прямоугольника со сторонами  %f и %f = %f", a, b, perimeter(a, b));
        System.out.println(text);
    }

    public static double area(double a, double b) {
        return a * b;
    }

    public static double perimeter(double a, double b) {
        return (a + b) * 2;
    }


}
