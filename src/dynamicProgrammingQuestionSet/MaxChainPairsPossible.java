package dynamicProgrammingQuestionSet;

import java.util.*;

/** Maximum Length Chain of Pairs
 * You are given n pairs of numbers. In every pair, the first number is always 
 * smaller than the second number. A pair (c, d) can follow another pair (a, b) 
 * if b < c. Chain of pairs can be formed in this fashion. Find the longest chain 
 * which can be formed from a given set of pairs.
 * 
 * For example, if the given pairs are {{5, 24}, {39, 60}, {15, 28}, {27, 40}, {50, 90} }, 
 * then the longest chain that can be formed is of length 3, 
 * and the chain is {{5, 24}, {27, 40}, {50, 90}}
 * @author Utkarsh
 */
public class MaxChainPairsPossible {
	
	/** Solution entry point to initiate mem for dynamic solution and starting point 
	 * of chains  */
	public static int MCPP(int[][] pairs) {
		int n = pairs.length;
		sort(pairs, 0, n-1); // sorts the given pairs array using quick sort technique
		Map<Integer,Integer> mem = new HashMap<Integer, Integer>();
		int maxPairs = 0; // keeps maximum chains possible so far
		for(int i = 0; i<n; i++)
			maxPairs = Math.max(maxPairs, 1+ MCPP(pairs, i, i+1, mem));
		// 1 is added when a pair is counted as part of the chain
		System.out.println(maxPairs); // prints answer
		return maxPairs;
	}
	
	/**
	 * Dynamic memoization solution
	 * we always either consider or not consider a pair if it is valid for 
	 * consideration otherwise we skip it
	 * since we have already sorted the pairs array based on first number, 
	 * we can start and increment ci from li+1
	 * @param pairs = given pairs array
	 * @param li = last index i.e. last pair counted
	 * @param ci = current pair to be checked
	 * @param mem = memory that holds key as an index and value as max chain possible
	 * from that relative pair in the pairs array
	 * @returns max chain possible
	 */
	public static int MCPP(int[][] pairs, int li, int ci, Map<Integer,Integer> mem) {
		int n = pairs.length;
		if(ci==n)
			return 0;
		if(mem.containsKey(li)) {
			return mem.get(li);
		}
		int maxPairs = 0;
		// first we consider the pair so we update li and ci and add 1 to the result.
		// then we don't consider the pair and update only ci. Then take max of both
		if(pairs[ci][0]>pairs[li][1]) {
			maxPairs = Math.max(1+ MCPP(pairs, ci, ci+1, mem), 
					MCPP(pairs, li, ci+1, mem));
		}
		else
			maxPairs = Math.max(maxPairs, MCPP(pairs, li, ci+1, mem));
		mem.put(li, maxPairs);
		return maxPairs;
	}
	
	/** modified quicksort to sort the 2d array based on 1st int */
	public static void sort(int[][] arr, int l, int r) {
		if(l>=r)
			return;
		int pivot = partitioning(arr, l, r);
		sort(arr, l, pivot-1);
		sort(arr, pivot+1, r);
	}
	
	/** partitioning logic using in sort method to find next pivot */
	public static int partitioning(int[][] arr, int l, int r) {
		int i = l-1;
		int[] pivElement = arr[r];
		for(int j = l; j<r; j++) {
			if(arr[j][0]<pivElement[0]) {
				i++;
				int[] temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		i++;
		arr[r] = arr[i];
		arr[i] = pivElement;
		return i;
	}
	
	//tester
	public static void main(String[] args) {
		int[][] pairs = {{5, 24}, {39, 60}, {15, 28}, {27, 40}, {50, 90}};
		MCPP(pairs);
	}
}
