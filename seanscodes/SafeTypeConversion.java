/* This program showcases compliant code.
   Rule Code: NUM12-J
   Name: Ensure Conversions of Numeric Types to Narrower Types Do Not Result in Lost or Misinterpreted Data
*/

public class SafeTypeConversion {
    public static void main(String[] args) {
        long largeNumber = 123456789L;
        try {
            int convertedNumber = safeLongToInt(largeNumber);
            System.out.println("Converted Number: " + convertedNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static int safeLongToInt(long value) {
        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Long value " + value + " cannot be safely converted to int.");
        }
        return (int) value;
    }
}
