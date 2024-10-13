import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public final class SchoolDirectory {

    // Singleton instance to prevent multiple instances
    private static SchoolDirectory instance;

    // Storage for people in the directory
    private Map<Integer, Person> personDirectory = new HashMap<>();
    private Principal principal; // Hold the single instance of Principal

    // Private constructor to prevent instantiation
    private SchoolDirectory() {
    }

    // Singleton method to get the instance
    public static synchronized SchoolDirectory getInstance() {
        if (instance == null) {
            instance = new SchoolDirectory();
        }
        return instance;
    }

    // Add a person to the directory (input validation)
    public void addPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Invalid person information.");
        }
        personDirectory.put(person.getId(), person);
        System.out.println(person.getRole() + " added: " + person.getName());
    }

    // Add the principal to the directory (Singleton handling)
    public void addPrincipal(Principal principal) {
        if (this.principal != null) {
            System.out.println("Principal already exists: " + this.principal.getName());
        } else {
            this.principal = principal;
            personDirectory.put(principal.getId(), principal);
            System.out.println("Principal added: " + principal.getName());
        }
    }

    // Print all people in the directory
    public void printAllPeople() {
        if (personDirectory.isEmpty()) {
            System.out.println("No people available in the directory.");
        } else {
            for (Person person : personDirectory.values()) {
                System.out.println(person);
            }
        }
    }

    // Generate secure random IDs
    public int generateSecureId() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(9999) + 10000; // Generate strong random 5-digit number
    }

    // Read people from a file and add them to the directory
    public void loadPeopleFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) { // no empty loops are being used
                String[] tokens = line.split(", ");
                String role = tokens[0].trim();
                String name = tokens[1].trim();

                if ("STUDENT".equalsIgnoreCase(role)) {
                    String course = tokens[2].trim();
                    int studentId = generateSecureId();
                    Student student = new Student(name, studentId, course);
                    addPerson(student);
                } else if ("TEACHER".equalsIgnoreCase(role)) {
                    String subject = tokens[2].trim();
                    int teacherId = generateSecureId();
                    Teacher teacher = new Teacher(name, teacherId, subject);
                    addPerson(teacher);
                } else if ("PRINCIPAL".equalsIgnoreCase(role)) {
                    int principalId = generateSecureId();
                    Principal principal = Principal.getInstance(name, principalId);
                    addPrincipal(principal);
                } else {
                    System.out.println("Unknown role: " + role); // used incase the the expected input are not right , a
                                                                 // type of validation
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Do not suppress exceptions
        }
    }

    public static void main(String[] args) {
        SchoolDirectory directory = SchoolDirectory.getInstance();

        // Load people from file
        directory.loadPeopleFromFile("C:\\Programs\\355project1\\largerprogram/people.txt");

        // Print all entries in the directory
        directory.printAllPeople();
    }
}
