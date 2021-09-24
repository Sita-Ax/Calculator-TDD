package com.example.calculatorex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void test_add_two_number_success() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(15, calculator.add(4, 11));
        assertEquals(10, calculator.add(5, 5));
    }

    @Test
    void test_subtraction_two_number_success() {
        assertEquals(3, calculator.minus(5, 2));
        assertEquals(5, calculator.minus(9, 4));
        assertEquals(15, calculator.minus(20, 5));
    }

    @Test
    void test_multiply_two_multiply_tree_success() {
        int sum = calculator.multiply(2, 3);
        assertEquals(6, sum);
    }

    @Test
    void test_multiply_six_multiply_five_success() {
        int sum = calculator.multiply(6, 5);
        assertEquals(30, sum);
    }

    @Test
    void test_divide_four_divide_two() throws CalculatorException {
        int sum = calculator.divide(4, 2);
        assertEquals(2, sum);
    }

    @Test
    void test_divide_ten_divide_five() throws CalculatorException {
        int sum = calculator.divide(10, 5);
        assertEquals(2, sum);
    }

    @Test
    public void test_calc_failed_because_div_zero() {
        CalculatorException calculatorException = assertThrows(CalculatorException.class, () -> calculator.divide(5, 0));
        assertEquals("Division by zero", calculatorException.getMessage());
    }

    @Test
    void test_string_math_expression() {
        assertEquals(50, calculator.mathExpression("(10*5)"));
        assertEquals(70, calculator.mathExpression("20+(10*5)"));
        assertEquals(33, calculator.mathExpression("((3+2)*2+1)*3"));
        assertEquals(38, calculator.mathExpression("((3+2)*2+1)*3+5"));
        assertEquals(10, calculator.mathExpression("2+8"));
        assertEquals(16, calculator.mathExpression("2*8"));
        assertEquals(6, calculator.mathExpression("8-2"));
        assertEquals(2, calculator.mathExpression("4/2"));
        assertEquals(5015.94, calculator.mathExpression("16+10*500-3.000/50"));
        assertEquals(76.05, calculator.mathExpression("(-5.2+-5*-5*((5/4+2)))"));
    }
}
