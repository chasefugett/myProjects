package data_structures;

import java.util.Random;
import java.lang.IndexOutOfBoundsException;

/**
 * SLNode: A helper class
 * This implementation stores a key and value as a dictionary for each node. Each node also 
 * contains a next and down pointer, that points to either the next node to the right, 
 * or the node down the stack. Each node also specifies a height.
 */
class SLNode<key_type, value_type> {
	public SLNode<key_type, value_type> next;
	public SLNode<key_type, value_type> down;
	public key_type key;
	public value_type value;
	public int height;

	public SLNode(key_type key, value_type value, int height) {
		this(key, value, height, null, null);
	}

	public SLNode(key_type key, value_type value, int height,
			SLNode<key_type, value_type> next, SLNode<key_type, value_type> down) {
		this.next = next;
		this.down = down;
		this.key = key;
		this.value = value;
		this.height = height;
	}

}

/**
 * @author Chase Fugett
 *
 * @param <key_type>
 *            The dictionary key type.
 * @param <value_type>
 *            The dictionary value type.
 */
public class SkipList<key_type extends Comparable<key_type>, value_type>
		extends Dictionary<key_type, value_type> {

	// The top node of the left sentinel tower
	SLNode<key_type, value_type> head;
	// Probability of increasing the height by 1. (p = 1.0 / expected_height).
	double p;
	// Maximum allowed height
	int max_height;
	// Random number generator
	Random rng;

	// Lower bound on values allowed in the skip list
	key_type min_key;
	// Upper bound on values allowed in the skip list
	key_type max_key;

	/**
	 * Helper function: Compare the key values of two node. numOps will be
	 * incremented in the compareKeys call.
	 * 
	 * @param n1
	 *            First node
	 * @param n2
	 *            Second node
	 * @return -1: n1 has smaller key; 0: keys equal; 1: n2 has smaller key
	 */
	int compareNodes(SLNode<key_type, value_type> n1,
			SLNode<key_type, value_type> n2) {
		return compareKeys(n1.key, n2.key);
	}

	/**
	 * Compare two keys and increment numOps.
	 * 
	 * @param k1
	 *            First key
	 * @param k2
	 *            Second key
	 * @return -1: k1 smaller; 0: elements equal; 1: k2 smaller
	 */
	int compareKeys(key_type k1, key_type k2) {
		return k1.compareTo(k2);
	}

	/**
	 * Skip list constructor.
	 * 
	 * @param expected_height
	 *            Expected height of each "tower".
	 * @param max_height
	 *            Maximum allowed height of a tower.
	 * @param min_key
	 *            Minimum allowed value of any key.
	 * @param max_key
	 *            Maximum allowed value of any key.
	 */
	public SkipList(int expected_height, int max_height, key_type min_key,
			key_type max_key) {
		this(expected_height, max_height, min_key, max_key, null);
	}

	/**
	 * Skip list constructor.
	 * 
	 * @param expected_height
	 *            Expected height of each "tower". (p = 1/expected_height)
	 * @param max_height
	 *            Maximum allowed height of a tower.
	 * @param min_key
	 *            Minimum allowed value of any key.
	 * @param max_key
	 *            Maximum allowed value of any key.
	 * @param rng_seed
	 *            Seed for the random number generator.
	 */
	public SkipList(int expected_height, int max_height, key_type min_key,
			key_type max_key, int rng_seed) {
		this(expected_height, max_height, min_key, max_key,
				new Random(rng_seed));
	}

	/**
	 * Skip list constructor.
	 * 
	 * @param expected_height
	 *            Expected height of each "tower".
	 * @param max_height
	 *            Maximum allowed height of a tower.
	 * @param min_key
	 *            Minimum allowed value of any key.
	 * @param max_key
	 *            Maximum allowed value of any key.
	 * @param rng
	 *            Random number generator object. (If null, create a new rng().)
	 */
	public SkipList(int expected_height, int max_height, key_type min_key,
			key_type max_key, Random rng) {
		super();
		this.p = (1.0 / expected_height);
		if (rng != null) {
			this.rng = rng;
		}
		else {
			this.rng = new Random();
		}
		
		// Sets the max height, min key, and max key
		this.max_height = max_height;
		this.min_key = min_key;
		this.max_key = max_key;
		
		// * min_key and max_key should become the values in the sentinel nodes.
		// They are specified by the user, and place limits on dictionary
		// content.
		head = new SLNode<key_type, value_type>(min_key, null, 0, 
				new SLNode<key_type, value_type>(max_key, null, 0, null, null), null);

		// Goes up to create the entire sentinel nodes
		for (int h = 1; h <= max_height - 1; h++) {
			head = new SLNode<key_type, value_type>(min_key, null, h, 
					new SLNode<key_type, value_type>(max_key, null, h, null, head.next), head);
		}
	}

	/**
	 * Reset the SkipList to an "empty" state.
	 */
	public void clear() {
		n = 0;
		head = new SLNode<key_type, value_type>(min_key, null, 0, 
				new SLNode<key_type, value_type>(max_key, null, 0, null, null), null);

		// Goes up to create the entire sentinel nodes
		for (int h = 1; h <= max_height - 1; h++) {
			head = new SLNode<key_type, value_type>(min_key, null, h, 
					new SLNode<key_type, value_type>(max_key, null, h, null, head.next), head);
		}
		resetOps();
	}

	/**
	 * Searches for a key.
	 * 
	 * @param key
	 *            Key being searched for.
	 * @return The corresponding value if the key is contained; null otherwise.
	 */
	public value_type find(key_type key) {
		// Start at the top of the sentinel stack
		SLNode<key_type, value_type> node = head;
		int r = max_height - 1;
		
		// While the current node's key is less than the max value, continue
		// looping
		while (r >= 0) {
			while (compareKeys(node.next.key, key) < 0 && node.next != null) {
				node = node.next;
			}
			// Find the key, return the value
			if (compareKeys(key, (key_type) node.next.key) == 0 && node.next != null) {
				return (value_type) node.next.value;
			}
			node = node.down;
			r--;
		}

		// Key was not found in any node, so return null
		return null;
	}

	/**
	 * Insert a new key/value pair into the skiplist.
	 * 
	 * @param key
	 *            The key being inserted.
	 * @param value
	 *            The value being inserted into the skiplist.
	 * @exception Throw
	 *                IndexOutOfBoundsException if key <= min_value, >=
	 *                max_value, or already in the list.
	 */
	public void insert(key_type key, value_type value) {
		// Throws exception if the key is outside the min or max values or if repeated key
		if ((compareKeys(key, (key_type) min_key) <= 0)
				|| (compareKeys(key, max_key) >= 0) || in(key)) {
			throw new IndexOutOfBoundsException();
		}

		// Keep track of the order of traversal with a stack
		LinkedList<SLNode<key_type, value_type>> stack = new LinkedList<SLNode<key_type, value_type>>();

		// Start at the top of the sentinel stack
		SLNode<key_type, value_type> node = head;
		int r = max_height - 1;
		// While the current node's key is less than the max value, continue
		// looping
		while (r >= 0) {
			while (node.next != null && (compareKeys(node.next.key, key) < 0 ) ) {
				node = node.next;
			}
			// If key is found, throw exception
			if (node.next != null && compareKeys(node.next.key, key) == 0) {
				throw new IndexOutOfBoundsException();
			}
			stack.push_front(node);
			node = node.down;
			r--;
		}
		
		// Create a random height for the new stack
		int randomNum;
		do {
			randomNum = (int) (rng.nextDouble() * 10);
		} while (randomNum > 9 || randomNum < 0);
		
		// The stack of nodes we need to insert
		LinkedList<SLNode<key_type, value_type>> stackofNodes = new LinkedList<SLNode<key_type, value_type>>();
		
		// Creating the nodes and putting them into the stack
		SLNode<key_type, value_type> newNode = new SLNode<key_type, value_type>(key, value, 0, null, null);
		stackofNodes.push_back(newNode);
		for (int h = 1; h < randomNum; h++) {
			newNode = new SLNode<key_type, value_type>(key, value, h, null, newNode);
			stackofNodes.push_back(newNode);
		}
		
		// Rewiring the skip list
		for (int i = 0; i < stackofNodes.size(); i++) {
			stackofNodes.get(i).next = stack.get(i).next;
			stack.get(i).next = stackofNodes.get(i);
		}
		n++;
	}

	/**
	 * Remove a key/value pair if present. (Do nothing otherwise.)
	 * 
	 * @param key
	 *            Key of the key/value pair to be removed.
	 */
	public void remove(key_type key) {
		// Only executes if the key exists
		if (find(key) != null) {
			// Start at the top of the sentinel stack
			SLNode<key_type, value_type> node = head;
			int r = max_height - 1;
			// While the current node's key is less than the max value, continue
			// looping
			while (r >= 0) {
				while (node.next != null && (compareKeys(node.next.key, key) < 0 ) ) {
					node = node.next;
				}
				// When we find the node we are looking for, rewire the next values
				if (node.next != null && compareKeys(node.next.key, key) == 0) {
					node.next = node.next.next;
				}
				node = node.down;
				r--;
			}
			n--;
		}
	}

	/**
	 * Number of nodes in the tree. (Nodes, not keys.)
	 * 
	 * @return int specifying the number of nodes in the tree.
	 */
	public int num_nodes() {
		int numNodes = 0;

		return numNodes;
	}

	/**
	 * Print out a string representation of data structure to help with
	 * debugging
	 */
	public void print_structure() {
		SLNode<key_type, value_type> q = head;
		while (q != null) {
			SLNode<key_type, value_type> q2 = q;
			while (q2 != null) {
				System.out.print(q2.key + "(" + q2.height + ")" + "\t");
				q2 = q2.next;
			}
			System.out.println("");
			q = q.down;
		}
	}

	/**
	 * Check to make sure it contains a legitimate skip-list structures
	 */
	public boolean check_structure() {
		SLNode<key_type, value_type> p = head;
		if (p.height != max_height - 1) {
			System.err.println("Head node not at correct height");
			return false;
		}

		for (int h = max_height - 1; h >= 0; h--) {
			SLNode<key_type, value_type> q = p;

			while (q.next != null) {
				if (q.height != h) {
					System.err.println("Height " + h
							+ ": Node at wrong height (" + q.height + ")");
					return false;
				}
				if (this.compareNodes(q, q.next) > 0) {
					System.err.println("Height " + h + ": Values out of order");
					return false;
				}
				if (compareNodes(q, q.next) == 0) {
					System.err.println("Height " + h + ": Duplicate value");
					return false;
				}
				if (q.down != null && q.key != null
						&& compareNodes(q, q.down) != 0) {
					System.err.println("Height " + h
							+ ": Tower values not the same");
					return false;
				}
				if (h == 0 && q.down != null) {
					System.err
							.println("Bottom node without null down pointer.");
					return false;
				}
				if (h > 0 && q.height != q.down.height + 1) {
					System.err.println("Height " + h + ": Height skipped");
					return false;
				}
				q = q.next;
			}

			if (q.next != null) {
				System.err.println("Height " + h
						+ ": Right-most node does not have a null pointer");
				return false;
			}
			if (h == 0 && q.down != null) {
				System.err.println("Bottom node without null down pointer.");
				return false;
			}
			if (h > 0 && q.height != q.down.height + 1) {
				System.err.println("Height " + h + ": Height skipped");
				return false;
			}
			p = p.down;

		}
		return true;

	}
}
