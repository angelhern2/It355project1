/* This program showcases compliant code.
   Rule Code: NUM00-J
   Name: Detect or Prevent Integer Overflow
*/

public class IntegerOverflowProtection {
    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        int b = 1;
        
        if (willAdditionOverflow(a, b)) {
            System.out.println("Addition will cause overflow.");
        } else {
            int result = a + b;
            System.out.println("Result: " + result);
        }
    }
    
    public static boolean willAdditionOverflow(int x, int y) {
        if (y > 0 && x > Integer.MAX_VALUE - y) {
            return true;
        }
        if (y < 0 && x < Integer.MIN_VALUE - y) {
            return true;
        }
        return false;
    }
}
