package generalPracticeQuestionSet;

import java.util.Stack;

/**
 * Reverse a stack using recursion
 * Write a program to reverse a stack using recursion. You 
 * are not allowed to use loop constructs like while, for..
 * etc, and you can only use the following ADT functions on Stack
 * Runtime: O(n^2) 
 * @author Utkarsh
 */
public class ReverseStack {
	// tester
	public static void main(String[] args){
		Stack<Integer> s = new Stack<Integer>();
		s.push(10);
		s.push(20);
		s.push(30);
		s.push(40);
		s.push(50);
		reverse(s);
		System.out.println(s);
	}
	
	/**
	 * uses call stack to store numbers in num and pop them
	 * once all are popped it starts inserting everything from bottom
	 * Runtime: O(n^2) because reverse is called n times and 
	 * insertAtBottom goes through all elements of stack again
	 * to insert element at bottom for each element to be inserted
	 * @param s = given stack of integers
	 */
	static void reverse(Stack<Integer> s){
		if(s.isEmpty())
			return;
		else{
			Integer num = s.peek();
			s.pop();
			reverse(s);
			insertAtBottom(s, num);
		}
	}
	
	/**
	 * stores all integers from stack in method call stack until 
	 * stack is emptied, then puts the number passed in it 
	 * and push back all the elements previously in the stack
	 * @param s = given stack of integers 
	 * @param num  = element to be entered at bottom of stack
	 */
	static void insertAtBottom(Stack<Integer> s, Integer num){
		if(s.isEmpty()){
			s.push(num);
		}else{
			Integer x = s.peek();
			s.pop();
			insertAtBottom(s, num);
			s.push(x);
		}
	}
}
