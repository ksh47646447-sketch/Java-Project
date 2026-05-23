package checker;

import model.Student;

public class GraduationChecker {
    private RequirementChecker[] checkers;

    public GraduationChecker() {
        checkers = new RequirementChecker[] {
                new CreditChecker(),
                new RequiredMajorChecker(),
                new CheomseongChecker(),
                new SDGChecker(),
                new EnglishChecker(),
                new CounselingChecker(),
                new GpaChecker()
        };
    }

    public boolean canGraduate(Student student) {
        for (int i = 0; i < checkers.length; i++) {
            if (!checkers[i].check(student)) {
                return false;
            }
        }

        return true;
    }

    public void printGraduationResult(Student student) {
        System.out.println("===== 졸업 요건 검사 결과 =====");

        for (int i = 0; i < checkers.length; i++) {
            System.out.println(checkers[i].getMessage(student));
        }

        System.out.println();

        if (canGraduate(student)) {
            System.out.println("최종 결과: 졸업 가능합니다.");
        } else {
            System.out.println("최종 결과: 졸업 불가능합니다.");
        }
    }
}