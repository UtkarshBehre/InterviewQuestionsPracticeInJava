package utilityDataStructuresImplementations;
/**
 * Implement following methods for a max-heap
 * getmax() - O(1)
 * extractmax(), decreaseKey(), insert(), delete() - O(logn) 
 * @author Utkarsh
 *
 */
public class MaxHeap {
	int[] maxHeap;
	int size;
	int capacity;
	public MaxHeap(int capacity) {
		this.size = 0;
		this.capacity = capacity;
		maxHeap = new int[capacity];
	}
	
	/**
	 * returns first element as thats maximum for max-heap
	 * Runtime: O(1)
	 */
	public int getmax() {
		return maxHeap[0];
	}
	
	/**
	 * takes out the first element as max element, assigns last element
	 * to it and call heapify to fix the heap
	 * Runtime: O(logn)
	 */
	public int extractMax() {
		int max = maxHeap[0];
		maxHeap[0] = maxHeap[size-- -1];
		heapify(0);
		return max;
	}
	
	/**
	 * increases value at i and makes it newVal, assuming newVal is higher,
	 * then puts the newVal in its right position by check with parent
	 * Runtime: O(logn)
	 */
	public void increaseKey(int i, int newVal) {
		maxHeap[i] = newVal;
		int parent = (i-1)/2;
		while(maxHeap[parent] < newVal) {
			swap(parent, i);
			i = parent;
			parent = (i-1)/2;
		}
	}
	
	/**
	 * inserts a new value to the max heap
	 * inserts at after the last element and starts pushing it upwards to
	 * its correct position by swapping with parent until parent is smaller
	 * Runtime: O(logn)
	 */
	public void insert(int val) {
		maxHeap[size++] = val;
		int i = size-1;
		int parent = (i-1)/2;
		while(parent>=0 && maxHeap[parent] < maxHeap[i]) {
			swap(parent,i);
			i = parent;
			parent = (i-1)/2;
		}
	}
	
	/**
	 * deletes a value at index i by increasing value to max and extracting it
	 * Runtime: O(logn)
	 */
	public void delete(int i) {
		increaseKey(i,Integer.MAX_VALUE);
		extractMax();
	}
	
	/**
	 * starts heapify process on element i cascading it to its proper place
	 * by swapping with children as they are encountered
	 * Runtime: O(logn)
	 */
	private void heapify(int i) {
		int left = 2*i+1;
		int right = 2*i+2;
		int largest = i;
		if(left<size && maxHeap[largest] < maxHeap[left])
			largest = left;
		if(right<size && maxHeap[largest] < maxHeap[right])
			largest = right;
		if(largest!=i) {
			swap(largest, i);
			heapify(largest);
		}
	}
	
	/**
	 * utility: print the maxHeap
	 */
	public void print() {
		System.out.println();
		for(int i = 0; i<size; i++) {
			System.out.print(maxHeap[i]+ " ");
		}
	}
	
	/**
	 * utility: swaps values on indexes i1 and i2 in the maxHeap
	 */
	private void swap(int i1, int i2) {
		int tmp = maxHeap[i1];
		maxHeap[i1] = maxHeap[i2];
		maxHeap[i2] = tmp;
	}
}
