/* This program showcases compliant code.
   Rule Code: NUM04-J
   Name: Do Not Use Floating-Point Numbers if Precise Computation is Required
*/

import java.math.BigDecimal;

public class PreciseComputation {
    public static void main(String[] args) {
        // Incorrect: Using double for financial calculations
        double priceDouble = 19.99;
        double totalDouble = priceDouble * 3;
        System.out.println("Total (double): " + totalDouble); // May result in precision issues
        
        // Correct: Using BigDecimal for precise calculations
        BigDecimal price = new BigDecimal("19.99");
        BigDecimal quantity = new BigDecimal("3");
        BigDecimal total = price.multiply(quantity);
        System.out.println("Total (BigDecimal): " + total); // Accurate result
    }
}
