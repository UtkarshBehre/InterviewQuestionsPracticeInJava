package popularQuestionSet;

import java.util.Stack;

/**
 * Given an array, print the Next Greater Element (NGE) for every element. 
 * The Next greater Element for an element x is the first greater element 
 * on the right side of x in array. Elements for which no greater element 
 * exist, consider next greater element as -1.
 * @author Utkarsh
 *
 */
public class NextGreaterElement {
	// tester
	public static void main (String[] args){
		int[] arr = {13, 15, 5, 7, 6,8, 12};
		printNextGreaterElement(arr);
		System.out.println();
		printNextGreaterElementInOrder(arr);
	}
	
	/**
	 * loops from start to end | does not print in same order
	 * push first element in stack.
	 * get next element from array and start comparing with element from stack
	 * if next is bigger then element from stack then print the pair
	 * otherwise pop the next element and compare, unless stack empty then break
	 * if next was not bigger then element push it back to stack
	 * finally push the next in the stack as well
	 * print remaining elements in stack with -1 as they don't have greater element
	 * @param arr = given array of integers
	 */
	static void printNextGreaterElement(int[] arr){
		int n = arr.length;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(arr[0]);
		for(int i = 1; i<n; i++){
			int next = arr[i]; // next is the next element in the array
			int element = stack.pop(); // element is always the element coming out of stack
			// if next is bigger, means pair of the element is found, so print and pop next element
			while(next>element){
				System.out.println(element +" --> "+ next);
				if(stack.isEmpty())
					break;
				element = stack.pop();
			}
			// if next wasn't big enough push the element back in stack to check later
			if(next<element){
				stack.push(element);
			}
			// lastly push back the next to stack as it's bigger element is yet to be found
			stack.push(next);
		}
		// print the remaining elements in stack with -1 as no greater element was found for them
		while(!stack.isEmpty()){
			System.out.println(stack.pop()+" --> -1");
		}
	}
	
	/**
	 * loops from end to start | prints pairs in the given order
	 * keep popping elements from stack till next greater is found
	 * put the next greater (be it -1 or more in respective i in 
	 * the other array. At the end print all array elements and 
	 * its pair stored in nge array
	 * @param arr = given array of integers
	 */
	static void printNextGreaterElementInOrder(int[] arr){
		int n = arr.length;
		Stack<Integer> stack = new Stack<Integer>();
		int[] nge = new int[n]; 
		for(int i = n-1; i>=0; i--){
			// until stack is not empty stack's top element is not bigger
			// keep popping to find the bigger element
			while(!stack.isEmpty() && stack.peek() <= arr[i]){
				stack.pop();
			}
			// put the greater element if found (or -1) onto nge
			nge[i] = stack.isEmpty() ? -1 : stack.peek();
			// push the current element into the stack to be compared with
			// remaining elements
			stack.push(arr[i]);
		}
		// finally print all the pairs using both arr and nge 
		for(int i = 0; i < n; i++)
			System.out.println(arr[i] +" --> " +nge[i]);
	}
}
