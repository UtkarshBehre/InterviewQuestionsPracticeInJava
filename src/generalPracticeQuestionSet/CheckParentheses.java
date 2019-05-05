package generalPracticeQuestionSet;

import java.util.Stack;
/**
 * Check for balanced parentheses in an expression
 * Given an expression string exp , write a program to examine 
 * whether the pairs and the orders of “{“,”}”,”(“,”)”,”[“,”]” 
 * are correct in exp. For example, the program should print true 
 * for exp = “[()]{}{[()()]()}” and false for exp = “[(])”
 * Runtime: O(n)
 * @author Utkarsh
 *
 */
public class CheckParentheses {
	
	// tester
	public static void main(String[] args){
		String s1 = "[()]{}{[()()]()}";
		//String s2 = "[(])";
		boolean isBalanced = isBalanced(s1);
		if(isBalanced)
			System.out.println("Balanced");
		else
			System.out.println("Not balanced");
	}
	
	/**
	 * checks the parentheses pair using stack
	 * Runtime: O(n)
	 * @param s = given parentheses string
	 * @return = true if balanced, false if not balanced
	 */
	static boolean isBalanced(String s){
		Stack<Character> stack = new Stack<Character>();
		for(int i=0; i<s.length(); i++){
			Character c = s.charAt(i);
			/* looks for respective pair of parenthesis in
			   stack and pop if found otherwise return false
			   since we pop the closest pairs, we should get 
			   the same pairing parentheses when we check on top */
			if(c == ')'){
				if(stack.peek()!='(')
					return false;
				else
					stack.pop();
			}
			else if(c == ']'){
				if(stack.peek()!='[')
					return false;
				else
					stack.pop();
			}
			else if(c == '}'){
				if(stack.peek()!='{')
					return false;
				else
					stack.pop();
			}
			// push if its open parentheses
			else if(c == '(' || c == '[' || c == '{')
				stack.push(c);
		}
		// should be empty for balanced sets of parentheses
		if(!stack.isEmpty())
			return false;
		return true;
	}
}
