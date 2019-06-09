package generalPracticeQuestionSet;

/**
 * Detect Cycle in an Undirected Graph using Disjoint Set.
 * Use find and union to group all vertices in a single subset one at a time, and 
 * if at any point a new vertex is found to be already in the subset then graph is
 * cyclic. If all vertices are put in single subset without finding a cycle then its
 * a acyclic.
 * Runtime: O(VE) 
 * @author Utkarsh
 */
public class GraphUndirectedCyclicDisjoint {
	static class Graph{
		int V,E;
		Edge[] edges;
		static int edgeIndex = 0;
		
		static class Edge{
			int src;
			int dest;
			
			public Edge(int src, int dest) {
				this.src = src;
				this.dest = dest;
			}
		}
		
		public Graph(int v, int e) {
			this.V = v;
			this.E = e;
			edgeIndex = 0;
			edges = new Edge[E];
		}
		
		public void addEdge(int src, int dest) {
			edges[edgeIndex++] = new Edge(src,dest);
		}
		
		/**
		 * parents array holds parent of each vertex to represent set
		 * find parent recals itself unless a vertex's parent is -1 in which
		 * case it has no parent.
		 * Runtime: O(V) 
		 */
		public int findParent(int[] parents, int v) {
			if(parents[v] == -1)
				return v;
			return findParent(parents, parents[v]);
		}
		
		/**
		 * all union does is that it assigns dest's set, meaning its greatest parent 
		 * to the src's parent
		 * Runtime: O(V)
		 * @param parents
		 * @param src
		 * @param dest
		 */
		public void union(int[] parents, int src, int dest) {
			int x = findParent(parents, src);
			int y = findParent(parents, dest);
			parents[x] = y;
		}
		
		/**
		 * first initialize the parents of each edge as -1 then go over each edge 
		 * to see src dest have same parent set otherwise union them
		 * Runtime: O(VE)
		 */
		public boolean isCyclic() {
			int[] parents = new int[V];
			for(int i = 0; i< parents.length; i++)
				parents[i] = -1;
			
			for(Edge e : edges) {
				if(findParent(parents, e.src) == findParent(parents, e.dest))
					return true;
				union(parents, e.src, e.dest);
			}
			return false;
		}
		
	}
	// tester
	public static void main(String[] args) {
		Graph g1 = new Graph(5, 5); 
        g1.addEdge(1, 0); 
        g1.addEdge(0, 2);
        g1.addEdge(2, 1); 
        g1.addEdge(0, 3); 
        g1.addEdge(3, 4); 
        if (g1.isCyclic()) 
            System.out.println("Graph contains cycle"); 
        else
            System.out.println("Graph doesn't contains cycle"); 
  
        Graph g2 = new Graph(3,2); 
        g2.addEdge(0, 1); 
        g2.addEdge(1, 2); 
        if (g2.isCyclic()) 
            System.out.println("Graph contains cycle"); 
        else
            System.out.println("Graph doesn't contains cycle"); 	
         
        Graph g3 = new Graph(3, 3); 
  
        // add edge 0-1
        g3.addEdge(0, 1); 
        g3.addEdge(1, 2);
        g3.addEdge(0, 2); 
        if (g3.isCyclic()) 
            System.out.println( "graph contains cycle" ); 
        else
            System.out.println( "graph doesn't contain cycle" );
	}
}
