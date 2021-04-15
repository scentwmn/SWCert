package prolv1.aftertest.baloon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [연습A-0007] 풍선 맞추기
 * Ad-hoc
 * => 해결
 */
public class Solution2 {

	static int MAX = 7;//1_000_001;
	static int T, N;
	static int[] ar = new int[MAX];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			Arrays.fill(ar, 0);

			int cnt = 0;
			for (int inx = 1; inx <= N; inx++) {
				int a = Integer.parseInt(st.nextToken());
				if (ar[a+1] == 0) {
					ar[a]++;
					cnt++;
					continue;
				}
				ar[a+1]--;
				ar[a]++;
			}

			System.out.println("#"+tnx + " " + cnt);
		}

		br.close();
	}
}
