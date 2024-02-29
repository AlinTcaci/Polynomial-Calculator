package org.example;

import data_models.Polynomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Comparator;
import java.util.TreeMap;

import static data_models.Operations.*;

public class OperationsTest {
    Polynomial p1 = new Polynomial();
    Polynomial p2 = new Polynomial();
    Polynomial p3 = new Polynomial();

    @BeforeEach
    void parameters() {
        TreeMap<Integer, Double> map1 = new TreeMap<>(Comparator.reverseOrder());
        TreeMap<Integer, Double> map2 = new TreeMap<>(Comparator.reverseOrder());
        TreeMap<Integer, Double> map3 = new TreeMap<>(Comparator.reverseOrder());
        p1 = new Polynomial();
        p2 = new Polynomial();
        p3 = new Polynomial();

        // 5x^3-2x^2+x
        map1.put(3, 5.0);
        map1.put(2, -2.0);
        map1.put(1, 1.0);
        // 2x^2+5
        map2.put(2, 2.0);
        map2.put(0, 5.0);
        // -x^3+5x-3
        map3.put(3, -1.0);
        map3.put(1, 5.0);
        map3.put(0, -3.0);

        p1.setPolynomial(map1);
        p2.setPolynomial(map2);
        p3.setPolynomial(map3);
    }

    @Test
    public void testAdd() {
        String expectedResult1 = "5.0x^3+x+5.0";
        String expectedResult2 = "4.0x^3-2.0x^2+6.0x-3.0";
        String expectedResult3 = "-x^3+2.0x^2+5.0x+2.0";
        Assertions.assertEquals(expectedResult1, Polynomial.toString(add(p1, p2)));
        Assertions.assertEquals(expectedResult2, Polynomial.toString(add(p1, p3)));
        Assertions.assertEquals(expectedResult3, Polynomial.toString(add(p2, p3)));
    }

    @Test
    public void testSubtract() {
        String expectedResult1 = "5.0x^3-4.0x^2+x-5.0";
        String expectedResult2 = "6.0x^3-2.0x^2-4.0x+3.0";
        String expectedResult3 = "x^3+2.0x^2-5.0x+8.0";
        Assertions.assertEquals(expectedResult1, Polynomial.toString(diff(p1, p2)));
        Assertions.assertEquals(expectedResult2, Polynomial.toString(diff(p1, p3)));
        Assertions.assertEquals(expectedResult3, Polynomial.toString(diff(p2, p3)));
    }

    @Test
    public void testMultiply() {
        String expectedResult1 = "10.0x^5-4.0x^4+27.0x^3-10.0x^2+5.0x";
        String expectedResult2 = "-5.0x^6+2.0x^5+24.0x^4-25.0x^3+11.0x^2-3.0x";
        String expectedResult3 = "-2.0x^5+5.0x^3-6.0x^2+25.0x-15.0";
        Assertions.assertEquals(expectedResult1, Polynomial.toString(multiply(p1, p2)));
        Assertions.assertEquals(expectedResult2, Polynomial.toString(multiply(p1, p3)));
        Assertions.assertEquals(expectedResult3, Polynomial.toString(multiply(p2, p3)));
    }

    @Test
    public void testDerivate() {
        String expectedResult1 = "15.0x^2-4.0x+1.0";
        String expectedResult2 = "4.0x";
        String expectedResult3 = "-3.0x^2+5.0";
        Assertions.assertEquals(expectedResult1, Polynomial.toString(derivative(p1)));
        Assertions.assertEquals(expectedResult2, Polynomial.toString(derivative(p2)));
        Assertions.assertEquals(expectedResult3, Polynomial.toString(derivative(p3)));
    }

    @Test
    public void testIntegrate() {
        String expectedResult1 = "1.25x^4-0.67x^3+0.5x^2";
        String expectedResult2 = "0.67x^3+5.0x";
        String expectedResult3 = "-0.25x^4+2.5x^2-3.0x";
        Assertions.assertEquals(expectedResult1, Polynomial.toString(integrate(p1)));
        Assertions.assertEquals(expectedResult2, Polynomial.toString(integrate(p2)));
        Assertions.assertEquals(expectedResult3, Polynomial.toString(integrate(p3)));
    }

    @Test
    public void testDivision() {
        String expectedResult1 = "quotient = 2.5x-1.0 rest = -11.5x+5.0";
        String expectedResult2 = "quotient = -5.0 rest = -2.0x^2+26.0x-15.0";
        String expectedResult3 = "quotient = 0 rest = 2.0x^2+5.0";
        Assertions.assertEquals(expectedResult1, divide(p1, p2));
        Assertions.assertEquals(expectedResult2, divide(p1, p3));
        Assertions.assertEquals(expectedResult3, divide(p2, p3));
    }
}