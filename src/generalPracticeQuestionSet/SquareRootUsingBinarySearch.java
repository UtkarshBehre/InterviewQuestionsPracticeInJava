package generalPracticeQuestionSet;

/**
 * How to find square root of a large number in O(logn) time.
 * @author Utkarsh Behre
 *
 */
public class SquareRootUsingBinarySearch {
	
	public static int findSquareRoot(int x){
		int l = 0;
		int u = x/2;
		while(u>=l){
			int m = l + (u-l)/2;
			if(x == m*m)
				return m;
			else if(x < m*m)
				u = m-1;
			else
				l = m+1;
		}
		return -1;
	}

	public static void main(String[] args){
		int num = 400;
		int sqrt = findSquareRoot(num);
		if(sqrt == -1)
			System.out.println(num + " is not a perfect square.");
		else
			System.out.println(num + " is the square of " + sqrt +".");
	}
}


