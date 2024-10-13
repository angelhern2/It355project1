/* This program showcases compliant code.
   Rule Code: EXP00-J
   Name: Do Not Ignore Values Returned by Methods
*/

public class ValueHandling {
    public static void main(String[] args) {
        ValueHandler handler = new ValueHandler();
        int result = handler.calculateValue(5);
        if (result > 0) {
            System.out.println("Calculation successful: " + result);
        } else {
            System.out.println("Calculation failed.");
        }
    }
}

class ValueHandler {
    public int calculateValue(int input) {
        // Perform some calculation
        return input * 2;
    }
}
