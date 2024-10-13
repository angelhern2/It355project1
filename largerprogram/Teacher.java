public final class Teacher extends Person {
    private final String subject;

    public Teacher(String name, int id, String subject) {
        super(name, id); // Call to Person constructor
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty.");
        }
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String getRole() {
        return "Teacher";
    }
}
