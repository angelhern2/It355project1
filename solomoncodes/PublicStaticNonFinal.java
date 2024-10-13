public class Config {
    // Correct: Field is final, cannot be modified after initialization
    public static final int MAX_USERS = 100;

    // Incorrect: This allows modification by any class
    // public static int MAX_USERS = 100;  // Non-compliant
}
