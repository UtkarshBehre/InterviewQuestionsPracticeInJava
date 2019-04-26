package popularQuestionSet;

/**
 * Find common elements in three sorted arrays
 * Given three arrays sorted in non-decreasing order, print all common elements in these arrays.
 * 
 * Running Time: O(n1+n2+n3)
 * @author Utkarsh
 *
 */
public class CommonIn3SortedArrays {
	
	// tester
	public static void main(String[] args){
		int[] arr1 = {1, 5, 10, 20, 40, 80};
		int[] arr2 = {6, 7, 20, 80, 100};
		int[] arr3 = {3, 4, 15, 20, 30, 70, 80, 120};
		//Output: 20, 80
		printCommonElements(arr1,arr2,arr3);
	}
	
	/**
	 * loops through all 3 arrays together
	 * prints if all equal are found
	 * increments index of lower element array checks from 1 to 3
	 * @param arr1
	 * @param arr2
	 * @param arr3
	 */
	static void printCommonElements(int[] arr1, int[] arr2, int[] arr3){
		int n1 = arr1.length;
		int n2 = arr2.length;
		int n3 = arr3.length;
		// initiate 3 indexes with 0 for 3 arrays
		int i=0;
		int j=0;
		int k=0;
		// run until any array is traversed completely
		// as that would mean even maximum element of that array wasn't equal
		// to any other element of any other array
		while (i<n1 && j<n2 && k<n3){
			if(arr1[i] == arr2[j] && arr2[j] == arr3[k]){
				System.out.print(arr1[i]+" ");
				i++;
				j++;
				k++;
			}
			else if(arr1[i] < arr2[j])
				i++;
			else if(arr2[j] < arr1[i])
				j++;
			else // would be arr3[k] < arr1[i] && arr3[k] < arr2[j]
				k++;
		}
	}
}
