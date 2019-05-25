package generalPracticeQuestionSet;

/**
 * Sort a nearly sorted (or K sorted) array Given an array of n elements, where
 * each element is at most k away from its target position, devise an algorithm
 * that sorts in O(n log k) time. For example, let us consider k is 2, an
 * element at index 7 in the sorted array, can be at indexes 5, 6, 7, 8, 9 in
 * the given array. 
 * Runtime: O(n log k)
 * @author Utkarsh
 */
public class SortAlmostSortedArray {

	/**
	 * uses a minheap of size k+1 to keep storing k+1 items while iterating 
	 * through the array. First we put first k+1 element in the heap right away
	 * after which we extract and insert 1 at a time and storing min items 
	 * in non-decreasing order in new array which we return at the end
	 * Runtime: O(nlogk)
	 */
	public static int[] sortUsingMinHeap(int[] arr, int k) {
		MinHeap minHeap = new MinHeap(k + 1);
		int[] res = new int[arr.length];
		int i = 0;
		while (i < k + 1) {
			minHeap.insert(arr[i++]);
		}
		int resIndex = 0;
		while (i < arr.length || minHeap.size != 0) {
			res[resIndex++] = minHeap.extractMin();
			if (i < arr.length)
				minHeap.insert(arr[i++]);
		}
		return res;
	}
	
	// tester code
	public static void main(String[] args) {
//		int[] arr = { 6, 5, 3, 2, 8, 10, 9 };
//		int k = 3;
		int[] arr = {10, 9, 8, 7, 4, 70, 60, 50};
		int k = 4;
		printArray(arr);
		arr = sortUsingMinHeap(arr, k);
		printArray(arr);
	}

	/**
	 * MinHeap implementation which is used above
	 * @author Utkarsh
	 */
	static class MinHeap {
		int[] heap;
		int capacity;
		int size;

		public MinHeap(int capacity) {
			this.capacity = capacity;
			this.heap = new int[capacity];
			this.size = 0;
		}

		public void insert(int val) {
			int i = size;
			heap[size++] = val;
			int parent = (i - 1) / 2;
			while (parent >= 0 && heap[parent] > heap[i]) {
				int tmp = heap[parent];
				heap[parent] = heap[i];
				heap[i] = tmp;
				i = parent;
				parent = (i - 1) / 2;
			}
		}

		public int extractMin() {
			if (size == 0) {
				return 0;
			}
			int min = heap[0];
			heap[0] = heap[size - 1];
			size--;
			heapify(0);
			return min;
		}

		public void heapify(int i) {
			int l = 2 * i + 1;
			int r = 2 * i + 2;
			int smallest = i;
			if (l < size && heap[l] < heap[smallest])
				smallest = l;
			if (r < size && heap[r] < heap[smallest])
				smallest = r;
			if (smallest != i) {
				int tmp = heap[i];
				heap[i] = heap[smallest];
				heap[smallest] = tmp;
				heapify(smallest);
			}
		}
	}

	static void printArray(int[] arr) {
		System.out.println();
		for (int i : arr)
			System.out.print(i + " ");
	}
}
