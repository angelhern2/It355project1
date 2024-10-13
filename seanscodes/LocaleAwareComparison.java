/* This program showcases compliant code.
   Rule Code: IDS09-J
   Name: Specify an Appropriate Locale When Comparing Locale-Dependent Data
*/

import java.util.Locale;

public class LocaleAwareComparison {
    public static void main(String[] args) {
        String input = "straße";
        String comparison = "STRASSE";

        // Locale-sensitive comparison using German locale
        boolean isEqual = input.toLowerCase(Locale.GERMAN).equals(comparison.toLowerCase(Locale.GERMAN));

        System.out.println("Are the strings equal? " + isEqual);
    }
}
