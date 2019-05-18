package generalPracticeQuestionSet;

/**
 * Print nodes at k distance from root
 * @author Utkarsh
 *
 */
public class BinaryTreeNodesKDistance {
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
		 * Recursive solution keeps decrementing k by one on each level
		 * Runtime: O(n)
		 */
		public void printNodesAtK(Node node, int k) {
			if(node==null)
				return;
			if(k == 0) {
				System.out.print(node+" ");
				return; // to stop going further
			}
			printNodesAtK(node.left, k-1); 
			printNodesAtK(node.right, k-1);
		}
	}
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(); 

		/*
		Constructed bunary tree is: 
		        1 
		      /  \ 
		     2    3 
		   /  \    \ 
		  4   5     8  
         /   
      	6    
		*/
		tree.root = null;
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.right = new Node(8);
		tree.root.left.left.left = new Node(6);
		
		int k = 2;
		tree.printNodesAtK(tree.root,k);
	}
}
