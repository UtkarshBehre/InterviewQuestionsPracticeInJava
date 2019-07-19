package dynamicProgrammingQuestionSet;

/**
 * Floyd Warshall Algorithm
 * The Floyd Warshall Algorithm is for solving the All Pairs Shortest Path problem. 
 * The problem is to find shortest distances between every pair of vertices in a 
 * given edge weighted directed Graph.
 Input:
       graph[][] = {{0,  5,  -1,  10},
                    {-1,  0,   3,  -1},
                    {-1, -1,   0,   1},
                    {-1, -1,  -1,   0}}
which represents the following graph
             10
       (0)------->(3)
        |         /|\
      5 |          |
        |          | 1
       \|/         |
       (1)------->(2)
            3       
Note that the value of graph[i][j] is 0 if i is equal to j 
And graph[i][j] is -1 if there is no edge from vertex i to j.

Output:
Shortest distance matrix
     0     5      8      9
    -1     0      3      4
    -1    -1      0      1
    -1    -1     -1      0
 * 
 * Runtime: O(n^3)
 * @author Utkarsh
 */
public class FloydWarshallAlgo {
	
	/**
	 * bottom up dynamic solution 
	 * first, we first initialize shortest distance matrix using the given matrix
	 * then (as immidiate vertices dist is given)we start finding the shortest 
	 * distance from 0 to 2 then 1 to 3 and so on diagonally until last column
	 * at this point reset our points as 0 to 3 then 1 to 4, 2 to 5 and so on.
	 * Runtime: O(V^3)
	 */
	public static void findShortestDistances(int[][] graph) {
		int n = graph.length;
		int[][] SDM = graph;
		for(int i = 0; i<n; i++) 
			for(int j = 0; j<n; j++) 
				SDM[i][j] = graph[i][j];
		
		int i = 0;
		int dist = 2; // to set col respective of row to start from and go diagonally
		int row = i;
		int col = i+dist;
		while(row<n && col<n) 
		{	
			int minD = Integer.MAX_VALUE;
			// to find best path in 0 to k and k to the vertex
			// eg. this would find min of paths (0->0, 0->3), (0 -> 1, 1-> 3) (0->2, 2->3)
			for(int k = 0; k<col; k++) 
				minD = Math.min(minD, (SDM[0][k]+SDM[k][col]>0?SDM[0][k]+SDM[k][col]:Integer.MAX_VALUE));
			SDM[row][col] = minD;
			if(col==n-1){ // reset to row 0 next column (counts as outermost V iteration
				dist++;
				row = i;
				col = row+dist;						
			} else { // go bottom right as everything on left and bottom of it is filled
				row++;
				col++;
			}
		}
		System.out.println("Shortest distance matrix: ");
		printMatrix(SDM);
	}
	
	/**
	 *  prints a given 2d matrix
	 */
	public static void printMatrix(int[][] M) {
		for(int i = 0; i<M.length; i++) {
			for(int j = 0; j<M[0].length; j++) {
				System.out.print(M[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	// tester
	public static void main(String[] args) {
		int[][] graph = {{0,5,-1,10}, {-1,0,3,-1}, {-1,-1,0,1}, {-1,-1,-1,0}};
		System.out.println("Given distance matrix: ");
		printMatrix(graph);
		findShortestDistances(graph);
	}
}
