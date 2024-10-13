import java.io.Serializable;

public abstract class Person implements Serializable {
    private final String name;
    private final int id;

    // Constructor
    public Person(String name, int id) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
        this.id = id;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // Prevent cloning of sensitive objects
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning is not allowed for this class.");
    }

    // Abstract method to be implemented by subclasses
    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + "[ID: " + id + ", Name: " + name + "]";
    }
}
