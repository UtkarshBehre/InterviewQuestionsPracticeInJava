package popularQuestionSet;

/**
 * Check if a binary tree is subtree of another binary tree
 * @author Utkarsh
 *
 */
public class BinaryTreeSubCheck {
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
		
	}
	
	/**
	 * keep checking from each node encountered in t 
	 * Runtime: O(mn) number of nodes on tree1 and tree2
	 */
	static boolean isSubTree(Node subt, Node t) {
		if(t==null)
			return false;
		if(subt == null)
			return true;
		if(isMatchingTree(subt, t))
			return true;
		return (isSubTree(subt,t.left) || isSubTree(subt, t.right) );
	}
	
	static boolean isMatchingTree(Node subt, Node t) {
		if(subt==null && t==null) {
			return true;
		}else if( subt==null || t == null )
			return false;
		// all 3 conditions should be true for a matching tree condition
		return (subt.val == t.val && isMatchingTree(subt.left,t.left) && isMatchingTree(subt.right,t.right));
	}
	
	public static void main(String[] args) {
		BinaryTree tree1 = new BinaryTree(); 
		
        // TREE 1 
        /* Construct the following tree 
           10 
         /    \ 
         4      6 
          \ 
          30  */
            
        tree1.root = new Node(10); 
        tree1.root.right = new Node(6); 
        tree1.root.left = new Node(4); 
        tree1.root.left.right = new Node(30);
		
		BinaryTree tree2 = new BinaryTree(); 
        
        // TREE 1 
        /* Construct the following tree 
              26 
             /   \ 
            10     3 
           /    \     \ 
          4      6      3 
           \ 
            30  */
            
        tree2.root = new Node(26); 
        tree2.root.right = new Node(3); 
        tree2.root.right.right = new Node(3); 
        tree2.root.left = new Node(10); 
        tree2.root.left.left = new Node(4); 
        tree2.root.left.left.right = new Node(30); 
        tree2.root.left.right = new Node(6); 
        System.out.println("t1 is subtree of t2: "+isSubTree(tree1.root, tree2.root));
	}
}
