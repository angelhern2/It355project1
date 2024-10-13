/* This program showcases compliant code.
   Rule Code: EXP01-J
   Name: Do Not Use a Null in a Case Where an Object is Required
*/

public class ObjectInstantiation {
    public static void main(String[] args) {
        User user = createUser("Alice");
        if (user != null) {
            System.out.println("User created: " + user.getName());
        } else {
            System.out.println("Failed to create user.");
        }
    }
    
    public static User createUser(String name) {
        if (name == null || name.isEmpty()) {
            return null; // Caller must handle the null case
        }
        return new User(name);
    }
}

class User {
    private String name;
    
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
