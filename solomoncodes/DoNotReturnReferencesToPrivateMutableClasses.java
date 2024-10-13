public class PrivateMutable {
    private StringBuilder name = new StringBuilder("John");

    public String getName() {
        return name.toString();  // Return a copy instead of a reference
    }

    public void setName(String name) {
        this.name = new StringBuilder(name);  // Create a new StringBuilder to avoid external modifications
    }
}
