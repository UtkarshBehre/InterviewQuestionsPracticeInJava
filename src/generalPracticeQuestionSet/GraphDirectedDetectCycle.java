package generalPracticeQuestionSet;

import java.util.LinkedList;

/**
 * Detect Cycle in a Directed Graph.
 * Given a directed graph, check whether the graph contains a cycle or not. 
 * Your function should return true if the given graph contains at least one 
 * cycle, else return false. For example, the following graph contains three 
 * cycles 0->2->0, 0->1->2->0 and 3->3, so your function must return true.
 * Runtime: O(V+E)
 * @author Utkarsh
 *
 */
public class GraphDirectedDetectCycle {
	static class Graph{
		int V;
		LinkedList<Integer>[] adjVList;
		
		public Graph(int v) {
			this.V = v;
			adjVList = new LinkedList[v];
			for(int i =0; i<v; i++)
				adjVList[i] = new LinkedList<Integer>();
		}
		
		public void addEdge(int src, int dest) {
			adjVList[src].add(dest);
		}
		
		/**
		 * detects cycle using DFS traversal with extra recursion stack to keep track
		 * of the vertices visited already. DFS done on all vertices
		 * Runtime: O(V+E)
		 */
		public boolean detectCycle() {
			boolean[] visited = new boolean[V];
			boolean[] recStack = new boolean[V];
			// start dfs for all nodes
			for(int i = 0; i<V; i++) {
				if(DFSRec(i, visited, recStack))
					return true;
			}
			return false;
		}
		
		/**
		 * depth first traversal to find loops using recursion stack
		 * Runtime: O(V+E)
		 * @param vertex = current vertex
		 * @param visited = keeps track of all visited vertices, used to avoid repeat checks
		 * @param recStack = keeps track of ancestor vertices to check loop
		 */
		public boolean DFSRec(int vertex, boolean[] visited, boolean[] recStack) {
			if(recStack[vertex]) {
				return true;
			}
			// this is required for optimization for already visited nodes 
			// for whom cycle wasn't found in past
			if(visited[vertex])
				return false;
			visited[vertex] = true;
			recStack[vertex] = true;
			for(int v: adjVList[vertex]) 
					if(DFSRec(v, visited, recStack))
						return true;
			// reset recStack for vertex before leaving method
			recStack[vertex] = false;
			return false;
		}
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph(5);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		//graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 3);
		System.out.print("Is Cyclic? "+graph.detectCycle());
	}
}
