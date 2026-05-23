package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Course;
import model.GeneralCourse;
import model.MajorCourse;

public class CourseDB {
    public static ArrayList<MajorCourse> majorCourses = new ArrayList<>();
    public static ArrayList<GeneralCourse> genEdCourses = new ArrayList<>();

    public static void loadDB() {
        loadMajorCourses("data/majorCourses.txt");
        loadGeneralCourses("data/generalCourses.txt");
    }

    private static void loadMajorCourses(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length < 4) {
                    continue;
                }

                String courseCode = data[0].trim();
                String lectureName = data[1].trim();
                int lectureCredit = Integer.parseInt(data[2].trim());
                boolean isRequired = data[3].trim().equals("전필");

                majorCourses.add(new MajorCourse(courseCode, lectureName, lectureCredit, isRequired));
            }
        } catch (IOException e) {
            System.out.println("전공 과목 파일 읽기 실패: " + filename);
        }
    }

    private static void loadGeneralCourses(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }

                String[] data = line.split(",");

                if (data.length < 8) {
                    continue;
                }

                String courseCode = data[0].trim();
                String lectureName = data[1].trim();
                int lectureCredit = Integer.parseInt(data[2].trim());
                String category1 = data[3].trim();
                String category2 = data[4].trim();
                String category3 = data[5].trim();
                boolean isSDG = data[6].trim().equals("SDG");
                boolean isEnglish = data[7].trim().equals("영어");

                genEdCourses.add(new GeneralCourse(
                        courseCode,
                        lectureName,
                        lectureCredit,
                        category1,
                        category2,
                        category3,
                        isSDG,
                        isEnglish
                ));
            }
        } catch (IOException e) {
            System.out.println("교양 과목 파일 읽기 실패: " + filename);
        }
    }

    public static Course findCourse(String lectureName) {
        for (MajorCourse mc : majorCourses) {
            if (mc.getLectureName().equals(lectureName)) {
                return mc;
            }
        }

        for (GeneralCourse gc : genEdCourses) {
            if (gc.getLectureName().equals(lectureName)) {
                return gc;
            }
        }

        return null;
    }
}
