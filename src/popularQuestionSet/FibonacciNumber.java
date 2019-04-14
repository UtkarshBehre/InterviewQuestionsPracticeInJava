package popularQuestionSet;

/**
 * Find the nth fibonacci number using recursive, dynamic, and bottom up programming methods
 * @author Utkarsh Behre
 *
 */
public class FibonacciNumber {
	public static int fibNum(int n){
		if(n<1)
			return 0;
		if(n==1 || n ==2)
			return 1;
		return fibNum(n-1) + fibNum(n-2);
	}

	public static int fibNumDynamic(int n)
	{	
		return fibNumDynamic(n, new int[n+1]);
	}
	
	public static int fibNumDynamic(int n, int[] mem)
	{
		if(mem[n] != 0)
			return mem[n];
		int res = 0;
		if(n==1 || n==2)
			res = 1;
		if(n>2)
			res = fibNumDynamic(n-1,mem) + fibNumDynamic(n-2,mem);
		mem[n] = res;
		return res;
	}	

	public static int fibNumDynamicBottomUp(int n){
		int[] mem = new int[n+1];
		mem[1] = 1;
		mem[2] = 1;
		for(int i = 3; i<=n; i++){
			mem[i] = mem[i-1] + mem[i-2];
		}
		return mem[n];
	}


	public static void main(String[] args){
		long t0 = System.currentTimeMillis();
		System.out.println(fibNum(40));
		long t1 = System.currentTimeMillis();
		System.out.println(fibNumDynamic(40));
		long t2 = System.currentTimeMillis();
		System.out.println(fibNumDynamicBottomUp(40));
		long t3 = System.currentTimeMillis();
		System.out.println("Recursive programming: "+ (t1-t0));
		System.out.println("dynamic programming: "+ (t2-t1));
		System.out.println("bottom up programming: "+ (t3-t2));
	}
}
