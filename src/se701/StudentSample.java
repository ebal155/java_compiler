package se701;

public class StudentSample {

    int a;

    public interface MessagePrinterBehaviour {
        public String MessagePrinter(String message);
    }

    public static void main(String[] args) {
        MessagePrinterBehaviour handler = new DelegateMethodAImplementation();
        handler.MessagePrinter("yes");
        handler = new DelegateMethodBImplementation();
        handler.MessagePrinter("also yes");
        String[] y;
    }

    public static class DelegateMethodAImplementation implements MessagePrinterBehaviour {
        public String MessagePrinter(String msg) {
            return msg;
        }
    }

    public static class DelegateMethodBImplementation implements MessagePrinterBehaviour {
        public String MessagePrinter(String msg) {
            return "other";
        }
    }

    public void foo(boolean x) {
        a = 3;
    }
}
