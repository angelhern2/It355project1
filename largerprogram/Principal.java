public final class Principal extends Person {

    private static Principal instance;

    // Private constructor to prevent direct instantiation
    private Principal(String name, int id) {
        super(name, id); // Call to Person constructor
    }

    // Singleton method to get the only instance of Principal
    public static synchronized Principal getInstance(String name, int id) {
        if (instance == null) {
            instance = new Principal(name, id);
        }
        return instance;
    }

    @Override
    public String getRole() {
        return "Principal";
    }
}
