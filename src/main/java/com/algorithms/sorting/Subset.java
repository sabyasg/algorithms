import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
	public static void main(String[] args) {
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		String item;
		int n = Integer.parseInt(args[0]), i;

		while (!StdIn.isEmpty()) {
			item = StdIn.readString();
			if (item.equals("*")) break;
			q.enqueue(item);
		}
		for (i = 0; i < n; i++) {
			StdOut.println(q.dequeue());
		}
	}
}
