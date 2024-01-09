package exceptions;

/**
 * Exceção lançada quando uma operação em uma coleção vazia não é permitida.
 * Esta classe estende {@code RuntimeException}.
 *
 * @see EmptyCollectionException
 */
public class EmptyCollectionException extends RuntimeException {

    /**
     * Construtor padrão da exceção {@code EmptyCollectionException}.
     */
    public EmptyCollectionException() {
        super();
    }

    /**
     * Construtor da exceção {@code EmptyCollectionException} com uma mensagem
     * personalizada.
     *
     * @param s Mensagem personalizada que descreve a exceção.
     */
    public EmptyCollectionException(String s) {
        super(s);
    }
}
