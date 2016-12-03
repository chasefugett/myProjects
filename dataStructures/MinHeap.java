package data_structures;

import java.util.ArrayList;

/**
 * @author Chase Fugett
 */
public class MinHeap<E, P extends Comparable<P>> implements MinPriorityQueue<E, P> {
	// Keeps track of size
	private int n;
	
	// Keeps track of number of operations
	private int numOps;
	
	private ArrayList<Pair<E,P>> list = new ArrayList<Pair<E,P>>();
	
	// Methods for computing the left, right, and parent nodes in a heap
	int left(int i) {
        return 2*i + 1;
    }
    int right(int i) {
        return 2*i + 2;
    }
    int parent(int i) {
        return (i-1)/2;
    }
    
    /**
     * Simple method to swap two elements
     * @param i Index of element to be swapped with j
     * @param j Index of element to be swapped with i
     */
    private void swap(int i, int j) {
		Pair<E,P> temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	/**
	 * Push an element e with priority p into the priority queue
	 * @param e  The element being put in the PriorityQueue.
	 * @param p  The priority of the e
	 */
	public void push(E e, P v) {
		Pair<E,P> pair = new Pair<E,P>(e,v);
        list.add(n++, pair);
        bubbleUp(n-1);
	}
	
	/**
	 * Helper method for pushing a value into the heap
	 * @param i
	 */
	void bubbleUp(int i) {
        int p = parent(i);
        while (i > 0 && (list.get(i).second.compareTo(list.get(p).second) < 0)) {
            swap(i,p);
            i = p;
            p = parent(i);
        }
    }
	
	/**
	 * Remove the smallest element of the priority queue from the queue 
	 * and return the element.  Ties broken arbitrarily.
	 * @return E
	 * @exception ArrayIndexOutOfBoundsException  Thrown when called on an empty heap.
	 */
	public E pop() {
		if (n == 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		E e = list.get(0).first;
        list.set(0, list.get(--n));
        trickleDown(0);
        return e;
	}
	
	/**
	 * Helper method to pop the smallest element from the queue
	 * @param i
	 */
	void trickleDown(int i) {
        do {
            int j = -1;
            int r = right(i);
            if (r < n && (list.get(r).second.compareTo(list.get(i).second) < 0)) {
                int l = left(i);
                if (list.get(l).second.compareTo(list.get(r).second) < 0) {
                    j = l;
                } else {
                    j = r;
                }
            } else {
                int l = left(i);
                if (l < n && list.get(l).second.compareTo(list.get(i).second) < 0) {
                    j = l;
                }
            }
            if (j >= 0) {
            	swap(i, j);
            }
            i = j;
        } while (i >= 0);
    }
	
	/**
	 * Return the smallest element of the priority queue (but to not
	 * remove it from the queue.
	 * @return E
	 */
	public E peek() {
		return list.get(0).first;
	}
	
	/**
	 * Return the size of the queue.
	 * @return int
	 */
	public int size() {
		return n;
	}
	
	/**
	 * Test whether heap is empty
	 */
	public boolean empty() {
		if (n == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Clear structure
	 */
	public void clear() {
		n = 0;
		list.clear();
	}
	
	/**
	 * Reset numOp count -- does not do anything in current implementation
	 */
	public void resetOps() {
		numOps = 0;
	}
	
	/**
	 * Get number of basic operations
	 * @return Number of operations
	 * Will return 0 every time in current implementation
	 */
	public int numOps() {
		return numOps;
	}
}
