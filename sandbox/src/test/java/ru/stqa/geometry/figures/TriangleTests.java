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
}
