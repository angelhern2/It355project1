public final class Teacher extends Person {
    private final String title;
    private final String subject;

    public Teacher(String title, String firstName, String lastName, int id, String subject) {
        super(firstName + " " + lastName, id); // Combine first and last name
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty.");
        }
        this.title = title;
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String getRole() {
        return "Teacher";
    }

    @Override
    public String toString() {
        return getRole() + "[ID: " + getId() + ", Name: " + title + " " + getName() + ", Subject: " + subject + "]";
    }
}
