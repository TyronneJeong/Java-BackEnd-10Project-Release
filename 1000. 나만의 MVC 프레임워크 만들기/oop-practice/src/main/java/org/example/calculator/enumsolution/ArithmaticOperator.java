package org.example.calculator.enumsolution;

import java.util.Arrays;

public enum ArithmaticOperator {
    ADDITION("+") {
        @Override
        public int arithmaticCalculate(int operand1, int operand2) {
            return operand1 + operand2;
        }
    },
    SUBTRACTION("-") {
        @Override
        public int arithmaticCalculate(int operand1, int operand2) {
            return operand1 - operand2;
        }
    },
    MULTIPLICATION("*") {
        @Override
        public int arithmaticCalculate(int operand1, int operand2) {
            return operand1 * operand2;
        }
    },
    DIVISION("/") {
        @Override
        public int arithmaticCalculate(int operand1, int operand2) {
            return operand1 / operand2;
        }
    };

    private final String operator;

    ArithmaticOperator(String operator) {
        this.operator = operator;
    }

    public abstract int arithmaticCalculate(final int operand1, final int operand2);

    public static int calculate(int operand1, String operator, int operand2) {
        // ArithmaticOperator 생성자를 현재 enum class에 선언된 자료형 중 operator 와 일치하는 값으로 초기화 시킨다.
        ArithmaticOperator arithmaticOperator = Arrays.stream(values())
                .filter(v -> v.operator.equals(operator))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));

        // enum 에 override 함수 까지를 이미 가져온 상황에서 인자 1, 2를 넘겨 사칙연산을 수행한다.
        return arithmaticOperator.arithmaticCalculate(operand1, operand2);
    }
}
