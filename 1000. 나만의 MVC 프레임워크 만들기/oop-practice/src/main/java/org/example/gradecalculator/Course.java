package org.example.gradecalculator;

public class Course {
    private final String subject;
    private final String grade;
    private final int credit;

    public Course(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    public int getCredit() {
        return this.credit;
    }

    public double getGradeToNumber() {
        double grade = 0;
        switch (this.grade) {
            case "A+":
                grade = 4.5;
                break;
            case "A":
                grade = 4.0;
                break;
            case "B+":
                grade = 3.5;
                break;
            case "B":
                grade = 3.0;
                break;
            case "C+":
                grade = 2.5;
                break;
            case "C":
                grade = 2.0;
                break;
            default:
                break;
        }
        return grade;
    }
    public double multiplyCreditAndCourseGrade() {
        return this.credit * this.getGradeToNumber();
    }
}
