package dynamicProgrammingQuestionSet;

/**
 *	 Coin Change
 * Given a value N, if we want to make change for N cents, and we have infinite 
 * supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we 
 * make the change? The order of coins doesn’t matter.
 * For example, for N = 4 and S = {1,2,3}, there are four solutions: 
 * {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4. 
 * For N = 10 and S = {2, 5, 3, 6}, there are five solutions: 
 * {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. 
 * So the output should be 5.
 * Spacetime: O(n)
 * Runtime: O(mn) 
 * @author Utkarsh
 *
 */
public class CoinChange {
	static int k = 0;
	
	/**
	 * finds the number of ways to change coins using more space
	 * typical 2d array to keep track of previously computed number of ways
	 * Spacetime: O(mn)
	 * Runtime: O(mn)
	 */
	public static int waysToChangeCoins(int N, int[] coins) {
		int[][] mem = new int[coins.length+1][N+1];
		for(int i = 0; i<=coins.length; i++) {
			mem[i][0] = 1;
		}
		for(int i=1; i<=coins.length; i++) {
			for(int j=1; j<=N; j++) {
				if(coins[i-1]> j)
					mem[i][j] = mem[i-1][j];
				else {
					mem[i][j] = mem[i][j-coins[i-1]] + mem[i-1][j];
					//System.out.println(i+":"+j+" = "+i+":"+(j-coins[i-1])+" + "+(i-1)+":"+j);
					//print(mem);
				}
			}
		}
		return mem[coins.length][N];
	}
	
	/**
	 * finds the number of ways to change coins using less space
	 * makes use of the face that array has the last updated value at current
	 * index
	 * Spacetime: O(n)
	 * Runtime: O(MN)
	 */
	public static int waysToChangeCoinsSpace(int N, int[] coins) {
		int[] mem = new int[N+1];
		mem[0] = 1;
		for(int i=0; i<coins.length; i++) {
			for(int j=1; j<=N; j++) {
				if(coins[i] <= j)
					mem[j] = mem[j] + mem[j-coins[i]];
			}
		}
		return mem[N];
	}
	
	/**
	 * wrote this for debug purpose
	 */
	public static void print(int[][] arr) {
		for(int i = 0; i<arr.length; i++) {
			for(int j = 0; j<arr[0].length; j++)
				System.out.print(arr[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int N = 4;
		int[] S = {1,2,3};
		System.out.println("No. of ways using O(mn) space: "+waysToChangeCoins(N, S));
		System.out.println("No. of ways using O(n) space: "+ waysToChangeCoins(N, S));
		N = 10;
		int[] S2 = {2, 5, 3, 6};
		System.out.println("No. of ways using O(mn) space: "+waysToChangeCoins(N, S2));
		System.out.println("No. of ways using O(n) space: "+ waysToChangeCoins(N, S2));
	}
}
