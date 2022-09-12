package org.example.calculator.interfacesolution.impl;

import org.example.calculator.interfacesolution.NewArithmaticOperator;

public class MultiplicationOperator implements NewArithmaticOperator {
    @Override
    public boolean supports(String operator) {
        return operator.equals("*");
    }

    @Override
    public int calculate(int operand1, int operand2) {
        return operand1 * operand2;
    }
}
