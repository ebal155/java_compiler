public class Test {



    public static void DelegateMethod(String message, int b) {
    }

    public static void main(String args[]) {
        Del handler = DelegateMethod;
        Dog dog = new Dog();
    }

    public class Dog {
    }
}
