package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        double result = Triangle.area(5.0, 6.0, 7.0);
        Assertions.assertEquals(14.696938456699069, result);
    }

    @Test
    void canCalculatePerimeter() {
        double result = Triangle.perimeter(5.0, 6.0, 7.0);
        Assertions.assertEquals(18.0, result);
    }
}
