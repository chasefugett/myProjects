package data_structures;

/**
 * @param <value_type>
 *            The type of object to be stored in the list.
 */
class ListNode<value_type> {
	public value_type value;
	public ListNode<value_type> next;

	public ListNode(value_type v) {
		value = v;
		next = null;
	}

	public ListNode(value_type v, ListNode<value_type> n) {
		value = v;
		next = n;
	}

}

/**
 * @author Chase Fugett
 * Implementation of a single linked list
 */
public class LinkedList<value_type> extends Sequence<value_type> {

	/**
	 * head will be the first node of the list -- or null if the list is empty
	 */
	private ListNode<value_type> head;

	/**
	 * tail will be the last node of the list -- or null if n < 1
	 */
	private ListNode<value_type> tail;

	/**
	 * List constructor: must call the superclass constructor. Creates an empty
	 * LinkedList
	 */
	public LinkedList() {
		super();
		head = null;
		tail = null;
		n = 0;
	}

	/**
	 * Gets the value of index i
	 */
	@Override
	public value_type get(int i) {
		if (i < 0 || i >= size()) {
			throw new IndexOutOfBoundsException();
		}

		// Creates a node that is set to the head of the list
		ListNode<value_type> node = head;
		if (i == 0) {
			return head.value;
		} 
		else if (i == n) {
			return tail.value;
		}
		else {
			// Iterates through all nodes until j equals the ith node
			for (int j = 0; j != i; j++) {
				node = node.next;
			}

			// Returns the value of the ith node
			return node.value;
		}
	}

	/**
	 * Sets index i to a value
	 */
	@Override
	public value_type set(int i, value_type value) {
		if (i < 0 || i >= size()) {
			throw new IndexOutOfBoundsException();
		}

		// Creates a node that is set to the head of the list
		ListNode<value_type> node = head;
		value_type returnV;

		// If setting the first node
		if (i == 0) {
			returnV = head.value;
			head.value = value;
			return returnV;
		}
		// For all other nodes
		else {
			// Iterates through all nodes until j equals the ith node
			for (int j = 0; j != i; j++) {
				node = node.next;
			}

			// Sets the ith node equal to value
			returnV = node.value;
			node.value = value;

			// Returns the new value of the ith node
			return returnV;
		}

	}

	/**
	 * Adds a value to the LinkedList at index i
	 */
	@Override
	public void add(int i, value_type value) {
		if (i < 0 || i > size()) {
			throw new IndexOutOfBoundsException();
		}

		// Creates a node with value of value
		ListNode<value_type> addNode = new ListNode<value_type>(value);
		ListNode<value_type> node = head;
		
		// If the list is empty, make this the new head
		if (n == 0) {
			head = addNode;
		}
		if (i == 0) {
			addNode.next = head;
			head = addNode;
		}
		// Otherwise, set the current tail node to point to the newly made node
		else {
			for (int j = 0; j != i - 1; j++) {
				node = node.next;
			}
			ListNode<value_type> node2 = node.next;
			
			// Makes the new node point to the ith node
			addNode.next = node2;
			node.next = addNode;
		}
		//Sets the tail if necessary
		if (i == n) {
			tail = addNode;
		}
		n++;
	}

	/**
	 * Removes a value from the LinkedList
	 */
	@Override
	public value_type remove(int i) {
		if (i < 0 || i >= size()) {
			throw new IndexOutOfBoundsException();
		}

		value_type x;
		if (n == 0) {
			return null;
		}
		ListNode<value_type> node = head;

		// If we are removing the first element
		if (i == 0) {
			x = node.value;
			head = node.next;
		}
		// Removing all other positions of elements
		else {
			for (int j = 0; j != i - 1; j++) {
				node = node.next;
			}
			x = node.next.value;
			node.next = node.next.next;
		}

		// If there is only one node left in list, tail is set to null
		if (n - 2 == 0) {
			tail = null;
		}
		n--;
		return x;
	}

	/**
	 * Clears the LinkedList
	 */
	@Override
	public void clear() {
		n = 0;
		head = null;
		tail = null;
	}

	/**
	 * Overrides the in method to improve runtime
	 */
	@Override
	public boolean in(value_type query) {
		// Creates a node that is set to the head of the list
		ListNode<value_type> node = head;

		// If the head value contains the query, return true
		if (head.value.equals(query)) {
			return true;
		}
		// Check all other values in the list
		else {
			// Iterates through all nodes until j equals the ith node
			for (int j = 0; j != n; j++) {
				if (node.value.equals(query)) {
					return true;
				}
				node = node.next;
			}

			// Returns false if the value was not found in list
			return false;
		}
	}

	/**
	 * Overrides the push_back method to make it run in constant time
	 */
	@Override
	public void push_back(value_type value) {
		// Creates a node that is set to the head of the list
		ListNode<value_type> pushNode = new ListNode<value_type>(value);

		// If the list is empty, the new node is now the head
		if (n == 0) {
			head = pushNode;
		} else {
			tail.next = pushNode;
		}
        tail = pushNode;
		n++;

	}

}
