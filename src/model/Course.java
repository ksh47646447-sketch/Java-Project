package model;

public class Course {
    protected String courseCode;
    protected String lectureName;
    protected int lectureCredit;

    public Course(String courseCode, String lectureName, int lectureCredit) {
        this.courseCode = courseCode;
        this.lectureName = lectureName;
        this.lectureCredit = lectureCredit;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getLectureName() {
        return lectureName;
    }

    public int getLectureCredit() {
        return lectureCredit;
    }
}