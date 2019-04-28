package popularQuestionSet;

/**
 * Search in an almost sorted array
 * Given an array which is sorted, but after sorting some elements are moved 
 * to either of the adjacent positions, i.e., arr[i] may be present at arr[i+1] 
 * or arr[i-1]. Write an efficient function to search an element in this array. 
 * Basically the element arr[i] can only be swapped with either arr[i+1] or arr[i-1].
 * 
 * Input: arr[] =  {10, 3, 40, 20, 50, 80, 70}, key = 40
 * Output: 2 
 * Output is index of 40 in given array
 * Input: arr[] =  {10, 3, 40, 20, 50, 80, 70}, key = 90
 * Output: -1
 * -1 is returned to indicate element is not present
 * 
 * Running time: O(logn)
 * @author Utkarsh
 *
 */
public class SearchAlmostSorted {
	public static void main(String[] args) {
		int[] arr = {2, 3, 10, 4, 40};
		int key = 10;
		int index = findKey(arr, 0, arr.length-1, key);
		if(index == -1)
			System.out.println("-1 is returned to indicate element is not present");
		else
			System.out.println(index + "\nOutput is index of "+key+" in given array.");
	}
	
	/**
	 * gets the index of of the given key if found otherwise -1
	 * running time: O(logn)
	 */
	public static int findKey(int[] arr, int l, int r, int num) {
		if(l>r)
			return -1;
		int m = l + (r-l)/2;
		
		if(arr[m] == num)
			return m;
		
		if(m-1 >= l && arr[m-1] == num)
			return m-1;
		
		if(m+1 <= r && arr[m+1] == num)
			return m+1;
		
		if(arr[m] > num)
			return findKey(arr, l, m-2, num);
		return findKey(arr, m+2, r, num);
	}
}
