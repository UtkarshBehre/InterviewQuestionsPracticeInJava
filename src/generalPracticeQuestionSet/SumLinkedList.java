package generalPracticeQuestionSet;

public class SumLinkedList {
	
	public static void main(String[] args){
		LinkedList ll1 = new LinkedList();
		ll1.add(5);
		ll1.add(6);
		ll1.add(3);
		LinkedList ll2 = new LinkedList();
		ll2.add(8);
		ll2.add(4);
		ll2.add(7);
		ll2.add(9);
		System.out.print("First list: ");
		ll1.print();
		System.out.print("Second list:");
		ll2.print();
		LinkedList llSum = sumUsingCarry(ll1, ll2);
		System.out.print("Resultant list: ");
		llSum.print();
	}
	
	/**
	 * adds the numbers represented in linked list by 
	 * getting the integers out of the linked list, 
	 * adding them up and converting the sum to new list
	 * Downside: this method cannot work for big integers
	 * Runtime: O(n1+n2)
	 * @param ll1 = first integer
	 * @param ll2 = second integer
	 * @return = result linked list of sum
	 */
	static LinkedList sum(LinkedList ll1, LinkedList ll2){
		LinkedList llSum = new LinkedList();
		int num1 = 0;
		int num2 = 0;
		Node itr = ll1.head;
		int multi = 1;
		while(itr!=null){
			num1 += itr.val*multi;
			multi*=10;
			itr = itr.next;
		}
		itr = ll2.head;
		multi = 1;
		while(itr!=null){
			num2 += itr.val * multi;
			multi*=10;
			itr = itr.next;
		}
		int sum = num1 + num2;
		multi=10;
		while(sum!=0){
			llSum.add(sum%multi);
			sum/=multi;
		}
		return llSum;
	}
	
	/**
	 * adds the numbers represented in 2 linked lists using
	 * the carry method by starting addition from first node
	 * and forward as first node represents unit's digit and so on
	 * This method will work for really big integers as well
	 * O(n1+n2)
	 * @param ll1 = first integer
	 * @param ll2 = second integer
	 * @return = result linked list of sum
	 */
	static LinkedList sumUsingCarry(LinkedList ll1, LinkedList ll2){
		Node digit1 = ll1.head;
		Node digit2 = ll2.head;
		LinkedList llSum = new LinkedList();
		Node prev = null;
		int carry = 0;
		int sum = 0;
		while(digit1 != null || digit2 != null || carry == 1){
			int val1 = digit1!=null ? digit1.val:0;
			int val2 = digit2!=null ? digit2.val:0;
			// we get the sum of 2 digits and the carry if any
			sum = carry + val1 + val2;
			
			// set the new carry based on new sum
			carry = sum>9? 1:0;
			
			// sum%10 will always give the unit's place of the num
			Node newNode = new Node(sum%10);

			// if head of new list was not set
			if(llSum.head==null)
				llSum.head = newNode;
			else // else put the next of prev as new node
				prev.next = newNode;
			prev = newNode; // and set cur new node as new prev node
			if(digit1!=null)
				digit1 = digit1.next;
			if(digit2!=null)
				digit2 = digit2.next;
		}
		return llSum;
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
