/* This program showcases compliant code.
   Rule Code: IDS03-J
   Name: Do Not Log Unsanitized User Input
*/

import java.util.logging.Logger;

public class SafeLogging {
    private static final Logger logger = Logger.getLogger(SafeLogging.class.getName());

    public static void main(String[] args) {
        String userInput = "<script>alert('XSS')</script>"; // Example of malicious input
        String sanitizedInput = sanitize(userInput); // Sanitize the input before logging
        logger.info("User input received: " + sanitizedInput);
    }

    // Simple sanitization method
    private static String sanitize(String input) {
        return input.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
