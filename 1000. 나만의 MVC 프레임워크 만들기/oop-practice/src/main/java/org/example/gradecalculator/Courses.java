package org.example.gradecalculator;

import java.util.List;

// 일급콜랙션
public class Courses {

    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {
        double multipliedCreditAndCourseGrade = 0; // 수강 신청된 교과목별 학점 수와 취득 평점의 곱
        for (Course course : courses) {
            // 해당 값의 계산은 계산기에서 하는 것도 나쁘지 않지만 값의 근원지 자체가 Course이므로 해당 객체 메서드 호출로 값을 가져오는 식의 변경이 가능하다.
//            multipliedCreditAndCourseGrade += course.getCredit() * course.getGradeToNumber();
            multipliedCreditAndCourseGrade += course.multiplyCreditAndCourseGrade();
        }
        return multipliedCreditAndCourseGrade;
    }

    public int calculateTotalCompleteCredit() {
        return courses.stream().mapToInt(Course::getCredit)
                .sum();
    }
}
