package Exceptions;

public class OutOfMemmoryException extends RuntimeException {
    public OutOfMemmoryException() {
        super();
    }

    public OutOfMemmoryException(String s) {
        super(s);
    }
}
