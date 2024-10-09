/* this program is to demonstrate how to have code comlient with Rule Code: msc07-j     Name:prevent multiple instantiation  of singleton objects  */

public class PreventMultipleInstances {

    public static class Teacher {
        private String name;
        private String course;
        private int section;

        // Single instance of Teacher (Lazy initialization)
        private static Teacher instance;

        // Private constructor to prevent external instantiation
        private Teacher(String name, String course, int section) {
            this.name = name;
            this.course = course;
            this.section = section;
        }

        // Synchronized method to control simultaneous access by multiple threads
        public static synchronized Teacher getInstance(String name, String course, int section) {
            if (instance == null) {
                instance = new Teacher(name, course, section);
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        // Example usage of getInstance
        Teacher teacher = Teacher.getInstance("John Doe", "Math", 101);
        Teacher teacher2 = Teacher.getInstance("sam smith", "English", 110);
        System.out.println("Teacher name:" + teacher.name);
        System.out.println("Teacher name:" + teacher2.name);
    }
}
