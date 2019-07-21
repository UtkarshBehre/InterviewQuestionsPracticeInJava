package dynamicProgrammingQuestionSet;

import java.util.*;

/** Palindrome Partitioning
 * Given a string, a partitioning of the string is a palindrome partitioning if every 
 * substring of the partition is a palindrome. For example, “aba|b|bbabb|a|b|aba” is 
 * a palindrome partitioning of “ababbbabbababa”. Determine the fewest cuts needed for 
 * palindrome partitioning of a given string. For example, minimum 3 cuts are needed 
 * for “ababbbabbababa”. The three cuts are “a|babbbab|b|ababa”. If a string is palindrome, 
 * then minimum 0 cuts are needed. If a string of length n containing all different 
 * characters, then minimum n-1 cuts are needed
 * 
 * Runtime: O(n^2)
 * Spacetime: O(n^2)
 * @author Utkarsh
 *
 */
public class PalindromePartitioning {
	
	/**
	 * Solution entrypoint
	 */
	public static void minPartitions(String str) {
		int n = str.length();
		Map<String, Integer> mem = new HashMap<String, Integer>();
		int minP = minPartitions(str, 0, n-1, mem);
		System.out.println(minP);
	}
	
	/**
	 * Dynamic programming approach using memoization technique using a memo hashmap
	 * with key as start:end string format and value as int representing cuts required
	 * if string b/w start and end is a palindrome we can say 0 cuts requred
	 * else we try all possible cut positions and find the one which would give the
	 * minimum cuts required
	 * above if else will repeat at each level
	 * Runtime: O(n^2)
	 * Spacetime: O(n^2)
	 */
	public static int minPartitions(String s, int start, int end, Map<String, Integer> mem) {
		String key = start+":"+end;
		if(mem.containsKey(key)) {
			
			return mem.get(key);
		}
		int res = Integer.MAX_VALUE;
		if(start == end)
			res = 0;
		if(s.charAt(start) == s.charAt(end) && isPalindrome(s, start, end)) {
			res = 0;
		} 
		else {
			for(int i = start; i<end; i++) {
				// we add 1 to count the cut/partitions made
				res = Math.min(res, 1+ minPartitions(s, start, i, mem) + minPartitions(s, i+1, end, mem));
			}	
		}
		mem.put(key, res);
		return res;
	}
	
	/**
	 * Simple method to find if a string is a palindrome
	 */
	public static boolean isPalindrome(String s, int i1, int i2) {
		while(i1<=i2) {
			if(i1 == i2)
				return true;
			if(s.charAt(i1) == s.charAt(i2)) {
				i1++;
				i2--;
			} else
				return false;
		}
		return true;
	}
	
	/**
	 * stores all found palindromes of the range in the mem as well
	 */
	public static boolean isPalindrome(String s, int i1, int i2, Map<String, Integer> mem) {
		boolean b = false;
		if(i1 == i2)
			b = true;
		else if(s.charAt(i1) == s.charAt(i2)) {
			if(i1+1 > i2)
				b = true;
			else 
				b = isPalindrome(s, i1+1, i2-1, mem);
		} else
			return false;
		if(b==true)
			mem.put(i1+":"+i2,0);
		return b;
	}
	
	// tester
	public static void main(String[] args) {
		String str = "ababbbabbababa";
		minPartitions(str);
		
	}

}
