package org.example.gradecalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * 요구사항
 * 평균학점 계산 방법 = (학점수×교과목 평점)의 합계/수강신청 총학점 수
 * MVC패턴(Model-View-Controller) 기반으로 구현한다.
 * 일급 컬렉션 사용
 */
public class GradeCalculatorTest {
    // 1. 도메인을 구성하는 객체에는 어떤 것들이 있는지 고민
    // 학점계산기 도메인 : 이수한 과목, 학점 계산

    // 2. 객체들 간의 관계를 고민

    // 3. 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링 하기
    // 이수한 과목 : 국어, 영어, 수학, 과학 --> 과목(코스) 클래스로 추상화 한다.
    @DisplayName("평균 학점 계산")
    @Test
    void calculateGradeTest() {
        List<Course> courses = List.of(new Course("국어", 3, "A+"), // 4.5
                new Course("수학", 3, "C"), // 2.0
                new Course("영어", 3, "B"), // 3.0
                new Course("과학", 3, "A")); // 4.0

        GradeCalculator gradeCalculator = new GradeCalculator(new Courses(courses)); // List를 인자로 전달하여 초기화 한다.
        double gradeResult = gradeCalculator.calculateGrade(); // List인자들의 학점 정보를 호출 한다.
        assertThat(gradeResult).isEqualTo(3.375); // 예상 값과의 비교를 진행한다.
    }
}
