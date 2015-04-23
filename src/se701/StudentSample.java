public class Test {



    public static void DelegateMethod(String message, int b) {
    }

    public static void main(String args[]) {
        Del handler = DelegateMethod;
        handler("Hello World", 5);
    }

    public class Dog {
    }
}
