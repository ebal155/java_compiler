public class Test {



    public static void DelegateMethod(String message, int b) {
    }

    public void lala(String m, int a) {
    }

    public static void main(String args[]) {
        Del handler = DelegateMethod;
        Del handler2 = lala;
        handler("Hello World", 5);
    }

    public class Dog {
    }
}
