package sw.p05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [기출P-0091][2021년02월07일] 디자이너의 고민
 */
public class Solution {

	static int T, N, K;
	static int[] design = new int[101];
	static int d1, d2;

	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int inx = 1; inx <= K; inx++) {
				design[inx] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			d1 = Integer.parseInt(st.nextToken());
			d2 = Integer.parseInt(st.nextToken());

			// 처리. dp
			result = 0;
			dp(0, false, false);

			System.out.println("#"+tnx + " " + result);
		}

		br.close();
	}

	static void dp(int sum, final boolean b1, final boolean b2) {
		for (int inx = 1; inx <= K; inx++) {
			if (sum + design[inx] == N) {
				if ((inx == d1 || b1) && (inx == d2 || b2))
					result++;
			}
			else if (sum + design[inx] < N) {
				dp(sum + design[inx], (inx == d1 ? true : b1), (inx == d2 ? true : b2));
			}
		}
	}
}
