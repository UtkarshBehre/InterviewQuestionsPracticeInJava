package popularQuestionSet;

/**
 * Find the closest pair from two sorted arrays
 * Given two sorted arrays and a number x, find the pair whose 
 * sum is closest to x and the pair has an element from each array.
 * 
 * We are given two arrays ar1[0…m-1] and ar2[0..n-1] and a number x, we need to 
 * find the pair ar1[i] + ar2[j] such that absolute value of (ar1[i] + ar2[j] – x) is minimum.
 * 
 * Running Time: O(n1+n2)
 * @author Utkarsh
 *
 */
public class ClosestPairSortedArrays {
	
	// tester
	public static void main(String[] args){
		int[] arr1 = {1, 4, 5, 7};
        int[] arr2 = {10, 20, 30, 40};
        int x = 32; 
// 		input x = 32
//		Output:  1 and 30
// 		int[] arr1 = {0,1,2};
//      int[] arr2 = {3,25,26};
//      int[] arr1 = new int[10000];
//		int[] arr2 = new int[10000];
//		//arr2[0] = 1000;
//		for( int i = 0; i<10000; i++){
//			arr1[i] = 1-i;
//		}
//		for(int i = 1; i<10000; i++){
//			arr2[i] = 10001+i;
//		}
//        int x = 20001;
        int[] positions = findClosestPair(arr1, arr2, x);
        System.out.println(arr1[positions[0]] +" and "+ arr2[positions[1]]);
	}
	
	/**
	 * finds closest pair with absolute sum as given sum value
	 * running time: O(n1+n2)
	 * @param arr1 = first array
	 * @param arr2 = second array
	 * @param sum = sum to which the closest pair is to be found
	 * @return
	 */
	public static int[] findClosestPair(int[] arr1, int[] arr2, int sum){
		int n1 = arr1.length;
		int n2 = arr2.length;
		// starting point of i and j to compare
		int i = 0;
		int j = n2-1;
		// positions to hold final indexes of closest pair
		int[] positions = {i, j};
		// first min diff
		int minDiff = Math.abs(arr1[0] + arr2[n2-1] - sum);
		
		while(i<n1 && j >= 0){
			// if sum found then return
			if(arr1[i] + arr2[j] == sum){
				positions[0] = i;
				positions[1] = j;
				return positions;
			}
			// reset minDiff if lower diff found
			if(minDiff > Math.abs(arr1[i] + arr2[j] - sum)){
				minDiff = Math.abs(arr1[i]+arr2[j] -sum);
				positions[0] = i;
				positions[1] = j;
			}
			
			// if sum is smaller than given increment index from 1st array
			if(arr1[i] + arr2[j] < sum)
				i++;
			else // else decrement from 2nd array
				j--;
		}
		// return final positions which should have the least diff by the end
		return positions;
	}
	
}
