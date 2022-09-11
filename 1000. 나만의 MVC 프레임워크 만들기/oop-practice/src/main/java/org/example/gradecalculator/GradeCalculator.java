package org.example.gradecalculator;

import java.util.List;

public class GradeCalculator {

    // AS-IS 일반적인 리스트 객체 선언
    //    private final List<Course> courses;

    // TO-BE 일급콜렉션으로 대체
    private final Courses courses;

    // AS-IS 리스트 객체를 대입 받아 초기화
    //    public GradeCalculator(List<Course> courses) {
//        this.courses = courses;
//    }

    // TO-BE 일급 콜렉션을 통해 초기화
    public GradeCalculator(Courses courses) {
        this.courses = courses;
    }

    /**
     * 요구사항
     * 평균학점 계산 방법 = 전체과목의 (학점의 수 * 교과목의 평점) 합계 / 수강신청된 총 학점의 수
     * 일급 컬렉션 사용
     * - 일급 컬렉션이란?
     * ㄴ 특정 비즈니스 목적에 맞는 이름을 클래스명으로 선언하고 콜렉션 자료형를 특정 메서드를 통해서 자료에 접근 하도록 한 형태.
     * ㄴ 클래스에 자료형은 지정 자료형 하나 뿐이며, 해당 자료에 논리적인 이름을 부여하고 접근가능 루트를 단순화 시 콜렉션의 불변성을 보장하게 된다.
     */
    public double calculateGrade() {
        // AS-IS 개별 객체 순환을 통해 계산 하던 값들을
//        for (Course course : courses) {
        // 해당 값의 계산은 계산기에서 하는 것도 나쁘지 않지만 값의 근원지 자체가 Course이므로 해당 객체 메서드 호출로 값을 가져오는 식의 변경이 가능하다.
//            multipliedCreditAndCourseGrade += course.getCredit() * course.getGradeToNumber();
//            multipliedCreditAndCourseGrade += course.multiplyCreditAndCourseGrade();
//        }
        // 수강신청한 총 학점의 수
//        int totalCompletedCredit = courses.stream()
//                .mapToInt(Course::getCredit)
//                .sum();

        // TO-BE
        // Courses 일급 콜렉션을 선언 하였으므로, 응집도(cohesion))를 위해 해당 콜렉션내 함수로 위임이 가능 하다.
        // 각 기능별 클래스로 응집 시켜서 계산기 클래스에서는 각 클래스의 함수 리턴 값만 가지고 결과 산출이 가능함을 미덕으로 본다.
        double totalMultiplyedCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();
        int totalCompletedCredit = courses.calculateTotalCompleteCredit();

        // 취득평점 / 전체학점수
        return totalMultiplyedCreditAndCourseGrade / totalCompletedCredit;
    }
}
