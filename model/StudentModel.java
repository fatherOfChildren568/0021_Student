package model;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    // declear
    private int id;
    private String name;
    private int semester;
    private List<CourseModel> course;

    // constructor mo parameter
    public StudentModel() {
    }

    // StudentModel
    public StudentModel(int id, String name, int semester) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.course = new ArrayList<>();
    }

    // constructor have parameter
    public StudentModel(int id, String name, int semester, List<CourseModel> course) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.course = course;
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

    // getSemester
    public int getSemester() {
        return semester;
    }

    // setSemester
    public void setSemester(int semester) {
        this.semester = semester;
    }

    // getCourse
    public List<CourseModel> getCourse() {
        return course;
    }

    // setCourse
    public void setCourse(List<CourseModel> course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return String.format("%-5s%-15s%-15s%-20s\n", getId(), getName(), getSemester(), getCourse());
    }

}
