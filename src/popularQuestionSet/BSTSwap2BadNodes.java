package popularQuestionSet;

/**
 * Two nodes of a BST are swapped, correct the BST
 * Two of the nodes of a Binary Search Tree (BST) are swapped. 
 * Fix (or correct) the BST.
 * Runtime: O(n)
 * @author Utkarsh
 *
 */
public class BSTSwap2BadNodes {
	
	/**
	 * solution using inOrder recursion so that we compare a node with its 
	 * adjacent left node using array of 4 nodes that indicate the following
	 * nodes[0] = first, denotes first wrong node
	 * nodes[1] = second, denotes possibly second wrong node if adjacent
	 * nodes[2] = prev, hold value for last node
	 * nodes[3] = last, denotes second wrong node if not adjacent
	 * and we swap nodes at the end based on last being set or not
	 * Runtime: O(n)
	 */
	public static void correctNodes(Node root){
		System.out.print("Nodes before correction: ");
		inOrder(root);
		
		Node[] nodes = new Node[4];
		setIncorrectNodesRec(root, nodes);
		
		int tmp = nodes[0].val;
		if(nodes[3] == null){
			nodes[0].val = nodes[1].val;
			nodes[1].val = tmp;
		}else{  
			nodes[0].val = nodes[3].val;
			nodes[3].val = tmp;
		}
		
		System.out.print("\nNodes after Correction: ");
		inOrder(root);
	}
	
	/**
	 * recursive inorder calls to find both incorrect nodes and populate in
	 * nodes array
	 * Runtime: O(n)
	 */
	public static void setIncorrectNodesRec(Node node, Node[] nodes){
		if(node == null)
			return;
		setIncorrectNodesRec(node.left, nodes);
		// if current is less than prev it violates inorder rule
		if(nodes[2]!=null && node.val < nodes[2].val){
			// first violation
			if(nodes[0] == null){
				nodes[0] = nodes[2];
				nodes[1] = node;
			} else { // only reaches at second violation
				nodes[3] = node;
			}
		}
		nodes[2] = node;
		setIncorrectNodesRec(node.right, nodes);
	}
	
	static class Node{
		int val;
		Node left;
		Node right;
		
		public Node(int val){
			this.val = val;
		}
		
		@Override
		public String toString(){
			return String.valueOf(this.val);
		}
	}
	
	static class BinaryTree{
		Node root;
	}
	
	public static void inOrder(Node node){
		if(node==null)
			return;
		inOrder(node.left);
		System.out.print(node.val+" ");
		inOrder(node.right);
	}
	
	// tester
	public static void main(String[] args){
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(10);
		tree.root.left = new Node(8);
		tree.root.right = new Node(20);
		tree.root.left.left = new Node(2);
		tree.root.left.right = new Node(5);
		correctNodes(tree.root);
		
		System.out.println();
		//set 2
		tree.root = new Node(6); 
		tree.root.left = new Node(10); 
		tree.root.right = new Node(2); 
		tree.root.left.left = new Node(1); 
		tree.root.left.right = new Node(3); 
		tree.root.right.right = new Node(12); 
		tree.root.right.left = new Node(7); 
		correctNodes(tree.root);
	}
}
