/* This program showcases compliant code.
   Rule Code: EXP04-J
   Name: Do Not Pass Arguments to Certain Java Collections Framework Methods That Are a Different Type Than the Collection Parameter Type
*/

import java.util.ArrayList;
import java.util.List;

public class CollectionTypeSafety {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        
        // Correct: Adding a String to a List<String>
        stringList.add("Hello");
        
        // Incorrect: Uncommenting the following line will cause a compile-time error
        // stringList.add(123); // Error: incompatible types
        
        // Safe iteration with type checking
        for (String item : stringList) {
            System.out.println(item);
        }
    }
}
