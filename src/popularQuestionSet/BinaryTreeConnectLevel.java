package popularQuestionSet;

/**
 * Connect nodes at same level
 * Runtime: O(n)
 * @author Utkarsh
 *
 */
public class BinaryTreeConnectLevel {
	static class Node{
		int val;
		Node left;
		Node right;
		Node nextRight;
		
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
		 * simple solution using array of size height to store current node
		 * at all levels and use them to point to nextRight and update for 
		 * next encounter
		 * Runtime:O(n)
		 */
		void connectAdjacent() {
			// we calc height before recursion which saves time
			// index is level of a node
			Node[] nodes = new Node[height(root)+1];
			connectAdjacentNodes(root, nodes, 1);
		}
		
		void connectAdjacentNodes(Node node, Node[] nodes, int level) {
			if(node == null)
				return;
			if(nodes[level] == null) {
				nodes[level] = node;
			}else {
				nodes[level].nextRight = node;
				nodes[level] = node;
			}
			connectAdjacentNodes(node.left, nodes, level+1);
			connectAdjacentNodes(node.right, nodes, level+1);
		}
		
		int height(Node node) {
			if(node == null)
				return 0;
			
			return Math.max(height(node.left), height(node.right))+1; 
		}
		
		void printUsingAdjacent() {
			Node node = root;
			while(node!=null) {
				Node adj = node;
				while(adj!=null) {
					System.out.print(adj.val);
					adj = adj.nextRight;
				}
				node = node.left;
			}
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
		tree.connectAdjacent();
		tree.printUsingAdjacent();
	}
}
