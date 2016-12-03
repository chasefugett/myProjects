package data_structures;

/**
 * 
 * @author Chase Fugett
 * 
 * Simple class that creates a generic node for a binary search tree. 
 * Contains a comparable key and value.
 *
 * @param <K> key
 * @param <V> value
 */
public class TreeNode<K extends Comparable<K>, V> {
	public K key;
	public V value;
	public TreeNode<K,V> left;
	public TreeNode<K,V> right;

	public TreeNode(K key, V value) {
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public TreeNode(K key, V value, TreeNode<K,V> left, TreeNode<K,V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
}
