package popularQuestionSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Snake and Ladder Problem
 * Given a snake and ladder board, find the minimum number of dice throws required 
 * to reach the destination or last cell from source or 1st cell. Also find what rolls
 * were considered in minimum number of dice throws to reach the destination.
 * Runtime: O(n), where n is number of cells in the board
 * @author Utkarsh
 *
 */
public class GraphSnakeAndLadder {
	static class Graph{
		int V;
		LinkedList<Integer>[] adjVList;
		
		@SuppressWarnings("unchecked")
		public Graph(int v) {
			this.V = v;
			adjVList = new LinkedList[V];
			for(int i = 0; i<V; i++)
				adjVList[i] = new LinkedList<Integer>();
		}
		
		public void addEdge(int src, int dest) {
			adjVList[src].add(dest);
		}
		
		/**
		 * find the minimum number of times one has to throw a dice to reach final
		 * cell in the snake and ladder. prints the result at the end
		 * considers all cells as vertices, and snakes and ladders as edges.
		 * Does BFS traversal to figure out best path for each cell so far and 
		 * updates the same.
		 * At the end we have the rolls of dice that one can throw to reach any cell
		 * in the maze so that it is the least number of throws. So we have answer in
		 * the last cell's rolls list
		 * Runtime: O(n), where n is the number of cells in the board
		 * @return
		 */
		public void findAndPrintMinDiceRolls() {
			@SuppressWarnings("unchecked")
			//rolls is array of lists to hold rolls made for a vertex 
			// represented by index
			List<Integer>[] rolls = new ArrayList[V];
			LinkedList<Integer> queue = new LinkedList<Integer>();
			boolean[] visited = new boolean[V];
			int[] dist = new int[31]; 
			
			for(int i=0; i<V; i++) {
				rolls[i] = new ArrayList<Integer>();
			}
			
			// start from cell 1
			int cur = 1;			
			queue.add(cur);
			dist[cur] = 0;
			
			// for DEBUGGING time complexity
			boolean check = true;
			int counter = 0;
			
			// do breadth first search from cell 1 till 30
			while(!queue.isEmpty()) {
				cur = queue.poll();
				visited[cur] = true;
				for(int i = 1; i<7; i++) {
					if(cur+i >30 || visited[cur+i])
						continue;
					finalDest(cur, i, dist, visited, rolls);
					
					// for DEBUGGING time complexity
					if(check) {
						counter ++;
						check = false;
					}
					
					// second condition of if reduces time by a lot
					// it avoid repeat checks on cells by checking among only 5
					if(!visited[cur+i] && !queue.contains(cur+i))
						queue.add(cur+i);
				}
				check = true;
			}
			System.out.println("DEBUG: Runtime Check: "+counter);
			System.out.println("Minimum number of dice throws required: "+dist[30]);
			System.out.print("One possible best dice throws: ");
			System.out.print(rolls[30]);
		}
		
		/**
		 * helper method to find update destination cell's rolls
		 * Runtime: O(1)
		 */
		public void finalDest(int current, int roll, int[] dist, boolean[] visited, List<Integer>[] rolls) {
			int next = current + roll;
			// normal roll destination update
			finalDestUtil(current, next, roll, dist, rolls);
			if(next> 30)
				return; // non existing cell
			if(!adjVList[next].isEmpty()) {
				next = adjVList[next].getFirst();
				// after roll next cell after ladder or snake update
				finalDestUtil(current, next, roll, dist, rolls);
			}
		}
		
		/**
		 * utility method to help finalDest method
		 * Runtime:O(1)
		 */
		public void finalDestUtil(int current, int next, int roll, int[] dist, List<Integer>[] rolls) {
			// case of snake
			if(dist[next] != 0 && dist[next] < dist[current] + 1) {
				dist[next] = dist[next];
			}
			// case of ladder
			else {
				dist[next] = dist[current]+1;
				rolls[next].clear();
				rolls[next].addAll(rolls[current]);
				rolls[next].add(roll);
			}
		}
	}
	
	// tester
	public static void main(String[] args) {
		Graph g = new Graph(31); // 31 to take 1st as cell 1
		g.addEdge(3, 22);
		g.addEdge(5, 8);
		g.addEdge(9, 30); // toggle this edge to test
		g.addEdge(11, 26);
		g.addEdge(17, 4);
		g.addEdge(19, 7);
		g.addEdge(20, 29);
		g.addEdge(21, 9);
		g.addEdge(27, 1);
		g.findAndPrintMinDiceRolls();
	}
}
