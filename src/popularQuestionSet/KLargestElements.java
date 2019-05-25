package popularQuestionSet;

import utilityDataStructuresImplementations.MaxHeap;
import utilityDataStructuresImplementations.MinHeap;

/**
 * print k largest(or smallest) elements in an array 
 * Runtime: O(n+klogn), O(k+(n-k)logk
 * @author Utkarsh
 */
public class KLargestElements {
	
	/**
	 * build the entire max heap then extract top k
	 * Runtime: O(n+klogn)
	 */
	public static void printKLargestNumbersUsingMaxHeap(int[] arr, int k) {
		MaxHeap heap = new MaxHeap(arr.length);
		for(int num: arr) {
			heap.insert(num);
		}
		int count = 1;
		System.out.print("Largest "+ k+" elements using max heap are: ");
		while(count++<=k)
			System.out.print(heap.extractMax()+" ");
	}
	
	/**
	 * keep track of only top 3 while progressing through array in min heap
	 * Runtime: O(k+(n-k)logk)
	 */
	public static void printKLargestNumbersUsingMinHeap(int[] arr, int k) {
		MinHeap heap = new MinHeap(k);
		
		for(int i=0; i<k; i++) {
			heap.insert(arr[i]);
		}
		for(int i =k; i<arr.length; i++) {
			if(arr[i]>heap.getMini()) {
				heap.extractMin();
				heap.insert(arr[i]);
			}
		}
		System.out.print("\nLargest "+k+" elements using min heap: ");
		int[] largestK = new int[k];
		while(heap.size!=0) {
			largestK[heap.size-1] = heap.extractMin();
		}
		for(int num: largestK)
			System.out.print(num+" ");
	}
	// tester
	public static void main(String[] args) {
		int[] arr = {1, 23, 12, 9, 30, 2, 50};
		int k = 3;
		printKLargestNumbersUsingMaxHeap(arr,k);
		printKLargestNumbersUsingMinHeap(arr,k);
	}
}
