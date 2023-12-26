package exceptions;

public class OutOfMemmoryException extends Exception {
    public OutOfMemmoryException() {
        super();
    }

    public OutOfMemmoryException(String s) {
        super(s);
    }
}
