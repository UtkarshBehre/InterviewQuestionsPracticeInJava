package dynamicProgrammingQuestionSet;

import java.util.*;

/**
 * Edit Distance
 * Given two strings str1 and str2 and below operations that can performed on str1. 
 * Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
 * 1. Insert
 * 2. Remove
 * 3. Replace
 * All of the above operations are of equal cost.
 * Runtime: O(mn)
 * @author Utkarsh
 */
public class EditDistance {
	
	public static void minEdits(String s1, String s2) {
		int m = s1.length()-1;
		int n = s2.length()-1;
		Map<String, Integer> mem = new HashMap<String, Integer>();
		int edits = minEditsRec(s1, s2, m, n, mem);
		System.out.println("Min edits required for "+s1+" and "+s2+": "+ edits);
	}
	
	/**
	 * brute force approach using memoization, trying all possible operations on current
	 * last character if it wasn't a match.
	 * Runtime: O(mn)
	 */
	public static int minEditsRec(String s1, String s2, int m, int n, Map<String,Integer> mem) {
		// if s1 is over insert remaining s2 chars | +1 coz indexing starts at 0
		if(m<0)
			return n+1;
		// if s2 is over remove remaining s1 chars
		if(n<0)
			return m+1;
		String key = m+":"+n;
		int count = 0;
		if(mem.containsKey(key)) {
			return mem.get(key);
		}
		if(s1.charAt(m) == s2.charAt(n)) {
			count = minEditsRec(s1, s2, m-1, n-1, mem);
		}
		else {
			// 1st one is for insert, 2nd one is for remove and 3rd one is for replace
			count = 1+ Math.min(minEditsRec(s1, s2, m, n-1, mem), Math.min(minEditsRec(s1, s2, m-1, n, mem),
					minEditsRec(s1, s2, m-1, n-1, mem)));
		}
		mem.put(key,count);
		return count;
	}
	
	/**
	 * Bottom up approach using dynamic programming doing double looping
	 * Runtime: O(mn)
	 */
	public static int minEditsBotUp(String s1, String s2) {
		int m = s1.length();
		int n = s2.length();
		int[][] M = new int[m+1][n+1];
		
		for(int i = 0; i<=m; i++) {
			for(int j = 0; j<=n; j++) {
				if(i==0)
					M[i][j] = j;
				else if(j==0)
					M[i][j] = i;
				else if(s1.charAt(i-1)==s2.charAt(j-1)) {
					M[i][j] = M[i-1][j-1];
				}
				else {
					M[i][j] = 1+Math.min(M[i][j-1], Math.min(M[i-1][j], M[i-1][j-1]));
				}
			}
		}
		System.out.println("Bottom Up Ans: "+M[m][n]);
		return M[m][n];
	}
	
	public static void main(String[] args) {
		String s1 = "geek";
		String s2 = "gesek";
		minEdits(s1, s2);
		minEditsBotUp(s1, s2);
		s1 = "cat";
		s2 = "cut";
		minEdits(s1, s2);
		minEditsBotUp(s1, s2);
		s1 = "sunday";
		s2 = "saturday";
		minEdits(s1, s2);
		minEditsBotUp(s1, s2);
	}
}
