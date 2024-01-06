package util;

import java.util.Iterator;

import exceptions.EmptyCollectionException;
import exceptions.FullBufferException;

public class Buffer<T> implements Iterable<T> {
    private T[] buffer;
    private int count;
    private int front;
    private int rear;

    public Buffer(int length) {
        this.buffer = (T[]) new Object[length];
        this.count = 0;
        this.front = 0;
        this.rear = 0;
    }

    public void addFront(T element) {
        if (isFull()) {
            throw new FullBufferException("Buffer is full!");
        }

        front = (front - 1 + buffer.length) % buffer.length;
        buffer[front] = element;
        count++;

    }

    public void addRear(T element) {
        if (isFull()) {
            throw new FullBufferException("Buffer is full!");
        }

        buffer[rear] = element;
        rear = (rear + 1) % buffer.length;
        count++;

    }

    public T removeFront() {
        if (isEmpty()) {
            return null;
        }

        T removedElement = buffer[front];
        front = (front + 1) % buffer.length;
        count--;
        return removedElement;
    }

    public T removeRear() {
        if (isEmpty()) {
            return null;
        }

        rear = (rear - 1 + buffer.length) % buffer.length;
        T removedElement = buffer[rear];
        count--;
        return removedElement;
    }

    public boolean isFull() {
        return count == buffer.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

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
