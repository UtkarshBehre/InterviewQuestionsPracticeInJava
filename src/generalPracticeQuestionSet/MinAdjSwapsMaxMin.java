package generalPracticeQuestionSet;

/**
 * Minimum adjacent swaps to move maximum and minimum to left and right corners
 * Input: a[] = {3, 1, 5, 3, 5, 5, 2} Output: 6
 * Input: a[] = {5, 6, 1, 3} Output: 2
 * @author Utkarsh
 */
public class MinAdjSwapsMaxMin {
	// tester
	public static void main(String[] args){
		int[] arr = {3,1,5,3,5,5,2};
		//{3,1,5,3,5,1,2}
		int minSwaps = findMinSwaps(arr);
		System.out.println("Minimum swaps required: "+minSwaps);
	}
	
	/**
	 * logic to find min swaps required
	 * runtime: O(n)
	 */
	static int findMinSwaps(int[] arr){
		int n = arr.length;
		int min = 0;
		int max = 0;
		for(int i = 0; i<n; i++){
			if( arr[i] > arr[max] )
				max = i;
			if( arr[i] <= arr[min]){
				min = i;
			}
		}
		int swapCount = 0;
		swapCount = max + (n-1-min);
		// to reduce duplicate swap in case where max element was placed
		// right to the min element we reduce swap count by 1
		if(max>min)
			swapCount--;
		return swapCount;
	}
}
