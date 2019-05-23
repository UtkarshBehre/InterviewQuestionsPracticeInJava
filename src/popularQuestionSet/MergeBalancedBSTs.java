package popularQuestionSet;

/**
 * Merge Two Balanced Binary Search Trees
 * Tree. Write a function that merges the two given balanced BSTs into a 
 * balanced binary search tree. Let there be m elements in first tree and 
 * n elements in the other tree. Your merge function should take O(m+n) time
 * Runtime: O(m+n)
 * @author Utkarsh
 *
 */
public class MergeBalancedBSTs {
	static class Node{
		int val;
		Node left;
		Node right;
		public Node(int val) {
			this.val = val;
		}
		
		@Override
		public String toString() {
			return String.valueOf(this.val);
		}
	}
	
	private static int i = 0;
	
	public static void main(String[] args) {
		Node root1 = new Node(50);
		root1.left = new Node(30);
		root1.right = new Node(70);
		root1.left.left = new Node(20);
		root1.left.right = new Node(40);
		root1.right.left = new Node(60);
		root1.right.right = new Node(80);
		
		Node root2 = new Node(55);
		root2.left = new Node(35);
		root2.right = new Node(75);
		root2.left.left = new Node(25);
		root2.left.right = new Node(45);
		root2.right.left = new Node(65);
		root2.right.right = new Node(85);
		root2.right.right.left = new Node(83);
		
		Node root = mergeBalancedBSTs(root1, 7, root2, 8);
		printInOrder(root);
	}
	
	/**
	 * Main solution that merges 2 balanced binary search trees into one
	 * assumes sizes for both trees are given
	 * Step 1 convert both arrays to sorted arrays O(m+n)
	 * step 2 merge both sorted arrays into 1 O(m+n)
	 * step 3 convert the merged sorted array into a balanced bst O(m+n)
	 * Runtime: O(m+n)
	 * @return root of merged balanced bst
	 */
	public static Node mergeBalancedBSTs(Node root1, int m, Node root2, int n) {
		int[] arr1 = new int[m];
		int[] arr2 = new int[n];
		int[] arr = new int[m+n];
		
		// step 1
		inOrderFillRec(root1, arr1);
		i = 0;
		inOrderFillRec(root2, arr2);
		i = 0;
		
		// step 2
		int i1 = 0; int i2 = 0;
		while(i1<m && i2<n) {
			if(arr1[i1] < arr2[i2]) 
				arr[i++] = arr1[i1++];
			else
				arr[i++] = arr2[i2++];
		}
		while(i1<m) 
			arr[i++] = arr1[i1++];
		while(i2<n)
			arr[i++] = arr2[i2++];
		
		// step 3
		return constructBalancedBSTRec(arr, 0, arr.length-1);
	}
	
	/**
	 * constructs a balanced binary search tree using sorted array
	 * takes mid node as root, goes left and right and does the same for that
	 * portion of array to be set as left and right
	 * Runtime: O(m+n)
	 * @param arr = given sorted array
	 * @param start = start index of current array range to look at
	 * @param end = end index of current array range to look at
	 * @return mid node created with left and right nodes already linked
	 */
	public static Node constructBalancedBSTRec(int[] arr, int start, int end) {
		if(start>end)
			return null;
		if(start == end)
			return new Node(arr[start]);
		
		int mid = start + (end-start)/2;
		Node node = new Node(arr[mid]);
		node.left = constructBalancedBSTRec(arr, start, mid-1);
		node.right = constructBalancedBSTRec(arr, mid+1, end);
		return node;
	}
	
	/**
	 * converts given tree to a sorted array using inorder traverse
	 */
	public static void inOrderFillRec(Node node, int[] arr) {
		if(node==null)
			return;
		inOrderFillRec(node.left, arr);
		arr[i++] = node.val;
		inOrderFillRec(node.right, arr);
	}
	
	/**
	 * prints given tree in inorder
	 */
	public static void printInOrder(Node node) {
		if(node == null)
			return;
		printInOrder(node.left);
		System.out.print(node.val+" ");
		printInOrder(node.right);
	}
}
