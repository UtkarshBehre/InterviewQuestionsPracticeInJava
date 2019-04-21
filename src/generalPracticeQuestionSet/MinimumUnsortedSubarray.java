package generalPracticeQuestionSet;

/**
 * Find the Minimum length Unsorted Subarray, sorting which makes the complete array sorted
 * running time: O(n)
 * @author Utkarsh Behre
 *
 */
public class MinimumUnsortedSubarray {

	static int findMinUnsortedSubarray(int[] arr) {
		int l = 0;
		int r = 0;
		int n = arr.length;
		// loop from start to find start left index
		for(int i = 0; i<n-1; i++) {
			if(arr[i]>arr[i+1]) {
				l = i+1;
				break;
			}
		}
		// loop from end to find end right index
		for(int i = n-2; i>=0; i--) {
			if(arr[i]>arr[i+1]) {
				r=i;
				break;
			}
		}
		// loop in range to find min and max
		int min = arr[l];
		int max = arr[l];
		for(int i = l; i<=r; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}

		// loop before until before left index to find actualy start index
		for(int i = 0; i<l; i++) {
			if(arr[i]>min)
				l = i;
		}
		// loop until after right index to find actual right index
		for(int i = n-1; i>r; i--) {
			if(arr[i] < max)
				r=i;
		}

		// print result
		System.out.println("The minimum unsorted subarray required to be sorted lies between the indexes "+l+" and "+r+".");
		return r-l+1;
	}

	// tester
	public static void main(String[] args) {
		int[] input = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
		int length = findMinUnsortedSubarray(input);
		System.out.println("Minimum length of Unsorted Subarray : "+length);
	}
}
