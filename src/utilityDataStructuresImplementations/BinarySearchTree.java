package utilityDataStructuresImplementations;

/**
 * Binary Search Tree operations available in this class
 * Search, insert, delete, and find the node with minimum value
 * @author Utkarsh
 */
public class BinarySearchTree {
	
	public Node root;
	
	public static class Node{
		public int val;
		public Node left;
		public Node right;

		public Node(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return String.valueOf(this.val);
		}
	}

	public boolean searchRec(int key) {
		return searchRec(root,key);
	}

	/**
	 * searches for a key recursively
	 * Runtime: O(h)
	 * @param node = current node
	 * @param key = element to search
	 * @return true if element is found
	 */
	private boolean searchRec(Node node, int key) {
		if(node==null)
			return false;
		if(key == node.val)
			return true;
		else if(key < node.val)
			return searchRec(node.left, key);
		return searchRec(node.right,key);
	}

	public void insertKey(int num) {
		root = insertRec(root, num);
	}

	/**
	 * inserts element into the bst as a leaf in right position
	 * Runtime: O(h)
	 * @param node = current node
	 * @param num = element to insert
	 * @return expect node after insertion
	 */
	private Node insertRec(Node node, int num) {
		if(node == null) {
			node = new Node(num);
			return node;
		}
		if(num == node.val) {
			System.out.println("No duplicate elements are allowed in a "
					+ "Binary Search Tree.");
			return root;
		}
		else if(num < node.val) {
			node.left = insertRec(node.left,num);
		}
		else 
			node.right = insertRec(node.right,num);
		return node;
	}

	/**
	 * used for internally calling recursive delete method
	 * @param num = key to be deleted from the tree
	 */
	public void deleteKey(int num) {

		root = deleteRec(root, num);
	}

	/**
	 * Replaces the value of key to be deleted in node with the min value
	 * found on right subtree and then deletes the replaced value from its
	 * original location
	 * Runtime: O(h)
	 * @param node = current node
	 * @param num = key to delete
	 * @return expected node after deletion
	 */
	private Node deleteRec(Node node, int num) {
		if(node == null)
			return node;
		if(num < node.val)
			node.left = deleteRec(node.left, num);
		else if(num > node.val)
			node.right = deleteRec(node.right, num);
		else { // node.val is equal to num
			//in case of only 1 child we just return that child to link
			if(node.right==null)
				return node.left;
			else if(node.left==null)
				return node.right;
			else{ // node has 2 children
				// update node's val to next min on right side to delete
				node.val = getMin(node.right).val;
				// now delete that next min
				node.right = deleteRec(node.right, node.val);
			}
		}
		return node;
	}

	/**
	 * gets node with min value in bst
	 * Runtime:O(n)
	 * @param node = root of bst to look at
	 */
	public Node getMin(Node node) {
		while(node.left!=null){
			node = node.left;
		}
		return node;
	}
	
	public void printInOrder() {
		printInOrder(root);
		System.out.println();
	}
	
	/**
	 * inOrder traversal should give values in increasing order for bst
	 * Runtime: O(n)
	 */
	private void printInOrder(Node node) {
		if(node==null)
			return;
		printInOrder(node.left);
		System.out.print(node.val+" ");
		printInOrder(node.right);
	}
}
