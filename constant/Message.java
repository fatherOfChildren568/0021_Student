// CONSTANTS - Message.java
package constant;

public class Message {
    private Message() {}

    
    public static final String PROMPT_ID = "Enter student ID: ";
    public static final String PROMPT_NAME = "Enter student name: ";
    public static final String PROMPT_SEMESTER = "Enter semester: ";
    public static final String PROMPT_COURSE = "Select course: ";
    
    public static final String PROMPT_SEARCH_NAME = "Enter name to search: ";
    public static final String PROMPT_ADD_MORE = "Database is full (maximum 10 students)! Do you want to continue? (Y/N): ";
    public static final String PROMPT_UPDATE_DELETE = "Update (U) or Delete (D)? ";
    public static final String PROMPT_CHOICE = "Choice: ";
    
    public static final String SUCCESS_CREATE = "Student created successfully!";
    public static final String SUCCESS_UPDATE = "Student updated successfully!";
    public static final String SUCCESS_DELETE = "Student deleted successfully!";
    
    public static final String ERROR_INVALID_CHOICE = "Invalid choice! Please select 1-5.";
    public static final String ERROR_INVALID_ID = "Invalid ID! Please enter a positive number.";
    public static final String ERROR_INVALID_SEMESTER = "Invalid semester! Please enter 1-9.";
    public static final String ERROR_EMPTY_INPUT = "Input cannot be empty!";
    public static final String ERROR_STUDENT_EXISTS = "Student with this ID already exists in semester!";
    public static final String ERROR_STUDENT_NOT_FOUND = "Student not found!";
    public static final String ERROR_INVALID_COURSE = "Invalid course selection!";
    public static final String ERROR_DATABASE_FULL = "Cannot create more students!";
    public static final String ERROR_INPUT_YES_NO = "Input must be Y or N";
    public static final String ERROR_INPUT_UPDATE_DELETE = "Please enter 'U' for Update or 'D' for Delete";
    public static final String ERROR_INVALID_INPUT = "Invalid choice";
    
    
    public static final String HEADER_STUDENT_LIST = String.format("%-5s%-15s%-10s%-20s","ID", "Name", "Semester", "Courses");
    public static final String HEADER_STUDENT_LIST_STT = String.format("%-5s%-15s%-10s%-20s","STT", "Name", "Semester", "Courses");
    public static final String HEADER_SEPARATOR = "------------------------------------------------------------";
    public static final String HEADER_COURSE_LIST = String.format("%-5s %-15s", "ID", "Course Name");
    public static final String HEADER_REPORT = String.format("%-5s%-15s%-15s%-10s", "ID",
            "Student Name", "Course", "Count");
    
    public static final String EXIT_MESSAGE = "Thank you for using Student Management System!";
    public static final String AVAILABLE_COURSES = "Available Courses:";
    
    public static final int MAX_STUDENTS = 10;
}