package popularQuestionSet;

import java.util.HashSet;

/**
 * Boggle - Find all possible words in a board of characters.
 * Given a dictionary, a method to do lookup in dictionary and a M x N board where 
 * every cell has one character. Find all possible words that can be formed by a 
 * sequence of adjacent characters. Note that we can move to any of 8 adjacent 
 * characters, but a word should not have multiple instances of same cell.
 * Runtime: O(mn)
 * @author Utkarsh
 *
 */
public class BoggleProblem {
	String[] dictionary = {"GEEKS", "FOR", "QUIZ", "GO", "SEEK"};
	HashSet<String> foundWords = new HashSet<String>();
	
	/**
	 * if a word is found print it
	 * Runtime O(n), n is number of words in dictionary
	 */
	public boolean isWord(StringBuffer str) {
		for(String word: dictionary) 
			if(word.equals(str.toString()) && !foundWords.contains(word)) {
				System.out.println(str);
				foundWords.add(word);
				return true;
			}
		return false;
	}
	
	/**
	 * sets off a word starting alphabet
	 * Runtime:O(mn), m = rows, n = columns
	 */
	public void findWordsFormed(char[][] boggle, boolean[][] visited) {
		StringBuffer str = new StringBuffer();
		for(int r=0; r<boggle.length; r++) 
			for(int c=0; c<boggle[0].length; c++) 
				addAdjCharAndCheckRec(str, r, c, boggle, visited);
	}
	
	/**
	 * adds adjacent character checks and goes for next possible adjacent character
	 * Runtime: O(1), since both for loops are running for constant 3 times  
	 */
	public void addAdjCharAndCheckRec(StringBuffer str, int r, int c, char[][] boggle, boolean[][] visited) {
		str.append(boggle[r][c]);
		visited[r][c] = true;
		isWord(str);
		for(int row=r-1; row<=r+1 && row<boggle.length; row++) 
			for(int col=c-1; col<=c+1 && col<boggle[0].length; col++) 
				if(row>=0 && col>=0 && !visited[row][col]) 
					addAdjCharAndCheckRec(str, row, col, boggle, visited);
		visited[r][c] = false;
		str.deleteCharAt(str.length()-1);
	}
	
	/**
	 * Entry point of solution. initiates a visited array to keep track of 
	 * visited cells
	 * Runtime: O(mn)
	 */
	public void findWordsFormed(char[][] boggle) {
		boolean[][] visited = new boolean[boggle.length][boggle[0].length];
		System.out.println("Following words are formed from the given boggle.");
		findWordsFormed(boggle, visited);
	}
	
	public static void main(String[] args) {
		BoggleProblem bp = new BoggleProblem();
		char[][] boggle = { {'G', 'I', 'Z'},
                			{'U', 'E', 'K'},
                			{'Q', 'S', 'E'}};
		bp.findWordsFormed(boggle);
	}
}
