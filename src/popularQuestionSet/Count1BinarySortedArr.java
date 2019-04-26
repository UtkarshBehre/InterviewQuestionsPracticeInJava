package popularQuestionSet;

/**
 * Count 1’s in a sorted binary array
 * Given a binary array sorted in non-increasing order, count the number of 1’s in it.
 * 
 * Running Time: O(logn)
 * @author Utkarsh
 *
 */
public class Count1BinarySortedArr {
	// tester
	public static void main(String[] args){
		int[] arr = {1,1,1,1,1,1,0,0,0,0,0,0,0};
		int count = count1s(arr);
		System.out.println("count: "+count);
	}
	
	/**
	 * counts number 1's present
	 * since given list is in decreasing order
	 * return 0 if first element is 0
	 * return n if last element is 1
	 * otherwise find the number using modified binary search
	 * @param arr
	 * @return
	 */
	static int count1s(int[] arr){
		if(arr[0] == 0)
			return 0;
		else if(arr[arr.length-1] == 1)
			return arr.length;
		else{
			return modifiedBinarySearch(arr, 0, arr.length-1);
		}
	}
	
	/**
	 *  we use binary search to find the 0 whose left element is 1
	 */
	static int modifiedBinarySearch(int[] arr, int l, int r){
		int m = -1;
		while(l<r){
			m = l + (r-l)/2;
			// we check if arr[m] is 0 and previous one is 1 to 
			// to find the last index where 1 ends
			if(m-1 >=0 && arr[m] == 0 && arr[m-1] == 1)
				break;
			else if(arr[m] == 1)
				l = m+1;
			else	
				r = m-1;
		}
		// return m is m-1 has 1 and starting from 0
		return m;
	}
}
