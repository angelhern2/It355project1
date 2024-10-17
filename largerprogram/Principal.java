public final class Principal extends Person {
    private static Principal instance;
    private final String title;

    // Private constructor to enforce Singleton pattern
    private Principal(String title, String firstName, String lastName, int id) {
        super(firstName + " " + lastName, id); // Combine first and last name
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }

    // Singleton method to get the only instance of Principal
    public static synchronized Principal getInstance(String title, String firstName, String lastName, int id) {
        if (instance == null) {
            instance = new Principal(title, firstName, lastName, id);
        }
        return instance;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getRole() {
        return "Principal";
    }

    @Override
    public String toString() {
        return getRole() + "[ID: " + getId() + ", Name: " + title + " " + getName() + "]";
    }
}
