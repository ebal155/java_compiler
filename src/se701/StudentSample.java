package se701;

public class StudentSample {

    public interface doShowBehaviour {
        public void doShow(String s);
    }

    private static boolean someBoolean = false;

    public static void main(String[] args) {
        doShowBehaviour item = new showImplementation();
        item.doShow("Hello World");
        item = new displayImplementation();
        item.doShow("Hello World");
        item = new otherDisplayImplementation();
        item.doShow("Hello World");
    }

    public static class showImplementation implements doShowBehaviour {
        public void doShow(String s) {
            System.out.println(s);
        }
    }

    public static class displayImplementation implements doShowBehaviour {
        public void doShow(String s) {
            for (int i = 0; i < 5; i++) {
                System.out.println(s + " " + i);
            }
        }
    }

    public static class otherDisplayImplementation implements doShowBehaviour {
        public void doShow(String s) {
            if (someBoolean) {
                System.out.println(s);
            } else {
                System.out.println(s + " something interesting");
            }
        }
    }
}
