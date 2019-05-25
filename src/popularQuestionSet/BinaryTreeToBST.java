package popularQuestionSet;

import java.util.Arrays;

/**
 * Binary Tree to Binary Search Tree Conversion
 * The conversion must be done in such a way that keeps the original structure 
 * of Binary Tree.
 * Runtime: O(nlogn)
 * @author Utkarsh
 */
public class BinaryTreeToBST {
	
	static class Node{
		int val;
		Node left;
		Node right;
		public Node(int val) {
			this.val = val;
		}
	}
	static int i = 0;
	
	public static void main(String[] args) {
		Node root = new Node(10);
		root.left = new Node(2);
		root.right = new Node(7);
		root.left.left = new Node(8);
		root.left.right = new Node(4);
		System.out.println("InOrder before conversion: ");
		printInOrder(root);
		root = convertBTToBST(root);
		System.out.println("\nInOrder after conversion: ");
		printInOrder(root);
	}
	
	/**
	 * main solution method that converts a binary tree to binary search tree
	 * by maintaining its original structure.
	 * Step1: converts binary tree to inorder array. 
	 * Step2: sort array.
	 * Step3: reassign new values to the nodes without changing links.
	 * We use static int i throught to keep track of array index.
	 * Runtime: O(nlogn)
	 * @param root of given binary tree
	 * @return root of converted binary search tree
	 */
	public static Node convertBTToBST(Node root) {
		int[] arr = new int[i];
		i=0;
		// step 1 O(n)
		convertBTToArray(root, arr);
		// step 2 O(nlogn) as Arrays.sort implements dual pivot quick sort
		Arrays.sort(arr);
		i=0;
		// step 3 O(n)
		reassignNodeValues(root, arr);
		return root;
	}
	
	/**
	 * reassign node values from left most to right using sorted array
	 */
	public static void reassignNodeValues(Node node, int[] arr) {
		if(node == null)
			return;
		reassignNodeValues(node.left, arr);
		node.val = arr[i++];
		reassignNodeValues(node.right, arr);
	}
	
	/**
	 * converts tree to array using inorder traversal
	 */
	public static void convertBTToArray(Node node, int[] arr) {
		if(node == null)
			return;
		convertBTToArray(node.left, arr);
		arr[i++] = node.val;
		convertBTToArray(node.right,arr);
	}
	
	/**
	 * prints inorder of tree and counts
	 */
	public static void printInOrder(Node node) {
		if(node == null)
			return;
		printInOrder(node.left);
		i++;
		System.out.print(node.val+" ");
		printInOrder(node.right);
	}
}
