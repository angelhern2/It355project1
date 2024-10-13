public class Resource {
    private FileWriter writer;

    // Private constructor to prevent direct instantiation
    private Resource(FileWriter writer) {
        this.writer = writer;
    }

    // Factory method for creating an instance rather than throwing the exception in the constructor
    public static Resource createResource(String fileName){ // 'thorws IOException' would be here in another case {
        try {
            FileWriter writer = new FileWriter(fileName);
            return new Resource(writer);  // Return the fully constructed object
        } catch (IOException e) {
            System.err.println("Failed to create resource: " + e.getMessage());
            return null;  // Return null or handle failure accordingly
        }
    }

    public void close() {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println("Error closing writer: " + e.getMessage());
            }
        }
    }
}
