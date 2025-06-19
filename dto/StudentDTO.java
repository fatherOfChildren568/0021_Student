package dto;

import java.util.List;

public class StudentDTO {
    private int id;
    private String name;
    private int semester;
    private List<Integer> course;

    
    //StudentDTO
    public StudentDTO() {
    }

    // constructor for course
    public StudentDTO(List<Integer> course) {
        this.course = course;
    }

    // constructor for all parameter
    public StudentDTO(int id, String name, int semester, List<Integer> course) {
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
    public List<Integer> getCourse() {
        return course;
    }

    // setCourse
    public void setCourse(List<Integer> course) {
        this.course = course;
    }

}
