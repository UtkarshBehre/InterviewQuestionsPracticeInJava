package generalPracticeQuestionSet;

/**
 * Quick Sort implementation on doubly linked list
 * running time: O(n^2) in worst case and O(nlogn) average case
 * @author Utkarsh
 */
public class QuickSortOnDoublyLinkedList {
	
	// tester
	public static void main(String[] args) {
		DoublyLinkedList list = new DoublyLinkedList();
		list.add(2);
		list.add(4);
		list.add(5);
		list.add(3);
		list.add(83);
		list.add(73);
		list.add(4);
		list.add(16);
		list.add(23);
		list.add(83);
		list.add(46);
		list.add(63);
		list.add(1);
		System.out.print("Linked list before sort: ");
		list.print();
		list.printFullList();
		list.head = list.quickSort(list.head);
		System.out.println();
		System.out.print("Linked list after sort: ");
		list.print();
		list.printFullList();
	}
	
}

class DoublyLinkedList{
	Node head;
	
	class Node{
		public int val;
		public Node prev;
		public Node next;
		
		public Node(int num) {
			this.val = num;
			this.prev = null;
			this.next = null;
		}
		
		@Override
		public String toString() {
			// used solely for printing the list
			return String.valueOf(this.val);
		}
	}
	
	/**
	 * 
	 * logic for quick sort implementation for Singly Linked List
	 * this logic is same as in singly linked list, except for here we maintain the prev. of each node
	 * refer QuickSortForSinglyLinkedList.java for detailed comments
	 * @param head -> first node of the list to sort
	 */
	public Node quickSort(Node head) {
		Node first = head;
		Node pivot = null;
		Node beforePivot = null;
		DoublyLinkedList dll = new DoublyLinkedList();
		if(head == null || head.next == null)
			return head;
		//since we can access prev element this is simpler than singly linked list implementation
		while(head.next!=null) {
			head = head.next;
		}
		pivot = head;
		beforePivot = pivot.prev;
		pivot.prev = null;
		beforePivot.next = null;
		
		Node newFirst = partitioning(first, pivot);
		
		if(newFirst!=null) {
			newFirst = quickSort(newFirst);
			dll.addAtLast(newFirst);
		}
		
		Node second = null;
		if(pivot!=null) {
			second = quickSort(pivot.next);
		}
		pivot.prev = null;
		pivot.next = null;
		dll.addAtLast(pivot);
		dll.addAtLast(second);
		return dll.head;
	}
	
	/**
	 * logic for partitioning using last node as pivot
	 * this logic is same as in singly linked list, except for here we maintain the prev. of each node
	 * refer QuickSortForSinglyLinkedList.java for detailed comments
	 * @param head -> first node of the list that goes until node before last "last" node is not part of this list
	 * @param last -> last node which is the pivot
	 */
	public static Node partitioning(Node head, Node last) {
		Node cur = null;
		Node first = null;
		Node piv = last;
		while(head!=null) {
			Node temp = head.next;
			if(head.val <= piv.val) {
				if(first == null) {
					first = head;
					cur = head;
					cur.next = null;
				}
				else {
					cur.next = head;
					cur.next.prev = cur;
					cur = cur.next;
					cur.next = null;
				}
			}
			else {
				last.next = head;
				last.next.prev = last;
				last = last.next;
				last.next = null;
			}
			head = temp;
		}
		
		if(cur==null) {
			head = null;
		}
		else {
			head = first;
		}
		return head;
	}
	
	/**
	 * adds an element at last position, takes element or data as parameter
	 * @param num --> element to add
	 */
	public void add(int num) {
		Node last = head;
		if(this.head == null)
			this.head = new Node(num);
		else {
			while (last.next != null)
				last = last.next;
			last.next = new Node(num);
			last.next.prev = last;
		}
	}
	
	/**
	 * adds a node at last position, takes node as parameter
	 * only difference here is maintaining prev link while adding each new node
	 * @param node --> node to add
	 */
	public void addAtLast(Node node) {
		Node cur = head;
		if(node == null)
			return;
		if(head == null) {
			head = node;
			// break prev link as this is added as first node
			head.prev = null;
		}
		else {
			while(cur.next != null) {
				cur = cur.next;
			}
			cur.next = node;
			node.prev = cur;
		}
	}
	
	/**
	 * prints this linked list values only
	 */
	public void print() {
		Node n = head;
		if(n != null) {
			while(n!= null) {
				System.out.print(n.val+" ");
				n = n.next;
			}
		}
		System.out.println();
	}
	
	/**
	 * prints this full linked list with next and prev values of nodes
	 */
	public void printFullList() {
		Node n = head;
		
		if(n != null) {
			while(n!= null) {
				System.out.print("| Val:"+n.val+" Prev:"+n.prev+" Next:"+n.next+" | --> ");
				n = n.next;
			}
		}
		System.out.println();
	}
}