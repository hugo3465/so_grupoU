package exceptions;

/**
 * Exceção lançada quando uma operação que requer mais memória do que está
 * disponível
 * é tentada. Esta classe estende {@code Exception}.
 *
 * @see OutOfMemmoryException
 */
public class OutOfMemmoryException extends Exception {

    /**
     * Construtor padrão da exceção {@code OutOfMemmoryException}.
     */
    public OutOfMemmoryException() {
        super();
    }

    /**
     * Construtor da exceção {@code OutOfMemmoryException} com uma mensagem
     * personalizada.
     *
     * @param s Mensagem personalizada que descreve a exceção.
     */
    public OutOfMemmoryException(String s) {
        super(s);
    }
}
