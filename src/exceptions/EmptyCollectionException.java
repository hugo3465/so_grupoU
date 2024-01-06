package exceptions;

public class EmptyCollectionException extends RuntimeException {
    public EmptyCollectionException() {
        super();
    }

    public EmptyCollectionException(String s) {
        super(s);
    }
}
