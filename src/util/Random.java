package util;

/**
 * A classe Random fornece métodos para geração de números aleatórios.
 */
public class Random {
    /**
     * Gera um número aleatório no intervalo especificado.
     *
     * @param min Valor mínimo do intervalo.
     * @param max Valor máximo do intervalo.
     * @return Um número aleatório no intervalo [min, max].
     */
    public static int generateRandomNumber(long min, long max) {

        int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);

        return randomInt;
    }
}