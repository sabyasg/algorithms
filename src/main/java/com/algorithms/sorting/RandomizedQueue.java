
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private class Node {
		Item item;
		Node next;
	}
	private Node first;
	private int N;

	// construct an empty randomized queue
	public RandomizedQueue() {
		first = null;
		N = 0;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return first == null;
	}

	// return the number of items on the queue
	public int size() {
		return N;
	}

	// add the item
	public void enqueue(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	private Node getRandomNode() {
		Random rn = new Random();
		int index = N > 0 ? rn.nextInt(N) : -1, i;
		Node n;

		if (isEmpty()) throw new NoSuchElementException("underflow");
		for (i = 0, n = first; i < index - 1; n = n.next, i++);
		return n;
	}

	// remove and return a random item
	public Item dequeue() {
		Node p = getRandomNode();
		Node r;

		if (p == first) {
			r = p;
			first = p.next;
		} else {
			r = p.next;
		}
		if (r != null) p.next = r.next; else p.next = null;
		if (isEmpty()) { first = null; } // no loitering
		N--;
		return r != null ? r.item : p.item;
	}

	// return (but do not remove) a random item
	public Item sample() {
		Node p = getRandomNode();
		return p.item;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext()  { return current != null; }
		public void remove() {
			throw new UnsupportedOperationException();
		}
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next; 
			return item;
		}
	}

	// unit testing
	public static void main(String[] args) {
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		StdOut.println("Enter elements: ");
		String item;
		while (true) {
			item = StdIn.readString();
			if (item.equals("*")) break;
			q.enqueue(item);
		}
		StdOut.println("(" + q.size() + " left on queue)");
		for (String s : q) {
			StdOut.print(s + ",");
		}
		StdOut.println();
		StdOut.println("Removal: ");
		while (true) {
			item = StdIn.readString();
			if (item.equals("*")) break;
			else if (item.equals("-")) {
				StdOut.println("Removed " + q.dequeue());
				StdOut.println("(" + q.size() + " left on queue)");
				for (String s : q) {
					StdOut.print(s + ",");
				}
				StdOut.println();
			}
		}
	}
}
