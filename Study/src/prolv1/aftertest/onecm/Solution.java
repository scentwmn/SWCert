package prolv1.aftertest.onecm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [기출A-0006] 1cm의 자존심
 * 백트래킹
 */
public class Solution {
	
	static int MAX = 12;

	static int T, N, M;
	static List<Integer>[] list = new List[MAX];
	static int[] result = new int[MAX];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int inx = 1; inx < MAX; inx++) {
			list[inx] = new ArrayList<Integer>();
		}
		
		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for (int inx = 1; inx <= N; inx++) {
				list[inx].clear();
			}
			
			for (int inx = 1; inx <= M; inx++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				list[a].add(b);
			}
			
			Arrays.fill(result, 0);
			for (int inx = 1; inx <= N; inx++) {
				if (result[inx] == 0)
					process(inx);
			}

			System.out.print("#"+tnx);
			for (int inx = 1; inx <= N; inx++) {
				System.out.print(" " + result[inx]);
			}
			System.out.println();
		}
		
		br.close();
	}

	static void process(int a) {
		if (list[a].isEmpty()) {
			result[a] = 1;
			return;
		}

		int max = 0;
		for (int inx = 0; inx < list[a].size(); inx++) {
			int b = list[a].get(inx);
			if (result[b] == 0) {
				process(b);
			}
			max = Math.max(max, result[b]);
		}

		result[a] = max+1;
	}
}
