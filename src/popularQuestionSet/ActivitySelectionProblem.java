package popularQuestionSet;

import java.util.*;

/**
 * You are given n activities with their start and finish times. 
 * Select the maximum number of activities that can be performed 
 * by a single person, assuming that a person can only work on a 
 * single activity at a time.
 * Sample Input:
 *     	start[]  =  {10, 12, 20};
 *     	finish[] =  {20, 25, 30};
 * Sample Output: 0, 2 [ These are indexes in start[] and finish[] ]
 * Sample Input:
 *     	start[]  =  {1, 3, 0, 5, 8, 5};
 *  	finish[] =  {2, 4, 6, 7, 9, 9};
 * Sample Output: 0, 1, 3, 4
 * @author Utkarsh
 */
public class ActivitySelectionProblem {
	public static void main(String[] args){
		int[] start = {1, 3, 4, 5, 8, 5, 7, 9, 11, 23, 13};
		int[] finish = {4, 5, 5, 7, 9, 9, 10, 11, 12, 24, 25};
		// if finish was not given in sorted, then we can easily sort both arrays
		// according to finish array and then do the same as below
		List<Integer> acts = new ArrayList<Integer>();
		int activityCount = countMaxActivitiesPossible(start, finish, 0, acts);
		printSolutions(activityCount,acts);
		System.out.println();
		printMaxActivities(start,finish, start.length);
	}
	
	/**
	 * Brute Force approach
	 * Gives optimal solution for maximum activities possible
	 * runtime: exponential without dynamic programming but O(n) if dynamic programming used 
	 * @param start = start times of activities
	 * @param end = end times of activities
	 * @param current
	 * @return
	 */
	static int countMaxActivitiesPossible(int[] start, int[] end, int current, List<Integer> acts){
		if(current == start.length)
			return 0;
		int temp = end[current];
		int k = current;
		while(k<start.length && start[k] < temp){
			k++;
		}
		int count1 = countMaxActivitiesPossible(start,end,current+1, acts);
		int count2 = countMaxActivitiesPossible(start,end,k,acts)+1;
		if(count1 > count2){
			int i = acts.size()-1;
			while(--i>=0 && count2 > 0){
				acts.remove(i);
				count2--;
			}
			return count1;
		}
		else{
			int i = acts.size()-count2;
			while(--i>=0){
				acts.remove(i);
			}
			acts.add(current);
			return count2;
		}
	}
	
	public static void printSolutions(int count, List<Integer> acts){
		int i = acts.size();
		System.out.print("The maximum number of activities that can be executed is "+count+", which are ");
		while(count >0 && --i>=0){
			System.out.print(acts.get(i)+" ");
			count--;
		}
	}
	
	/**
	 * Greedy Approach
	 * takes whatever activity it can from the start until it can't take
	 * anymore. So this always takes the first one.
	 * 
	 * Runtime:O(n) if sorted | O(nlogn) if not sorted
	 *  
	 * @param start = array with start time of activities
	 * @param finish = array with finish time of activities
	 * @param n = number of activities
	 */
	public static void printMaxActivities(int start[], int finish[], int n) 
	{ 
		int i, j; 
		System.out.print("Following activities are selected : "); 

		// The first activity always gets selected 
		i = 0; 
		System.out.print(i+" "); 

		// Consider rest of the activities 
		for (j = 1; j < n; j++){ 
			// If this activity has start time greater than or 
			// equal to the finish time of previously selected 
			// activity, then select it 
			if (start[j] >= finish[i]) 
			{ 
				System.out.print(j+" ");
				i = j; // reset i as we selected the element
			} 
		} 
	} 
}
