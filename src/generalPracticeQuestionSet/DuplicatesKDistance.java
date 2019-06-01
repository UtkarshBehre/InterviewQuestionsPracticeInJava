package generalPracticeQuestionSet;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Check if a given array contains duplicate elements within k distance from 
 * each other
 * Runtime: O(n)
 * @author Utkarsh
 *
 */
public class DuplicatesKDistance {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3 ,4, 1, 5};
		int k = 3;
		boolean result = haveDuplicateWithinK(arr, k);
		System.out.println("Duplicate present: "+result);
	}
	
	/**
	 * finds duplicate within k distance using hashing data structures
	 * Runtime: O(n)
	 * @param arr
	 * @param k
	 * @return
	 */
	public static boolean haveDuplicateWithinK(int[] arr, int k) {
		return usingHashSet(arr, k);
	}
	public static boolean usingHashMap(int[] arr, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();		
		for(int i = 0; i<arr.length; i++) {
			if(map.containsValue(arr[i])) 
				return true;
			map.put(i%k, arr[i]);
		}
		return false;
	}
	public static boolean usingHashSet(int[] arr, int k) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i<arr.length; i++) {
			if(set.contains(arr[i]))
				return true;
			set.add(arr[i]);
			if(i>=k) { // note: i starts from 0 but k from 1
				set.remove(arr[i-k]);
			}
		}
		return false;
	}
}
