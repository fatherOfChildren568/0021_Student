package model;

import java.util.List;

public class EnrolledCourse {
    // declear
    private int semester;
    private List<CourseModel> courses;

    //constructor no paramter
    public EnrolledCourse() {
    }

    //constructor hava parameter
    public EnrolledCourse(int semester, List<CourseModel> courses) {
        this.semester = semester;
        this.courses = courses;
    }

    //getSemester
    public int getSemester() {
        return semester;
    }

    //setSemester
    public void setSemester(int semester) {
        this.semester = semester;
    }

    //getCourses
    public List<CourseModel> getCourses() {
        return courses;
    }

    //setCourses
    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return String.format("%-9s%-20s", semester, courses);
    }

    
}
