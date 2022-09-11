package org.example.calculator;

import org.example.calculator.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 요구사항
 * 간단한 사칙연산을 할 수 있다.
 * 양수로만 계산할 수 있다.
 * 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다. MVC패턴(Model-View-Controller) 기반으로 구현한다
 */
public class CalculatorTest {
    // given - when - then 형식으로 테스트 코드를 작성 한다.
    @DisplayName("덧셈 연산 테스트")
    @Test
    void additionTest() {
        int calculate = Calculator.calculate(1, "+", 2);
        assertThat(calculate).isEqualTo(3);
    }

    @DisplayName("뺄셈 연산 테스트")
    @Test
    void subtractionTest() {
        int calculate = Calculator.calculate(3, "-", 2);
        assertThat(calculate).isEqualTo(1);
    }

    @DisplayName("곱셈 연산 테스트")
    @Test
    void multiplicationTest() {
        int calculate = Calculator.calculate(1, "*", 2);
        assertThat(calculate).isEqualTo(2);
    }

    @DisplayName("나눗셈 연산 테스트")
    @Test
    void divisionTest() {
        int calculate = Calculator.calculate(2, "/", 2);
        assertThat(calculate).isEqualTo(1);
    }

    // 앞서 나열한 테스트 케이스를 하나의 케이스로 요약 하기
    @DisplayName("덧셈 뺄셈 곱셈 나눗셈 테스트")
    @ParameterizedTest
    @MethodSource("fomulaAndResult")
    // 하단 Static 메소드의 아규먼트 값을 인자로 사용 하기 위한 어노테이션
    void calculatorTest(int operand1, String operator, int operand2, int result) {
        int calculateResult = Calculator.calculate(operand1, operator, operand2);
        assertThat(calculateResult).isEqualTo(result);
    }

    private static Stream<Arguments> fomulaAndResult() {
        return Stream.of(
                Arguments.arguments(1, "+", 2, 3),
                Arguments.arguments(2, "*", 2, 4),
                Arguments.arguments(3, "-", 3, 0),
                Arguments.arguments(4, "/", 4, 1)
        );
    }

    @DisplayName("0을 모수로 가지는 연산의 오류 처리")
    @Test
    void diveToZeroTest() {
        assertThatCode(() -> Calculator.calculate(10, "/", 0)) // 0으로 나눌때
                .isInstanceOf(IllegalArgumentException.class) // 해당 Exception이 발생되는 지 확인
                .hasMessage("나눗셈에서 모수가 0인 경우 연산이 불가능 합니다."); // 메세지 내용 까지 확인
    }
}
