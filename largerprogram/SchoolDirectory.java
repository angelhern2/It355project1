import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
        // Ensure the correct types: person.getId() returns an int (autoboxed to Integer)
        personDirectory.put(person.getId(), person);
        System.out.println(person.getRole() + " added: " + person.getName());
    }

    // Add the principal to the directory (Singleton handling)
    public void addPrincipal(Principal principal) {
        if (principal == null) {
            throw new IllegalArgumentException("Principal cannot be null.");
        }
        if (this.principal != null) {
            System.out.println("Principal already exists: " + this.principal.getName());
        } else {
            this.principal = principal;
            personDirectory.put(principal.getId(), principal);
            System.out.println("Principal added: " + principal.getName());
        }
    }

    // Print all people in the directory in the order: Principal, Teachers, Students
    public void printAllPeople() {
        if (personDirectory.isEmpty()) {
            System.out.println("No people available in the directory.");
            return;
        }

        // Print Principal
        if (principal != null) {
            System.out.println(principal);
        }

        // Print Teachers
        for (Person person : personDirectory.values()) {
            if (person instanceof Teacher) {
                System.out.println(person);
            }
        }

        // Print Students
        for (Person person : personDirectory.values()) {
            if (person instanceof Student) {
                System.out.println(person);
            }
        }
    }

    // Print only Teachers
    public void printOnlyTeachers() {
        boolean hasTeachers = false;
        for (Person person : personDirectory.values()) {
            if (person instanceof Teacher) {
                System.out.println(person);
                hasTeachers = true;
            }
        }
        if (!hasTeachers) {
            System.out.println("No teachers available in the directory.");
        }
    }

    // Print only Students
    public void printOnlyStudents() {
        boolean hasStudents = false;
        for (Person person : personDirectory.values()) {
            if (person instanceof Student) {
                System.out.println(person);
                hasStudents = true;
            }
        }
        if (!hasStudents) {
            System.out.println("No students available in the directory.");
        }
    }

    // Find a Student by first and last name
    public void findStudentByName(String firstName, String lastName) {
        String fullName = firstName.trim() + " " + lastName.trim();
        List<Student> matchingStudents = new ArrayList<>();

        for (Person person : personDirectory.values()) {
            if (person instanceof Student && person.getName().equalsIgnoreCase(fullName)) {
                matchingStudents.add((Student) person);
            }
        }

        if (matchingStudents.isEmpty()) {
            System.out.println("No student found with the name " + fullName + ".");
        } else if (matchingStudents.size() == 1) {
            System.out.println(matchingStudents.get(0));
        } else {
            System.out.println("Multiple students found with the name " + fullName + ".");
            System.out.println("Please enter the unique ID of the student you are looking for.");
            Scanner scanner = new Scanner(System.in);
            int id = -1;
            while (true) {
                System.out.print("Enter student ID: ");
                try {
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    boolean found = false;
                    for (Student student : matchingStudents) {
                        if (student.getId() == id) {
                            System.out.println(student);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    } else {
                        System.out.println("Invalid ID. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numerical ID.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
        }
    }

    // Find and print a person by their unique ID
    public void findPersonById(Scanner scanner) {
        System.out.print("Enter the unique ID of the person: ");
        try {
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Person person = personDirectory.get(id);
            if (person != null) {
                System.out.println(person);
            } else {
                System.out.println("No person found with ID " + id + ".");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numerical ID.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    // Adjust a Student's flex dollars
    public void adjustStudentFlexDollars(Scanner scanner) {
        try {
            System.out.print("Enter the student's first name: ");
            String firstName = scanner.nextLine().trim();
            System.out.print("Enter the student's last name: ");
            String lastName = scanner.nextLine().trim();

            if (firstName.isEmpty() || lastName.isEmpty()) {
                System.out.println("First and last names cannot be empty.");
                return;
            }

            String fullName = firstName + " " + lastName;
            Student student = getStudentByName(fullName);

            if (student == null) {
                System.out.println("Student with name " + fullName + " not found.");
                return;
            }

            System.out.println("Current Flex Dollars for " + fullName + ": $" + student.getFlexDollars());
            System.out.print("Enter the new flex dollars amount: ");
            String newAmountInput = scanner.nextLine().trim();

            if (newAmountInput.isEmpty()) {
                System.out.println("Amount cannot be empty.");
                return;
            }

            BigDecimal newAmount;
            try {
                newAmount = new BigDecimal(newAmountInput);
                if (newAmount.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Amount cannot be negative.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount entered.");
                return;
            }

            BigDecimal difference = student.setFlexDollars(newAmount);
            System.out.println("Flex Dollars adjusted by $" + difference + ".");
            System.out.println("New Flex Dollars for " + fullName + ": $" + student.getFlexDollars());
        } catch (IllegalArgumentException e) {
            System.out.println("Error adjusting flex dollars: " + e.getMessage());
        }
    }

    // Generate secure random IDs
    public int generateSecureId() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(90000) + 10000; // Generate strong random 5-digit number
    }

    // Read people from a file and add them to the directory
    public void loadPeopleFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0; // To track line numbers for better error messages
            while ((line = reader.readLine()) != null) { // No empty loops are being used
                lineNumber++;
                String[] tokens = line.split(", ");
                if (tokens.length < 2) {
                    System.out.println("Invalid entry at line " + lineNumber + ": " + line);
                    continue; // Skip invalid entries
                }
                String role = tokens[0].trim();
                String name = tokens[1].trim();

                if ("STUDENT".equalsIgnoreCase(role)) {
                    if (tokens.length < 4) {
                        System.out.println("Incomplete student entry at line " + lineNumber + ": " + line);
                        continue;
                    }
                    String major = tokens[2].trim();
                    BigDecimal flexDollars = BigDecimal.ZERO; // Default flex dollars
                    try {
                        flexDollars = new BigDecimal(tokens[3].trim());
                        if (flexDollars.compareTo(BigDecimal.ZERO) < 0) {
                            System.out.println("Negative flex dollars for " + name + " at line " + lineNumber + ". Defaulting to $0.0.");
                            flexDollars = BigDecimal.ZERO;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid flex dollars for " + name + " at line " + lineNumber + ". Defaulting to $0.0.");
                    }
                    int studentId = generateSecureId();
                    Student student = new Student(name, studentId, major, flexDollars);
                    addPerson(student);
                } else if ("TEACHER".equalsIgnoreCase(role)) {
                    if (tokens.length < 5) { // Expecting Title and Name
                        System.out.println("Incomplete teacher entry at line " + lineNumber + ": " + line);
                        continue;
                    }
                    String title = tokens[1].trim();
                    String firstName = tokens[2].trim();
                    String lastName = tokens[3].trim();
                    String subject = tokens[4].trim();
                    int teacherId = generateSecureId();
                    Teacher teacher = new Teacher(title, firstName, lastName, teacherId, subject);
                    addPerson(teacher);
                } else if ("PRINCIPAL".equalsIgnoreCase(role)) {
                    if (tokens.length < 4) { // Expecting Title and Name
                        System.out.println("Incomplete principal entry at line " + lineNumber + ": " + line);
                        continue;
                    }
                    String title = tokens[1].trim();
                    String firstName = tokens[2].trim();
                    String lastName = tokens[3].trim();
                    int principalId = generateSecureId();
                    Principal newPrincipal = Principal.getInstance(title, firstName, lastName, principalId);
                    addPrincipal(newPrincipal);
                } else {
                    System.out.println("Unknown role: " + role + " at line " + lineNumber + " in entry: " + line); // Validation for unexpected roles
                }
            }
            System.out.println("People loaded successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            // You may choose to rethrow or handle it as per your application's needs
        }
    }

    // Save people to a file
    public void savePeopleToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Person person : personDirectory.values()) {
                if (person instanceof Student) {
                    Student student = (Student) person;
                    writer.write(String.format("STUDENT, %s, %s, %.2f%n",
                            student.getName(),
                            student.getMajor(),
                            student.getFlexDollars()));
                } else if (person instanceof Teacher) {
                    Teacher teacher = (Teacher) person;
                    // Split name into first and last
                    String[] names = teacher.getName().split(" ", 2);
                    String firstName = names.length > 0 ? names[0] : "";
                    String lastName = names.length > 1 ? names[1] : "";
                    writer.write(String.format("TEACHER, %s, %s, %s, %s%n",
                            teacher.getTitle(),
                            firstName,
                            lastName,
                            teacher.getSubject()));
                } else if (person instanceof Principal) {
                    Principal principal = (Principal) person;
                    // Split name into first and last
                    String[] names = principal.getName().split(" ", 2);
                    String firstName = names.length > 0 ? names[0] : "";
                    String lastName = names.length > 1 ? names[1] : "";
                    writer.write(String.format("PRINCIPAL, %s, %s, %s%n",
                            principal.getTitle(),
                            firstName,
                            lastName));
                }
            }
            System.out.println("People saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Retrieve a person by their ID
    public Person getPersonById(int id) {
        return personDirectory.get(id);
    }

    // Retrieve a student by their name
    public Student getStudentByName(String name) {
        for (Person person : personDirectory.values()) {
            if (person instanceof Student && person.getName().equalsIgnoreCase(name)) {
                return (Student) person;
            }
        }
        return null;
    }

    // Retrieve a teacher by their name
    public Teacher getTeacherByName(String name) {
        for (Person person : personDirectory.values()) {
            if (person instanceof Teacher && person.getName().equalsIgnoreCase(name)) {
                return (Teacher) person;
            }
        }
        return null;
    }

    // Menu-driven interface
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        String currentFilePath = "";

        while (choice != 8) { // Updated to 8 options after removing load option
            System.out.println("\n--- School Directory Menu ---");
            System.out.println("1. Add a student");
            System.out.println("2. Add a teacher");
            System.out.println("3. Add a principal");
            System.out.println("4. Save people to a file");
            System.out.println("5. Print all people");
            System.out.println("6. Selective Printing and Searching");
            System.out.println("7. Adjust a student's flex dollars");
            System.out.println("8. Exit");
            System.out.print("Enter your choice (1-8): ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addStudentInteractive(scanner);
                        break;
                    case 2:
                        addTeacherInteractive(scanner);
                        break;
                    case 3:
                        addPrincipalInteractive(scanner);
                        break;
                    case 4:
                        if (currentFilePath.isEmpty()) {
                            System.out.print("Enter the file name (with path) to save people: ");
                            currentFilePath = scanner.nextLine();
                        }
                        savePeopleToFile(currentFilePath);
                        break;
                    case 5:
                        printAllPeople();
                        break;
                    case 6:
                        selectivePrintMenu(scanner);
                        break;
                    case 7:
                        adjustStudentFlexDollars(scanner);
                        break;
                    case 8:
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numerical values only.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        scanner.close();
    }

    // Interactive method to add a student
    private void addStudentInteractive(Scanner scanner) {
        try {
            System.out.print("Enter student's first name: ");
            String firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) {
                System.out.println("First name cannot be empty.");
                return;
            }

            System.out.print("Enter student's last name: ");
            String lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) {
                System.out.println("Last name cannot be empty.");
                return;
            }

            String fullName = firstName + " " + lastName;

            System.out.print("Enter student's major: ");
            String major = scanner.nextLine().trim();
            if (major.isEmpty()) {
                System.out.println("Major cannot be empty.");
                return;
            }

            System.out.print("Enter initial flex dollars (or press Enter for $0.0): ");
            String flexInput = scanner.nextLine().trim();
            BigDecimal flexDollars = BigDecimal.ZERO;
            if (!flexInput.isEmpty()) {
                try {
                    flexDollars = new BigDecimal(flexInput);
                    if (flexDollars.compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println("Flex dollars cannot be negative. Defaulting to $0.0.");
                        flexDollars = BigDecimal.ZERO;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid flex dollars amount. Defaulting to $0.0.");
                }
            }

            int studentId = generateSecureId();
            Student student = new Student(fullName, studentId, major, flexDollars);
            addPerson(student);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    // Interactive method to add a teacher
    private void addTeacherInteractive(Scanner scanner) {
        try {
            System.out.print("Enter teacher's title (Mr., Ms., Dr., etc.): ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty.");
                return;
            }

            System.out.print("Enter teacher's first name: ");
            String firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) {
                System.out.println("First name cannot be empty.");
                return;
            }

            System.out.print("Enter teacher's last name: ");
            String lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) {
                System.out.println("Last name cannot be empty.");
                return;
            }

            String fullName = firstName + " " + lastName;

            System.out.print("Enter teacher's subject: ");
            String subject = scanner.nextLine().trim();
            if (subject.isEmpty()) {
                System.out.println("Subject cannot be empty.");
                return;
            }

            int teacherId = generateSecureId();
            Teacher teacher = new Teacher(title, firstName, lastName, teacherId, subject);
            addPerson(teacher);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding teacher: " + e.getMessage());
        }
    }

    // Interactive method to add a principal
    private void addPrincipalInteractive(Scanner scanner) {
        try {
            if (this.principal != null) {
                System.out.println("A principal already exists: " + this.principal.getName());
                return;
            }

            System.out.print("Enter principal's title (Mr., Ms., Dr., etc.): ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty.");
                return;
            }

            System.out.print("Enter principal's first name: ");
            String firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) {
                System.out.println("First name cannot be empty.");
                return;
            }

            System.out.print("Enter principal's last name: ");
            String lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) {
                System.out.println("Last name cannot be empty.");
                return;
            }

            String fullName = firstName + " " + lastName;

            int principalId = generateSecureId();
            Principal newPrincipal = Principal.getInstance(title, firstName, lastName, principalId);
            addPrincipal(newPrincipal);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding principal: " + e.getMessage());
        }
    }

    // Selective Printing and Searching Menu
    private void selectivePrintMenu(Scanner scanner) {
        System.out.println("\n--- Selective Print Menu ---");
        System.out.println("1. Print all people");
        System.out.println("2. Print only students");
        System.out.println("3. Print only teachers");
        System.out.println("4. Find a student by name");
        System.out.println("5. Find a person by ID");
        System.out.println("6. Save printed list to a file");
        System.out.println("7. Return to main menu");
        System.out.print("Enter your choice (1-7): ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    printAllPeople();
                    break;
                case 2:
                    printOnlyStudents();
                    break;
                case 3:
                    printOnlyTeachers();
                    break;
                case 4:
                    System.out.print("Enter student's first name: ");
                    String firstName = scanner.nextLine().trim();
                    System.out.print("Enter student's last name: ");
                    String lastName = scanner.nextLine().trim();
                    if (firstName.isEmpty() || lastName.isEmpty()) {
                        System.out.println("First and last names cannot be empty.");
                        break;
                    }
                    findStudentByName(firstName, lastName);
                    break;
                case 5:
                    findPersonById(scanner);
                    break;
                case 6:
                    System.out.print("Enter the output file name (with path) to save the list: ");
                    String outputPath = scanner.nextLine();
                    savePrintedListToFile(outputPath);
                    break;
                case 7:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numerical values only.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    // Save the printed list to a specified file
    private void savePrintedListToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write Principal first
            if (principal != null) {
                writer.write(principal.toString() + System.lineSeparator());
            }

            // Write Teachers
            for (Person person : personDirectory.values()) {
                if (person instanceof Teacher) {
                    writer.write(person.toString() + System.lineSeparator());
                }
            }

            // Write Students
            for (Person person : personDirectory.values()) {
                if (person instanceof Student) {
                    writer.write(person.toString() + System.lineSeparator());
                }
            }

            System.out.println("Printed list saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Menu for Selective Printing and Searching
    private void printSelective(Scanner scanner) {
        selectivePrintMenu(scanner);
    }

    public static void main(String[] args) {
        SchoolDirectory directory = SchoolDirectory.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean loadedSuccessfully = false;

        // Prompt user to load a file until a valid one is provided
        while (!loadedSuccessfully) {
            System.out.print("Enter the file name (with path) to load people: ");
            String filePath = scanner.nextLine();

            try {
                directory.loadPeopleFromFile(filePath);
                loadedSuccessfully = true;
            } catch (Exception e) {
                System.out.println("Failed to load the file. Please try again.");
            }
        }

        // Proceed to display the main menu
        directory.displayMenu();

        scanner.close();
    }
}
