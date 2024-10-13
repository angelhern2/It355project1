/* This program showcases compliant code.
   Rule Code: IDS05-J
   Name: Use a Safe Subset of ASCII for File and Path Names
*/

import java.io.File;

public class SafeFileName {
    public static void main(String[] args) {
        String userInput = "example_file.txt";
        if (isValidFileName(userInput)) {
            File file = new File("/safe/directory/", userInput);
            // Proceed with file operations
            System.out.println("File is valid and safe to use: " + file.getPath());
        } else {
            System.out.println("Invalid file name provided.");
        }
    }

    // Allows only alphanumeric characters, underscores, hyphens, and dots
    private static boolean isValidFileName(String fileName) {
        return fileName.matches("^[a-zA-Z0-9._-]+$");
    }
}
