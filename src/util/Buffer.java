package util;

import java.util.Iterator;

import exceptions.FullBufferException;

/**
 * Um buffer circular genérico que suporta adição e remoção de elementos na frente e atrás.
 *
 * @param <T> o tipo de elementos armazenados no buffer
 */
public class Buffer<T> implements Iterable<T> {
    private T[] buffer;
    private int count;
    private int front;
    private int rear;

    /**
     * Cria um buffer com o comprimento especificado.
     *
     * @param length o comprimento do buffer
     */
    public Buffer(int length) {
        this.buffer = (T[]) new Object[length];
        this.count = 0;
        this.front = 0;
        this.rear = 0;
    }

    /**
     * Adiciona um elemento à frente do buffer.
     *
     * @param element o elemento a ser adicionado
     * @throws FullBufferException se o buffer estiver cheio
     */
    public void addFront(T element) {
        if (isFull()) {
            throw new FullBufferException("Buffer is full!");
        }

        front = (front - 1 + buffer.length) % buffer.length;
        buffer[front] = element;
        count++;

    }

    /**
     * Adiciona um elemento atrás do buffer.
     *
     * @param element o elemento a ser adicionado
     * @throws FullBufferException se o buffer estiver cheio
     */
    public void addRear(T element) {
        if (isFull()) {
            throw new FullBufferException("Buffer is full!");
        }

        buffer[rear] = element;
        rear = (rear + 1) % buffer.length;
        count++;

    }

    /**
     * Remove e retorna o elemento da frente do buffer.
     *
     * @return o elemento removido ou null se o buffer estiver vazio
     */
    public T removeFront() {
        if (isEmpty()) {
            return null;
        }

        T removedElement = buffer[front];
        front = (front + 1) % buffer.length;
        count--;
        return removedElement;
    }

    /**
     * Remove e retorna o elemento de trás do buffer.
     *
     * @return o elemento removido ou null se o buffer estiver vazio
     */
    public T removeRear() {
        if (isEmpty()) {
            return null;
        }

        rear = (rear - 1 + buffer.length) % buffer.length;
        T removedElement = buffer[rear];
        count--;
        return removedElement;
    }

    /**
     * Verifica se o buffer está cheio.
     *
     * @return true se o buffer estiver cheio, false caso contrário
     */
    public boolean isFull() {
        return count == buffer.length;
    }

    /**
     * Verifica se o buffer está vazio.
     *
     * @return true se o buffer estiver vazio, false caso contrário
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Retorna um iterador para percorrer os elementos no buffer.
     *
     * @return um iterador para os elementos no buffer
     */
    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T> {
        private int currentIndex = front;
        private int elementsRemaining = count;

        @Override
        public boolean hasNext() {
            return elementsRemaining > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements in the buffer.");
            }
            T element = buffer[currentIndex];
            currentIndex = (currentIndex + 1) % buffer.length;
            elementsRemaining--;
            return element;
        }
    }

}
