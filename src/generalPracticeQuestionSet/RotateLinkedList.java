package generalPracticeQuestionSet;

import generalPracticeQuestionSet.SumLinkedList.Node;

public class RotateLinkedList {

	public static void main(String[] args){
		LinkedList ll = new LinkedList();
		ll.add(10);
		ll.add(20);
		ll.add(30);
		ll.add(40);
		ll.add(50);
		ll.add(60);
		int k = 4;
		ll.rotate(k);
		ll.print();
	}
	
	static class Node{
		int val;
		Node next;
		public Node(int val){
			this.val = val;
			this.next = null;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return this.val == ((Node)obj).val;
		}
	}
	
	static class LinkedList{

		Node head;

		public void add(int num){
			Node newNode = new Node(num);
			Node temp = head;
			if(this.head == null)
				this.head = newNode;
			else{
				while(temp.next!=null){
					temp = temp.next;
				}
				temp.next = newNode;
			}
		}
		
		/**
		 * rotates the linked list after k nodes
		 * so that new head becomes k+1th node 
		 * and nodes from start to k are trailed at the end
		 * @param k = number of nodes to rotation 
		 */
		public void rotate(int k){
			// if k was 0 no need to rotate
			if(k==0)
				return;
			Node itr = this.head;
			// this would be the new last node ( kth node )
			Node lastNode = null;
			while(itr.next!=null){
				if(k == 1)
					lastNode = itr;
				k--;
				itr = itr.next;
			}
			// if lastNode was not set, means k was either size or more
			if(lastNode==null)
				return;
			// store old head pointer temporarily
			Node oldHead = this.head;
			// assign the new head to list head
			this.head = lastNode.next;
			// set next of last node to null for new list
			lastNode.next = null;
			// current itr which is last of old list would point to old head
			itr.next = oldHead;
		}
		
		// prints the list
		public void print(){
			Node temp = this.head;
			while(temp!=null){
				System.out.print(temp.val+"--> ");
				temp = temp.next;
			}
			System.out.println();
		}
	}
}
