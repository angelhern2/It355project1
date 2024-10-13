/* This program showcases compliant code.
   Rule Code: IDS07-J
   Name: Sanitize Untrusted Data Passed to the Runtime.exec() Method
*/

import java.io.IOException;

public class SafeRuntimeExec {
    public static void main(String[] args) {
        String userInput = "dir"; // Example command

        // Sanitize the user input to allow only safe commands
        if (isAllowedCommand(userInput)) {
            try {
                Process process = Runtime.getRuntime().exec(userInput);
                // Handle process input/output as needed
                System.out.println("Command executed successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid command provided.");
        }
    }

    // Define a whitelist of allowed commands
    private static boolean isAllowedCommand(String command) {
        String[] allowedCommands = { "dir", "ls", "echo" };
        for (String cmd : allowedCommands) {
            if (cmd.equalsIgnoreCase(command)) {
                return true;
            }
        }
        return false;
    }
}
