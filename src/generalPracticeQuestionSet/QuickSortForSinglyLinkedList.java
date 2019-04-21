package generalPracticeQuestionSet;

/**
 * Implement quick sort on a singly linked list
 * Time Complexity: O(n^2) or O(nlogn)
 * @author Utkarsh
 *
 */
public class QuickSortForSinglyLinkedList {
	
	// tester
	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList();
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
		list.head = list.quickSort(list.head);
		System.out.print("Linked list after sort: ");
		list.print();
	}
}

class SinglyLinkedList{
	public Node head;
	
	class Node{
		public int val;
		public Node next;
		public Node(int num, Node n) {
			this.val = num;
			this.next = n;
		}
	}
	
	/**
	 * adds an element at last position, takes element as parameter
	 * @param num
	 */
	public void add(int num) {
		Node last = head;
		if(this.head == null)
			this.head = new Node(num,null);
		else {
			while (last.next != null)
				last = last.next;
			last.next = new Node(num, null);
		}
	}
	/**
	 * add a node at last position, takes node as parameter
	 * @param node
	 */
	public void addAtLast(Node node) {
		Node last = head;
		if(this.head == null)
			this.head = node;
		else {
			while(last.next!=null)
				last = last.next;
			last.next = node;
		}
	}
	
	/**
	 * logic for quick sort implementation for Singly Linked List
	 * @param head -> first node of the list to sort
	 */
	public Node quickSort(Node head) {
		// set the first node as head, used in partitioning
		Node first = head;
		// set pivot as the last node of the list
		Node pivot = null;
		if(head == null || head.next == null)
			return head;
		while(head !=null) {
			if(head.next !=null) {
				if(head.next.next==null) {
					pivot = head.next;
					// unlink the pivot element from list
					head.next=null;
				}
					
				head = head.next;
			}
				
		}
		
		/*  "first" and "pivot" nodes' values remain same after partitioning, but next links get changed
		 *  "first" is not the actual first node of 1st list | but "pivot" is the first node from pivot (2nd list)
		 *  "newFirst" will have the returned new first node of 1st list from the method. */
		Node newFirst = partitioning(first, pivot);
		
		// we use a new list to simply process of linking everything back together at the end
		// first list, pivot and second list need to be linked
		SinglyLinkedList sll = new SinglyLinkedList();
		
		// run QUICKSORT again on first list and add result to sll list
		if(newFirst != null) {
			newFirst = quickSort(newFirst);
			sll.addAtLast(newFirst);
		}
		
		// run QUICKSORT again on second list from next element of pivot and store its first node on "second" node
		Node second = null;
		if(pivot!= null)
			second = quickSort(pivot.next);

		// unlink pivot to its next nodes before linking
		pivot.next = null;
		
		// link pivot and second list with sll
		sll.addAtLast(pivot);
		sll.addAtLast(second);
		return sll.head;
	}
	
	/**
	 * logic for partitioning using last node as pivot
	 * @param head -> first node of the list that goes until node before last "last" node is not part of this list
	 * @param last -> last node which is the pivot
	 */
	public Node partitioning(Node head, Node last) {
		// Node cur keeps track of first linked list, keep in mind second linked list is tracked by "last" node itself
		Node cur = null;
		
		// "first" keeps track of the node in first position after partitioning is done
		Node first = null;
		
		// "piv" holds "last" node and also used for comparison while finding right position as last can change everytime
		Node piv = last;
		
		// traverse nodes starting from "head" until we reach end
		// remember "last" node is actually unlinked from head before we entered partitioning
		while(head!=null) {
			// made temp to hold next node's value which is assigned later
			Node temp = head.next;
			

			/* below we compare head's value with pivot
			 * if less or equals we add the node to first linked list. i.e. starting from "first" and maintained in "cur" node
			 * otherwise we add it to the second one. i.e. starting from "last" node */
			if(head.val <= piv.val) {
				/* cur check is done to cover the case where all elements could be greater then pivot
				 * and would need reset of the first node 
				 * Note: we set cur.next null here to avoid endless list as we are adding all nodes 1 by 1 here */
				if(cur==null) {
					cur = head;
					first = head;
					cur.next = null;
				}
				else {
					cur.next = head;
					cur = cur.next;
					cur.next = null;
				}
					
			}else {
				// if all elements were greater then pivot then first actually becomes last
				if(first == null)
					first = last;
				last.next = head;
				last = last.next;
				// set next null as precaution to avoid unwanted links
				last.next = null;
			}
			// setting head to be the next node
			head = temp;
		}
		last.next = null;
		// we reset the head as we will use this head later as first node ref in quick Sort
		if(cur == null)
			head = null;
		else 
			head = first;
		
		return head;
	}
	
	/**
	 * prints this linked list
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
}