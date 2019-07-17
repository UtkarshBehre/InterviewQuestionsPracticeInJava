package dynamicProgrammingQuestionSet;
import java.util.*;

/**
 * Longest Bitonic Subsequence
 * Given an array arr[0 … n-1] containing n positive integers, a subsequence of 
 * arr[] is called Bitonic if it is first increasing, then decreasing. Write a 
 * function that takes an array as argument and returns the length of the longest 
 * bitonic subsequence.
 * A sequence, sorted in increasing order is considered Bitonic with the decreasing 
 * part as empty. Similarly, decreasing order sequence is considered Bitonic with 
 * the increasing part as empty
 * 	Input arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
 *	Output: 6 (A Longest Bitonic Subsequence of length 6 is 1, 2, 10, 4, 2, 1)
 *
 *	Input arr[] = {12, 11, 40, 5, 3, 1}
 *	Output: 5 (A Longest Bitonic Subsequence of length 5 is 12, 11, 5, 3, 1)
 *
 *	Input arr[] = {80, 60, 30, 40, 20, 10}
 *	Output: 5 (A Longest Bitonic Subsequence of length 5 is 80, 60, 30, 20, 10)
 *	
 * Runtime: 
 * @author Utkarsh
 */
public class LongestBitonicSubsequence {
	
	/**
	 * Solution entry point
	 * We find the longest biotonic subsequence by
	 * first, assuming the peak points from 0 to n-1
	 * then, finding longest decreasing subsequence possible from this peak b
	 * to either sides, i.e. left to 0 and right to n-1
	 * for each peak point assumed we keep track of max subsequence found for previous
	 * peak points and so after the last one we have our answer
	 * Runtime: O(n^2)
	 */
	public static int LBS(int[] arr) {
		int n = arr.length;
		int maxCount = 0;
		Map<String, Integer> mem = new HashMap<String,Integer>();
		for(int b = 0; b<n; b++) {
			// -1 as item at b is counted twice
			maxCount = Math.max(maxCount, LBSRec(arr, b, b, true , mem)+ LBSRec(arr, b, b, false, mem) -1); 
		}
		System.out.println("Max Count: " + maxCount);
		//System.out.println("mem count: " + mem.size());
		return maxCount;
	}
	
	/**
	 * Dynamic memoization technique used here
	 * for each side, approach in same way
	 * if current element is smaller than last one than we take max of either 
	 * considering it or not considering it
	 * else we skip the element and move to next one
	 * @param arr = given array
	 * @param last = index of the last big element found
	 * @param n = current index
	 * @param isInc = true means go left and false means go right to find longest decreasing subsequence
	 * @param mem = memory for memoization
	 * @return
	 */
	public static int LBSRec(int[] arr, int last, int n, boolean isInc, Map<String, Integer> mem) {
		if(n<0 || n == arr.length) {
			return 0;
		}
		String key = last+":"+n;
		if(mem.containsKey(key))
			return mem.get(key);
		int sum = 0;
		if(isInc) {
			// 1 is added wherever the element was to be considered
			if(arr[last] > arr[n])
				sum = Math.max(LBSRec(arr, last, n-1, isInc, mem), 1 + LBSRec(arr, n, n-1, isInc, mem));
			else if( last == n)
				sum = 1 + LBSRec(arr, last , n-1, isInc, mem);
			else 
				sum = LBSRec(arr, last , n-1, isInc, mem);
		}
		else {
			if(arr[last] > arr[n])
				sum = Math.max(LBSRec(arr, last, n+1, isInc, mem), 1 + LBSRec(arr, n, n+1, isInc, mem));
			else if( last == n)
				sum = 1 + LBSRec(arr, last, n+1, isInc, mem);
			else 
				sum = LBSRec(arr, last, n+1, isInc, mem);
		}
		if( last != n)
			mem.put(key, sum);
		return sum;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
		LBS(arr);
		int[] arr2 = {12, 11, 40, 5, 3, 1};
		LBS(arr2);
		int[] arr3 = {80, 60, 30, 40, 20, 10};
		LBS(arr3);
	}
}
