package checker;

import model.Student;

public class GpaChecker implements RequirementChecker {
    private static final double REQUIRED_GPA = 1.7;

    @Override
    public boolean check(Student student) {
        return student.getGpa() >= REQUIRED_GPA;
    }

    @Override
    public String getMessage(Student student) {
        double gpa = student.getGpa();

        if (check(student)) {
            return String.format("평점 요건 충족 - %.2f/1.70", gpa);
        }

        return String.format("평점 요건 미충족 - %.2f 부족(현재 %.2f/1.70)",
                REQUIRED_GPA - gpa, gpa);
    }
}