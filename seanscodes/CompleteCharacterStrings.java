/* This program showcases compliant code.
   Rule Code: IDS10-J
   Name: Don't Form Strings Containing Partial Characters
*/

public class CompleteCharacterStrings {
    public static void main(String[] args) {
        // Example of constructing a string without breaking Unicode characters
        String part1 = "Hello, ";
        String part2 = "World!";
        String complete = part1 + part2; // Safe concatenation

        System.out.println(complete);

        // Unsafe example (for demonstration only): Avoid splitting surrogate pairs
        // char high = '\uD83D';
        // char low = '\uDE00';
        // String emoji = "" + high + low; // Properly formed
    }
}
