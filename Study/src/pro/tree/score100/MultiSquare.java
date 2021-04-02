package pro.tree.score100;

import java.util.Scanner;

public class MultiSquare {

	static int N = 0;
	static long M = 0;
	static long[][] K = new long[60][2];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextLong();

		// [0]= 2^0, N^1%10007 숫자
		// [1]= 2^1, N^2%10007 숫자
		// [2]= 2^2, N^4%10007 숫자
		// [2]= 2^3, N^8%10007 숫자
		long num2 = 2L;
		K[0][0] = 1;
		K[0][1] = N;
		for (int inx = 1; inx < 60; inx++) {
			K[inx][0] = num2;
			K[inx][1] = (K[inx-1][1] * K[inx-1][1])%10007;
			num2 *= 2L;
		}

		long result = 1;
		long m = M;
		while (m > 0) {
			int inx = search(m);
			result = (result * K[inx][1])%10007L;

			if (inx==0)
				break;

			m -= K[inx][0];
		}
		
		System.out.println(result);

		scan.close();
	}
	
	static int search(long loc) {
		if (loc == 1)
			return 0;

		int inx = 59;
		while(true) {
			if (K[inx][0] <= loc) {
				break;
			}
			inx--;
		}
		
		return inx;
	}
}
