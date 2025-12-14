package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle; //использование класса в другом пакете
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.function.Consumer;

public class Geometry {
    public static void main(String[] args) {

        var squares = List.of(new Square(7.0), new Square(5.0), new Square(3.0));
//        for (Square square : squares) {  //цикл который пробегает по элменетам списка
//            Square.printSquareArea(square); // для каждого элмемнта вызываем данную функцию
//            Square.printSquarePerimeter(square);
//        }


        //функциональный стиль программирования
//        Consumer<Square> print = square ->  //функция, которая принимает на вход параметр, но ничего не возвращает
//                Square.printSquareArea(square);
//        squares.forEach(print);


        Consumer<Square> print = Square::printSquareArea;
        squares.forEach(print);

//        Rectangle.printRectangleArea(new Rectangle(5.0, 6.0));
//        Rectangle.printRectanglePerimeter(new Rectangle(5.0, 6.0));
//
//        Triangle.printTriangleArea(new Triangle(5.0, 6.0, 7.0));
//        Triangle.printTrianglePerimeter(new Triangle(5.0, 6.0, 7.0));
    }

}
