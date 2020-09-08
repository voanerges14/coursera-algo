//package com.company;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] elementData;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        elementData = (Item[]) new Object[10];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == elementData.length) {
            resize(2 * elementData.length);
        }
        elementData[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size > 0 && size == elementData.length / 4) {
            resize(elementData.length / 2);
        }
        return remove(StdRandom.uniform(size));
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elementData[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int i = size;
            private int [] numbers = StdRandom.permutation(size);

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public Item next() {
                if (isEmpty()) {
                    throw new NoSuchElementException();
                }
                return elementData[numbers[--i]];
            }
        };
    }

    private void resize(int capacity) {
        elementData = Arrays.copyOf(elementData, capacity);
    }


    private Item remove(int index) {
        Item oldValue = elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}

