package sw.p05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [기출P-0091][2021년02월07일] 디자이너의 고민
 */
public class Solution2 {

	static int T, N, K;
	static int[] design = new int[101];
	static int d1, d2;
	
	static int MAX = 50_000;
	static int[][] DP = new int[4][MAX];
	static final int mod = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] tmp = new int[K+1];
			for (int inx = 1; inx <= K; inx++) {
				tmp[inx] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			d1 = Integer.parseInt(st.nextToken());
			d2 = Integer.parseInt(st.nextToken());

			design[1] = tmp[d1];
			design[2] = tmp[d2];
			int loc = 3;
			for (int inx = 1; inx <= K; inx++) {
				if (inx == d1 || inx == d2)
					continue;
				design[loc++] = tmp[inx];
			}

			// 처리. dp
			Arrays.fill(DP[0], 0);
			Arrays.fill(DP[1], 0);
			Arrays.fill(DP[2], 0);
			Arrays.fill(DP[3], 0);
			
			DP[1][design[1]] = design[1];
			DP[2][design[2]] = design[2];
			dp();

			System.out.println("#"+tnx + " " + DP[3][N-1]);
		}

		br.close();
	}

	static void dp() {
		for (int inx = 0; inx < N; inx++) {
			// 디자인 조각 a를 이어붙이는 케이스
			if (inx - design[1] >= 0) {
				DP[1][inx] = (DP[1][inx] + DP[0][inx-design[1]]) % mod;
				DP[1][inx] = (DP[1][inx] + DP[1][inx-design[1]]) % mod;
				DP[3][inx] = (DP[3][inx] + DP[2][inx-design[1]]) % mod;
				DP[3][inx] = (DP[3][inx] + DP[3][inx-design[1]]) % mod;
			}
			// 디자인 조각 b를 이어붙이는 케이스
			if (inx - design[2] >= 0) {
				DP[2][inx] = (DP[2][inx] + DP[0][inx-design[2]]) % mod;
				DP[2][inx] = (DP[2][inx] + DP[2][inx-design[2]]) % mod;
				DP[3][inx] = (DP[3][inx] + DP[1][inx-design[2]]) % mod;
				DP[3][inx] = (DP[3][inx] + DP[3][inx-design[2]]) % mod;
			}
			
			for (int jnx = 3; jnx <= K; jnx++) {
				if (inx - design[jnx] >= 0) {
					DP[0][inx] = (DP[0][inx] + DP[0][inx-design[jnx]]) % mod;
					DP[1][inx] = (DP[1][inx] + DP[1][inx-design[jnx]]) % mod;
					DP[2][inx] = (DP[2][inx] + DP[2][inx-design[jnx]]) % mod;
					DP[3][inx] = (DP[3][inx] + DP[3][inx-design[jnx]]) % mod;
				}
			}
		}
	}
}
