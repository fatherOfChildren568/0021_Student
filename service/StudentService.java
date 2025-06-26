package service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.CourseModel;
import model.EnrolledCourse;
import model.StudentModel;

public class StudentService {
    // declear
    private List<StudentModel> list;

    // getList
    public List<StudentModel> getList() {
        return list;
    }

    // setList
    public void setList(List<StudentModel> list) {
        this.list = list;
    }

    public String getReport() {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (StudentModel studentModel : list) {
            int id = studentModel.getId();
            String name = studentModel.getName();
            for (EnrolledCourse enrolledCourse : studentModel.getEnrolledCourses()) {
                for (CourseModel courseModel : enrolledCourse.getCourses()) {
                    String key = id + ";" + name + ";" + courseModel.getCourse();
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String[] parts = entry.getKey().split(";");
            String id = parts[0];
            String name = parts[1];
            String course = parts[2];
            String data = String.format("%-5s%-15s%-15s%-10s\n", id, name, course, entry.getValue());
            sb.append(data);
        }

        return sb.toString();
    }

}
