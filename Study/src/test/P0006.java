package test;

import java.util.Scanner;

/**
 * 아름다운 비트문자열
 */
public class P0006 {
	
	static int T = 0;
	static int N = 0;
	static int K = 0;
	static long X = 0;
	static long MAX_X = 1024L * 1024L * 1024L * 1024L * 1024L * 1024L;
	static byte[] B = new byte[1001];
	
	static long CNT = 0;
	
	static final long MOD = 1000000007;
	static long[][] combi = new long[1001][1001];

	public static void main(String[] args) {
		combi[0][0] = 1;
		combi[1][0] = 1;
		combi[1][1] = 1;
		for (int inx = 2; inx <= 1000; inx++) {
			combi[inx][0] = 1;
			for (int jnx = 1; jnx <= inx; jnx++) {
				combi[inx][jnx] = combi[inx-1][jnx-1] + combi[inx-1][jnx];
				if (combi[inx][jnx] > MAX_X) {
					combi[inx][jnx] = MAX_X + 1;
				}
			}
		}

		Scanner s = new Scanner(System.in);
		T = s.nextInt();
		
		for (int tnx = 1; tnx <= T; tnx++) {
			N = s.nextInt();
			K = s.nextInt();
			X = s.nextLong();
			
			// 초기화
			CNT = 0;
			
			process(1, K); // 위치, 원소수
			
			System.out.print("#" + tnx + " ");
			for (int jnx = 1; jnx <= N; jnx++) {
				System.out.print(B[jnx]);
			}
			System.out.println();
		}
		s.close();
	}
	
	static void process(int p, int k) {

		if (X - CNT == 1) {
			// 1번째 남은 경우, 마지막부터 남은 원소 갯수만큼 1로 채운다.
			for (int inx = p; inx <= N; inx++) {
				if (inx >= N-k+1) {
					B[inx] = 1;
				} else {
					B[inx] = 0;
				}
			}
			
			CNT += 1;
		}

		if (X == CNT || k == 0)
			return;

		// 0을 배치할 경우, 가지수 확인
//		long sum0 = combi(N-p, k);
		long sum0 = combi[N-p][k];

		if (X < CNT + sum0) {
			B[p] = 0;
			process(p+1, k);
		}
		else if (X > CNT + sum0) {
			B[p] = 1;
			CNT += sum0;
			process(p+1, k-1);
		}
		else {
			CNT += sum0;
			// p 다음 위치부터 남은원만큼 1로 우선 채운다.
			int e = 0;
			B[p] = 0;
			for (int inx = p+1; inx <= N; inx++) {
				e++;
				if (e <= k) {
					B[inx] = 1;
				} else {
					B[inx] = 0;
				}
			}
		}
	}
	
	static long combination(int a, int b) {
		// 카운트, 3C2 구하기
		long temp = 1L;
		for (int jnx = 1; jnx <= b; jnx++) {
			long up = ((long)a-(long)jnx+(long)1);
			long down = (long)jnx;

			temp = temp * up / down;
		}
		
		return temp;
	}
	static long combi(int n, int r) {
		long a = 1;
		for (int i = 1; i <= n; i++) {
			a *= i;
			a %= MOD;
		}
		long b = 1;
		for (int i = 1; i <= r; i++) {
			b *= i;
			b %= MOD;
		}
		for (int i = 1; i <= n - r; i++) {
			b *= i;
			b %= MOD;
		}
		
		long m = MOD - 2;
		long Ans = 1;
		while (m > 0) {
			if (m%2 == 1) {
				Ans = (Ans * b) % MOD;
			}
			b = (b*b) % MOD;
			m /= 2;
		}
		Ans = (Ans*a) % MOD;
		return Ans;
	}
}
