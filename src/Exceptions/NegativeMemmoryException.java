package exceptions;

/**
 * Exceção lançada quando uma operação que envolve uma quantidade negativa de
 * memória
 * é tentada. Esta classe estende {@code RuntimeException}.
 *
 * @see NegativeMemmoryException
 */
public class NegativeMemmoryException extends RuntimeException {

    /**
     * Construtor padrão da exceção {@code NegativeMemmoryException}.
     */
    public NegativeMemmoryException() {
        super();
    }

    /**
     * Construtor da exceção {@code NegativeMemmoryException} com uma mensagem
     * personalizada.
     *
     * @param s Mensagem personalizada que descreve a exceção.
     */
    public NegativeMemmoryException(String s) {
        super();
    }
}
