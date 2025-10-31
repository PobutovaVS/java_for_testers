package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.IllformedLocaleException;

public class SquareTests {

    @Test
    void canCalculateArea() {
        var s=new Square(5.0); // создание объекта
        double result = s.area();
       // Assertions.assertEquals(25.0, result);
        if (result!=25.0) {
            throw new AssertionError(String.format("Expexted %f, actual %f", 25.0, result));  // выбросить икслючение
        }
    }

    @Test
    void canCalculatePerimeter() {
        var s=new Square(5.0);
        double result=s.perimeter();
        Assertions.assertEquals(20.0, new Square(5.0).perimeter());
    }

    @Test
    void cannotCreateSquareWithNegativeSide() {
        try {
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        //OK
        }
        }



}
