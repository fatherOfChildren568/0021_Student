package model;

import constant.CourseType;

public class CourseModel {
    // declear
    private int idCourse;
    private String course;

    // constructor
    public CourseModel() {
    }

    // constructor
    public CourseModel(int idCourse, String course) {
        this.idCourse = idCourse;
        this.course = course;
    }

    //
    public CourseModel(CourseType type) {
        this.idCourse = type.getId();
        this.course = type.getNameCourse();
    }

    // getId
    public int getIdCourse() {
        return idCourse;
    }

    // setId
    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    // getCourse
    public String getCourse() {
        return course;
    }

    // setCourse
    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return course;
    }

}