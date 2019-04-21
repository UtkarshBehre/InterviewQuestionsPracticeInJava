package popularQuestionSet;

/**
 * Sort n numbers in range from 0 to n^2 – 1 in linear time
 * Runtime: O(n)  | detailed explaination below
 * @author Utkarsh
 *
 */
public class SortNNumsRangeN2 {

/*
	Concept used to solve this: modified radix sort where base numbers used is n instead of 10
	Since we use n base numbers, the initial runtime O(d*(n+b)) changes proved below
	format log x (y) = d , where x is base so that x^d = y
	d = log 10 (n)  / normal radix sort b = 10 as count[] will have 10 range
	d = log n (n)  / our case b = n as count[] will have n range
	d = 1
	b = n          / our case, range of numbers used in count[] would be n
	we will run count sort exactly 1 time as per above and sort elements as per num%5
	we will have to run count sort 1 additional time at the end to sort as per num / 5
	this technicaly makes d as 2 so the new running time becomes 
	our running time = O(2*(n+n)) = O(2*2*n) = O(4n) = O(n)
	
	if given range of elements is n^2 -1
	then Below is an example of how {0, 23, 14, 12, 9} would be sorted in 2 iterations using radix and count sort
								Iteration 1(num/1%5)		Iteration 2 (num/5%5)
	Original/modified Array	|	0 23 14 12 9		|		0 12 23 14 9
	modded values			|	0 3 4 2 4			|		0 2 4 2 1
	count[]					|   1 0 1 1 2			|		1 1 2 0 1
	count[] cumulative		|	1 1 2 3 5			|		1 2 4 4 5
	partial/full sorted arr |   0 12 23 14 9		|		0 9 12 14 23
	
	if given range of elements is n*k -1
	then after first iteration it'll take k-1 more iterations
*/
	
	// tester
	public static void main(String[] args) {
		// case with range 0 - n^2-1
		int[] arr1 = {0,23,14,12,9};
		System.out.print("Before: ");
		printArr(arr1);
		modifiedRadixSort(arr1);
		System.out.print("AFter: ");
		printArr(arr1);
		
		// case with range 0 - n^4-1
		int[] arr2 = {27, 53,1, 62};
		System.out.print("Before: ");
		printArr(arr2);
		modifiedGeneralizedRadixSort(arr2,4);
		System.out.print("After: ");
		printArr(arr2);
	}
	
	/**
	 * logic to sort given array using modified count sort with base n
	 * This call will only work for range 0 - n^2-1
	 * @param arr
	 */
	public static void modifiedRadixSort(int[] arr) {
		
		countSortUsingBaseN(arr, 1);
		countSortUsingBaseN(arr, arr.length);
		
	}
	
	/**
	 * logic to sort given array using modified count sort with base n
	 * This call will work for range 0 - n^power-1 in a given array of n elements
	 * will work for any given power unless its out of int limit
	 * @param arr = given array
	 * @param power = power of n as given
	 */
	public static void modifiedGeneralizedRadixSort(int[] arr, int power) {
		int k = 0;
		int n = arr.length;
		while(k < power)
			countSortUsingBaseN(arr, (int)Math.pow(n, k++));
	}
	
	/**
	 * Logic for count sort except, here we use base of numbers as n and not the usual 10
	 * also factor is used to divide a number to power of n and get number to compare
	 * factor which usually is 10 gives us units 10ths 100ths place, 
	 * here it'll give units, 5th 25th if n is 5
	 * @param arr = given array
	 * @param sortByMod = boolean to check whether to sort by mod or divide
	 */
	public static void countSortUsingBaseN(int[] arr, int factor) {
		int n = arr.length;
		
		int[] arrCopy = new int[n];
		
		// O(n)
		for(int i = 0; i < n; i++) 
			arrCopy[i] = arr[i];
		
		int[] count = new int[n];
		// O(n)
		for(int i = 0; i < n; i++) 
			count[arrCopy[i]/factor%n]++;
		
		// O(n-1)
		for(int i = 1; i < n; i++) 
			count[i] += count[i-1];
		
		// O(n)
		for(int i = n-1; i >=0; i--) 
			arr[--count[arrCopy[i]/factor%n]] = arrCopy[i];
		
	}
	
	// prints an integer array
	public static void printArr(int[] arr) {
		for(int i : arr)
			System.out.print(i+" ");
		System.out.println();
	}
}
