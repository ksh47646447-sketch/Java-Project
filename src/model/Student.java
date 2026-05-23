package model;

public class Student {
    private int studentId;
    private String studentName;
    private int studentMajor;
    private int counselingCount;
    private int toeicScore;
    private double gpa;

    private TakenCourse[] takenCourses = new TakenCourse[100];
    private int courseCount = 0;

    public Student(int studentId, String studentName, int studentMajor,
                   int counselingCount, int toeicScore) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        this.counselingCount = counselingCount;
        this.toeicScore = toeicScore;
        this.gpa = 0.0;
    }

    public void addTakenCourse(TakenCourse tc) {
        if (courseCount < takenCourses.length) {
            takenCourses[courseCount++] = tc;
        }
    }

    public TakenCourse[] getTakenCourses() {
        return takenCourses;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public int getCounselingCount() {
        return counselingCount;
    }

    public int getToeicScore() {
        return toeicScore;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getStudentMajor() {
        return studentMajor;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}