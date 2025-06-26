package constant;

public enum CourseType {
    JAVA(1, "Java"),
    DOTNET(2, ".Net"),
    CPP(3, "C/C++");

    private final int id;
    private final String nameCourse;

    CourseType(int id, String nameCourse) {
        this.id = id;
        this.nameCourse = nameCourse;
    }

    public int getId() {
        return id;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    // Phương thức để lấy CourseType theo ID
    public static CourseType getById(int id) {
        for (CourseType type : CourseType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    // Phương thức để lấy tất cả courses dưới dạng array
    public static CourseType[] getAllCourses() {
        return CourseType.values();
    }

    @Override
    public String toString() {
        return id + ". " + nameCourse;
    }
}