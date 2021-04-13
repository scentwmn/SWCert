package prolv1.aftertest.cutstick;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [교육A-0007] 막대기 자르기
 * DP
 */
public class Solution {
	
	static int MAX = 5;//3001;
	static int T, N;
	static int[] weight = new int[MAX];
	static int[] dp = new int[MAX];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			N = Integer.parseInt(br.readLine());
			Arrays.fill(dp, 0, N+1, 0);

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int inx = 1; inx <= N; inx++) {
				weight[inx] = Integer.parseInt(st.nextToken());
			}

			for (int inx = 1; inx <= N; inx++) {
				for (int jnx = 1; jnx <= inx; jnx++) {
					dp[inx] = Math.max(dp[inx], dp[inx-jnx]+weight[jnx]);
				}
			}

			System.out.println("#"+tnx + " " + dp[N]);
		}

		br.close();
	}

}
