/* this program is to demonstrate how to have code compliant with Rule Code: FIO14-J   Name:  Perform Proper Clean Up at Program Termination */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
    public static void main(String[] args) {
        FileWriter writer = null;
        try {
            File file = new File("example.txt");
            writer = new FileWriter(file);
            writer.write("Sample data.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();  // Ensure proper cleanup at program termination
                } catch (IOException e) {
                    System.out.println("Error closing FileWriter: " + e.getMessage());
                }
            }
        }
    }
}
