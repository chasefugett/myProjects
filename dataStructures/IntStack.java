import java.lang.IllegalStateException;
import java.util.Stack;
/**
 * 
 * @author Chase Fugett
 * 
 * Implements a simple stack class, containing ints and using arrays
 * for implementation
 *
 */
public class IntStack {
	
	private Stack<Integer> stack;
	private int maxSize;

	/**
	 * Constructor
	 * @param max_size     Maximum size of the queue   
	 */
	public IntStack(int maxSize) {
		this.stack = new Stack<Integer>();
		this.maxSize = maxSize;
	}
	
	/**
	 * Push a value onto the top of a queue.
	 * @param value   Value to be pushed
	 * @exception  IllegalStateException   Thrown if the stack is full
	 */
	public void push(int value) {
		if (stack.size() >= maxSize) {
			throw new IllegalStateException();
		}
		else {
			stack.push(value);
		}
	}
	
	/**
	 * Pop a value from the stack: return the value, remove from stack.
	 * @return Top values of stack
	 * @exception IllegalStateException   Thrown if you pop from an empty stack.
	 */
	public int pop() {
		if (stack.size() == 0) {
			throw new IllegalStateException();
		}
		else {
			return stack.pop();
		}
	}
	
	/**
	 * Return the top value on the stack without removing it.
	 * @return Top of value of stack.
	 * @exception IllegalStateException()  Thrown if if applied to an empty stack.
	 */
	public int peek() {
		if (stack.size() == 0) {
			throw new IllegalStateException();
		}
		else {
			return stack.peek();
		}
	}
	
	/**
	 * Determine if the stack is empty.
	 * @return True if empty.
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * Determine if the stack is at capacity.
	 * @return True if at capacity.
	 */
	public boolean isFull() {
		if (stack.size() >= maxSize) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Number of elements in the stack.
	 * @return Number of elements in the stack.
	 */
	public int size() {
		return stack.size();
	}
}
