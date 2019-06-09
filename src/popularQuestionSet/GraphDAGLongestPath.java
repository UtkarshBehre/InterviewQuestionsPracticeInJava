package popularQuestionSet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Longest Path in a Directed Acyclic Graph.
 * Given a Weighted Directed Acyclic Graph (DAG) and a source vertex s in it, 
 * find the longest distances from s to all other vertices in the given graph.
 * Runtime: O(V+E)
 * @author Utkarsh
 */
public class GraphDAGLongestPath {
	static class Graph{
		int V;
		LinkedList<AdjNode>[] adjNodes;
		
		static class AdjNode {
			int adjV;
			int w;
			public AdjNode(int v, int weight) {
				this.adjV = v;
				this.w = weight;
			}
		}
		
		@SuppressWarnings("unchecked")
		public Graph(int v) {
			V = v;
			adjNodes = new LinkedList[V];
			for(int i=0; i<V; i++)
				adjNodes[i] = new LinkedList<AdjNode>();
		}
		
		/**
		 * directed graph, so adding edge only on src.
		 * weight tells its distance from main adjacent vertex
		 */
		public void addEdge(int src, int dest, int weight) {
			AdjNode adjNode = new AdjNode(dest, weight);
			adjNodes[src].add(adjNode);
		}
		
		/**
		 * step 1: get stack of vertices in topological order.
		 * step 2: initilize all distances as min possible but source as 0.
		 * step 3: Then do following for each popped vertex:
		 * update distance of each adjacent node for the popped vertex.
		 * Add the popped vertex's path till now if it was considered 
		 * and add the vertex itself as well as it was considered.
		 * step 4: Now find the farthest vertex and print its path
		 * Runtime: O(V+E)
		 * @param src = given starting vertex
		 */
		public void printLongestPath(int src) {
			Stack<Integer> stack = topologicalSort(src);
			// array dist used to store max distance of each vertex from src
			int[] dist = new int[V];
			for(int i = 0; i < V; i++) {
				dist[i] = Integer.MIN_VALUE;
			}
			dist[src] = 0;
			
			// paths hold list of vertices as path for each vertex
			@SuppressWarnings("unchecked")
			ArrayList<Integer>[] paths = new ArrayList[V];
			for(int i = 0; i<V; i++)
				paths[i] = new ArrayList<Integer>();
			
			// step 3
			while(!stack.isEmpty()) {
				int v = stack.pop();
				for(AdjNode adjNode: adjNodes[v]) {
					if(dist[adjNode.adjV] > dist[v] + adjNode.w)
						dist[adjNode.adjV] = dist[adjNode.adjV];
					else {
						dist[adjNode.adjV] = (dist[v] + adjNode.w);
						// avoid duplicate vertex addition to path
						for(int p : paths[v])
							if(!paths[adjNode.adjV].contains(p))
								paths[adjNode.adjV].add(p);
						if(!paths[adjNode.adjV].contains(v))
							paths[adjNode.adjV].add(v);
					}
				}
			}
			// find farthest vertex
			int max = Integer.MIN_VALUE;
			int longestV = 0;
			for(int i = 0; i<V; i++) {
				if(dist[i]>max)
					longestV = i;
			}
			// print the longest path info
			System.out.println("Longest path's distance: "+ dist[longestV]);
			System.out.print("Longest path: ");
			for(int v: paths[longestV])
				System.out.print(v+" ");
			System.out.print(longestV);
		}
		
		/**
		 * sorts the graph in topological order so that for any edge uv u is
		 * always on left of v. Uses DFS to find this order, pushes a vertex
		 * in the result stack once all of its adjacent vertices are visited.
		 * Runtime: O(V+E)
		 * @param src = given starting vertex
		 */
		public Stack<Integer> topologicalSort(int src) {
			boolean[] visited = new boolean[V];
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(src);
			if(!visited[stack.peek()])
				topologicalSortRec(src, visited, stack);
			return stack;
		}
		
		/**
		 * recursive helper method to do topological sorting
		 * Runtime: O(V+E)
		 */
		public void topologicalSortRec(int s, boolean[] visited, Stack<Integer> stack ) {
			visited[s] = true;
			for(AdjNode adjNode : adjNodes[s]) {
				if(!visited[adjNode.adjV]) {
					topologicalSortRec(adjNode.adjV, visited, stack);
					stack.push(adjNode.adjV);
				}
			}
			stack.push(s);
		}
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0, 1, 5); 
		g.addEdge(0, 2, 3); 
		g.addEdge(1, 3, 6); 
		g.addEdge(1, 2, 2); 
		g.addEdge(2, 4, 4); 
		g.addEdge(2, 5, 2); 
		g.addEdge(2, 3, 7); 
		g.addEdge(3, 5, 1); 
		g.addEdge(3, 4, -1); 
		g.addEdge(4, 5, -2);
		int src = 1;
		g.printLongestPath(src);
	}
}
