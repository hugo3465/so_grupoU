package exceptions;

/**
 * Exceção lançada quando uma operação de inserção é realizada em um buffer
 * cheio,
 * e não é possível adicionar mais elementos. Esta classe estende
 * {@code RuntimeException}.
 *
 * @see FullBufferException
 */
public class FullBufferException extends RuntimeException {

    /**
     * Construtor padrão da exceção {@code FullBufferException}.
     */
    public FullBufferException() {
        super();
    }

    /**
     * Construtor da exceção {@code FullBufferException} com uma mensagem
     * personalizada.
     *
     * @param s Mensagem personalizada que descreve a exceção.
     */
    public FullBufferException(String s) {
        super(s);
    }
}
