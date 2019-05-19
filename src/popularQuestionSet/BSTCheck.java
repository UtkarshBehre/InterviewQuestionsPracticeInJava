package popularQuestionSet;

import utilityDataStructuresImplementations.BinarySearchTree;
import utilityDataStructuresImplementations.BinarySearchTree.Node;

/**
 * check if a binary tree is BST or not
 * @author Utkarsh
 */
public class BSTCheck {
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.insertKey(50);
		tree.insertKey(30);
		tree.insertKey(20);
		tree.insertKey(40);
		tree.insertKey(70);
		tree.insertKey(60);
		tree.insertKey(80);
		tree.root.right.right.left = new Node(45); // change tree here and check solution
		System.out.println(isTreeBST(tree));
	}

	/**
	 * recursive solution using min and max integers to check each node encountered
	 * O(n)
	 * @param tree
	 * @return
	 */
	public static boolean isTreeBST(BinarySearchTree tree) {
		return isTreeBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * checks if the current node is in its alowed range between small and big
	 * @param node = current node to be checked for correctness
	 * @param small = lower bound excluded
	 * @param big = higher bound excluded
	 * @return = true if tree is a binary search tree
	 */
	private static boolean isTreeBST(Node node, int small, int big) {
		if(node == null)
			return true;
		if(node.val <= small || node.val >= big)
			return false;
		return isTreeBST(node.left, small, node.val) && isTreeBST(node.right, node.val, big);
	}
	
	/**
	 * solution uses inOrder traversal technique, so if any current node is lower
	 * or equal to last encountered node then its not a bst
	 * Runtime: O(n)
	 * @param tree
	 * @return
	 */
	public static boolean isTreeBSTUsingInOrder(BinarySearchTree tree) {
		return isTreeBSTUsingInOrder(tree.root, Integer.MAX_VALUE);
	}
	
	/**
	 * InOrder Traversal that returns false if rules are broken at any node
	 * @param node
	 * @param prev
	 * @return
	 */
	private static boolean isTreeBSTUsingInOrder(Node node, int prev) {
		if(node == null) {
			return true;
		}
		if(!isTreeBSTUsingInOrder(node.left, prev))
			return false;
		if(node.val <= prev) {
			return false;
		}
		prev = node.val;
		return isTreeBSTUsingInOrder(node.right,prev);
	}
}
