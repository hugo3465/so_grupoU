import Exceptions.EmptyBufferException;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer {
    private LinkedBlockingQueue<String> buffer;
    private int bufferCount;

    public Buffer() {
        this.buffer = new LinkedBlockingQueue<>();
        this.bufferCount = 0;
    }
    /* Penso que não seja preciso este metodo uma vezque é uma queue */
    public boolean isFull() {
        return (bufferCount == buffer.size());
    }

    public synchronized void put(String elementString) {
        try {
            buffer.put(elementString);
            System.out.println("Dado colocado no buffer" + elementString);
        } catch (Exception e) {
            // TODO: handle exception
        }
        // if (!isFull()) {
        //     buffer[bufferCount] = character;
        //     bufferCount++;
        // }
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
