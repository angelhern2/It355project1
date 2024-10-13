public final class Student extends Person {
    private final String course;

    public Student(String name, int id, String course) {
        super(name, id);
        if (course == null || course.isEmpty()) {
            throw new IllegalArgumentException("Course cannot be null or empty.");
        }
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String getRole() {
        return "Student";
    }
}
