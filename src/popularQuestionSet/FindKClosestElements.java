package popularQuestionSet;

/**
 * Find k closest elements to a given value
 * Given a sorted array arr[] and a value X, find the k closest elements to X in arr[]. 
 * Note that if the element is present in array, then it should not be in output, only 
 * the other closest elements are required.
 * Running time: O(klogn) 
 * @author Utkarsh
 *
 */
public class FindKClosestElements {
	
	// tester
	public static void main(String[] args) {
		/* expected output for {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56}
		   Output: 30 39 42 45 */
		int[] arr = {12, 16, 22, 30, 35, 39, 42, 
	               45, 48, 50, 53, 55, 56};
		//int[] arr = {33,43,75};
		int k = 4; 
		int x = 35;
		printClosestElements(arr, k, x);
	}
	
	/**
	 * Solution for the given problem
	 * first, we find the closest element's index from the given element using modified binary search
	 * then we traverse left and right at the same time to find closest element and print them
	 * if count is not met, means we hit an end. So we travel in other direction 
	 * to print remaining elements
	 * closest element is based on element distance i.e. X - element
	 * running time: O(klogn)
	 * @param arr = given array
	 * @param k = number of closest elements to print
	 * @param x = given element to look for
	 */
	public static void printClosestElements(int[] arr, int k, int x) {
		
		// find closest index using modified binary search O(logn)
		int index = findClosest(arr, 0, arr.length-1, x);
		int left = index;
		int right = index+1;
		// if we find the element we move left index 1 left so that we don't print the element
		if(arr[index] == x)
			left = left-1;
		
		// we compare left and right element each time and decrement or increment
		// based on which element was printed O(k)
		while(left>=0 && right<arr.length && k>0) {
			if(x - arr[left] < x - arr[right])
				System.out.print(arr[left--]+" ");
			else
				System.out.print(arr[right++]+" ");
			k--;
		}
		// by this point if k >0 then we need more elements to be printed
		// print leftover left elements if no more right elements
		while(k>0 && left>=0) {
			System.out.print(arr[left--]+" ");
			k--;
		}
		// print leftover right elements if no more left elements
		while(k>0 && right<arr.length) {
			System.out.print(arr[right++]+" ");
			k--;
		}
	}
	
	/**
	 * logic for modified binary search
	 * returns the closest element to the given element's index
	 * Running time: O(logn)
	 * @param arr = given array
	 * @param l = left most index
	 * @param r = right most index
	 * @param x = element to search
	 * @return index of closest match of x
	 */
	public static int findClosest(int[] arr, int l, int r, int x) {
		// if the element is higher then highest or lower then lowest we return
		if(arr[l] > x)
			return l;
		else if(arr[r]<x)
			return r;
		int mid = l+ (r-l)/2;
		// return if exact match found
		if(arr[mid] == x)
			return mid;
		else if(arr[mid] > x)
			return findClosest(arr, l, mid-1, x);
		return findClosest(arr, mid+1, r, x);
		 
	}
	
}
