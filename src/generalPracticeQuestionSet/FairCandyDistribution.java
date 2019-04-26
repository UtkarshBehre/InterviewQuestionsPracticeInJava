package generalPracticeQuestionSet;

import java.util.*;
/**
 * Find maximum children to which n candies can be distributed
 * make sure no 2 children get same number of candies
 * Sample Input 8 | Output 1, 2, 5  | Input 5 | Output 1,4
 * @author Utkarsh
 *
 */
public class FairCandyDistribution {
	public static void main(String[] args){
		int n = 10000;
		printOutputCandiesArray(n);
	}
	
	/**
	 * prints the array of candies representing the distribution from lowest 
	 * to highest number
	 * @param n
	 */
	static void printOutputCandiesArray(int n){
		List<Integer> candyList = new ArrayList<Integer>();
		int candy = 0;
		int candyTotal = 0;
		while(candyTotal + ++candy <= n){
			candyList.add(candy);
			candyTotal += candy;
		}
		int lastIndex = candyList.size()-1;
		candyList.set(lastIndex, (candyList.get(lastIndex)+n - candyTotal));
		for(Integer c : candyList){
			System.out.print(c+" ");
		}
	}
}
