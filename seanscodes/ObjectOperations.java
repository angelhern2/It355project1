/* This program showcases compliant code.
   Rule Code: EXP05-J
   Name: Do Not Follow a Write by a Subsequent Write or Read of the Same Object Within an Expression
*/

public class ObjectOperations {
    public static void main(String[] args) {
        User user = new User("Bob");
        
        // Correct: Separate write and read operations
        user.setName("Robert");
        String name = user.getName();
        System.out.println("User's name: " + name);
        
        // Incorrect: Chaining write and read in a single expression
        // System.out.println("User's name: " + user.setName("Rob") + user.getName());
    }
}

class User {
    private String name;
    
    public User(String name) {
        this.name = name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
