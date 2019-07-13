package dynamicProgrammingQuestionSet;

import java.util.*;

/**
 * LCS Problem Statement: Given two sequences, find the length of longest subsequence 
 * present in both of them. A subsequence is a sequence that appears in the same 
 * relative order, but not necessarily contiguous. 
 * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
 * LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
 * Runtime: O(mn)
 * @author Utkarsh
 *
 */
public class LongestCommonSubsequence {
	
	public static void findLCS(String s1, String s2) {
		int m = s1.length()-1;
		int n = s2.length()-1;
		int lcsCount = 0;
		Map<String, Integer> mem = new HashMap<String, Integer>();
		lcsCount = findLCSRec(s1, s2, m, n, mem);
		System.out.println("LCS using memoization recursion: "+lcsCount);
	}
	
	/**
	 * Solution uses dp with map with key as last indices of s1 and s2 and value 
	 * as count of lcs till that point
	 * Runtime: O(mn)
	 */
	public static int findLCSRec(String s1, String s2, int m, int n, Map<String, Integer> mem) {
		if(m<0 || n<0)
			return 0;
		int count = 0;
		String key = m+":"+n;
		if(mem.containsKey(key)) {
			return mem.get(key);
		}
		if(s1.charAt(m) == s2.charAt(n)) {
			count = 1 + findLCSRec(s1, s2, m-1, n-1, mem);
		}
		else {
			count = Math.max(findLCSRec(s1, s2, m, n-1,mem), findLCSRec(s1, s2, m-1, n, mem));
		}
		mem.put(key, count);
		return count;
	}
	
	/**
	 * dynamic bottom up approach using 2d int array to find out the lcs from
	 * start at each i and j till last i and j
	 * Runtime: O(mn)
	 */
	public static void findLCSIter(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] mem = new int[m+1][n+1];
		
		for(int i = 0; i<=m; i++) {
			for(int j = 0; j<=n; j++) {
				if(i==0 || j == 0)
					mem[i][j] = 0;
				else if(s1.charAt(i-1) == s2.charAt(j-1)) {
					mem[i][j] = 1 + mem[i-1][j-1];
				}
				else {
					mem[i][j] = Math.max(mem[i][j-1], mem[i-1][j]);
				}
			}
		}
		System.out.println("LCS using bottom up: "+mem[m][n]);
	}
	
	public static void main(String[] args) {
		String s1 = "ABCDGH";
		String s2 = "AEDFHR";
		System.out.println("s1="+s1+"\ts2="+s2);
		findLCS(s1,s2);
		findLCSIter(s1,s2);
		s1 = "AGGTAB";
		s2 = "GXTXAYB";
		System.out.println("s1="+s1+"\ts2="+s2);
		findLCS(s1,s2);
		findLCSIter(s1,s2);
	}
}
