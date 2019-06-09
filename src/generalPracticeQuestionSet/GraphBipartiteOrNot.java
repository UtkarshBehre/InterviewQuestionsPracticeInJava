package generalPracticeQuestionSet;

import java.util.LinkedList;

/**
 * Check whether a given graph is Bipartite or not.
 * A Bipartite Graph is a graph whose vertices can be divided into two independent 
 * sets, U and V such that every edge (u, v) either connects a vertex from U to V 
 * or a vertex from V to U. In other words, for every edge (u, v), either u belongs 
 * to U and v to V, or u belongs to V and v to U. We can also say that there is no 
 * edge that connects vertices of same set.
 * Runtime: O(V+E)
 * @author Utkarsh
 */
public class GraphBipartiteOrNot {
	static class Graph{
		int V;
		LinkedList<Integer>[] adjVertices;
		
		@SuppressWarnings("unchecked")
		public Graph(int v) {
			V = v;
			adjVertices = new LinkedList[v];
			for(int i = 0; i<v; i++) {
				adjVertices[i] = new LinkedList<Integer>();
			}
		}
		
		public void addEdge(int src, int dest) {
			adjVertices[src].add(dest);
			adjVertices[dest].add(src);
		}
		
		/**
		 * colors the vertices with only 2 colors and at any point if 2
		 * adjacent vertices end up having same color graph is not bipartite.
		 * Use int 1 and 2 to represent 2 colors.
		 * if a vertex is not visited it'll have 0 in color array.
		 * Runtime: O(V+E)
		 */
		public boolean isBipartiteGraph() {
			int[] colors = new int[V];
			
			LinkedList<Integer> queue = new LinkedList<Integer>();
			for(int i = 0; i<V; i++) {
				if(!(colors[i]==0))
					continue;
				colors[i] = 1;
				queue.add(i);
				
				while(!queue.isEmpty()) {

					int v = queue.poll();
					
					for(int adjV : adjVertices[v]) {
						if(colors[v] == colors[adjV])
							return false;
						
						if(colors[adjV] == 0) {
							colors[adjV] = colors[v] == 1?2:1;
							queue.add(adjV);
						}
					}
				}
			}
			return true;
		}
		
		// tester
		public static void main(String[] args) {
			Graph g = new Graph(6);
			g.addEdge(0, 1);
			g.addEdge(0, 2);
			g.addEdge(1, 3);
			g.addEdge(2, 3);
			g.addEdge(4, 5);
			g.addEdge(4, 1);// comment to check split trees
			System.out.println("Bipartite? \n"+g.isBipartiteGraph());
		}
	}
	
	
}
