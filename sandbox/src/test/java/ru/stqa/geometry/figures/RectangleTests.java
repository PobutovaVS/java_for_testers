package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void canCalculateArea() {
        var r = new Rectangle(5.0, 6.0);
        double result = r.area();
        Assertions.assertEquals(30.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        var r = new Rectangle(5.0, 6.0);
        double result = r.perimeter();
        Assertions.assertEquals(24.0, result);
    }

    @Test
    void cannotCreateSquareWithNegativeSide() {
        try {
            new Rectangle(-5.0, 6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void testEquality() {
        var r1 = new Rectangle(5.0, 4.0);
        var r2 = new Rectangle(5.0, 4.0);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testEquality2() {
        var r1 = new Rectangle(5.0, 4.0);
        var r2 = new Rectangle(4.0, 5.0);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testPass() {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertTrue(s1.equals(s2));
    }

}
