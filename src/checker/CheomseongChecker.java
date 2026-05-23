package checker;

import model.*;

public class CheomseongChecker implements RequirementChecker {
    private static final int REQUIRED_BASIC = 6;
    private static final int REQUIRED_CORE = 6;

    public int getBasicCredit(Student student) {
        int credit = 0;

        for (int i = 0; i < student.getCourseCount(); i++) {
            TakenCourse tc = student.getTakenCourses()[i];
            Course c = tc.getCourse();
            String grade = tc.getGrade().toUpperCase();

            if (grade.equals("F") || grade.equals("NP")) {
                continue;
            }

            if (c instanceof GeneralCourse) {
                GeneralCourse gc = (GeneralCourse)c;

                if (gc.getCategory1().equals("첨성인기초")) {
                    credit += gc.getLectureCredit();
                }
            }
        }

        return credit;
    }

    public int getCoreCredit(Student student) {
        int credit = 0;

        for (int i = 0; i < student.getCourseCount(); i++) {
            TakenCourse tc = student.getTakenCourses()[i];
            Course c = tc.getCourse();
            String grade = tc.getGrade().toUpperCase();

            if (grade.equals("F") || grade.equals("NP")) {
                continue;
            }

            if (c instanceof GeneralCourse) {
                GeneralCourse gc = (GeneralCourse)c;

                if (gc.getCategory1().equals("첨성인핵심")) {
                    credit += gc.getLectureCredit();
                }
            }
        }

        return credit;
    }

    @Override
    public boolean check(Student student) {
        return getBasicCredit(student) >= REQUIRED_BASIC
                && getCoreCredit(student) >= REQUIRED_CORE;
    }

    @Override
    public String getMessage(Student student) {
        int basic = getBasicCredit(student);
        int core = getCoreCredit(student);

        if (check(student)) {
            return "첨성인 요건 충족 - 기초 " + basic + "/6, 핵심 " + core + "/6";
        }

        String result = "첨성인 요건 미충족 - ";

        if (basic < REQUIRED_BASIC) {
            result += "첨성인기초 " + (REQUIRED_BASIC - basic)
                    + "학점 부족(" + basic + "/6) ";
        }

        if (core < REQUIRED_CORE) {
            result += "첨성인핵심 " + (REQUIRED_CORE - core)
                    + "학점 부족(" + core + "/6)";
        }

        return result;
    }
}