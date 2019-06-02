package generalPracticeQuestionSet;

import java.util.LinkedList;

/**
 * Detect cycle in an undirected graph
 * Assumption: there are no parallel edges between any two vertices
 * Runtime: O(V+E)
 * @author Utkarsh
 *
 */
public class GraphUndirectedDetectCycle {
	static class Graph{
		int V;
		LinkedList<Integer>[] adjVList;
		
		@SuppressWarnings("unchecked")
		public Graph(int v) {
			this.V = v;
			adjVList = new LinkedList[v];
			for(int i =0; i<v; i++)
				adjVList[i] = new LinkedList<Integer>();
		}
		
		public void addEdge(int src, int dest) {
			adjVList[src].add(dest);
			adjVList[dest].add(src);
		}
		
		/**
		 * detects cycle using DFS traversal with extra recursion stack to keep track
		 * of the vertices visited already and last/parent vertex. DFS done on all 
		 * vertices.
		 * Runtime: O(V+E)
		 */
		public boolean isCyclic() {
			boolean[] visited = new boolean[V];
			boolean[] recStack = new boolean[V];
			for(int i = 0; i<V; i++)
				if(DFSRec(i, visited, recStack, -1))
					return true;
			return false;
		}
		
		/**
		 * Recursive DFS traversal to detect loop and return true if found
		 * RUNTIME: O(V+E)
		 * @param vertex = current vertex
		 * @param visited = keeps track of all visited vertices, used to avoid 
		 * repeat checks
		 * @param recStack = keeps track of ancestor vertices to check loop
		 * @param last = parent / vertex we came from
		 */
		public boolean DFSRec(int vertex, boolean[] visited, boolean[] recStack, int last) {
			if(recStack[vertex]) {
				return true;
			}
			// this is required for optimization for already visited nodes 
			// for whom cycle wasn't found in past
			if(visited[vertex]) {
				return false;
			}
			visited[vertex] = true;
			recStack[vertex] = true;
			for(int v : adjVList[vertex])
				if(v!=last && DFSRec(v, visited, recStack, vertex))
					return true;
			// reset recStack for vertex before leaving method
			recStack[vertex] = false;
			return false;
		}
	}
	
	// tester
	public static void main(String[] args) {
        Graph g1 = new Graph(5); 
        g1.addEdge(1, 0); 
        g1.addEdge(0, 2);
        g1.addEdge(2, 1); 
        g1.addEdge(0, 3); 
        g1.addEdge(3, 4); 
        if (g1.isCyclic()) 
            System.out.println("Graph contains cycle"); 
        else
            System.out.println("Graph doesn't contains cycle"); 
  
        Graph g2 = new Graph(3); 
        g2.addEdge(0, 1); 
        g2.addEdge(1, 2); 
        if (g2.isCyclic()) 
            System.out.println("Graph contains cycle"); 
        else
            System.out.println("Graph doesn't contains cycle"); 
	}
}
