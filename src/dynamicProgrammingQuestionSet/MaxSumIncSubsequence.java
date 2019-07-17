package dynamicProgrammingQuestionSet;

import java.util.*;

/**
 * Maximum Sum Increasing Subsequence
 * Given an array of n positive integers. Write a program to find the sum of maximum 
 * sum subsequence of the given array such that the integers in the subsequence are 
 * sorted in increasing order. For example, if input is {1, 101, 2, 3, 100, 4, 5}, 
 * then output should be 106 (1 + 2 + 3 + 100), if the input array is {3, 4, 5, 10}, 
 * then output should be 22 (3 + 4 + 5 + 10) and if the input array is {10, 5, 4, 3}, 
 * then output should be 10
 * Runtime: O(n^2)
 * @author Utkarsh
 */
public class MaxSumIncSubsequence {
	
	/**
	 * Solution entry point
	 * we loop back from last index to get max of sum of decreasing sub sequence
	 * from each index points which done using dynamic programming using memoization
	 * Runtime: O(n^2)
	 */
	public static int maxSumIncSubseq(int[] arr) {
		int n = arr.length;
		Map<String, Integer> mem = new HashMap<String, Integer>();
		int maxSum = 0;
		for(int i = n-1; i>=0; i--) {
			// add the element on which msis is called upon
			maxSum = Math.max(maxSum, arr[i] + msis(arr, i, i, mem));
		}
		System.out.println(maxSum);
		return maxSum;
	}
	
	/**
	 *  solution using memoization
	 * starts from n and maintains 2 pointers, 1 to the last big element and 1 to current element
	 * adds current element if it is smaller than last element after same is done
	 * from 0 to this point. we memoize the solution found using a String key with changing
	 * last and n ints in the method
	 * Runtime: O(n^2)
	 */
	public static int msis(int[] arr, int last, int i, Map<String,Integer> mem) {
		if(i<0)
			return 0;
		String key = last+":"+i;
		if(mem.containsKey(key))
			return mem.get(key);
		int sum = 0;
		if(arr[last] > arr[i]) { // add this element
			sum = Math.max(msis(arr, last, i-1, mem), arr[i]+ msis(arr, i, i-1, mem));
		}else {  // element is bigger so ignore it
			sum = msis(arr, last, i-1, mem);
		}
		mem.put(key, sum);
		return sum;
	}
	
	public static void main(String[] args) {
		int[] arr1 = {1, 101, 2, 3, 100, 4, 5};
		int[] arr2 = {3, 4, 5, 10};
		int[] arr3 = {10, 5, 4, 2};
		maxSumIncSubseq(arr1);
		maxSumIncSubseq(arr2);
		maxSumIncSubseq(arr3);
	}
}
