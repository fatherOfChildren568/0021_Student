package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import constant.CourseType;
import constant.Message;
import controller.StudentController;
import dto.EnrollmentDTO;
import utility.Validator;

public class Main {

    private static Scanner sc = new Scanner(System.in);;
    private static StudentController studentController = new StudentController();
    private static EnrollmentDTO dto = new EnrollmentDTO();

    public static void main(String[] args) {
        // declear

        // handle
        while (true) {
            try {
                // display main menu
                studentController.displayMenu();
                // input choice
                int choice = Integer.parseInt(sc.nextLine().trim());
                if (!Validator.isLimitInRange(choice, 1, 5)) {
                    System.out.println(Message.ERROR_INVALID_CHOICE);
                    continue;
                }

                // handle choice
                switch (choice) {
                    // create new course for student
                    case 1:
                        // check is full in db
                        if (studentController.isDatabaseFull()) {
                            System.out.println(Message.PROMPT_ADD_MORE);
                            String addMore = sc.nextLine().trim();
                            // if input = "N" or blank or => continue
                            if (!Validator.isValidYesNo(addMore)) {
                                continue;
                            }
                        }
                        // input ID
                        System.out.print(Message.PROMPT_ID);
                        int id = Integer.parseInt(sc.nextLine().trim());

                        // check valid id
                        if (!Validator.isValidId(id)) {
                            System.out.println(Message.ERROR_INVALID_ID);
                            continue;
                        }

                        // if id exist in db => show error exist
                        String name;
                        if (studentController.isExist(id)) {
                            name = studentController.getNameStudentById(id);
                            System.out.format("Name: %s\n", name);
                        } else {
                            // input name
                            System.out.print(Message.PROMPT_NAME);
                            name = sc.nextLine().trim();
                            if (!Validator.isValidString(name)) {
                                System.out.println(Message.ERROR_EMPTY_INPUT);
                            }
                        }

                        // input semester
                        System.out.print(Message.PROMPT_SEMESTER);
                        int semester = Integer.parseInt(sc.nextLine().trim());
                        if (!Validator.isValidSemester(semester, 1, 9)) {
                            System.out.println(Message.ERROR_INVALID_COURSE);
                            continue;
                        }

                        // check exist 1 student in 1 semester
                        if (studentController.isExistInOneSemester(id, semester, null)) {
                            System.out.println(Message.ERROR_STUDENT_EXISTS);
                            continue;
                        }

                        // input course
                        List<Integer> idCourse = getCourseSelection();
                        // set data dto
                        dtoSetData(id, name, semester, idCourse);
                        // set data for controller
                        studentController.setInput(dto);
                        // create new student
                        studentController.createStudent();
                        break;
                    // find and sort by sub name
                    case 2:
                        // input search name
                        System.out.print(Message.PROMPT_SEARCH_NAME);
                        String searchName = sc.nextLine().trim();
                        if (!Validator.isValidString(searchName)) {
                            System.out.println(Message.ERROR_EMPTY_INPUT);
                            continue;
                        }
                        // dto set data
                        dtoSetData(0, searchName, 0, null);
                        // handle find and sort
                        studentController.findAndSort();
                        break;
                    // update or delete
                    case 3:
                        // input id
                        System.out.print(Message.PROMPT_ID);
                        int mId = Integer.parseInt(sc.nextLine().trim());
                        // check valid id
                        if (!Validator.isValidId(mId)) {
                            System.out.println(Message.ERROR_INVALID_ID);
                            continue;
                        }

                        // display list student by id
                        studentController.displayStudentById(mId);
                        // get size of this iist
                        int size = studentController.getSizeOfListStudentById(mId);
                        // if not found student with this id => continue
                        if (size == 0) {
                            continue;
                        }
                        // choice u or d
                        System.out.print(Message.PROMPT_UPDATE_DELETE);
                        String action = sc.nextLine().trim();
                        // check valid choice
                        if (!Validator.isValidUpdateDelete(action)) {
                            System.out.println(Message.ERROR_INPUT_UPDATE_DELETE);
                            continue;
                        }

                        switch (action.toLowerCase()) {
                            // update
                            case "u":
                                // choice record need update
                                System.out.print(Message.PROMPT_CHOICE);
                                int updateChoice = Integer.parseInt(sc.nextLine().trim());
                                if (!Validator.isLimitInRange(updateChoice, 1, size)) {
                                    System.out.println(Message.ERROR_INVALID_INPUT);
                                    continue;
                                }
                                // input new name
                                System.out.print("Enter new name: ");
                                String newName = sc.nextLine();
                                // check valid name
                                if (!Validator.isValidString(newName.trim())) {
                                    System.out.println(Message.ERROR_EMPTY_INPUT);
                                    continue;
                                }
                                // input new semester
                                System.out.print("Enter new semester: ");
                                int newSemester = Integer.parseInt(sc.nextLine());
                                // check valid name
                                if (!Validator.isLimitInRange(newSemester, 1, 9)) {
                                    System.out.println(Message.ERROR_INVALID_SEMESTER);
                                    continue;
                                }

                                // check new update is the same with data in db
                                if (studentController.isExistInOneSemester(mId, newSemester, null)) {
                                    System.out.println(Message.ERROR_STUDENT_EXISTS);
                                    continue;
                                }

                                List<Integer> newIdCourse = getCourseSelection();
                                // set data for dto
                                dtoSetData(mId, newName, newSemester, newIdCourse);
                                // update
                                studentController.update(updateChoice);
                                break;
                            case "d":
                                // choice record need update
                                System.out.print(Message.PROMPT_CHOICE);
                                int deleteChoice = Integer.parseInt(sc.nextLine().trim());
                                // check valid choice
                                if (!Validator.isLimitInRange(deleteChoice, 1, size)) {
                                    System.out.println(Message.ERROR_INVALID_INPUT);
                                    continue;
                                }
                                // delete
                                studentController.delete(deleteChoice);
                                break;

                        }
                        break;
                    // report
                    case 4:
                        studentController.report();
                        break;
                    // exit
                    case 5:
                        // System.exit(0);
                        sc.close();
                        break;

                }
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
    }

    // getCourseSelection - updated version
    private static List<Integer> getCourseSelection() {
        studentController.displayAllCourse();
        // input list course by id
        System.out.print(Message.PROMPT_COURSE);
        String courseInput = sc.nextLine().trim();
        List<Integer> courseIds = new ArrayList<>();

        // check input
        if (courseInput.isEmpty()) {
            return courseIds;
        }

        String[] listGetCourse = courseInput.split("\\s+");
        // loop in array to get list course id
        for (String getCourse : listGetCourse) {
            try {
                int courseId = Integer.parseInt(getCourse.trim());
                // Validate courseId với enum
                if (CourseType.getById(courseId) != null) {
                    courseIds.add(courseId);
                } else {
                    System.out.println("Invalid course ID: " + courseId);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid course input: " + getCourse);
            }
        }

        return courseIds;
    }

    private static void dtoSetData(int id, String name, int semester, List<Integer> courseIds) {
        dto.setId(id);
        dto.setName(name);
        dto.setSemester(semester);
        dto.setCourse(courseIds);
    }
}
