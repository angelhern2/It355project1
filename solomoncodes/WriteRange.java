import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExample {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("output.txt")) {
            int data = 300;  // Outside the valid byte range (0-255)
            if (data >= 0 && data <= 255) {
                fos.write(data);  // Write the data only if it's within range
            } else {
                System.err.println("Data out of valid byte range.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 
