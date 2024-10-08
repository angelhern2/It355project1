
/*    This program was made to show the security rule  IDS01-J. Name: Normalize
     strings before validating them */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    String input = "\uFE64" + "script" + "\uFE65"; // Unicode-escaped version of "<script>"

    // Normalize the input
    input=Normalizer.normalize(input,Normalizer.Form.NFKC);

    // Validate to ensure it doesn't contain prohibited characters
    Pattern pattern = Pattern.compile("[<>]"); // Looking for angle brackets
Matcher matcher = pattern.matcher(input);if(matcher.find())
    {
        throw new IllegalStateException("Invalid input detected!"); // Handle invalid input
    }

}
