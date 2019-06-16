package dynamicProgrammingQuestionSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LongestIncSubsequence {
	
	/**
	 * find and prints the longest inc sub sequence using dynamic programming
	 * to store changing values as key and count as value
	 * Runtime: O(n^2) in worst case of decreasing order array
	 * @param arr
	 */
	public static void findLongestIncSeq(int[] arr) {
		int n = arr.length;
		HashMap<String, Integer> mem = new HashMap<String, Integer>();
		//mem[n-1] = 1;
		int max = 0;
		// O(n^2) in decreasing as all iterations will be unique
		for(int i=0;i<n; i++) {
			max = Math.max(max, dpRec(i, i+1, arr, mem));
		}
		
		// print out LIS
		System.out.println("Length of LIS: "+max);
		int start = 0;
		// find the max LIS start index, could go upto O(n^2) in worst case
		for(Entry<String, Integer> entry : mem.entrySet()) {
			if(max == entry.getValue())
				start = Integer.valueOf(entry.getKey().substring(0,1));
		}
		
		// print the longest increasing subsequence
		System.out.print("Longest increasing subsequence: ");
		while(start<n) {
			System.out.print(start+" ");
			int next = start +1;
			while(next<n && arr[next] < arr[start])
				next ++;
			if(next == n)
				break;
			start = next;
		}
	}
	
	/**
	 *	keeps the count of increasing sequence on an element by holding last 
	 *	visited pattern and using the memory to avoid repeating computation
	 *	Runtime: O(n)
	 */
	public static int dpRec(int i, int next, int[] arr, HashMap<String, Integer> mem) {
		int seqCount = 0;
		while(next < arr.length && arr[next] < arr[i]) {
			mem.put(i+":"+next, 0);
			next +=1;
		}
		if(next == arr.length)
			return 0;
		String key = i+":"+next;
		if(mem.containsKey(key))
			return mem.get(key);
		else {
			if(next == arr.length-1)
				seqCount = 2;
			else 
				seqCount = dpRec(next, next+1, arr, mem)+1;
		}
			
		mem.put(key, seqCount);
		return seqCount;
	}
	
	public static void main(String[] args) {
		int[] arr = {50,3,10,7,40,80};
		findLongestIncSeq(arr);
	}
}
