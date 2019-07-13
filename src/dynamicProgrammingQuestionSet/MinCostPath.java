package dynamicProgrammingQuestionSet;

/**
 * Min Cost Path 
 * Given a cost matrix cost[][] and a position (m, n) in cost[][], write a function 
 * that returns cost of minimum cost path to reach (m, n) from (0, 0). Each cell of 
 * the matrix represents a cost to traverse through that cell. Total cost of a path 
 * to reach (m, n) is sum of all the costs on that path (including both source and destination)
 * https://www.geeksforgeeks.org/min-cost-path-dp-6/
 * Runtime: O(mn)
 * @author Utkarsh
 *
 */
public class MinCostPath {
	
	/**
	 * min path bottom up solution
	 * setting starts from 1,1 on minMatrix instead of 0,0 to avoid out of index, we set max
	 * int values on first row and column in min matrix so that 1,1 is always taken
	 * which is 0,0 of the given matrix
	 * Runtime: O(mn)
	 */
	public static void printMinCostPath(int[][] mat, int m, int n) {
		int[][] minMat = new int[mat.length+1][mat[0].length+1];
		for(int i = 0;i<=m+1;i++) {
			minMat[i][0] = Integer.MAX_VALUE;
		}
		for(int i = 0; i<=n+1;i++) {
			minMat[0][i] = Integer.MAX_VALUE;
		}
		for(int i = 1;i<=m+1; i++) {
			for(int j = 1; j<=n+1; j++) {
				// if its first element no need to check prev path
				if(i==1 && j==1)
					minMat[i][j] = mat[i-1][j-1];
				else 
					minMat[i][j] = mat[i-1][j-1] + Math.min(minMat[i][j-1], Math.min(minMat[i-1][j], minMat[i-1][j-1]));				
			}
		}
		System.out.println("Min cost to reach: " + minMat[m+1][n+1]);
	}
	
	/**
	 * min path recursive memoization solution
	 * similar to bottom up, except we start from end point here and trace path
	 * back to 0,0
	 * Runtime: O(mn)
	 */
	public static int minCostPathRec(int[][] mat, int m, int n, int[][] mem) {
		int cost = 0;
		if(m<0 || n<0)
			return Integer.MAX_VALUE;
		
		if(mem[m][n]!=0)
			return mem[m][n];
		else if(m==0&& n==0) {
			cost = mat[m][n];
		}
		else {
			cost = mat[m][n] + Math.min(minCostPathRec(mat, m, n-1, mem), 
					Math.min(minCostPathRec(mat, m-1, n, mem), minCostPathRec(mat, m-1, n-1, mem)));
		}
		mem[m][n] = cost;
		return cost;
	}
	
	public static void main(String[] args) {
		int[][] mat = {{1,2,3},{4,8,2},{1,5,3}};
		printMinCostPath(mat, 2, 2);
		System.out.println(minCostPathRec(mat, 2,2, new int[3][3]));
	}
}
