package model;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    // declear
    private int id;
    private String name;
    private List<EnrolledCourse> enrolledCourses;

    // constructor mo parameter
    public StudentModel() {
    }

    // constructor have parameter
    public StudentModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // getId
    public int getId() {
        return id;
    }

    // setId
    public void setId(int id) {
        this.id = id;
    }

    // getName
    public String getName() {
        return name;
    }

    // setName
    public void setName(String name) {
        this.name = name;
    }

    // getEnrolledCourses
    public List<EnrolledCourse> getEnrolledCourses() {
        return enrolledCourses;
    }

    // setEnrolledCourses
    public void setEnrolledCourses(List<EnrolledCourse> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    // add new course
    public void addNewCourses(EnrolledCourse enrolledCourse) {
        enrolledCourses = new ArrayList<>();
        enrolledCourses.add(enrolledCourse);
    }

    @Override
    public String toString() {
        return  String.format("%-5s%-15s%-30s\n", id, name, enrolledCourses);
    }

    public String toStringByStt(int i) {
        return  String.format("%-5s%-15s%-30s\n", i, name, enrolledCourses);
    }
}
