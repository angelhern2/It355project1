/* This program showcases compliant code.
   Rule Code: IDS06-J
   Name: Exclude Unsanitized User Input from Format Strings
*/

public class SafeStringFormatting {
    public static void main(String[] args) {
        String userInput = "John Doe"; // Assume this comes from an untrusted source

        // Unsafe: Including user input directly in format strings
        // String message = String.format("Welcome, %s!", userInput);

        // Safe: Using parameterized format strings without altering the format
        String message = String.format("Welcome, %s!", sanitize(userInput));
        System.out.println(message);
    }

    // Simple sanitization method to escape format specifiers
    private static String sanitize(String input) {
        return input.replaceAll("%", "%%");
    }
}
