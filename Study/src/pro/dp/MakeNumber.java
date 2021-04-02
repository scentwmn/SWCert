package pro.dp;

import java.util.Scanner;

public class MakeNumber {

	static int N = 0;
	static int[] R = new int[100001];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		process();
		
		System.out.println(R[N]);
		scan.close();
	}

	static void process() {
		R[1] = 1;
		R[2] = 2;
		R[3] = 4;

		for (int inx = 4; inx <= N; inx++) {
			R[inx] = (R[inx-1]%1000007 + R[inx-2]%1000007 + R[inx-3]%1000007)%1000007;
		}
	}
}
