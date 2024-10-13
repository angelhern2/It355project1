import java.util.Scanner;

public class ValidateArguments {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("please enter a number ");
        String userinput = scan.nextLine();
        while (isInteger(userinput) == false) {
            System.out.println("please enter a number , please make sure it is a real number in integer format ");
            userinput = scan.nextLine();
        }
        int usernumber = Integer.parseInt(userinput);
        usernumber *= usernumber;
        System.out.println("your number squared is " + usernumber);

    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input); // Trys parsing the string as an integer and if it fails we have an exception to
                                     // handle error
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}