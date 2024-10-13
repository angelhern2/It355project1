/* this program is to demonstrate how to have code comlient with Rule Code: MSC03-J.   Name:   Never hard code sensitive information   */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NoHardCoding {
    public static void main(String[] args) {
        File ssnFile = new File("fake_ssns.txt");

        try {
            // Reads the SSN from the file
            Scanner fileReader = new Scanner(ssnFile);

            // Prompting user for the last 4 digits
            Scanner inputScanner = new Scanner(System.in);
            System.out.print("Please enter the last 4 digits of your SSN: ");
            String last4FromUser = inputScanner.nextLine();
            inputScanner.close();

            boolean matchFound = false; // Track if a match was found

            // Iterate through the file line by line
            while (fileReader.hasNextLine()) {
                String fullSSN = fileReader.nextLine();

                // Checks to see if the SSN is valid
                if (fullSSN != null && fullSSN.length() >= 4) {
                    // Extract the last 4 digits of the SSN
                    String last4FromFile = fullSSN.substring(fullSSN.length() - 4);

                    // Compares the last 4 digits
                    if (last4FromUser.equals(last4FromFile)) {
                        System.out.println("The last 4 digits match.");
                        matchFound = true;
                        break;
                    }
                }
            }

            fileReader.close();

            // no match founb message
            if (!matchFound) {
                System.out.println("The last 4 digits do not match any SSN.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("There has been an error loading the file, please try again later.");
            e.printStackTrace();
        }
    }
}