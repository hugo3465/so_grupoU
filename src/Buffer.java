import Exceptions.EmptyBufferException;

public class Buffer {
    private char[] buffer;
    private int bufferCount;

    public Buffer() {
        this.buffer = new char[5];
        this.bufferCount = 0;
    }

    public boolean isFull() {
        return (bufferCount == buffer.length);
    }

    public synchronized void put(char character) {
        if (!isFull()) {
            buffer[bufferCount] = character;
            bufferCount++;
        }
    }

    private boolean isEmpty() {
        return (bufferCount == 0);
    }

    /**
     * O Buffer vai ser quase que uma fila, pois o primeiro a entrar vai ser o
     * primeiro a sair, por isso que neste método é preciso fazer um shift, pois vai
     * se remover do primeiro elemento
     * 
     * @return elemento removido
     */
    public synchronized char get() {
        if (isEmpty()) {
            throw new EmptyBufferException();
        }

        char element = buffer[0];

        // shift de todos os elementos para a esquerda
        for (int i = 0; i < bufferCount - 1; i++) {
            buffer[i] = buffer[i + 1];
        }

        bufferCount--;

        return element;
    }
}
