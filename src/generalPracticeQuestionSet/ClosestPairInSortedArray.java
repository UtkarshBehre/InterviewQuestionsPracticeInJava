package generalPracticeQuestionSet;

/**
 * Given a sorted array and a number x, find the pair in array whose sum is closest to x
 * 
 * Running Time: O(n)
 * @author Utkarsh
 *
 */
public class ClosestPairInSortedArray {
	// tester
	public static void main(String[] args){
		int[] arr = {10, 22, 28, 29, 30, 40};
		int x = 54;
		int[] arr2 = {1, 3, 4, 7, 10};
		int x2 = 15;
		printClosestPair(arr, x);
		printClosestPair(arr2,x2);
	}
	
	/**
	 * traverses through front and back of the array in single loop through meet point
	 * maintains closest pair with minimum difference so far and prints at the end
	 * @param arr
	 * @param x
	 */
	static void printClosestPair(int[] arr, int x){
		int l = 0;
		int r = arr.length -1;
		int i1 = l;
		int i2 = r;
		int minDiff = Math.abs(arr[l] + arr[r] - x);
		// loop through start as well as end until l<r i.e. mid point hit
		while (l<r){
			if(arr[l] + arr[r] == x){
				i1 = l;
				i2 = r;
				break;
			}
			if(Math.abs(arr[l] + arr[r] - x) < minDiff){
				minDiff = Math.abs(arr[l] + arr[r] - x);
				i1 = l;
				i2 = r;
			}
			if(arr[l] + arr[r] < x)
				l++;
			else
				r--;
		}
		// print the pair found
		System.out.println(arr[i1] + " and "+arr[i2]);
	}
}
