package generalPracticeQuestionSet;

/**
 * Implement merge sort for linked list
 * running time of solution: O(nlogn)
 * @author Utkarsh
 *
 */
public class MergeSortForLinkedList {
	
	// tester
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(100);
		list.add(62);
		list.add(25);
		list.add(34);
		list.add(83);
		list.add(73);
		list.add(4);
		list.add(16);
		list.add(23);
		list.add(83);
		list.add(46);
		list.add(63);
		list.add(34);
		list.add(22);
		System.out.print("Linked list before sort: ");
		list.print();
		list.first = list.mergeSort(list.first);
		System.out.print("Linked list after sort: ");
		list.print();
	}
}

/**
 * Linked List implementation
 * @author Utkarsh
 *
 */
class LinkedList{
	
	Node first;
	
	class Node{
		public int num;
		public Node next;
		public Node(int num, Node node) {
			this.num = num;
			this.next = node;
		}
	}
	
	/**
	 * pushed an element at the end of linked list
	 */
	public void add(int num) {
		Node newNode = new Node(num, null);
		if(first == null) {
			first = newNode;
		} else {
			Node node = first;
			while(node.next!=null) {
				node = node.next;
			}
			node.next = newNode;
		}
	}
	
	/**
	 * gets the middle node of a linked list
	 */
	static Node getMidNode(Node n) {
		Node nodeSlow = n;
		Node nodeFast = n.next;
		// think of this as one pointer going at twice the speed as 2nd one
		// when faster reaches beyond end the slower one reaches middle node
		while(nodeFast!=null) {			
			nodeFast = nodeFast.next;
			if(nodeFast !=null) {
				nodeSlow = nodeSlow.next;
				nodeFast = nodeFast.next;
			}
		}
		return nodeSlow;
	}
	
	/**
	 * main merge sort logic for a linked list
	 * similar to original logic except here we have to break link of middle node
	 * in order to divide into 2 seperate lists
	 * Running time: O(nlogn)
	 */
	public Node mergeSort(Node n) {
		if(n==null || n.next == null)
			return n;
		
		Node mid = getMidNode(n);
		Node nextOfMid = mid.next;
		
		// unlink the next of mid to break the list
		mid.next = null;
		
		// left will get sorted list until mid element as next of mid was null
		// right will get sorted list of nodes from next of mid
		Node left = mergeSort(n);
		Node right = mergeSort(nextOfMid);
		
		Node sortedLinkedList = mergeSortedLists(left,right);
		// returns the first node of the sorted linked list
		return sortedLinkedList;
	}
	
	/**
	 * merge 2 already sorted linked lists
	 * concept is same as for merging 2 sorted arrays except here we have
	 * to traverse through the list and link each node to the next appropriate one
	 */
	public Node mergeSortedLists(Node n1, Node n2) {
		Node head = null;
		Node first = null;
		if(n1 == null) 
			return n2;
		else if(n2 == null)
			return n1;
		if(n1.num < n2.num) {
			head = n1;
			first = n1;
			n1 = n1.next;
		}else {
			head = n2;
			first = n2;
			n2 = n2.next;
		}
		while(n1 != null && n2 != null) {
			
			if(n1.num < n2.num) {
				head.next = n1;
				head = head.next;
				n1 = n1.next;
			}
			else {
				head.next = n2;
				head = head.next;
				n2 = n2.next;
			}
				
		}
		if(n1 == null)
			head.next = n2;
		if(n2 == null) 
			head.next = n1;
		return first;
	}
	
	/**
	 * prints this linked list
	 */
	public void print() {
		Node n = first;
		if(n != null) {
			while(n!= null) {
				System.out.print(n.num+" ");
				n = n.next;
			}
		}
		System.out.println();
	}
}