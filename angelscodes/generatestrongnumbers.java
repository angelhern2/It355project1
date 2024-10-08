
/*    This program was made to show the security rule MSC02-J. Name: Generate
    // strong random numbers */
import java.security.SecureRandom;

public class Main {

    public static void main(String args[]) {
        SecureRandom number = new SecureRandom();// using a secure unpredictable radnom number generator
        for (int i = 0; i < 20; i++) {
            System.out.println(number.nextInt(21));
        }
    }
}
