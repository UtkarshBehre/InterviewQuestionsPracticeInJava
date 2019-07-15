package dynamicProgrammingQuestionSet;

import java.util.*;

public class KnapsackProblem {
	
	/**
	 * solution entry point
	 */
	public static int findMaxValuePossible(int[] values, int[] weights, int w) {
		//return findMaxRec(values, weights, values.length-1, w, mem);
		return findMaxValue(values, weights, values.length, w);
	}
	
	/**
	 * recursive memoization approach using string (n:w) as key and max value till
	 * that point as value, this would use less space as only points that are hit
	 * are stored and not the entire range of weight, however worst time can still
	 * be same
	 * Runtime: O(nw)
	 * Spacetime: O(nw)
	 */
	public static int findMaxRec(int[] values, int[] weights, int n, int w, Map<String, Integer> mem) {
		String key = n+":"+w;
		int value=0;
		if(mem.containsKey(key))
			return mem.get(key);
		if(n<0 || w<=0)
			return value;
		else if(weights[n] <= w)
			value = Math.max(findMaxRec(values, weights, n-1, w, mem), + values[n] + findMaxRec(values, weights, n-1, w-weights[n], mem));
		mem.put(key, value);
		return value;
	}
	
	/**
	 * bottom up approach that takes up lots of space as w can be high and 
	 * we start at 0 incrementing 1 at a time
	 * Runtime: O(nw)
	 * Spacetime: O(nw)
	 */
	public static int findMaxValue(int[] values, int[] weights, int n, int w) {
		int[][] V = new int[n+1][w+1];
		
		for(int i = 0; i<=n; i++) {
			for(int j = 0; j<=w; j++) {
				if(i==0 || w == 0)
					V[i][j] = 0;
				else if(weights[i-1] <= j) {
					V[i][j] = Math.max(values[i-1] + V[i-1][j-weights[i-1]], V[i-1][j]);
				}
				else
				V[i][j] = V[i-1][j]; 
			}
		}
		return V[n][w];
	}
	
	// tester
	public static void main(String[] args) {
		int[] values = {60, 100, 120};
		int[] weights = {10,20,30};
		int w = 50;
//		int[] values = {10,20,30};
//		int[] weights = {1,1,1};
//		int w = 2;
		System.out.println("Max value possible: "+findMaxValuePossible(values, weights, w));
	}
}
