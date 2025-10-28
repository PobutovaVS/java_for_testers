package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void canCalculateArea() {
        double result = Rectangle.area(5.0, 7.0);
        Assertions.assertEquals(35.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        double result = Rectangle.perimeter(5.0, 7.0);
        Assertions.assertEquals(24.0, result);
    }

}
