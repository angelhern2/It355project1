public final class Student extends Person {
    private final String course;
    private double flexDollars;

    // Constructor with flex dollars
    public Student(String name, int id, String course, double flexDollars) {
        super(name, id); // Call to Person constructor
        if (course == null || course.isEmpty()) {
            throw new IllegalArgumentException("Course cannot be null or empty.");
        }
        if (flexDollars < 0) {
            throw new IllegalArgumentException("Flex dollars cannot be negative.");
        }
        this.course = course;
        this.flexDollars = flexDollars;
    }

    public String getCourse() {
        return course;
    }

    public double getFlexDollars() {
        return flexDollars;
    }

    public void addFlexDollars(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add negative flex dollars.");
        }
        this.flexDollars += amount;
    }

    public void spendFlexDollars(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot spend negative flex dollars.");
        }
        if (amount > flexDollars) {
            throw new IllegalArgumentException("Insufficient flex dollars.");
        }
        this.flexDollars -= amount;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return super.toString() + ", Course: " + course + ", Flex Dollars: $" + flexDollars;
    }
}
