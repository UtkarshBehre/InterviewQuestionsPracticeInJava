package popularQuestionSet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

/**
 * Design an algorithm to simplify cash flow between people
 * Minimize Cash Flow among a given set of friends who have borrowed money from 
 * each other. Given a number of friends who have to give or take some amount of 
 * money from one another. Design an algorithm by which the total cash flow among 
 * all the friends is minimized.
 * O(VE), V is number of persons and E is number of transactions
 * @author Utkarsh
 */
public class GraphSplitwiseSimplify {
	static class Graph{
		int V;
		LinkedList<AdjNode>[] adjNodesList;
		
		static class AdjNode{
			int adjV;
			int dept;
			public AdjNode(int adjV, int dept) {
				this.adjV = adjV;
				this.dept = dept;
			}
			
			@Override
			public boolean equals(Object o) {
				if(this.adjV == ((AdjNode) o).adjV)
					return true;
				else
					return false;
			}
		}
		
		@SuppressWarnings("unchecked")
		public Graph(int v) {
			this.V = v;
			adjNodesList = new LinkedList[V];
			for(int i=0; i<V; i++) {
				adjNodesList[i] = new LinkedList<AdjNode>();
			}
		}
		
		public void addEdge(int src, int dest, int dept){
			AdjNode adjNode = new AdjNode(dest, dept);
			adjNodesList[src].add(adjNode);
		}
		
		/**
		 * simplifies depts between a group of friends by calculating individual 
		 * depts first, reset the initial depts and assign the new depts only between
		 * the required individuals
		 * Runtime: O(VE)
		 */
		@SuppressWarnings("unchecked")
		public void simplifyDepts() {
			// key is the vertex indication of person and value is the amount person needs or owes
			HashMap<Integer, Integer> takers = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> givers = new HashMap<Integer, Integer>();
			// depts array keeps track of current total dept whether 
			// positive or negative or 0 on each individuals, where index is vertex
			int[] depts = new int[V];
			for(int i=0; i<V; i++) {
				for(AdjNode adjNode : adjNodesList[i]) {
					// for each edge on a vertex we remove dept from both vertices
					depts[i] -= adjNode.dept; 
					depts[adjNode.adjV] += adjNode.dept; 
				}
			}
			// ultimately we will have depts in positive negative or 0 in depts array
			for(int i=0; i<V; i++) {
				// negative dept means the person owes others
				if(depts[i]<0) {
					givers.put(i,Math.abs(depts[i]));
				}
				// positive dept means the person needs to take from others
				else if(depts[i]>0) {
					takers.put(i,depts[i]);
				}
			}
			
			// since we updated depts[] from adjNodesList, we now reset the adjNodesList
			adjNodesList = new LinkedList[V];
			for(int i=0; i<V; i++) {
				adjNodesList[i] = new LinkedList<AdjNode>();
			}
			/* time complexity of this block can be calculated as below. In worst 
			/ case takers = givers, but even in that case we don't compute anything 
			/ for a taker if it is 0. ( here we keep it to avoid hashmap ConcurrentModification problem)
			/ So this can be considered like iterating an array from both sides to find sum */
			
			// we run outer giver loop and it keeps giving until it can to the takers i.e. amount to give > 0
			for ( Entry<Integer, Integer> giver : givers.entrySet()) {
				int amountToGive = giver.getValue(); // max amount current giver can give
				for(Entry<Integer, Integer> taker : takers.entrySet()) {
					int amountToTake = taker.getValue(); // amount current taker has to take
					if(amountToTake>0) { // if the taker still needs to take
						// if the amount to give is more, that means we'll be left with some 
						//amount even after this transaction
						if(amountToTake < amountToGive) { 
							taker.setValue(0); // as taker gets entire amount he needed
							amountToGive -= amountToTake; // left out amount that can be given
							// we add the transaction done as required edge
							addEdge(giver.getKey(), taker.getKey(), amountToTake); 
						}else {	// in case taker can't take the full amount needed, takes whatever left
							taker.setValue(amountToTake-amountToGive);
							addEdge(giver.getKey(), taker.getKey(), amountToGive);
							// since give has nothing more to give we break the inner for loop
							break;
						}
					} 
				} 
			}
		}
		
		/**
		 * prints the current depts between all members according to the edges
		 */
		public void printDepts() {
			System.out.println("depts are: ");
			for(int i = 0; i<V; i++) {
				for(AdjNode adjNode: adjNodesList[i]) {
					System.out.println(i +" owes " + adjNode.adjV +" "+ adjNode.dept+ " bucks.");
				}
			}
		}
	}
	
	// testing grounds
	public static void main(String[] args) {
		System.out.println("Test set 1");
		Graph g = new Graph(3);
		// each edge represents the dept between the 2 persons, use printDepts to understand
		g.addEdge(0, 1, 1000);
		g.addEdge(1, 2, 5000);
		g.addEdge(0, 2, 2000);
		g.printDepts();
		g.simplifyDepts();
		System.out.print("After simplification, ");
		g.printDepts();
		
		System.out.println("\nTest set 2");
		g = new Graph(5);
		g.addEdge(0, 1, 1000);
		g.addEdge(0, 2, 5000);
		g.addEdge(1, 3, 2000);
		g.addEdge(1, 4, 1500);
		g.addEdge(2, 1, 3000);
		g.addEdge(2, 3, 4000);
		g.addEdge(3, 0, 500);
		g.addEdge(4, 3, 500);
		g.printDepts();
		g.simplifyDepts();
		System.out.print("After simplification, ");
		g.printDepts();
		
		System.out.println("\nTest set 3");
		g = new Graph(5);
		g.addEdge(0, 3, 4);
		g.addEdge(0, 4, 1);
		g.addEdge(1, 3, 5);
		g.addEdge(1, 4, 2);
		g.addEdge(2, 3, 3);
		g.addEdge(2, 4, 8);	

		g.printDepts();
		g.simplifyDepts();
		System.out.print("After simplification, ");
		g.printDepts();
	}
}
