package chapter_1_3;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
    private Node first;
    private int size;

    private class Node {
        Node next;
        Item item;
    }

    public int size() {
        return size;
    }

    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item currentItem = current.item;
            current = current.next;
            return currentItem;
        }
    }
}
