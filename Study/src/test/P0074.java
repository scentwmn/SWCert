package test;

import java.util.Scanner;

public class P0074 {
	
	static int T;
	static int N;
	static int Q;
	
	static int[] U1 = new int[1001];
	static int[] U2 = new int[1001];
	
	static int FINAL_SUM1 = 0;
	static int FINAL_SUM2 = 0;
	
	static int u1First = 0;
	static int u1Second = 0;
	static int u2First = 0;
	static int u2Second = 0;

	static int[][] P = new int[1000][];
	
	public static void main(String[] args) throws Exception {
		// 숫자 위치별 가중치 저장
		P[1] = new int[2+1];
		P[1][1] = 1; P[1][2] = 1;
		for (int inx = 2; inx < 1000; inx++) {
			P[inx] = new int[inx+2];
			P[inx][1] = 1;
			P[inx][inx+1] = 1;
			for (int jnx = 2; jnx <= inx; jnx++) {
				P[inx][jnx] = (P[inx-1][jnx-1] + P[inx-1][jnx]) % 10;
			}
		}
		
		Scanner scan = new Scanner(System.in);
		T = scan.nextInt();
		for (int tnx = 1; tnx <= T; tnx++) {

			u1First = 0; u1Second = 0;
			u2First = 0; u2Second = 0;
			FINAL_SUM1 = 0;
			FINAL_SUM2 = 0;

			N = scan.nextInt();
			Q = scan.nextInt();
			
			for (int inx = 1; inx <= N; inx++) {
				U1[inx] = scan.nextInt();
			}
			for (int inx = 1; inx <= N; inx++) {
				U2[inx] = scan.nextInt();
			}

			process();

			for (int inx = 1; inx <= Q; inx++) {
				int c = scan.nextInt();
				if (c == 4) {
					FINAL_SUM1 += u1First * 10 + u1Second;
					FINAL_SUM2 += u2First * 10 + u2Second;
				}
				else if (c == 1) {
					int a = scan.nextInt();
					int b = scan.nextInt();
					
					int prev = U1[a];
					int next = U2[b];
					swap(U1[a], U2[b], a, 1);
					swap(U2[b], U1[a], b, 2);

					U1[a] = next;
					U2[b] = prev;
				}
				else if (c == 2) {
					int a = scan.nextInt();
					int b = scan.nextInt();

					int prev = U1[a];
					int next = U1[b];
					swap(U1[a], U1[b], a, 1);
					swap(U1[b], U1[a], b, 1);

					U1[a] = next;
					U1[b] = prev;
				}
				else if (c == 3) {
					int a = scan.nextInt();
					int b = scan.nextInt();

					int prev = U2[a];
					int next = U2[b];
					swap(U2[a], U2[b], a, 2);
					swap(U2[b], U2[a], b, 2);

					U2[a] = next;
					U2[b] = prev;
				}
			}
			
			System.out.print("#"+tnx+ " " + FINAL_SUM1 + " " + FINAL_SUM2);
			System.out.println();
			
		}
		
		scan.close();
	}
	
	static void process() {
		// P[N-2] 까지의 가중치 사용함.
		for (int inx = 1; inx <= N-1; inx++) {
			u1First = (u1First + U1[inx] * P[N-2][inx]) % 10;
			u1Second = (u1Second + U1[inx+1] * P[N-2][inx]) % 10;

			u2First = (u2First + U2[inx] * P[N-2][inx]) % 10;
			u2Second = (u2Second + U2[inx+1] * P[N-2][inx]) % 10;
		}
	}
	
	static void swap(int bef, int aft, int idx, int u12) {
		int ten = u12 == 1 ? u1First : u2First;
		int one = u12 == 1 ? u1Second : u2Second;

		int tenPrev = 0;
		int tenNext = 0;
		if (idx < N) {
			tenPrev = (bef * P[N-2][idx])%10;
			tenNext = (aft * P[N-2][idx])%10;
		}

		int onePrev = 0;
		int oneNext = 0;
		if (idx > 1) {
			onePrev = (bef * P[N-2][idx-1])%10;
			oneNext = (aft * P[N-2][idx-1])%10;
		}

		tenNext -= tenPrev;
		oneNext -= onePrev;

		if(idx < N) {
			ten += tenNext;
			ten = ten < 0 ? ten + 10 : ten;
			ten = ten % 10;
		}
		if (1 < idx) {
			one += oneNext;
			one = one < 0 ? one + 10 : one;
			one = one % 10;
		}

		if (u12 == 1) {
			u1First = ten;
			u1Second = one;
		} else {
			u2First = ten;
			u2Second = one;
		}
	}
}
