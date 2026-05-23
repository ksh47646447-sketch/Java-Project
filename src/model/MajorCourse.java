package model;

public class MajorCourse extends Course {
    private boolean isRequired;

    public MajorCourse(String courseCode, String lectureName, int lectureCredit, boolean isRequired) {
        super(courseCode, lectureName, lectureCredit);
        this.isRequired = isRequired;
    }

    public boolean isRequired() {
        return isRequired;
    }
}