
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
	private class Node {
		Item item;
		Node next;
	}
	private Node first;
	private Node last;
	private int N;
	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		N = 0;
	}
	// is the deque empty?
	public boolean isEmpty() {
		return first == null;
	}
	// return the number of items on the deque
	public int size() {
		return N;
	}
	// add the item to the front
	public void addFirst(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		if (first.next == null) last = first;
		N++;
	}
	// add the item to the end
	public void addLast(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldlast.next = last;
		N++;
	}
	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException("underflow");
		Item item = first.item;	// save item to return
		first = first.next;	// delete first node
		N--;
		return item;
	}
	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty()) throw new NoSuchElementException("underflow");
		Node n, oldLast = last;
		for (n = first; n.next != last; n = n.next);
		n.next = null;
		last = n;
		if (isEmpty()) { last = null; first = null; } // no loitering
		N--;
		return oldLast.item;
	}
	// return an iterator over items in order from front to end
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
		Deque<String> q = new Deque<String>();
		StdOut.println("Enter elements: ");
		String item;
		while (true) {
			item = StdIn.readString();
			if (item.equals("*")) break;
			else if (item.startsWith("-")) q.addFirst(item);
			else if (item.startsWith("+")) q.addLast(item);
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
			else if (item.equals("-")) q.removeFirst();
			else if (item.equals("+")) q.removeLast();
		}
		StdOut.println("(" + q.size() + " left on queue)");
		for (String s : q) {
			StdOut.print(s + ",");
		}
		StdOut.println();
	}
}
