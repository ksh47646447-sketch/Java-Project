package checker;

import model.*;

public class SDGChecker implements RequirementChecker {
    private static final int REQUIRED_SDG = 3;

    public int getSDGCredit(Student student) {
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

                if (gc.isSDG()) {
                    credit += gc.getLectureCredit();
                }
            }
        }

        return credit;
    }

    @Override
    public boolean check(Student student) {
        return getSDGCredit(student) >= REQUIRED_SDG;
    }

    @Override
    public String getMessage(Student student) {
        int sdg = getSDGCredit(student);

        if (check(student)) {
            return "SDG 요건 충족 - " + sdg + "/3학점";
        }

        return "SDG 요건 미충족 - " + (REQUIRED_SDG - sdg)
                + "학점 부족(" + sdg + "/3)";
    }
}