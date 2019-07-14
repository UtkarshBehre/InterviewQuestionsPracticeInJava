package generalPracticeQuestionSet;

import java.util.*;

/**
 * Q1. Give the count of numbers that are non unique in a list of numbers
 * Q2. Give the first repeating word in a sentence
 * Runtime: O(n) for both
 * @author Utkarsh
 *
 */
public class NonUniqueCount {
	
	/**
	 * Solution that gives count of non unique numbers in a given list
	 * Runtime: O(n)
	 */
	public static int countNonUniqueNums(List<Integer> numbers) {
		Set<Integer> set = new HashSet<Integer>();
		int count = 0;
		for(int num: numbers) {
			if(set.contains(num)){
				count++;
			}
			else
				set.add(num);
		}
		return count;
	}
	
	/**
	 * Solution that gives first repeating word in a sentence
	 * Runtime: O(n), where n is the length of the given string
	 */
	public static String firstRepeatingWord(String s) {
		String[] words = s.split(" "); // this is the one taking O(n)
		Set<String> set = new HashSet<String>();
		for(int i = 0;i<words.length; i++) { // this would take O(word count)
			if(set.contains(words[i]))
				return words[i];
			else
				set.add(words[i]);
		}
		return "";
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(10);
		numbers.add(20);
		numbers.add(30);
		numbers.add(10);
		numbers.add(20);
		numbers.add(40);
		System.out.println("count of non unique numbers: "+countNonUniqueNums(numbers));
		String s = "I am, whatever I say I am, and if I wasn't than why would I say I am.";
		System.out.println("First repeating word: "+firstRepeatingWord(s));
	}
}
