package checker;

import model.*;

public class CreditChecker implements RequirementChecker {
    private static final int REQUIRED_TOTAL = 130;
    private static final int REQUIRED_MAJOR = 72;
    private static final int REQUIRED_GENERAL = 30;

    public int getTotalCredit(Student student) {
        int total = 0;

        for (int i = 0; i < student.getCourseCount(); i++) {
            TakenCourse tc = student.getTakenCourses()[i];
            String grade = tc.getGrade().toUpperCase();

            if (grade.equals("F") || grade.equals("NP")) {
                continue;
            }

            total += tc.getCourse().getLectureCredit();
        }

        return total;
    }

    public int getMajorCredit(Student student) {
        int total = 0;

        for (int i = 0; i < student.getCourseCount(); i++) {
            TakenCourse tc = student.getTakenCourses()[i];
            Course c = tc.getCourse();
            String grade = tc.getGrade().toUpperCase();

            if (grade.equals("F") || grade.equals("NP")) {
                continue;
            }

            if (c instanceof MajorCourse) {
                total += c.getLectureCredit();
            }
        }

        return total;
    }

    public int getGeneralCredit(Student student) {
        int total = 0;

        for (int i = 0; i < student.getCourseCount(); i++) {
            TakenCourse tc = student.getTakenCourses()[i];
            Course c = tc.getCourse();
            String grade = tc.getGrade().toUpperCase();

            if (grade.equals("F") || grade.equals("NP")) {
                continue;
            }

            if (c instanceof GeneralCourse) {
                total += c.getLectureCredit();
            }
        }

        return total;
    }

    @Override
    public boolean check(Student student) {
        return getTotalCredit(student) >= REQUIRED_TOTAL
                && getMajorCredit(student) >= REQUIRED_MAJOR
                && getGeneralCredit(student) >= REQUIRED_GENERAL;
    }

    @Override
    public String getMessage(Student student) {
        int total = getTotalCredit(student);
        int major = getMajorCredit(student);
        int general = getGeneralCredit(student);

        if (check(student)) {
            return "학점 요건 충족 - 총 " + total + "/130, 전공 " + major + "/72, 교양 " + general + "/30";
        }

        String result = "학점 요건 미충족 - ";

        if (total < REQUIRED_TOTAL) {
            result += "총학점 " + (REQUIRED_TOTAL - total) + "학점 부족(" + total + "/130) ";
        }

        if (major < REQUIRED_MAJOR) {
            result += "전공 " + (REQUIRED_MAJOR - major) + "학점 부족(" + major + "/72) ";
        }

        if (general < REQUIRED_GENERAL) {
            result += "교양 " + (REQUIRED_GENERAL - general) + "학점 부족(" + general + "/30)";
        }

        return result;
    }
}