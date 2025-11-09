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

    @Test
    void testEquality() {
        var t1=new Triangle(5.0,6.0,7.0);
        var t2=new Triangle(5.0,6.0,7.0);
    Assertions.assertEquals(t1,t2);
    }

    @Test
    void testNonEquality() {
        var t1=new Triangle(5.0,6.0,7.0);
        var t2=new Triangle(5.0,6.0,8.0);
        Assertions.assertNotEquals(t1,t2);
    }

    @Test
    void testEquality2() {
        var t1=new Triangle(5.0,6.0,7.0);
        var t2=new Triangle(5.0,7.0,6.0);
        Assertions.assertEquals(t1,t2);
    }

    @Test
    void testEquality3(){
        var a = 2;
        var b = 3;
        var c = 4;
        var triangle = new Triangle(a, b, c);
        var triangle1 = new Triangle(a, c, b);
        Assertions.assertEquals(triangle, triangle1);
    }
}
