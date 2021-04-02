package pro.dp;

import java.util.Scanner;

public class SquareSum {

	static int[][] NUM = new int[1000][1000];
//	static int[][] ACC = new int[1000][1000];
	static int[][] ROW_ACC = new int[1000][1000];
	static int[][] COL_ACC = new int[1000][1000];
	
	static int N, M, Q;
	static int result = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		Q = scan.nextInt();
		
		for (int inx = 0; inx < N; inx++) {
			for (int jnx = 0; jnx < M; jnx++) {
				NUM[inx][jnx] = scan.nextInt();
				COL_ACC[inx][jnx] += NUM[inx][jnx] + (jnx > 0 ? COL_ACC[inx][jnx-1] : 0);
				ROW_ACC[inx][jnx] += NUM[inx][jnx] + (inx > 0 ? ROW_ACC[inx-1][jnx] : 0);
//				ACC[inx][jnx] += ROW_ACC[inx][jnx] + (inx > 0 ? ROW_ACC[inx-1][jnx] : 0);
			}
		}
		
		for (int inx = 1; inx <= Q; inx++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();
			int d = scan.nextInt();

			result = 0;
			process(a, b, c, d);

			System.out.println(result);
		}

		scan.close();
	}

	static void process(int a, int b, int c, int d) {
		if (a - c < b - d) {
			for (int inx = a; inx <= c; inx++) {
				result += COL_ACC[inx][d] - (b==0 ? 0 : COL_ACC[inx][b-1]);
			}
		}
		else {
			for (int inx = b; inx <= d; inx++) {
				result += ROW_ACC[c][inx] - (a==0 ? 0 : ROW_ACC[a-1][inx]);
			}
		}
	}
}
