
/*    This program was made to show the security rule  IDS01-J. Name: Normalize
     strings before validating them */

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class normalizestringsbeforevalidating {

    public static void main(String[] args) {
        // Unicode-escaped version of "<script>"
        String input = "\uFE64" + "script" + "\uFE65";

        // Normalized input
        input = Normalizer.normalize(input, Normalizer.Form.NFKC);

        // Validate to ensure it doesn't contain prohibited characters
        Pattern pattern = Pattern.compile("[<>]"); // Looking for angle brackets
        Matcher matcher = pattern.matcher(input);

        // throws exception if invalid
        if (matcher.find()) {
            throw new IllegalStateException("Invalid input detected!");
        } else {
            System.out.println("Input is safe.");
        }
    }

}
