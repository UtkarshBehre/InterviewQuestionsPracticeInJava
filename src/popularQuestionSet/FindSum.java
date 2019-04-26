package popularQuestionSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Find a pair of integers if any in an array, whose sum is a specific number
 * Running time = O(nlogn) if unsorted | O(n) if sorted
 * @author Utkarsh Behre
 *
 */
public class FindSum {
	// fastest way O(nlogn)  | needs sorting
	public static boolean findPairWithSum(int[] arr, int sum){
		Arrays.sort(arr);
		boolean result = false;
		for(int i = 0; i<arr.length; i++){
			int comp = sum - arr[i];
			result = binarySearch(arr,i,comp);
			if(result == true)
				break;
		}
		
		return result;
	}
	
	// O(n) if array is sorted O(nlogn) if array is not sorted
	public static boolean findPairWithSumBothEnds(int[] arr, int sum){
		// below line can be removed if the given array is sorted
		Arrays.sort(arr);
		boolean result = false;
		int l = 0;
		int r = arr.length-1;
		while(l<r){
			if(arr[l] + arr[r] == sum )
				result = true;
			else if(arr[1] + arr[r] < sum)
				l++;
			else
				r--;
		}
		return result;
	}
	
	// slowest way O(n^2) | no sorting required
	public static boolean findPairWithSumN2(int[] arr, int sum){
		boolean result = false;
		for(int i = 0; i<arr.length; i++){
			int comp = sum - arr[i];
			
			for(int j = i+1; j<arr.length;j++){
				if(comp == arr[j]){
					result = true;
					break;
				}
			}
			if(result == true)
				break;
		}
		
		return result;
	}
	
	// very close to fastest way almost O(nlogn)
	// no sorting required
	public static boolean findPairWithSumMap(int[] arr, int sum){
		//Arrays.sort(arr); 
		boolean result = false;
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i<arr.length; i++){
			int comp = sum - arr[i];
			
			if(map.containsKey(comp)){
				result = true;
				break;
			}
			
			if(!map.containsKey(arr[i]))
				map.put(arr[i], i);
			
		}
		return result;
	}
	// runtime O(logn)
	public static boolean binarySearch(int[] arr,int start,int num){
		int l = start;
		int u = arr.length-1;
		
		// run loop until higher index is lower than lower index
		while(u>=l){
			int m = l + (u-l)/2;
			// if it is middle element then return middle index
			if(num == arr[m])
				return true;
			// set upper index to middle -1 if x is lower than middle number
			else if (num < arr[m])
				u = m-1;
			// set lower index to middle +1 if x is higher than middle number
			else
				l = m+1;
		}
		// return -1 if number is not found
		return false;
	}
	
	public static void main(String[] args){
		int[] input = new int[100001];
		for(int i = 0; i<input.length; i++){
			input[i] = input.length-i;
		}
		long t11 = System.currentTimeMillis();
		System.out.println(findPairWithSum(input, 1999999));
		//System.out.println(findPairWithSumBothEnds(input, 1999999));
		long t12 = System.currentTimeMillis();
		
		for(int i = 0; i<input.length; i++){
			input[i] = input.length-i;
		}
		long t21 = System.currentTimeMillis();
		System.out.println(findPairWithSumN2(input, 1999999));
		long t22 = System.currentTimeMillis();
		
		for(int i = 0; i<input.length; i++){
			input[i] = input.length-i;
		}
		long t31 = System.currentTimeMillis();
		System.out.println(findPairWithSumMap(input, 1999999));
		long t32 = System.currentTimeMillis();
		System.out.println("findPairWithSumBinary: " + (t12-t11));
		System.out.println("findPairWithSumN2: " + (t22-t21));
		System.out.println("findPairWithSumMap: " + (t32-t31));
	}
}
