package org.example.calculator;

import org.example.calculator.interfacesolution.*;
import org.example.calculator.interfacesolution.impl.AdditionOperator;
import org.example.calculator.interfacesolution.impl.DivisionOperator;
import org.example.calculator.interfacesolution.impl.MultiplicationOperator;
import org.example.calculator.interfacesolution.impl.SubtractionOperator;

import java.util.List;

public class Calculator {
    private static final List<NewArithmaticOperator> arithmaticOperators = List.of(new AdditionOperator(),
            new SubtractionOperator(),
            new MultiplicationOperator(),
            new DivisionOperator());

    public static int calculate(int operand1, String operator, int operand2) {
        // 단순 로직의 나열
//         if (operator.equals("+")) {
//             return operand1 + operand2;
//         } else if (operator.equals("-")) {
//             return operand1 - operand2;
//         } else if (operator.equals("*")) {
//             return operand1 * operand2;
//         } else if (operator.equals("/")) {
//             return operand1 / operand2;
//         }
//         return -1;

        // enum을 이용한 리팩토링 1
//        return ArithmaticOperator.calculate(operand1, operator, operand2);

        // 인터페이스를 이용한 리팩토링 2
        return arithmaticOperators.stream()
                .filter(arithmaticOperators -> arithmaticOperators.supports(operator)) // 호출된 operator 를 선택(필터링) 한다.
                .map(arithmaticOperators -> arithmaticOperators.calculate(operand1, operand2)) // 선택된 operator 에 인자 1과 인자2를 전달한다.
                .findFirst() // 연산 결과 항목이 존재하는 경우 정상
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙 연산이 아닙니다.")); // 결과가 존재하지 않을경우 Exception을 발생시킨다.
    }
}
