package popularQuestionSet;

import utilityDataStructuresImplementations.BinarySearchTree;
import utilityDataStructuresImplementations.BinarySearchTree.Node;
/**
 * Inorder predecessor and successor for a given key in BST
 * key may or may not exist in the tree
 * Runtime: O(n)
 * @author Utkarsh
 *
 */
public class BSTPredecessorSuccessor {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insertKey(50);
		bst.insertKey(30);
		bst.insertKey(20);
		bst.insertKey(40);
		bst.insertKey(70);
		bst.insertKey(60);
		bst.insertKey(80);
		printSucPredecesor(bst.root, 20); // change the key to testdifferent cases
	}
	
	/**
	 * we make use of an array of size 3 to store
	 * predecessor, successor, and leftMost nonKey nodes
	 * use modified inOrderTraversal to find solution
	 * Runtime:O(n)
	 * @param node
	 * @param key 
	 */
	public static void printSucPredecesor(Node node, int key) {
		// array position 0 = predecessor, 1 = successor, 2 = left most non-key node
		Node[] inOrder = new Node[4];
		fillInOrderRec(node,inOrder, new Node(key));
		
		// if predecessor was never stored then key was smaller than
		// left most element in the tree
		if(inOrder[0] == null)
			inOrder[1] = inOrder[2];
		
		System.out.print(inOrder[0]+" " + inOrder[1]);
	}

	/**
	 * 
	 * @param node
	 * @param inOrder
	 * @param key
	 * @return
	 */
	public static boolean fillInOrderRec(Node node, Node[] inOrder, Node key) {
		if(node == null)
			return false;
		boolean isFilled = fillInOrderRec(node.left,inOrder, key);
		// we skip rest of the steps if predecessor and successor were already set
		if(!isFilled) {
			// store left most non-key node only once
			if(inOrder[2] == null && node.val !=key.val)
				inOrder[2] = node;
			
			// store predecessor only if lower element found
			if(inOrder[0] == null && node.val < key.val)
				inOrder[0] = node;
			
			// replace predecessor if it is lower than key but higher than old value
			else if(node.val < key.val && node.val > inOrder[0].val) 
				inOrder[0] = node;
			
			// finally fill the successor only if predecessor was filled in past
			else if(inOrder[0]!=null && node.val > key.val) {
				inOrder[1] = node;
				return true;
			}

			return fillInOrderRec(node.right,inOrder, key);
		}
		return isFilled;
	}
}
