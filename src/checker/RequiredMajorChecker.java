package checker;

import model.*;

public class RequiredMajorChecker implements RequirementChecker {
    private static final int REQUIRED_MAJOR_CREDIT = 72;
    private static final int REQUIRED_MAJOR_COUNT = 6;

    public int getMajorCredit(Student student) {
        int credit = 0;

        for (int i = 0; i < student.getCourseCount(); i++) {
            TakenCourse tc = student.getTakenCourses()[i];
            Course c = tc.getCourse();
            String grade = tc.getGrade().toUpperCase();

            if (grade.equals("F") || grade.equals("NP")) {
                continue;
            }

            if (c instanceof MajorCourse) {
                credit += c.getLectureCredit();
            }
        }

        return credit;
    }

    public int getRequiredMajorCount(Student student) {
        int count = 0;

        for (int i = 0; i < student.getCourseCount(); i++) {
            TakenCourse tc = student.getTakenCourses()[i];
            Course c = tc.getCourse();
            String grade = tc.getGrade().toUpperCase();

            if (grade.equals("F") || grade.equals("NP")) {
                continue;
            }

            if (c instanceof MajorCourse) {
                MajorCourse mc = (MajorCourse)c;

                if (mc.isRequired()) {
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public boolean check(Student student) {
        return getMajorCredit(student) >= REQUIRED_MAJOR_CREDIT
                && getRequiredMajorCount(student) >= REQUIRED_MAJOR_COUNT;
    }

    @Override
    public String getMessage(Student student) {
        int majorCredit = getMajorCredit(student);
        int requiredCount = getRequiredMajorCount(student);

        if (check(student)) {
            return "전공 요건 충족 - 전공학점 " + majorCredit + "/72, 전필 " + requiredCount + "/6";
        }

        String result = "전공 요건 미충족 - ";

        if (majorCredit < REQUIRED_MAJOR_CREDIT) {
            result += "전공학점 " + (REQUIRED_MAJOR_CREDIT - majorCredit)
                    + "학점 부족(" + majorCredit + "/72) ";
        }

        if (requiredCount < REQUIRED_MAJOR_COUNT) {
            result += "전필 " + (REQUIRED_MAJOR_COUNT - requiredCount)
                    + "과목 부족(" + requiredCount + "/6)";
        }

        return result;
    }
}