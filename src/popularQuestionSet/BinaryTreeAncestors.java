package popularQuestionSet;

/**
 * Print Ancestors of a given node in Binary Tree
 * Runtime: O(n)
 * @author Utkarsh
 *
 */
public class BinaryTreeAncestors {
	static class Node{
		int val;
		Node left;
		Node right;

		public Node(int num) {
			val = num;
		}
		@Override
		public String toString() {
			return String.valueOf(val);
		}
	}

	static class BinaryTree{
		Node root;
		
		/**
		 * keeps going left and right until node is found then returns true 
		 * and prints the nodes from the call stack memory if it was found
		 * which would print only the ancestor nodes 
		 * Runtime: O(n)
		 */
		public boolean printAncestors(Node node, Node key) {
			if(node == null)
				return false;
			// once node is found return true directly to start printing ancestors
			if(node.val == key.val)
				return true;
			
			// great thing is if found on left side right side will no longer execute
			if(printAncestors(node.left,key) || printAncestors(node.right, key)) {
				System.out.print(node.val + " ");
				return true;
			}else
				return false;
		}
	}
	
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(); 

		/*
		Constructed bunary tree is: 
		        1 
		      /  \ 
		     2    3 
		    / \    \ 
		  4    5    8  
         / \  
      	6   7 
		*/
		tree.root = null;
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.right = new Node(8);
		tree.root.left.left.left = new Node(6);
		tree.root.left.left.right = new Node(7);
		Node node = tree.root.left.left.right;
		tree.printAncestors(tree.root, node);
	}
}
