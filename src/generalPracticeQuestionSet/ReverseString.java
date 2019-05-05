package generalPracticeQuestionSet;

import java.util.Stack;

public class ReverseString {
	
	//tester
	public static void main(String[] args){
		StringBuffer s = new StringBuffer("My name is Utkarsh Behre");
		System.out.println("Original string: " +s);
		s = reverse(s);
		System.out.println("Reversed string: " +s);
		
		char[] chars = "My name is Utkarsh Behre".toCharArray();
		reverseWithoutAuxSpace(chars);
		System.out.print("Reverse string without using auxiliary space: "); 
		System.out.print(chars);
	}
	
	/**
	 * reverses a string using stack
	 * takes stack space
	 * Runtime: O(n)
	 * @param s
	 * @return
	 */
	static StringBuffer reverse(StringBuffer s){
		Stack<Character> stack = new Stack<Character>();
		// push all characters onto the stack one by one
		for(int i = 0; i<s.length(); i++)
			stack.push(s.charAt(i));
		int i = 0;
		// pop each char and set in the string
		while(!stack.isEmpty())
			s.setCharAt(i++, stack.pop());
		return s;
	}
	
	/**
	 * reverses a string without using stack but with char array
	 * takes no extra space as we just swap the elements
	 * Runtime: O(n)
	 * @param s
	 * @return
	 */
	static char[]  reverseWithoutAuxSpace(char[] s){
		for(int i = 0; i<s.length/2; i++){
			swap(s, i, s.length-1-i);
		}
		return s;
	}
	
	static void swap(char[] s, int a, int b){
		char temp = s[a];
		s[a] = s[b];
		s[b] = temp;
	}
}
