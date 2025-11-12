package ru.stqa.geometry.figures;

public record Square(
        double side) { //можно испольозовать record вместо class и после имени класса указывается набор свйоств

    /*   private double side; //описание структуры объекта перечисляет его свойства

       public Square(double side) { //конструктор присваивает знаяения параматеров в эти свойства
           this.side = side; //свойство объекта= параметр функции
       }
    */
    public Square {
        if (side < 0) {
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }

    public static void printSquareArea(Square s) {
        String text = String.format("Плодащь квадрата со стороной %f = %f ", s.side, s.area());
        System.out.println(text);
    }

    public static void printSquarePerimeter(Square s) {
        String text = String.format("Плодащь квадрата со стороной %f = %f ", s.side, s.perimeter());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return 4 * this.side;
    }
}
