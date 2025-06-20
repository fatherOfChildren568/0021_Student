package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import constant.Message;
import dto.EnrollmentDTO;
import model.CourseModel;
import model.EnrolledCourse;
import model.StudentModel;
import view.StudentView;

public class StudentController {
    // declear
    private List<StudentModel> listStudents;
    private List<CourseModel> availableCourses;
    private StudentView studentView = new StudentView();
    private EnrollmentDTO input = new EnrollmentDTO();

    // StudentController
    public StudentController() {
        this.listStudents = new ArrayList<>();
        this.availableCourses = new ArrayList<>();
        availableCourses.add(new CourseModel(1, "Java"));
        availableCourses.add(new CourseModel(2, ".Net"));
        availableCourses.add(new CourseModel(3, "C/C++"));

    }

    // setInput
    public void setInput(EnrollmentDTO input) {
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

        // create list to store name of course
        List<CourseModel> listCourses = new ArrayList<>();
        // loop in courseIds to get Id
        for (Integer courseId : courseIds) {
            // getCourseById
            CourseModel course = getCourseById(courseId);
            if (course != null) {
                // add course for new student
                listCourses.add(course);
            }
        }

        EnrolledCourse enrolledCourse = new EnrolledCourse(semester, listCourses);
        // create new student
        StudentModel newStudent = new StudentModel(id, name);
        // add
        newStudent.addNewCourses(enrolledCourse);
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
        return new CourseModel();
    }

    // findAndSort
    public void findAndSort() {
        // sort
        sortListStudentsByName();
    }

    private void sortListStudentsByName() {
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
        List<StudentModel> list = getListStudentById(mId);
        // check null
        if (list.isEmpty()) {
            studentView.displayNotice(Message.ERROR_STUDENT_NOT_FOUND);
            return;
        }
        // else
        StringBuilder sb = new StringBuilder();
        studentView.setHeader(Message.HEADER_STUDENT_LIST_STT);
        // create variable run
        int i = 1;
        for (StudentModel studentModel : list) {
            sb.append(studentModel.toStringByStt(i));
            i++;
        }
        studentView.setBody(sb.toString());
        studentView.display();
    }

    // getListStudentById
    private List<StudentModel> getListStudentById(int Id) {
        // create list to store
        List<StudentModel> list = new ArrayList<>();
        // loop in listStudents to get student sastify id
        for (StudentModel student : listStudents) {
            if (student.getId() == Id) {
                list.add(student);
            }
        }
        return list;
    }

    // getSizeOfListStudentById
    public int getSizeOfListStudentById(int id) {
        return getListStudentById(id).size();
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

    // displayListStudent
    public void displayListStudent() {
        studentView.setHeader(Message.HEADER_STUDENT_LIST);
        StringBuilder sb = new StringBuilder();
        for (StudentModel studentModel : listStudents) {
            sb.append(studentModel);
        }
        studentView.setBody(sb.toString());
        studentView.display();
    }

    // isExistInOneSemester
    public boolean isExistInOneSemester(int id, int semester, StudentModel exclucdeStudent) {
        // loop in listStudents
        for (StudentModel studentModel : listStudents) {
            if(studentModel == exclucdeStudent){
                continue;
            }
            // if have this id
            if (studentModel.getId() == id) {
                // loop in enrollments
                for (EnrolledCourse enrolledCourse : studentModel.getEnrolledCourses()) {
                    // if also have this semester
                    if (enrolledCourse.getSemester() == semester) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // update
    public void update(int updateChoice) {
        // get list student by id need update
        StudentModel studentUpdate = getStudentNeedChange(updateChoice);

        int id = input.getId();
        String newName = input.getName();
        int newSemester = input.getSemester();
        List<Integer> courseIds = input.getCourse();

        

        // create list to store name of course
        List<CourseModel> newListCourses = new ArrayList<>();
        // loop in courseIds to get Id
        for (Integer courseId : courseIds) {
            // getCourseById
            CourseModel course = getCourseById(courseId);
            if (course != null) {
                // add course for new student
                newListCourses.add(course);
            }
        }
        if (!newName.equalsIgnoreCase(studentUpdate.getName())) {
            setAllName(id, newName);
        }
        EnrolledCourse enrolledCourse = new EnrolledCourse(newSemester, newListCourses);
        studentUpdate.addNewCourses(enrolledCourse);
    }

    // setAllName
    private void setAllName(int id, String newName) {
        for (StudentModel studentModel : listStudents) {
            if (studentModel.getId() == id) {
                studentModel.setName(newName);
            }
        }
    }

    // get student by id
    private StudentModel getStudentNeedChange(int choice) {
        // get list student by id
        List<StudentModel> list = getListStudentById(input.getId());
        // get 1 student that is update choice
        int i = 1;
        for (StudentModel studentModel : list) {
            if (i == choice) {
                return studentModel;
            }
        }
        return null;
    }

    // delete
    public void delete(int deleteChoice) {
        // get student need be remove
        StudentModel deleteStudent = getStudentNeedChange(deleteChoice);
        // remove
        listStudents.remove(deleteStudent);
    }

    // report
    public void report() {

    }

}
