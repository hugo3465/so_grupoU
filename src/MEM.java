import Exceptions.NegativeMemmoryException;
import Exceptions.OutOfMemmoryException;

/**
 * Unidade de memória, memory manager, contém a lógica para gerenciar a memória
 */

// Buffer para guardar a infomração
public class MEM {

    /**
     * 
     */
    private final int MEMORY_MAX_SIZE = 200;

    /**
     * 
     */
    private int usedMemmory;

    public MEM() {
        this.usedMemmory = 0;
    }

    public boolean isFull() {
        return (usedMemmory == MEMORY_MAX_SIZE);
    }

    public boolean isEmpty() {
        return (usedMemmory == 0);
    }

    public synchronized void alocateMemmory(int memmoryToAllocate) {
        if (isFull() || (usedMemmory + memmoryToAllocate) > MEMORY_MAX_SIZE) {
            throw new OutOfMemmoryException();
        }

        this.usedMemmory += memmoryToAllocate;
    }

    public synchronized void deallocateMemmory(int memmoryToDeallocate) {
        if (isEmpty() || (usedMemmory - memmoryToDeallocate) < 0) {
            throw new NegativeMemmoryException();
        }

        this.usedMemmory -= memmoryToDeallocate;
    }
}
