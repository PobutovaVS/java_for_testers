package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var t = new Triangle(5.0, 6.0, 7.0);
        double result = t.area();
        Assertions.assertEquals(14.696938456699069, result);
    }

    @Test
    void canCalculatePerimeter() {
        var t = new Triangle(5.0, 6.0, 7.0);
        double result = t.perimeter();
        Assertions.assertEquals(18.0, result);
    }

    @Test
    void cannotCreateSquareWithNegativeSide() {
        try {
            new Triangle(-5.0, 6.0, 7.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void canSumOfTwoBeBiggerThanThirdSide() {
        try {
            new Triangle(5.0, 6.0, 20.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }
}
