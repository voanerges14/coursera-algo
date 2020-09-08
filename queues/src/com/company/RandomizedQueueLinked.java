//package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueueLinked<Item> implements Iterable<Item> {

    private int size;

    private Node first;
    private Node last;

    private class Node {

        private Item value;
        private Node next;

        Node(Item value) {
            this.value = value;
        }
    }

    // construct an empty randomized queue
    public RandomizedQueueLinked() {
        size = 0;
        first = last = null;
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

        Node newLast = new Node(item);
        if (last == null) {
            first = last = newLast;
        } else {
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item;
        int randomN = StdRandom.uniform(size);
        if (randomN == 0) {
            item = first.value;
            first = first.next;
        } else {
            Node current = getNode(randomN - 1);
            item = current.next.value;
            current.next = current.next.next;
        }
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.value;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            int [] numbers = StdRandom.permutation(size);
            int index = size;

            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @Override
            public Item next() {
                if (isEmpty()) {
                    throw new NoSuchElementException();
                }
                return getNode(numbers[--index]).value;
            }
        };
    }

    private Node getNode(int n) {
        Node current = first;
        while (n-- > 0) {
            current = current.next;
        }
        return current;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueueLinked<String> randomizedQueueLinked = new RandomizedQueueLinked<>();

        for (int i = 0; i < 5; i++) {
            randomizedQueueLinked.enqueue("Item " + i);
        }

        Iterator iterator = randomizedQueueLinked.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
//        for (int i = 0; i < 5; i++) {
//            System.out.println(randomizedQueue.dequeue());
//        }
    }

}
