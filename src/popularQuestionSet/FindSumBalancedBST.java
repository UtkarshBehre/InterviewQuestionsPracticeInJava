package popularQuestionSet;

import java.util.Stack;

import utilityDataStructuresImplementations.BinarySearchTree;
import utilityDataStructuresImplementations.BinarySearchTree.Node;

/**
 * Find a pair with given sum in a Balanced BST
 * Given a Balanced Binary Search Tree and a target sum, write a function that 
 * returns true if there is a pair with sum equals to target sum, otherwise 
 * return false. Expected time complexity is O(n) and only O(Logn) extra space 
 * can be used. Any modification to Binary Search Tree is not allowed. Note that 
 * height of a Balanced BST is always O(Logn).
 * Runtime: O(n)
 * Space: O(h) or O(logn) as given bst is balanced
 * @author Utkarsh
 */
public class FindSumBalancedBST {
	public static void main(String[] args){
		BinarySearchTree tree = new BinarySearchTree();
		tree.insertKey(15);
		tree.insertKey(10);
		tree.insertKey(20);
		tree.insertKey(8);
		tree.insertKey(12);
		tree.insertKey(16);
		tree.insertKey(25);
		
		// change sum to test
		int sum = 27;
		System.out.println("Pair present: " + isPairWithSumPresent(tree.root, sum));
	}
	
	/**
	 * solution traverses in inorder and reverse inorder from both end of bst
	 * logic is similar to how we find sum in sorted array 
	 * if both pointers end up reaching the root means no solution found
	 * Runtime: O(n)
	 * Space: O(h) or O(logn) as given bst is balanced
	 */
	public static boolean isPairWithSumPresent(Node root, int sum){
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		Node cur1 = root;
		Node cur2 = root;
		// cur1 and stack1 is used to traverse inorder
		// cur2 and stack2 is used to traverse reverse inorder
		while(cur1!=null || !stack1.isEmpty() || cur2!=null || !stack2.isEmpty()){
			while(cur1!=null){
				stack1.push(cur1);
				cur1 = cur1.left;
			}
			while(cur2!=null){
				stack2.push(cur2);
				cur2 = cur2.right;
			}
			
			cur1 = stack1.pop();
			cur2 = stack2.pop();
			// root reached so no pair with sum exists
			if(cur1.val == cur2.val)
				return false;
			// pair found
			if(cur1.val + cur2.val == sum)
				return true;
			// pair sum is more so only let cur2 traverse
			else if(cur1.val + cur2.val > sum){
				cur2 = cur2.left;
				stack1.push(cur1);
				cur1 = null;
			} // pair sum is less so only let cur1 traverse
			else {
				cur1 = cur1.right;
				stack2.push(cur2);
				cur2 = null;
			}
		}
		// code should never reach here.
		return false;
	}
}
