package se701;

public class StudentSample {

    public interface TestInterface {

        public void testMethodOne(int a);

        public int testMethodTwo(String b);
    }

    public class TestClass implements TestInterface {

        public void testMethodOne(int a) {
        }

        public int testMethodTwo(String b) {
        }
    }
}
