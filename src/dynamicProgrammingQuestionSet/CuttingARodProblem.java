package dynamicProgrammingQuestionSet;

/**
 * Cutting a Rod  https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
 * Given a rod of length n inches and an array of prices that contains prices of 
 * all pieces of size smaller than n. Determine the maximum value obtainable by 
 * cutting up the rod and selling the pieces.
 * Runtime: O(n^2)
 * @author Utkarsh
 */
public class CuttingARodProblem {
	
	/**
	 * dynamic bottom up approach
	 * here we make use of n x n M 2d array where
	 * row in M represents total length left, column in M represents length of rods available
     * this works somewhat similar to coin change problem, only here, value is
     * not representative directly, so while considering x length rod , we add 
     * and compare the values that we get if x length rod was cut or not for each
     * cut step.
     * general concept: if we have t total rod length then say l is current rod length, given values[]
     * max value for t and l would be max amongst 
     * 1. max value for t and l-1 (not considering l length rod)
     * 2. value[l] + max value for t-l and l (considering l length rod) 
     * considered.
     * i.e. M[t,l] = max(M[t,l-1],V[l-1] + M[t-l][l]
     * Runtime: O(n^2)
     * Spacetime: O(n^2)
	 */
	public static int mostValue(int total, int[] values) {
		int[][] M = new int[total+1][total+1];
		for(int i = 1; i<=total;i++) {
			M[i][1] = values[0]*i;
		}
		for(int t=1; t<=total; t++) {
			for(int l=1; l<=total; l++) {
				if(l>t) {
					M[t][l] = M[t][l-1]; // only 1 possible max value
				} else {
					int valOnAdd = values[l-1];
					if(t-l >= 0) // only when length doesn't exceed total
						valOnAdd += M[t-l][l];
					M[t][l] = Math.max(M[t][l-1], valOnAdd);	
				}
			}
		}
		System.out.println(M[total][total]);
		return M[total][total];
	}
	
	public static void main(String[] args) {
		int length = 8;
		//int[] values = {1,5,8,9,10,17,17,20};
		int[] values = {3,5,8,9,10,17,17,20};
		mostValue(length, values);
	}
	

}
