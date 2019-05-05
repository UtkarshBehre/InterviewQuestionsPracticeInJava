package generalPracticeQuestionSet;

import java.util.Stack;

public class StackInfixToPostfix {
	// tester
	public static void main(String[] args){
		String inFix = "a+b*(c^d-e)^(f+g*h)-i";
		System.out.println("Final Postfix expression: " +infixToPostfixTry(inFix));
	}

	/**
	 * converts given infix expression to postfix expression 
	 * @param exp = given infix expression
	 * @return = converted postfix expression
	 */
	static String infixToPostfixTry(String exp){
		String res = "";
		Stack<Character> stack = new Stack<>();
		System.out.println("Symbol \t Stack \t Postfix");
		for(int i = 0; i < exp.length(); i++){
			Character c = exp.charAt(i);
			// put to result if its character
			if(Character.isLetterOrDigit(c)){
				res += c;
			} // if ( directly push to stack
			else if(c=='('){
				stack.push(c);
			} // if ) pop  the stack and put to res until ( and pop (
			else if(c==')'){
				while(stack.peek()!='('){
					res+= stack.pop();
				}
				stack.pop();
			} 
			// if operator pop stack if higher or equal precedence found
			// then push the operand to stack
			else{
				while(!stack.empty() && prec(stack.peek()) >= prec(c)){
					res+= stack.pop();
				}
				stack.push(c);
			}
			// print below to understand each step
			System.out.println(c +"\t"+stack+"\t\t"+res);
		}
		// at the end empty all stack onto the res
		while(!stack.isEmpty())
			res+= stack.pop();
		System.out.println();
		// return the postfix result string
		return res;
	}
	
	/**
	 * converts operator to its precedence value
	 * higher value for higher precedence
	 * @param ch = given operand
	 * @return precedence equivalent number
	 */
	static int prec(char ch) 
	{ 
		switch (ch) { 
		case '+': 
		case '-': 
			return 1; 

		case '*': 
		case '/': 
			return 2; 

		case '^': 
			return 3;
		default:
			return -1;
		}
	} 
}
