package ru.stqa.geometry.figures;

public record Triangle(double sideA, double sideB, double sideC) {

    public Triangle {
        if (sideA<0||sideB<0||sideC<0){
           throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if ((sideA+sideB)<sideC||(sideB+sideC)<sideA||(sideA+sideC)<sideB) {
            throw new IllegalArgumentException("The sum of two sides of a triangle cannot be less then the third one");
        }
    }

    public static void printTriangleArea(Triangle t) {
        String text = String.format("Плодащь треугольника со стороной %f и %f и %f =%f", t.sideA, t.sideB, t.sideC, t.area());
        System.out.println(text);
    }

    public static void printTrianglePerimeter(Triangle t) {
        String text = String.format("Плодащь треугольника со стороной %f,%f и %f =%f", t.sideA, t.sideB, t.sideC, t.perimeter());
        System.out.println(text);
    }

    public double area() {
        double p = (this.sideA + this.sideB + this.sideC) / 2;
        return Math.sqrt(p * (p - this.sideA) * (p - this.sideB) * (p - this.sideC));
    }

    public double perimeter() {
        return this.sideA + this.sideB + this.sideC;
    }
}

