package pro.sort;

import java.util.Scanner;

public class Fibonacci {

	static int[] F = new int[46];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		F[0] = 0;
		F[1] = 1;
		int N = scan.nextInt();
		
		get(N);
		System.out.println(F[N]);
		scan.close();
	}
	
	static int get(int n) {
		if (n==0)
			return 0;
		if (n==1)
			return 1;
		if (F[n] > 0)
			return F[n];
		
		F[n] = get(n-1) + get(n-2);
		
		return F[n];
	}
}
