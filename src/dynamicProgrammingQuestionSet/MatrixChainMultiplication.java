package dynamicProgrammingQuestionSet;

import java.util.*;

/** Matrix Chain Multiplication
 * Given a sequence of matrices, find the most efficient way to multiply these 
 * matrices together. The problem is not actually to perform the multiplications, 
 * but merely to decide in which order to perform the multiplications.
 * https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
 * (ABC)D = (AB)(CD) = A(BCD) = ....
 * For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,
 * (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
 *  A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations
 *  
 * Runtime: O(n^3)
 * Spacetime: O(n^2)
 * @author Utkarsh
 */
public class MatrixChainMultiplication {

	/** solution entry point */
	public static int minMCM(int[] p) {
		int n = p.length;
		Map<String, int[]> mem = new HashMap<String, int[]>();
		//int[] res = minMCM(p, 1, n-1);
		int[] res = minMCM(p, 1, n-1, mem);
		return res[2];
	}
	
	/** solution using memoization technique
	 * uses key string (format = "start:end")to store an array of 3 ints
	 * first 2 ints represent the matrix dimensions and the 3rd one represents 
	 * the operations done so far to get to that point
	 * we start from 1st index and last index on the given array as nth matrix
	 * has n-1 and nth element of given array as dimensions 
	 * subproblem: put brackets in all possible positions to find min operations
	 * Runtime: O(n^3)
	 * Spacetime: O(n^2)
	 */
	public static int[] minMCM(int[] p, int start, int end, Map<String, int[]> mem) {
		int[] res = new int[3];
		String key = start +":"+end;
		if(mem.containsKey(key)) {
			return mem.get(key);
		}
		if(start == end) { // when only 1 matrix
			res[0] = p[start-1];
			res[1] = p[start];
			res[2] = 0;
		}
		else if(start+1 == end) { // for adjacent matrices in p
			res[2] = p[start-1] * p[start] * p[end];
			res[0] = p[start-1];
			res[1] = p[end];
		} else if(start < end) { // for non adjacent matrices
			res[2] = Integer.MAX_VALUE;
			// we try to put brackets in all possible positions and get the one with min operations
			for(int i = start; i<end; i++) {
				int[] res1 = minMCM(p, start, i, mem); // left part from start to i
				int[] res2 = minMCM(p, i+1, end, mem); // right part from i+1 to end
				if(res[2] > (res1[0] * res1[1] * res2[1]) + res1[2] + res2[2]) {
					res[0] = res1[0];
					res[1] = res2[1];
					res[2] = res1[0] * res1[1] * res2[1] + res1[2] + res2[2];
				}
			}
		}
		mem.put(key, res);		
		return res;
	}
	
	/**
	 * generic solution without dynamic programming
	 * this is same process as above dynamic solution without memoization 
	 * Runtime: exponential
	 */
	public static int[] minMCM(int[] p, int start, int end) {
		int[] res = new int[3];
		if(start == end) {
			res[0] = p[start-1];
			res[1] = p[start];
			res[2] = 0;
		}
		else if(start+1 == end) {
			res[2] = p[start-1] * p[start] * p[end];
			res[0] = p[start-1];
			res[1] = p[end];
		} else if(start < end) {
			res[2] = Integer.MAX_VALUE;
			for(int i = start; i<end; i++) {
				int[] res1 = minMCM(p, start, i);
				int[] res2 = minMCM(p, i+1, end);
				if(res[2] > (res1[0] * res1[1] * res2[1]) + res1[2] + res2[2]) {
					res[0] = res1[0];
					res[1] = res2[1];
					res[2] = res1[0] * res1[1] * res2[1] + res1[2] + res2[2];
				}
			}
		}
		return res;
	}
	
	// tester
	public static void main(String[] args) {
		int[] p = {40,20,30,10,30};
		System.out.println(minMCM(p));
		int[] p2 = {10, 20, 30, 40, 30};
		System.out.println(minMCM(p2));
		int[] p3 = {10, 20, 30};
		System.out.println(minMCM(p3));
	}

}
