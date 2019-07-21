package dynamicProgrammingQuestionSet;
import java.util.*;

/** Sum Partition problem
 * Partition problem is to determine whether a given set can be partitioned into 
 * two subsets such that the sum of elements in both subsets is same.
 * Runtime: O(sum*p)  
 * @author Utkarsh
 */
public class SumPartitionProblem {

	/**
	 * generic recursion approach 
	 * if the total sum is odd, there is no way we can divide it in 2 equal parts
	 * if it is an even number then we check if sum/2 is possible to have in the given
	 * list by either keeping or leaving a number at a time
	 * Runtime: O(2^n)
	 */
	public static boolean isSumPartPossible(int[] arr) {
		int n = arr.length;
		int sum = 0;
		for(int num: arr)
			sum+=num;
		if(sum%2 != 0)
			return false;
		else {
			sum /=2;
		}
		Map<String, Boolean> mem = new HashMap<String, Boolean>();
		return isSumPartPossible(arr, 0, n-1, sum, mem);
	}
	
	/**
	 * the recursion method for the generic recursion approach
	 * Runtime: O(2^n)
	 */
	public static boolean isSumPartPossible(int[] arr, int start, int end, int sum, Map<String, Boolean> mem) {
		String key = start+":"+end+":"+sum;
		if(sum == 0)
			return true;
		for(int i = start; i <= end; i++) {
			if(arr[i] > sum)
				return isSumPartPossible(arr, start+1, end, sum, mem);
			else if(isSumPartPossible(arr, start+1, end, sum, mem) || isSumPartPossible(arr, start+1, end, sum-arr[start], mem))
				return true;
		}
		return false;
	}
	
	/** Dynamic bottom up approach 
	 * we take a 2d half sum+1 X n+1 sized array and fill it up starting
	 * from sum 1 after prepopulating the table for sum = 0 and n = 0
	 * approach is similar to coin change except here numbers are limited to
	 * 1 use only
	 * Runtime: O(sum*n)
	 */
	public static boolean isSumPartPossibleDynamic(int[] arr) {
		int n = arr.length;
		int sum = 0;
		for(int num: arr)
			sum+=num;
		if(sum%2 != 0)
			return false;
		else
			sum = sum/2;
		boolean[][] S = new boolean[sum+1][n+1];
		S[0][0] = true;
		for(int i = 1; i<=sum; i++)  
			S[i][0] = false; // not possible without any numbers
		for(int i = 1; i<=n; i++)
			S[0][i] = true; // sum 0 is possible for all numbers
		
		for(int i = 1; i<=sum; i++) {
			for(int j = 1; j<=n; j++) {
				S[i][j] = S[i][j] || S[i][j-1];
				if(i-arr[j-1] >=0)
					S[i][j] = S[i][j] || S[i-arr[j-1]][j-1];
			}
		}
		return S[sum][n];
	}
	
	// tester
	public static void main(String[] args) {
		int[] arr = {1, 5, 11, 5};
		//int[] arr = {1, 5, 3};
		System.out.println("Dynamic solution's answer: "+isSumPartPossibleDynamic(arr));
		System.out.println("Generic recursive solution answer: "+isSumPartPossible(arr));
	}

}
