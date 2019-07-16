package dynamicProgrammingQuestionSet;

import java.util.*;

/**
 * 	Longest Palindromic Subsequence
 * Given a sequence, find the length of the longest palindromic subsequence in it.
 * Example Input: GEEKSFORGEEKS
 * Output 5 ( here it could have been EEKEE or EEFEE or EEGEE )
 * so it doesn't have to be continuous
 * Runtime: O(n^2)
 * @author Utkarsh
 */
public class LongestPalindromicSubsequence {
	
	/** 
	 * entry point for a solution method call
	 */
	public static void findLPS(String str) {
		int start = 0;
		int end = str.length()-1;
		Map<String, Integer> mem = new HashMap<String, Integer>();
		int lps = findLPSRec(str, start, end, mem);
		System.out.println("dynamically: "+lps);
		lps = findLPSRec(str, start, end);
		System.out.println("Normally: "+lps);
	}
	
	/**
	 * Dynamic memoization solution
	 * key concept: if current first and last chars match then check first +1 and last -1
	 * 				else check the max of (first and last -1) and first +1 and last)
	 * base cases being only 1 char left in string and no characters left at all
	 * Runtime: O(n^2)
	 */
	public static int findLPSRec(String str, int s, int e, Map<String,Integer> mem) {
		int res=0;
		String key = s+":"+e;
		if(mem.containsKey(key))
			return mem.get(key);
		if(s>e)
			return 0;
		if(s==e)
			return 1;
		else if(str.charAt(s) == str.charAt(e)){
			res = 2 + findLPSRec(str, s+1, e-1, mem);
			mem.put(key, res);
			return res;
		}
		res = Math.max(findLPSRec(str, s+1,e, mem), findLPSRec(str, s,e-1, mem));
		mem.put(key, res);
		return res;
	}
	
	/**
	 * Generic solution that does exponential computation
	 * key concept: if current first and last chars match then check first +1 and last -1
	 * 				else check the max of (first and last -1) and first +1 and last)
	 * base cases being only 1 char left in string and no characters left at all
	 * Runtime: exponential
	 */
	public static int findLPSRec(String str, int s, int e) {
		if(s>e)
			return 0;
		if(s==e)
			return 1;
		else if(str.charAt(s) == str.charAt(e)){
			return 2 + findLPSRec(str, s+1, e-1);
		}
		return Math.max(findLPSRec(str, s+1,e), findLPSRec(str, s,e-1));
	}
	
	public static void main(String[] args) {
		String str = "BOBBOBB";
		findLPS(str);
		str = "BBABCBAB";
		findLPS(str);
		str = "GEEKSFORGEEKS";
		findLPS(str);
	}

}
