package view;

import java.util.List;

import model.CourseModel;

public class StudentView {

    private String header;
    private String body;

    public void displayMenu() {
        System.out.println("========== STUDENT MANAGEMENT SYSTEM ==========");
        System.out.println("1. Create Student");
        System.out.println("2. Find and Sort Students");
        System.out.println("3. Update/Delete Student");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.println("===============================================");
        System.out.print("Choice: ");
    }

    //displayCourses
    public void displayCourses(List<CourseModel> courses) {
        System.out.println("List courses");
        for (CourseModel course : courses) {
            System.out.println(course.getIdCourse() + " " + course.getCourse());
        }
    }

    //setHeader
    public void setHeader(String header) {
        this.header = header;
    }

    //setBody
    public void setBody(String body) {
        this.body = body;
    }

    //display
    public void display(){
        System.out.println(header);
        System.out.print(body);
    }

    //display notice, error
    public void displayNotice(String notice){
        System.out.println(notice);
    }

}
