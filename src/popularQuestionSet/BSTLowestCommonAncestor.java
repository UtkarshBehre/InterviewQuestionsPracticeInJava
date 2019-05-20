package popularQuestionSet;

import utilityDataStructuresImplementations.BinarySearchTree;
import utilityDataStructuresImplementations.BinarySearchTree.Node;

/**
 * Lowest Common Ancestor in a Binary Search Tree
 * assuming both are present
 * Runtime: O(h)
 * @author Utkarsh
 */
public class BSTLowestCommonAncestor {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insertKey(20);
		bst.insertKey(8);
		bst.insertKey(22);
		bst.insertKey(4);
		bst.insertKey(12);
		bst.insertKey(10);
		bst.insertKey(14);
		printLCA(bst.root, 10,14);
		printLCA(bst.root, 14,8);
		printLCA(bst.root, 10,22);
		printLCAUsingRec(bst.root, 10, 14);
		printLCAUsingRec(bst.root, 14, 8);
		printLCAUsingRec(bst.root, 10, 22);
	}
	
	/**
	 * finds and prints the lowest common Ancestor by going either left and 
	 * right untill both keys are smaller or bigger and stopping when both
	 * conditions become false, uses iterative way
	 * Assumption: both keys are present. if this wasn't given we use search
	 * to make sure both are present to cover base cases in case they are not 
	 * present. It would still be O(h) solution
	 * Runtime: O(h)
	 */
	public static void printLCA(Node commonAnc, int key1, int key2) {
		while(true) {
			if(commonAnc.val > key1 && commonAnc.val > key2)
				commonAnc = commonAnc.left;
			else if(commonAnc.val < key1 && commonAnc.val < key2)
				commonAnc = commonAnc.right;
			else
				break;
		}
		System.out.println("Lowest Common Ancestor of "+key1+" and "+ key2+" is: " +commonAnc.val);
	}
	
	/**
	 * Recursive solution similar to iterative solution above but using extra 
	 * stack space to hold nodes variables
	 * Runtime: O(h) 
	 */
	public static void printLCAUsingRec(Node node, int key1, int key2) {
		int lca = printLCARec(node,key1,key2);
		System.out.println("Lowest Common Ancestor of "+key1+" and "+ key2+" is: " +lca);
	}
	
	public static int printLCARec(Node node, int key1, int key2) {
		if(node.val > key1 && node.val > key2)
			return printLCARec(node.left, key1, key2);
		if(node.val < key1 && node.val < key2)
			return printLCARec(node.right, key1, key2);
		return node.val;
		
	}
}
