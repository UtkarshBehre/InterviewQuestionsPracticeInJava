package dynamicProgrammingQuestionSet;

/**
 * 	Binomial Coefficient
 * Write a function that takes two parameters n(power of 1+x) and k(power of x 
 * whose coefficient is to be found) and returns the value of Binomial Coefficient 
 * C(n, k). For example, your function should return 6 for n = 4 and k = 2, and it 
 * should return 10 for n = 5 and k = 2.
 * Runtime: O(nk)
 * Spacetime: O(n)
 * @author Utkarsh
 */
public class BionomialCoefficient {
	
	/**
	 * double for loops, inner loop starts from k and comes back to 0 as we
	 * need to maintain old values
	 * Runtime: O(nk)
	 * Spacetime: O(n)
	 */
	public static int findBioCoefSpace(int n, int k) {
		int[] C = new int[n];
		C[0]=1;
		for(int i = 1; i<= n; i++) {
			for(int j = k; j>0; j--) {
				C[j] = C[j] + C[j-1];
			}
		}
		return C[k];
	}
	/**
	 * double for loops, inner loop starting from 0 since we have space to keep 
	 * old values
	 * Runtime: O(nk)
	 * Spacetime: O(nk)
	 */
	public static int findBioCoef(int n, int k) {
		int[][] C = new int[n+1][k+1];
		for(int i = 1; i<=n; i++) {
			for(int j = 0; j<=Math.min(i, k); j++) {
				if(j == 0 || j == i)
					C[i][j] = 1;
				else
					C[i][j] = C[i-1][j-1] + C[i-1][j];
			}
		}
		return C[n][k];
	}
	
	public static void main(String[] args) {
		int n = 4;
		int k = 2;
		System.out.println("Bionomial Coef: "+findBioCoef(n,k));
	}
}
