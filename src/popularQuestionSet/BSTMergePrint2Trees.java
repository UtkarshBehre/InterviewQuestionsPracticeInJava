package popularQuestionSet;

import java.util.Stack;

import utilityDataStructuresImplementations.BinarySearchTree;
import utilityDataStructuresImplementations.BinarySearchTree.Node;

/**
 * Merge two BSTs with limited extra space Given two Binary Search Trees(BST),
 * print the elements of both BSTs in sorted form. The expected time complexity
 * is O(m+n) where m is the number of nodes in first tree and n is the number of
 * nodes in second tree. Maximum allowed auxiliary space is O(height of the
 * first tree + height of the second tree).
 * 
 * Runtime: O(m+n) where m,n = nodes on tree1 and tree2
 * Spacetime: O(h1+h2) where h1,h2 = heights of tree1 and tree2
 * @author Utkarsh
 *
 */
public class BSTMergePrint2Trees {
	public static void main(String[] args) {
		BinarySearchTree bst1 = new BinarySearchTree();
		bst1.insertKey(7);
		bst1.insertKey(4);
		bst1.insertKey(2);
		bst1.insertKey(6);
		bst1.insertKey(0);
		bst1.insertKey(8);
		bst1.insertKey(10);
		bst1.insertKey(9);
		
		BinarySearchTree bst2 = new BinarySearchTree();
		bst2.insertKey(5);
		bst2.insertKey(3);
		bst2.insertKey(11);
		bst2.insertKey(1);
		bst2.insertKey(15);
		bst2.insertKey(13);
		bst2.insertKey(14);
		// prints elements of both trees in ascending order
		printElements(bst1, bst2);

	}
	
	/**
	 * prints elements of 2 binary search tree in ascending order
	 * @param bst1 = first binary search tree
	 * @param bst2 = second binary search tree
	 */
	public static void printElements(BinarySearchTree bst1, BinarySearchTree bst2) {
		System.out.println("Combined elements of both trees in sorted form:");
		printNextSmallestLinear(bst1.root, bst2.root);
	}

	/**
	 * Best linear approach using 2 stacks with O(m+n) runtime and O(h1+h2) spacetime
	 * iterative inorder approach using 2 stacks for both trees
	 * whenever we encounter elements from stacks we do 2 things
	 * step 1. compare them, print smaller one, and make it traverse 
	 * step 2. push back the bigger one back to stack for next comparison.
	 * Runtime: O(m+n) where m,n = nodes on tree1 and tree2
	 * Spacetime: O(h1+h2) where h1,h2 = heights of tree1 and tree2
	 * @param root1 = root of first binary search tree
	 * @param root2 = root of second binary search tree
	 */
	public static void printNextSmallestLinear(Node root1, Node root2) {
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		Node cur1 = root1;
		Node cur2 = root2;
		while(cur1!=null || cur2!=null || (!stack1.isEmpty() && !stack2.isEmpty())) {
			
			while(cur1!=null) {
				stack1.push(cur1);
				cur1 = cur1.left;
			}
			
			while(cur2!=null) {
				stack2.push(cur2);
				cur2 = cur2.left;
			}
			
			cur1 = stack1.pop();
			cur2 = stack2.pop();
			if(cur1.val < cur2.val) {
				System.out.print(cur1.val+" ");
				cur1 = cur1.right;
				stack2.push(cur2);
				cur2 =null;
			} else {
				System.out.print(cur2.val+" ");
				cur2 = cur2.right;
				stack1.push(cur1);
				cur1 = null;
			}
		}
		if(!stack1.isEmpty())
			printBSTFromStack(stack1);
		if(!stack2.isEmpty())
			printBSTFromStack(stack2);
	}
	
	/**
	 * utility inorder printing from stack used in linear solution
	 * @param stack
	 */
	public static void printBSTFromStack(Stack<Node> stack) {
		Node cur = null;
		while(cur!=null || !stack.isEmpty()) {
			
			while(cur!=null) {
				stack.push(cur);
				cur=cur.left;
			}
			cur = stack.pop();
			System.out.print(cur.val+" ");
			cur = cur.right;
		}
	}
}
