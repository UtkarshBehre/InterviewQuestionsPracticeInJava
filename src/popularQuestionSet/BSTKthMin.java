package popularQuestionSet;

import utilityDataStructuresImplementations.BinarySearchTree;
import utilityDataStructuresImplementations.BinarySearchTree.Node;

/**
 * Find k-th smallest element in BST (Order Statistics in BST)
 * O(n)
 * @author Utkarsh
 */
public class BSTKthMin {

	static int kCurrent =0;
	
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.insertKey(20);
		tree.insertKey(8);
		tree.insertKey(22);
		tree.insertKey(4);
		tree.insertKey(12);
		tree.insertKey(10);
		tree.insertKey(14);
		printKthUsingInOrderRec(tree.root, 5);
	}
	public static boolean printKthUsingInOrderRec(Node node, int k) {
		if(node == null)
			return false;
		if(printKthUsingInOrderRec(node.left, k))
			return true;
		kCurrent++;
		if(kCurrent == k) {
			System.out.println(node.val);
			return true;
		}
		return printKthUsingInOrderRec(node.right, k);
	}
}
