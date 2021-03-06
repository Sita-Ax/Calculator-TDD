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


    public static double mathExpression(String expression) {
        prioritizeBracketsContains(expression);
        String[] containerArr = new String[]{expression}; //create a string array and defines the size in this case:expression
        double leftVal = getNextOperand(containerArr); //put it in a container ["10*5"] and take out the left value
        expression = containerArr[0]; // put the right value and * in another container
        Double leftVal1 = arrayContentChecker(expression, leftVal);
        if (leftVal1 != null) return leftVal1;
        char operator = expression.charAt(0); //put expression: '*' to the operator
        expression = expression.substring(1); //leave just 5 in expression
        return multiOrDivide(expression, containerArr, leftVal, operator);
    }

    private static double multiOrDivide(String expression, String[] containerArr, double leftVal, char operator) {
        while (operator == '*' || operator == '/') {
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
        return addOrSub(expression, leftVal, operator);
    }

    private static Double arrayContentChecker(String expression, double leftVal) {
        if (expression.length() == 0) {
            return leftVal;
        }
        return null;
    }

    private static void prioritizeBracketsContains(String expression) {
        if (expression.startsWith("(") && expression.endsWith(")")) {
            mathExpression(expression.substring(1, expression.length() - 1));
        }
    }

    private static double addOrSub(String expression, double leftVal, char operator) {
        if (operator == '+') {
            return leftVal + mathExpression(expression);
        } else {
            return leftVal - mathExpression(expression);
        }
    }

    private static double getNextOperand(String[] exp) {
        double res;
        if (exp[0].startsWith("(")) {
            res = findEndBracket(exp);
        } else {
            res = extractNumber(exp);
        }
        return res;
    }

    private static double extractNumber(String[] exp) {
        double res;
        int i = 1;
        if (exp[0].charAt(0) == '-') {
            i++;
        }
        while (exp[0].length() > i && isNumber((int) exp[0].charAt(i))) {
            i++;
        }
        res = Double.parseDouble(exp[0].substring(0, i));
        exp[0] = exp[0].substring(i);
        return res;
    }

    private static double findEndBracket(String[] exp) {
        double res;
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
        return res;
    }

    private static boolean isNumber(int c) {
        int zero = (int) '0';
        int nine = (int) '9';
        return (c >= zero && c <= nine) || c == '.';
    }


}
