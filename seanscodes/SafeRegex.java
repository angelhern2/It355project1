/* This program showcases compliant code.
   Rule Code: IDS08-J
   Name: Sanitize Untrusted Data Included in a Regular Expression
*/

public class SafeRegex {
    public static void main(String[] args) {
        String userInput = "user@example.com"; // Assume this comes from an untrusted source

        // Unsafe: Using user input directly in regex
        // String pattern = "^[a-zA-Z]+$";
        // boolean isValid = userInput.matches(pattern);

        // Safe: Escaping user input to prevent regex injection
        String sanitizedInput = escapeRegex(userInput);
        String pattern = "^[a-zA-Z0-9@._-]+$";
        boolean isValid = sanitizedInput.matches(pattern);

        if (isValid) {
            System.out.println("Input is valid.");
        } else {
            System.out.println("Invalid input.");
        }
    }

    // Simple regex escape method
    private static String escapeRegex(String input) {
        return input.replaceAll("([\\[\\]{}()*+?.,\\\\^$|#\\s])", "\\\\$1");
    }
}
