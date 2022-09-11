package org.example.gradecalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class CourseTest {

    @DisplayName("과목 정보를 생성하는 테스트")
    @Test
    void createTEst() {
        assertThatCode(()->new Course("국어", 3, "A+")) // 과목, 학점, 스코어 를 선언한다.
                .doesNotThrowAnyException(); // 아무런 예외 발생이 없다면 정상 종료.
    }
}
