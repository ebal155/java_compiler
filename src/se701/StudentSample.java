public class StudentSample {

    int a;

    public interface MessagePrinterBehaviour {
        public String MessagePrinter(String message);
    }

    public static void main(String[] args) {
        MessagePrinterBehaviour handler = new DelegateMethodAImplementation();
        handler("yes");
        handler = new DelegateMethodBImplementation();
        handler("also yes");
        String[] y;
    }

    public class DelegateMethodAImplementation implements MessagePrinterBehaviour {
        public String MessagePrinter(String msg) {
            return msg;
        }
    }

    public class DelegateMethodBImplementation implements MessagePrinterBehaviour {
        public String MessagePrinter(String msg) {
            return "other";
        }
    }

    public void foo(boolean x) {
        a = 3;
    }
}
