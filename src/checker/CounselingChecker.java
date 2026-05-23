package checker;

import model.Student;

public class CounselingChecker implements RequirementChecker {
    private static final int REQUIRED_COUNSELING = 8;

    @Override
    public boolean check(Student student) {
        return student.getCounselingCount() >= REQUIRED_COUNSELING;
    }

    @Override
    public String getMessage(Student student) {
        int count = student.getCounselingCount();

        if (check(student)) {
            return "상담 요건 충족 - " + count + "/8회";
        }

        return "상담 요건 미충족 - "
                + (REQUIRED_COUNSELING - count)
                + "회 부족(" + count + "/8)";
    }
}