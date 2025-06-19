package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import constant.Message;
import dto.StudentDTO;
import model.CourseModel;
import model.StudentModel;
import view.StudentView;

public class StudentController {
    // declear
    private List<StudentModel> listStudents;
    private List<CourseModel> availableCourses;
    private StudentView studentView = new StudentView();
    private StudentDTO input = new StudentDTO();

    // StudentController
    public StudentController() {
        this.listStudents = new ArrayList<>();
        this.availableCourses = new ArrayList<>();
        availableCourses.add(new CourseModel(1, "Java"));
        availableCourses.add(new CourseModel(2, ".Net"));
        availableCourses.add(new CourseModel(3, "C/C++"));
    }

    // setInput
    public void setInput(StudentDTO input) {
        this.input = input;
    }

    // display main menu
    public void displayMenu() {
        studentView.displayMenu();
    }

    // check in db exist student have id
    public boolean isExist(int id) {
        for (StudentModel student : listStudents) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // check db is full student
    public boolean isDatabaseFull() {
        return listStudents.size() >= Message.MAX_STUDENTS;
    }

    // getAvailableCourses
    public List<CourseModel> getAvailableCourses() {
        return availableCourses;
    }

    // displayAllCourse
    public void displayAllCourse() {
        List<CourseModel> courses = getAvailableCourses();
        studentView.displayCourses(courses);
    }

    // createStudent
    public void createStudent() {
        // get data
        int id = input.getId();
        String name = input.getName();
        int semester = input.getSemester();
        List<Integer> courseIds = input.getCourse();

        // create new student
        StudentModel newStudent = new StudentModel(id, name, semester);
        // loop in courseIds to get Id
        for (Integer courseId : courseIds) {
            // getCourseById
            CourseModel course = getCourseById(courseId);
            if (course != null) {
                // add course for new student
                newStudent.getCourse().add(course);
            }
        }
        // add student in db
        listStudents.add(newStudent);
    }

    // get 1 course by its id
    private CourseModel getCourseById(int courseId) {
        // loop in availableCourses
        for (CourseModel course : availableCourses) {
            // compare courseId
            if (course.getIdCourse() == courseId) {
                return course;
            }
        }
        return null;
    }

    // findAndSort
    public void findAndSort() {
        // sort
        sortListStudents();
    }

    private void sortListStudents() {
        // getListStudentsByName
        List<StudentModel> list = getListStudentsByName();
        // check null
        if (list.isEmpty()) {
            return;
        }
        // sort
        list.sort(new Comparator<StudentModel>() {
            @Override
            public int compare(StudentModel o1, StudentModel o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        studentView.setHeader(Message.HEADER_STUDENT_LIST);
        StringBuilder sb = new StringBuilder();
        for (StudentModel studentModel : list) {
            sb.append(studentModel);
        }
        // set body
        studentView.setBody(sb.toString());
        studentView.display();
    }

    // getListStudentsByName
    private List<StudentModel> getListStudentsByName() {
        // get search name
        String searchName = input.getName();
        // create List
        List<StudentModel> listStudentsByName = new ArrayList<>();
        // loop in db
        for (StudentModel student : listStudents) {
            // get name and compare with search name
            if (student.getName().toLowerCase().contains(searchName)) {
                listStudentsByName.add(student);
            }
        }
        return listStudentsByName;
    }

    // disPlayListStudentById
    public void displayStudentById(int mId) {
        StudentModel student = getStudentById(mId);
        // check null
        if (student == null) {
            studentView.displayNotice(Message.ERROR_STUDENT_NOT_FOUND);
            return;
        }
        // else
        studentView.setHeader(Message.HEADER_STUDENT_LIST);
        studentView.setBody(student.toString());
        studentView.display();
    }

    // getStudentById
    private StudentModel getStudentById(int Id) {
        for (StudentModel student : listStudents) {
            if (student.getId() == Id) {
                return student;
            }
        }
        return null;
    }

    // deleteStudent
    public void deleteStudent(int id) {
        // getStudentById
        StudentModel student = getStudentById(id);
        // check null
        if (student == null) {
            studentView.displayNotice(Message.ERROR_STUDENT_NOT_FOUND);
            return;
        }

        // delete
        listStudents.remove(student);
    }

    // update
    public void update() {
        // getStudentById
        StudentModel student = getStudentById(input.getId());
        // set new data
        student.setName(input.getName());
        student.setSemester(input.getSemester());

        // clear old course
        student.getCourse().clear();
        // set new course
        for (int courseId : input.getCourse()) {
            CourseModel course = getCourseById(courseId);
            student.getCourse().add(course);
        }
    }

    // getNameStudentById
    public String getNameStudentById(int id) {
        for (StudentModel student : listStudents) {
            if (student.getId() == id) {
                return student.getName();
            }
        }
        return null;
    }
}
