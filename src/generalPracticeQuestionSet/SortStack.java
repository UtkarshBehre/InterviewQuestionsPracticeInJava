package generalPracticeQuestionSet;

import java.util.Stack;

/**
 * Sort a stack using recursion
 * Given a stack, sort it using recursion. Use of any 
 * loop constructs like while, for..etc is not allowed. 
 * We can only use push(), pop(), isEmpty(), peek() 
 * Runtime: O(n^2)
 * @author Utkarsh
 */
public class SortStack {
	
	// tester
	public static void main(String[] args){
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(30);
		stack.push(20);
		stack.push(40);
		stack.push(10);
		stack.push(5);
		sortStack(stack);
		System.out.println(stack);
	}
	
	/**
	 * logic is similar to reversing a stack using stack memory to 
	 * store popped elements, except here instead of inserting an
	 * element at bottom of stack, we'll place it in its correct position
	 * Runtime: O(n^2)
	 * @param stack
	 */
	static void sortStack(Stack<Integer> stack){
		if(!stack.isEmpty()){
			Integer x = stack.peek();
			stack.pop();
			sortStack(stack);
			insertSortedManner(stack, x);
		}
	}
	
	/**
	 * inserts an element onto the stack in its correct sorted position
	 * by popping elements until smaller is found
	 * @param stack
	 * @param num
	 */
	static void insertSortedManner(Stack<Integer> stack, Integer num){
		if(stack.isEmpty() || stack.peek() < num)
			stack.push(num);
		else{
			Integer x = stack.peek();
			stack.pop();
			insertSortedManner(stack, num);
			stack.push(x);	
		}
	}
}
