package org.example.calculator.interfacesolution.impl;

import org.example.calculator.interfacesolution.NewArithmaticOperator;

public class DivisionOperator implements NewArithmaticOperator {
    @Override
    public boolean supports(String operator) {
        return operator.equals("/");
    }

    @Override
    public int calculate(int operand1, int operand2) {
        if(operand1 == 0 || operand2 == 0){
            throw new IllegalArgumentException("나눗셈에서 모수가 0인 경우 연산이 불가능 합니다.");
        }
        return operand1 / operand2;
    }
}
