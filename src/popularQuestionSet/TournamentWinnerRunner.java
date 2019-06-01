package popularQuestionSet;

/**
 * Tournament Tree (Winner Tree)
 * should give winner and runner up
 * similar to min or max heap, assumption is complete binary tree and
 * all leaf nodes represent a player so we always get a pair
 * Runtime: O(n)
 * @author Utkarsh
 *
 */
public class TournamentWinnerRunner {
	
	// tester
	public static void main(String[] args) {
		int[] players = {3, 6, 100, 9, 10, 12, 7, -1};
		printTopAndRunner(players);
	} 
	
	/**
	 * assuming complete binary tree, i.e. all leaf nodes are players and rest
	 * being winners at some point, root being the winner
	 * Runtime: O(n)
	 */
	public static void printTopAndRunner(int[] players) {
		int first;
		int second;
		// parent nodes can only be total leaf nodes -1 in complete binary tree
		int[] heap = new int[players.length*2-1];
		int hi = heap.length -1; //heap index
		int pi = players.length-1; //player index
		// fill in the players in leaf positions
		while(pi>=0) {
			heap[hi--] = players[pi--];
		}
		// we assign the winner of 2 players to its parent position in until
		// we reach the root that is 0th position
		int current = heap.length-2;
		int winner = (current-1)/2;
		while(winner >= 0) {
			heap[winner] = heap[current]>heap[current+1]? heap[current]:heap[current+1];
			current = current -2;
			winner = (current-1)/2;
		}
		// get first and second position and print them
		first = heap[0];
		second = heap[1]<heap[2]?heap[1]:heap[2];
		System.out.print("Winner: "+first);
		System.out.print("\nRunner: "+second);
	}
}
