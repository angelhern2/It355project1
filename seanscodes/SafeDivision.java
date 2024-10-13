/* This program showcases compliant code.
   Rule Code: NUM02-J
   Name: Ensure That Division and Remainder Operations Do Not Result in Divide-by-Zero Errors
*/

public class SafeDivision {
    public static void main(String[] args) {
        int dividend = 10;
        int divisor = 0;
        
        try {
            int result = divide(dividend, divisor);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero.");
        }
        return dividend / divisor;
    }
}
