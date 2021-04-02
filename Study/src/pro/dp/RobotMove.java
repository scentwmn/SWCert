package pro.dp;

import java.util.Scanner;

public class RobotMove {

	static int N = 0;
	static int M = 0;
	
	static int[][] R = new int[1000][1000];
	static int max = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		
		max = 0;
		int befN = 0;
		int befM = 0;
		int curr = 0;
		for (int inx = 0; inx < N; inx++) {
			for (int jnx = 0; jnx < M; jnx++) {
				curr = scan.nextInt();

				befN = inx == 0 ? 0 : R[inx-1][jnx];
				befM = jnx == 0 ? 0 : R[inx][jnx-1];

				R[inx][jnx] = curr + (befN < befM ? befM : befN);
			}
		}

		System.out.println(R[N-1][M-1]);

		scan.close();
	}
}
