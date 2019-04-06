package popularQuestionSet;

/**
 * find the kth minimum number in a given array of distinct integers
 * @author Utkarsh Behre
 *
 */
public class FindKthMinInt {
	
	/**
	 * solution uses modified version of quicksort
	 * runtime O(nlogn) | would be < O(nlogn) for more cases depending on pivot selection and k
	 */
	static int findKMin(int[] arr, int k) {
		return partialQuickSort(arr, 0, arr.length-1, k);
	}
	
	/**
	 * solution uses quick sort to sort all elements and then give kth min
	 * runtime O(nlogn) } for all cases
	 */
	static int findKMinUsingFullSort(int[] arr, int k) {
		quickSort(arr, 0, arr.length -1);
		return arr[k];
	}
	
	static int partialQuickSort(int[] arr, int l, int r, int k) {
		
		int pi = partitioning(arr, l,r);
		if(pi == k)
			return arr[k];
		else if(pi > k)
			return partialQuickSort(arr, l, pi-1,k);
		else
			return partialQuickSort(arr,pi+1,r,k);
	}
	
	static int partitioning(int[] arr, int l, int r) {
		int pivot = arr[r];
		int i = l-1; 
		for(int j = l; j<r; j++) {
			if(arr[j]<=pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		i++;
		int temp = arr[i];
		arr[i] = pivot;
		arr[r] = temp;
		return i;
	}
	
	public static void main(String[] args) {
		//int[] arr = {5,88,34,2,74,8};
		int[] input = new int[10001];
		for(int i = 0; i<input.length; i++){
			input[i] = input.length-i;
		}
		
		/* IMPORTANT TO NOTE
		 * if k is close to 0 or last index it takes way lesser time than full sort
		 * the close k gets to middle index the closer is the running time to full sort
		 * if it is exactly same it is slightly more than full sort time because there are more comparisons done
		 * but again this depends on the kind of pivot selection that we use in partitioning */
		int k = 5000;
		
		long t11 = System.currentTimeMillis();
		int kMin = findKMin(input, k-1);
		long t12 = System.currentTimeMillis();
		for(int i = 0; i<input.length; i++){
			input[i] = input.length-i;
		}
		
		long t21 = System.currentTimeMillis();
		kMin = findKMinUsingFullSort(input, k-1);
		long t22 = System.currentTimeMillis();
		System.out.println("The Kth element is: " +kMin);
		System.out.println("Running time using partial quick sort: "+(t12-t11));
		System.out.println("Running time using full quick sort: "+ (t22-t21));
		
	}
	
	public static void quickSort(int[] arr, int l, int r){
		if(l<r){
			int pi = partitioning(arr, l, r);

			quickSort(arr, l, pi-1);
			quickSort(arr, pi+1, r);		
		}
	}
	
}
