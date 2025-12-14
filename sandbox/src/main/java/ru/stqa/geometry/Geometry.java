package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle; //использование класса в другом пакете
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

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


        Supplier<Rectangle> randomRectangle=()->new Rectangle(new Random().nextDouble(100),new Random().nextDouble(300));
        var rectangles = Stream.generate(randomRectangle).limit(5);
//        Consumer<Rectangle> print1 = rectangle->{
//            Rectangle.printRectangleArea(rectangle);
//            Rectangle.printRectanglePerimeter(rectangle);
//        }
        rectangles.peek(Rectangle::printRectangleArea).forEach(Rectangle::printRectanglePerimeter);



//        Triangle.printTriangleArea(new Triangle(5.0, 6.0, 7.0));
//        Triangle.printTrianglePerimeter(new Triangle(5.0, 6.0, 7.0));
    }

}
