package dynamicProgrammingQuestionSet;

/**
 *  Egg Dropping Puzzle
 * we are given number of floors and number of eggs available. We need to
 * find minimum number of trials needed to find the minimum floor from which the 
 * egg breaks when thrown.
 * for detailed question: https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
 * Runtime: O(en^2), where e is number of eggs given and n is number of floors
 * @author Utkarsh
 */
public class EggDroppingProblem {
	
	/**
	 * to figure out min drops required on a given number of e eggs and n floors,
	 * key subproblem is if we drop an egg from x floor, and it breaks then we have
	 * to check x-1 floors with e-1 eggs, but if it didn't break then we have to
	 * check n-x floors with e(same ammount of) eggs.
	 * base cases are when we have only 1 floor then we need atleast 1 try even with
	 * e eggs, and when we have n floors and only 1 egg then we need n tries.
	 * In below bottom up approach, first we prepopulate the base case values.
	 * Then for the rest, first we have to have double loop for floor and egg starting from
	 * 2 and 2 as 1 cases are already done. 
	 * Now inside the 2nd loop we start testing all scenarios as if we started dropping eggs
	 * from each floor from 1 to f, each of which will pick results from previously found
	 * answer using the key subproblem mentioned above
	 * Note: the values represent the min number of drops required and not the floor number
	 * 
	 * Runtime: O(en^2)
	 * Spacetime: O(en)
	 */
	public static void minDropsReq(int floors, int eggs) {
		int[][] D = new int[floors+1][eggs+1];
		for(int i = 0; i<=eggs; i++)
			D[1][i] = 1;
		for(int i = 0; i<= floors; i++)
			D[i][1] = i;
		for(int f = 2; f<=floors; f++) {
			for(int e = 2; e<=eggs; e++) {
				int min = Integer.MAX_VALUE;
				for(int i = 1; i<=f; i++) {
					// 1+ is to count the current drop, 
					// max part is to pick the max drops done
					// min part is to find the minimum route amongst different initial i drop
					// points
					min = Math.min(min, 1 + Math.max(D[i-1][e-1], D[f-i][e]));
				}
				D[f][e] = min;
			}
		}
		System.out.println("Min Drops Required: " + D[floors][eggs]);
	}
	
	/**
	 * 	solution prints floors along with min drops required
	 * Modified version of above solution which keeps track of floor number to drop
	 * egg from for any number of floors remaining. This is achieved by expanding the min and 
	 * max part done on previous solution and keeping track of the floor on which egg was dropped
	 * firstly for the maxed part and finally for the min part for each floor.
	 * We then use this array to print the floors from which we need to drop the eggs.
	 *  
	 * Runtime: O(en^2)
	 * Spacetime: O(en)
	 */
	public static void minDropsReqWithFloors(int floors, int eggs) {
		int[][] D = new int[floors+1][eggs+1];
		for(int i = 0; i<=eggs; i++)
			D[1][i] = 1;
		for(int i = 0; i<= floors; i++)
			D[i][1] = i;
		int[] minF = new int[floors+1];
		minF[1] = 1;
		for(int f = 2; f<=floors; f++) {
			for(int e = 2; e<=eggs; e++) {
				int min = Integer.MAX_VALUE;
				for(int i = 1; i<=f; i++) {
					int max = 0; 
					int curF = 0;
					if(D[i-1][e-1]>D[f-i][e]) {
						max = D[i-1][e-1];
						curF = i-1;
					} else {
						max = D[f-i][e];
						curF = f-i; 
					}
					if(min > (1+ max)){
						min = 1 + max;
						minF[f] = curF;
					}
				}
				D[f][e] = min;
			}
		}
		System.out.println("Min Drops Required: " + D[floors][eggs]);
		printDropFloors(minF);
	}

	/**
	 * below method just takes the floors to egg drop floor map, where index is
	 * the floor number and relative value is the floor egg can be dropped for that
	 * to get minimum drops overall solution.
	 * We keep track of lower and upper floor bounds along with floors left to end the
	 * while loop. We get the current floor according to the floors left after each iteration
	 * and update the trackers.
	 * 
	 * Runtime: O(n)
	 */
	public static void printDropFloors(int[] minF) {
		int floorsLeft = 36;
		int lastFU = 36;
		int lastFL = 0;
		int curF = 0;
		System.out.print("Eggs can be dropped on following floors: ");
		while(floorsLeft>0) {
			curF = lastFL + minF[floorsLeft];
			System.out.print(curF+" ");
			if(lastFU-curF > curF-lastFL)
				lastFL = curF;
			else 
				lastFU = curF;
			floorsLeft = lastFU-lastFL-1;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int floors = 36;
		int eggs = 2;
		minDropsReq(floors,eggs);
		minDropsReqWithFloors(floors, eggs);
	}
}
