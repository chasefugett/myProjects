package data_structures;

import java.lang.IndexOutOfBoundsException;

/**
 * @author Chase Fugett
 * 
 * Implementation of a binary search tree
 */
public class BST<K extends Comparable<K>, V> extends Dictionary<K, V> {
	// Stores the root of the BST
	protected TreeNode<K, V> root;

	public BST() {
		super();
		root = null;
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
	private int compareKeys(K k1, K k2) {
		numOps++;
		return k1.compareTo(k2);
	}

	/**
	 * Get the tree's root node.
	 * 
	 * @return root
	 */
	public TreeNode<K, V> getRoot() {
		return root;
	}

	@Override
	public void insert(K key, V value) {
		// Checks to make sure the same key is not inserted twice
		if (find(key) != null) {
			throw new IndexOutOfBoundsException();
		}
		// If there is no root, make this new node the root
		if (root == null) {
			root = new TreeNode<K, V>(key, value);
			n++;
		} else {
			TreeNode<K, V> u = new TreeNode<K, V>(key, value);
			TreeNode<K, V> p = findLast(u.key);
			addChild(p, u);
		}
	}

	/**
	 * Add the node u as a child of node p
	 * 
	 * @param p
	 * @param u
	 */
	private void addChild(TreeNode<K, V> p, TreeNode<K, V> u) {
		int comp = compareKeys(u.key, p.key);
		if (comp < 0) {
			p.left = u;
		} else if (comp > 0) {
			p.right = u;
		}
		n++;
	}

	/**
	 * Search for a value in the tree
	 * 
	 * @return the last node on the search path for key
	 */
	private TreeNode<K, V> findLast(K key) {
		TreeNode<K, V> w = root;
		TreeNode<K, V> prev = null;
		while (w != null) {
			prev = w;
			int comp = compareKeys(key, w.key);
			if (comp < 0) {
				w = w.left;
			} else if (comp > 0) {
				w = w.right;
			} else {
				return w;
			}
		}
		return prev;
	}

	@Override
	public void remove(K key) {
		root = deleteTreeNode(root, key);
	}

	/**
	 * Helper method that recursively 
	 * @param root
	 * @param key
	 * @return
	 */
	private TreeNode<K, V> deleteTreeNode(TreeNode<K, V> root, K key) {
		TreeNode<K, V> cur = root;
		if (cur == null) {
			return cur;
		}
		int comp = compareKeys(key, cur.key);
		if (comp < 0) {
			cur.left = deleteTreeNode(cur.left, key);
		} else if (comp > 0) {
			cur.right = deleteTreeNode(cur.right, key);
		} else {
			if (cur.left == null && cur.right == null) {
				cur = null;
			} else if (cur.right == null) {
				cur = cur.left;
			} else if (cur.left == null) {
				cur = cur.right;
			} else {
				TreeNode<K, V> temp = findMinFromRight(cur.right);
				cur = temp;
				cur.right = deleteTreeNode(cur.right, temp.key);
			}
		}
		n--;
		return cur;
	}

	/**
	 * Find the minimum value on the right subtree
	 * @param node
	 * @return TreenNode<K,V>
	 */
	private TreeNode<K, V> findMinFromRight(TreeNode<K, V> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	/**
	 *  Given a key, finds the value for that node
	 *  
	 *  @param K
	 *  @return V
	 */
	@Override
	public V find(K key) {
		TreeNode<K, V> u = root;

		while (u != null) {
			int comp = compareKeys(key, u.key);
			if (comp < 0) {
				u = u.left;
			} else if (comp > 0) {
				u = u.right;
			} else {
				return u.value;
			}
		}
		return null;
	}

	/**
	 * Return the smallest value in the tree. (Return null if empty)
	 * 
	 * @return key
	 */
	public K min() {
		// If there is no root, there is no smallest value
		if (root == null) {
			return null;
		}
		TreeNode<K, V> u = root;
		while (u.left != null) {
			u = u.left;
		}
		return u.key;
	}

	/**
	 * Return the smallest value in the tree. (Return null if empty)
	 * 
	 * @return key
	 */
	public K max() {
		// If there is no root, there is no largest value
		if (root == null) {
			return null;
		}
		TreeNode<K, V> u = root;
		while (u.right != null) {
			u = u.right;
		}
		return u.key;
	}

	/**
	 * Return the height of the tree. The height of a tree is
	 * equal to the depth of the node with the greatest depth of all the nodes.
	 * 
	 * @return int
	 */
	public int height() {
		TreeNode<K, V> u = root;
		return height(u);
	}

	/**
	 * Recursive height helper method
	 * @param u
	 * @return
	 */
	private int height(TreeNode<K, V> u) {
		if (u == null)
			return -1;
		return 1 + Math.max(height(u.left), height(u.right));
	}

	/**
	 * Helper method that check if the tree is a BST
	 * @param root
	 * @param min_value
	 * @param max_value
	 * @return
	 */
	boolean isBSTHelper(TreeNode<K, V> root, K min_value, K max_value) {
		if (root == null)
			return true;

		if ((min_value != null && root.key.compareTo(min_value) <= 0)
				|| (max_value != null && root.key.compareTo(max_value) >= 0))
			return false;

		return isBSTHelper(root.left, min_value, root.key)
				&& isBSTHelper(root.right, root.key, max_value);
	}

	/**
	 * Check that the tree is a BST.
	 * 
	 * @param root
	 *            Root of tree being checked.
	 * @return
	 */
	boolean isBST(TreeNode<K, V> root) {
		return isBSTHelper(root, null, null);
	}


	@Override
	public boolean check_structure() {
		return isBST(root);
	}

	void print_structure_helper(TreeNode<K, V> root, int indent) {
		for (int i = 0; i < indent; i++)
			System.out.print("\t");
		if (root == null) {
			System.out.println("LEAF");
			return;
		}
		System.out.println(root.key + ": " + root.value);
		print_structure_helper(root.left, indent + 1);
		print_structure_helper(root.right, indent + 1);
	}

	@Override
	public void print_structure() {
		print_structure_helper(root, 0);
	}

	@Override
	public void clear() {
		n = 0;
		root = null;
	}

}
