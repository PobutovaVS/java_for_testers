package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle; //использование класса в другом пакете
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {

        Square.printSquareArea(7.0);
        Square.printSquarePerimeter(7.0);

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectanglePerimeter(3.0,5.0);

        Triangle.printTriangleArea(5.0, 6.0, 7.0);
        Triangle.printTrianglePerimeter(5.0,6.0,7.0);


    }

}
