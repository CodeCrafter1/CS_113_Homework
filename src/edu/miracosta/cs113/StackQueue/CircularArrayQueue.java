package edu.miracosta.cs113.StackQueue;

import java.util.*;


public class CircularArrayQueue<E> extends AbstractQueue<E> implements Queue<E> {

    private int front;
    private int rear;
    private int size;
    private int capacity; //Size of if queue
    private static final int DEFAULT_CAPACITY = 10;
    private E[] theData;

    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int initCapacity) {
        capacity = initCapacity;
        front = 0;
        rear = capacity - 1;
        size = 0;
        theData = (E[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    private void reallocate() {
        int newCapacity = 2 * capacity;
        E[] newData = (E[]) new Object[newCapacity];
        int j = front;
        for (int i = 0; i < size; i++) {
            newData[i] = theData[j];
            j = (j + 1) % capacity;
        }
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
        theData = (E[]) new Object[capacity];//manual copy
        for (int i = 0; i < size; i++) {
            theData[i] = newData[i];
        }
    }

    /**
     * ****************************************************************************************************************
     */
    private class Iter implements Iterator<E> {

        private int i;
        private int num = 0;

        public Iter() {
            i = front;
        }

        @Override
        public boolean hasNext() {
            return num < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E returnValue = theData[i];
            i = (i + 1) % capacity; 
            num++;
            return returnValue;
        }

        @Override
        public void remove() {//no need - iterator is not used even in test
            throw new UnsupportedOperationException();
        }

    }

    /**
     * ****************************************************************************************************************
     */
    @Override
    public Iterator iterator() {
        return new Iter();
    }

    @Override
    public boolean offer(E item) {
        if (size == capacity) {
            reallocate();
        }
        size++;
        rear = (rear + 1) % capacity;
        theData[rear] = item;
        return true;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        } else {
            return theData[front];
        }
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        E result = theData[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }

    @Override
    public E remove() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E result = theData[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }

    @Override
    public E element() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return theData[front];
    }

    @Override
    public boolean add(E item) {//just call offer - offer calls reallocate if necessary
        return offer(item);
    }

    @Override
    public void clear() {
        //not implemented
    }

    @Override
    public int size() {
        return size;
    }
}
