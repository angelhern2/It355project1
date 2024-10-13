
/* this program is to demonstrate how to have code comlient with Rule Code: err00-j.   Name: do not suppress or ignore checked exceptions   */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DontSuppressExceptions {
    public static void main(String[] args) {
        // example input hopefully not in file
        String filename = "input.txt";

        try {
            // Attempt to open the file
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            System.out.println("File found!");

            // Reading the content of the file and printing them
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename); // exceptionj not supressed since it asks help from user
            System.out.println("temp file will be made, please follow instructions in file");

            // Create a new file named output.txt as a placeholder so the exception is not
            // supressed
            try {
                File newFile = new File("output.txt");
                if (newFile.createNewFile()) {
                    System.out.println("File created: " + newFile.getName());

                    // Write instuctions to fix in the file
                    FileWriter writer = new FileWriter("output.txt");
                    writer.write(
                            "please check your path , please check your imput spelling, please. please try again later.");
                    writer.close();
                    System.out.println("file : output.txt has been made please follow tips to help find the file.");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException ioException) {
                System.out.println("An error occurred while creating the file.");
                ioException.printStackTrace(); // shows the error so it doesnt go unchecked
            }
        }
    }
}