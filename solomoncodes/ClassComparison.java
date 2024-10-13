public class ClassComparison {
    public static void main(String[] args) {
        Object obj = new String("Test");

        // Compliant: Compare classes directly
        if (obj.getClass() == String.class) {
            System.out.println("Object is a String.");
        }

        // Non-compliant: Comparing class names (prone to errors)
        // if (obj.getClass().getName().equals("java.lang.String")) {  // Non-compliant
        //     System.out.println("Object is a String.");
        // }
    }
}
