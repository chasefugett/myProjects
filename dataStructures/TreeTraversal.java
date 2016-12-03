import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import data_structures.BST;
import data_structures.TreeNode;

/**
 * @author Chase Fugett
 */
public class TreeTraversal {
	
	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a
	 * binary search tree, return an ArrayList<Integer> holding the keys in
	 * order from a post-order traversal. Done with recursion.
	 * 
	 * @param r
	 * @return ArrayList
	 */
	public static ArrayList<Integer> postOrder(TreeNode<Integer, Integer> r) {
		ArrayList<Integer> postList = new ArrayList<Integer>();
		// Return for base case
		if (r == null) {
			return postList;
		}
		// Go left first, then right, then root node
		postList.addAll(postOrder(r.left));
		postList.addAll(postOrder(r.right));
		postList.add(r.key);
		return postList;
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a
	 * binary tree, return an ArrayList<Integer> holding the keys in order from
	 * a Breadth-First Search (BFS) traversal. Done iteratively with a queue.
	 * 
	 * @param root
	 * @return ArrayList
	 */
	public static ArrayList<Integer> BFS(TreeNode<Integer, Integer> root) {
		Queue<TreeNode<Integer, Integer>> q = new LinkedList<TreeNode<Integer, Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
        if (root == null) {
        	return list;
        }
        q.add(root);
        list.add(root.key);
        
        // Put into the ArrayList like a book, going from top to bottom and left to right
        while (!q.isEmpty()) {
            TreeNode<Integer, Integer> u = q.remove();
            if (u.left != null) {
            	q.add(u.left);
            	list.add(u.left.key);
            }
            if (u.right != null) {
            	q.add(u.right);
            	list.add(u.right.key);
            }
        }
        return list;
	}
	
	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a pre-order traversal.
	 * Done iteratively using a stack.
	 * 
	 * @param root
	 * @return ArrayList
	 */
	public static ArrayList<Integer> preOrder(TreeNode<Integer, Integer> root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		 
        if (root == null) {
            return list;
        }
        Stack<TreeNode<Integer, Integer>> stack = new Stack<TreeNode<Integer, Integer>>();
        stack.push(root);
 
        // Check root, then left child, then right child
        while(!stack.empty()){
            TreeNode<Integer, Integer> n = stack.pop();
            list.add(n.key);
 
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
 
        }
        return list;
    }
	
	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from an in-order traversal.
	 * Done iteratively using a stack.
	 * 
	 * @param root
	 * @return ArrayList
	 */
	public static ArrayList<Integer> inOrder(TreeNode<Integer, Integer> root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		 
        if (root == null) {
            return list; 
        }
        Stack<TreeNode<Integer, Integer>> stack = new Stack<TreeNode<Integer, Integer>>();
        TreeNode<Integer, Integer> p = root;
 
        // Check left child, then root, then right child
        while(!stack.empty() || p != null){
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode<Integer, Integer> t = stack.pop();
                list.add(t.key);
                p = t.right;
            }
        }
 
        return list;
    }
	
	/**
	 * My own series of tests I implemented to test a simple BST
	 * @param args
	 */
	public static void main(String[] args) {
		BST<Integer,Integer> T = new BST<Integer, Integer>();
		T.insert(4,4);
		T.insert(2,2);
		T.insert(6,6);
		T.insert(3,3);
		T.insert(1,1);
		T.insert(5,5);
		T.insert(7,7);
		
		ArrayList<Integer> pre = preOrder(T.getRoot());
		ArrayList<Integer> in = inOrder(T.getRoot());
		ArrayList<Integer> post = postOrder(T.getRoot());
		ArrayList<Integer> bfs = BFS(T.getRoot());
		
		System.out.println("PreOrder: " + pre.toString());
		System.out.println("InOrder: " + in.toString());
		System.out.println("PostOrder: " + post.toString());
		System.out.println("BFS: " + bfs.toString());
	}
}
