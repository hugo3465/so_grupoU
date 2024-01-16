package core;

import exceptions.NegativeMemmoryException;
import exceptions.OutOfMemmoryException;

/**
 * Unidade de memória, memory manager, contém a lógica para gerenciar a memória
 */
// Buffer para guardar a infomração

/**
 * Representa a unidade de memória (Memory Manager) do sistema.
 * Contém a lógica para gerenciar a memória.
 */
public class MEM {

    private final int MEMMORY_SIZE = 500;

    private int usedMemmory;

    /**
     * Construtor da classe `MEM`.
     * Inicializa a quantidade de memória utilizada como zero.
     */
    public MEM() {
        this.usedMemmory = 0;
    }

    /**
     * Verifica se a memória está completamente utilizada.
     *
     * @return `true` se a memória estiver cheia, `false` caso contrário.
     */
    public boolean isFull() {
        return (usedMemmory == MEMMORY_SIZE);
    }

    /**
     * Verifica se a memória está vazia.
     *
     * @return `true` se a memória estiver vazia, `false` caso contrário.
     */
    public boolean isEmpty() {
        return (usedMemmory == 0);
    }

    /**
     * Aloca uma determinada quantidade de memória.
     *
     * @param memoryToAllocate A quantidade de memória a ser alocada.
     * @throws OutOfMemmoryException Se a memória estiver completamente utilizada.
     */
    public synchronized void alocateMemmory(int memmoryToAllocate) throws OutOfMemmoryException {
        if (isFull() || (usedMemmory + memmoryToAllocate) > MEMMORY_SIZE) {
            throw new OutOfMemmoryException();
        }

        this.usedMemmory += memmoryToAllocate;
    }

    /**
     * Desaloca uma determinada quantidade de memória.
     *
     * @param memoryToDeallocate A quantidade de memória a ser desalocada.
     * @throws NegativeMemmoryException Se a tentativa de desalocar mais memória do
     *                                  que a utilizada.
     */
    public synchronized void deallocateMemmory(int memmoryToDeallocate) {
        if (isEmpty() || (usedMemmory - memmoryToDeallocate) < 0) {
            throw new NegativeMemmoryException();
        }

        this.usedMemmory -= memmoryToDeallocate;
    }

    /**
     * Obtém a quantidade atual de memória utilizada.
     *
     * @return A quantidade atual de memória utilizada.
     */
    public int getUsedMemory() {
        return usedMemmory;
    }
}
