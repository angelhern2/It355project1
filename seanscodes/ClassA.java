/* This program showcases compliant code.
   Rule Code: DCL00-J
   Name: Prevent Class Initialization Cycles
*/

public class ClassA {
    private ClassB classB;

    public ClassA() {
        this.classB = new ClassB(this); // Pass reference without causing cycle
    }

    public void doSomething() {
        System.out.println("ClassA is doing something.");
    }

    public static void main(String[] args) {
        ClassA a = new ClassA();
        a.doSomething();
        a.classB.doSomethingElse();
    }
}

class ClassB {
    private ClassA classA;

    public ClassB(ClassA classA) {
        this.classA = classA;
    }

    public void doSomethingElse() {
        System.out.println("ClassB is doing something else.");
    }
}
