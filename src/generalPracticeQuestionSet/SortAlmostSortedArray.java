package generalPracticeQuestionSet;

/**
 * Sort a nearly sorted (or K sorted) array
 *  Given an array of n elements, where each element is at most k away from
 *  its target position, devise an algorithm that sorts in O(n log k) time. 
 *  For example, let us consider k is 2, an element at index 7 in the sorted 
 *  array, can be at indexes 5, 6, 7, 8, 9 in the given array.
 *  O(n log k)
 * @author Utkarsh
 *
 */
public class SortAlmostSortedArray {
	
	public static void main(String[] args) {
		int[] arr = {6, 5, 3, 2, 8, 10, 9};
		int k = 3;
		//int[] arr = {10, 9, 8, 7, 4, 70, 60, 50};
		//int k = 4;
		
		modifiedQuickSort(arr, 0, arr.length-1, k);
		printArray(arr);
	}
	
	static void modifiedQuickSort(int[] arr, int first, int last, int k) {	
		if(first>last)
			return;
		int pi = partitioningUsingLastPivot(arr, first, last, k);
		modifiedQuickSort(arr, first, pi-1, k);
		modifiedQuickSort(arr, pi+1, last, k);
	}
	
	static int partitioningUsingLastPivot(int[] arr, int first, int last, int k) {
		int l = last-k;
		if(l<first) {
			l=first;
		}
		int i = l-1;
		for(int j = l; j<last; j++) {
			if(arr[j] <= arr[last]) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
				
		}
		i++;
		int temp = arr[i];
		arr[i] = arr[last];
		arr[last] = temp;
		return i;
	}
	
	static void swap(int a, int b) {
		int temp = a;
		a = b;
		b= temp;
	}
	
	static void printArray(int[] arr) {
		for(int i : arr)
			System.out.print(i+" ");
	}
}
