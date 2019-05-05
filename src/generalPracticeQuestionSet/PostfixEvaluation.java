package generalPracticeQuestionSet;

import java.util.Stack;

public class PostfixEvaluation {
	// tester
	public static void main(String[] args){
		String postfixExp = "231*+9-";
		String postfixExpNum = "100 200 + 2 / 5 * 7 +";
		System.out.println("Final Answer: "+evaluatePostfixExp(postfixExp));
		System.out.println("Final Answer of num: "+evaluatePostfixExpInt(postfixExpNum));
	}
	
	/**
	 * evaluates the postfix exp to get final integer
	 * can extracts only digits from exp
	 * O(n)
	 * @param exp = given postfix expression
	 * @return = evaluated integer
	 */
	static int evaluatePostfixExp(String exp){
		Stack<Integer> stack = new Stack<Integer>();
		System.out.println("Symbol \t Stack ");
		for(int i = 0; i <exp.length(); i++){
			Character c = exp.charAt(i);
			// if char is a digit we push it to stack
			if(Character.isDigit(c)){
				String str = "";
				str+=c;
				stack.push(c-'0');
			}
			// if char is operator we pop 2 digits from stack
			// operate using operator and push back the result
			else{
				int b = stack.pop();
				int a = stack.pop();
				stack.push(evaluate(a,b,c));
			}
			// this is to understand each step 
			System.out.println(c + "\t" + stack); 
		}
		return stack.pop();
	}
	
	/**
	 * evaluates the postfix exp to get final integer
	 * can extracts numbers from exp works when space given
	 * between each number or operator
	 * O(n)
	 * @param exp = given postfix expression
	 * @return = evaluated integer
	 */
	static int evaluatePostfixExpInt(String exp){
		Stack<Integer> stack = new Stack<Integer>();
		System.out.println("Symbol \t Stack ");
		for(int i = 0; i<exp.length(); i++){
			Character c = exp.charAt(i);
			// if space found just continue that iteration
			if(c == ' ')
				continue;
			// if digit found extract full number
			else if(Character.isDigit(c)){
				int num = 0;
				// extract full number until space
				while(Character.isDigit(c)){
					num = num*10 + (int) (c-'0');
					i++;
					c = exp.charAt(i);
				}
				// reset to space character
				i--;
				// push the extracted number
				stack.push(num);
			}
			// evaluate top 2 numbers with the operator encountered
			else {
				int b = stack.pop();
				int a = stack.pop();
				stack.push(evaluate(a,b,c));
			}
			// print for understanding each step
			System.out.println(c+"\t"+ stack);
		}
		return stack.pop();
	}
	
	// evaluate based on the operator found
	static int evaluate(int a, int b, char c){
		switch(c) {
			case '+':
				return a+b;
			case '-':
				return a-b;
			case '*':
				return a*b;
			case '/':
				return a/b;
			case '^':
				return (int) Math.pow((double) a, (double)b);
			default:
				return 0;
		}
	}
}
