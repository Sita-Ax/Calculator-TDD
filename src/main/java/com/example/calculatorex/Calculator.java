package com.example.calculatorex;

public class Calculator {
    public int add(int i, int i1) {
        return i + i1;
    }

    public int minus(int i, int i1) {
        return i - i1;
    }

    public int multiply(int i, int i1) {
        return i * i1;
    }

    public int divide(int i, int i1) throws CalculatorException {
        try {
            return i / i1;
        } catch (ArithmeticException e) {
            throw new CalculatorException("Division by zero");
        }
    }

    public static Double mathExpression(String expression) {
        if (expression.startsWith("(") && expression.endsWith(")")) { //if the expression start with ( and end with ) it will go inside
            return mathExpression(expression.substring(1, expression.length() - 1));//and return expression and get rid of the parentheses
        }
        String[] containerArr = new String[]{expression}; //create a string array and defines the size in this case:expression
        double leftVal = getNextOperand(containerArr); //put it in a container ["10*5"] and take out the left value
        expression = containerArr[0]; // put the right value and * in another container
        if (expression.length() == 0) { //leave expression "+(10+5)"
            return leftVal; //return the leftvalue
        }
        char operator = expression.charAt(0); //put expression: '*' to the operator
        expression = expression.substring(1); //leave just 5 in expression
        while (operator == '*' || operator == '/') { //while the operator is a math expression is '*,/'
            containerArr[0] = expression; //it will go here and compare, the containerArr is ["*5"] and the expression is "5"
            double rightVal = getNextOperand(containerArr);// change containerArr to just contains "5"
            expression = containerArr[0]; //leave the expression "5" to emptyArr
            if (operator == '*') { //check the operator and this will give us '*'
                leftVal = leftVal * rightVal; //the leftVal=10.0 and the rightVal=5
            } else {
                leftVal = leftVal / rightVal;
            }
            if (expression.length() > 0) { //if the expression is empty ""
                operator = expression.charAt(0);
                expression = expression.substring(1);
            } else {
                return leftVal; //return the answer 50.0
            }
        }
        if (operator == '+') {
            return leftVal + mathExpression(expression);
        } else {
            return leftVal - mathExpression(expression);
        }

    }

    private static double getNextOperand(String[] exp) {
        double res;
        if (exp[0].startsWith("(")) {
            int open = 1;
            int i = 1;
            while (open != 0) {
                if (exp[0].charAt(i) == '(') {
                    open++;
                } else if (exp[0].charAt(i) == ')') {
                    open--;
                }
                i++;
            }
            res = mathExpression(exp[0].substring(1, i - 1));
            exp[0] = exp[0].substring(i);
        } else {
            int i = 1;
            if (exp[0].charAt(0) == '-') {
                i++;
            }
            while (exp[0].length() > i && isNumber((int) exp[0].charAt(i))) {
                i++;
            }
            res = Double.parseDouble(exp[0].substring(0, i));
            exp[0] = exp[0].substring(i);
        }
        return res;
    }

    private static boolean isNumber(int c) {
        int zero = (int) '0';
        int nine = (int) '9';
        return (c >= zero && c <= nine) || c == '.';
    }


}
