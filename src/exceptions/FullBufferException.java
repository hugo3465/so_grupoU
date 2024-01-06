package exceptions;

public class FullBufferException extends RuntimeException {
    public FullBufferException() {
        super();
    }

    public FullBufferException(String s) {
        super(s);
    }
}
