package popularQuestionSet;

import java.util.Stack;

/**
 * Stock Span Problem
 * The stock span problem is a financial problem where we have a 
 * series of n daily price quotes for a stock and we need to 
 * calculate span of stock’s price for all n days. The span Si of 
 * the stock’s price on a given day i is defined as the maximum 
 * number of consecutive days just before the given day, for which 
 * the price of the stock on the current day is less than or equal 
 * to its price on the given day.
 * For example, if an array of 7 days prices is given as 
 * {100, 80, 60, 70, 60, 75, 85}, then the span values for 
 * corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 * @author Utkarsh
 *
 */
public class StockSpanProblem {
	
	// tester
	public static void main(String[] args){
		int[] stocks = {100, 80, 60,100, 70, 60, 75, 85};
		int[] stocks2 = { 10, 10, 4, 5, 90, 120, 80 } ;
		int[] spanValues = findSpansUseStack(stocks);
		printOutput(stocks,spanValues);
		int[] spans = findSpansNoStack(stocks2);
		printOutput(stocks2, spans);
	}
	
	/**    Solution 1: using stack in linear time
	 * calculates span values using stack
	 * store day number in stack to keep track of smaller
	 * or bigger prices. Pop each smaller price day until
	 * bigger price day shows up. If nothing left means all
	 * days were lower otherwise current day - day number at stack
	 * Runtime: O(n)
	 * Space: O(n) in worst case
	 * @param arr = given array of stock prices
	 * @return = span values
	 */
	static int[] findSpansUseStack(int[] arr){
		int n = arr.length;
		int[] spanValues = new int[n];
		Stack<Integer> stack = new Stack<Integer>();
		spanValues[0] =1;
		stack.push(0);
		for(int i = 1; i < n; i++){
			// keep popping until stack is empty or bigger element
			while(!stack.isEmpty() && arr[stack.peek()] <= arr[i])
				stack.pop();
			// set new span value according to current stack top
			spanValues[i] = stack.isEmpty()?i+1:i-stack.peek();
			stack.push(i);
		}
		return spanValues;
	}
	
	/** 	Solution 2: without using stack in linear time 
	 * finds spans without using stack.
	 * we store the first span value as 1 right away
	 * then we loop through next and increase counter acc. to 
	 * previous found span values until we hit start or bigger element
	 * Runtime: O(n)
	 * Space: O(1)
	 * @param prices = given prices of stocks for the days
	 */
	static int[] findSpansNoStack(int[] prices) 
	{ 
		int n = prices.length;
		int[] spans = new int[n];
	    // set span value of first element as 1 as no elements on left
	    spans[0] = 1; 
	  
	    // loop through remaining elements
	    for (int i = 1; i < n; i++) {
	    	// start counter as 1 since we'll check left element next
	        int counter = 1;
	        // keep jumping back to elements according to last span until bigger is found
	        while ((i - counter) >= 0 && prices[i - counter] <= prices[i] ) {
	        	// add new span value to counter
	            counter += spans[i - counter]; 
	        } 
	        spans[i] = counter; 
	    } 
	    return spans;
	}
	
	/**		Solution 3: using stacks in quadratic time
	 * finds the span values of stocks using call stack memory
	 * we go through each stock and look for number of stocks lesser
	 * Runtime: O(n^2) 
	 * Space: O(n)
	 * @param arr = given stock array
	 * @return span values array
	 */
	static int[] findSpans(int[] arr){
		int n = arr.length;
		int[] spanValues = new int[n];
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i<n; i++){
			Integer num = arr[i]; // stays in call stack memory
			spanValues[i] = findEachSpan(stack, arr[i]);
			stack.push(num); // push it to stack at end
		}
		return spanValues;
	}
	
	/**		part of solution 3
	 * finds span value by recalling itself until a greater value
	 * is found. Returns 1 if empty stack or greater element found
	 * otherwise adds 1 if element found was smaller.
	 * Runtime: O(n)
	 * Space: O(n)
	 * @param stack = current stack
	 * @param num = current number to be compared
	 * @return added total span value at the end
	 */
	static int findEachSpan(Stack<Integer> stack, int num){
		int span = 0; // keep count of lower stocks
		if(stack.isEmpty() || stack.peek() > num){
			return 1;
		} else{
			Integer x = stack.peek(); // stays in call stack memory
			stack.pop();
			span += findEachSpan(stack, num);
			if(x<num){
				span+= 1;
			}
			stack.push(x); // push back all the elements
		}
		return span;
	}
	
	// utility method to print output neatly
	static void printOutput(int[] stocks, int[] spanValues){
		System.out.print("stocks array: ");
		printArr(stocks);
		System.out.println();
		System.out.print("span values: ");
		printArr(spanValues);
		System.out.println();
	}
	
	// utility method to print array 
	static void printArr(int[] arr){
		for(int i:arr)
			System.out.print(i+" ");
	}
}
