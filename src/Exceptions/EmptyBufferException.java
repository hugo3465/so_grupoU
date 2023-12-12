package Exceptions;

public class EmptyBufferException extends RuntimeException {
    public EmptyBufferException() {
        super();
    }

    public EmptyBufferException(String s) {
        super(s);
    }
}