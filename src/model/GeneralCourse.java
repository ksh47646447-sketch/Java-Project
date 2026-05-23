package model;

public class GeneralCourse extends Course {
    private String category1;
    private String category2;
    private String category3;
    private boolean lectureIsSDG;
    private boolean lectureIsEnglish;

    public GeneralCourse(String courseCode, String lectureName, int lectureCredit,
                         String category1, String category2, String category3,
                         boolean lectureIsSDG, boolean lectureIsEnglish) {
        super(courseCode, lectureName, lectureCredit);
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.lectureIsSDG = lectureIsSDG;
        this.lectureIsEnglish = lectureIsEnglish;
    }

    public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public String getCategory3() {
        return category3;
    }

    public boolean isSDG() {
        return lectureIsSDG;
    }

    public boolean isEnglish() {
        return lectureIsEnglish;
    }
}