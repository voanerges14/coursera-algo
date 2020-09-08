//package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first;
    private Node last;
    private int max = Integer.MIN_VALUE;

    private class Node {
        Item value;
        Node previous;
        Node next;

        Node(Item value) {
            this.value = value;
            this.previous = this.next = null;
        }
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        this.first = this.last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw  new IllegalArgumentException();
        Node newFirst =  new Node(item);
        if (first == null) {
            first = last = newFirst;
        } else {
            newFirst.next = first;
            first.previous = newFirst;
            first = newFirst;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw  new IllegalArgumentException();
        Node newLast =  new Node(item);
        if (last == null) {
            first = last = newLast;
        } else {
            newLast.previous = last;
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.value;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.previous = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.value;
        last = last.previous;

        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node current = first;
            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public Item next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Item item = current.value;
                current = current.next;
                return item;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
