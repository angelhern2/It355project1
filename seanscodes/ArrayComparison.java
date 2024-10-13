/* This program showcases compliant code.
   Rule Code: EXP02-J
   Name: Do Not Use the Object.equals() Method to Compare Two Arrays
*/

import java.util.Arrays;

public class ArrayComparison {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        int[] array3 = {4, 5, 6};
        
        // Incorrect: Reference comparison
        boolean isEqualReference = array1.equals(array2);
        System.out.println("Reference Equal: " + isEqualReference); // Outputs: false
        
        // Correct: Content comparison
        boolean isEqualContent = Arrays.equals(array1, array2);
        System.out.println("Content Equal: " + isEqualContent); // Outputs: true
        
        boolean isEqualContent2 = Arrays.equals(array1, array3);
        System.out.println("Content Equal with array3: " + isEqualContent2); // Outputs: false
    }
}
