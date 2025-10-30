package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle; //использование класса в другом пакете
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {

        Square.printSquareArea(new Square(7.0));
        Square.printSquarePerimeter(new Square(7.0));

        Rectangle.printRectangleArea(new Rectangle(5.0, 6.0));
        Rectangle.printRectanglePerimeter(new Rectangle(5.0, 6.0));

        Triangle.printTriangleArea(new Triangle(5.0, 6.0, 7.0));
        Triangle.printTrianglePerimeter(new Triangle(5.0, 6.0, 7.0));

    }

}
