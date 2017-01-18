import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	
	static int T;
	static int N;
	static String[] str = new String[100000];
	static int[][] count = new int[100000][26];
	static int W;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int inx = 1; inx <= T; inx++) {
			N = Integer.parseInt(br.readLine());
			
			for (int jnx = 0; jnx < N; jnx++) {
				str[jnx] = br.readLine();
				for (int knx = 0; knx < str[jnx].length(); knx++) {
					count[jnx][str[jnx].charAt(knx)-'a']++;
				}
			}
			
			W = 0;
			for (int jnx = 0; jnx < N; jnx++) {
				for (int knx = 0; knx < 26; knx++) {
					W = Math.max(W, count[jnx][knx]);
				}
			}
			
			System.out.println("#"+inx + " " + process());
		}
	}

	static long process() {
		int[] order = new int[N];
		for (int inx = 0; inx < order.length; inx++) {
			order[inx] = inx;
		}
		
		for (int inx = 0; inx < 26; inx++) {
			int[] t = new int[W+1];
			for (int jnx = 0; jnx < N; jnx++) {
				t[count[jnx][inx]]++;
			}
			t[0]--;
			for (int jnx = 1; jnx < t.length; jnx++) {
				t[jnx] += t[jnx-1];
			}
			
			int[] tmp = new int[N];
			for (int jnx = N-1; jnx >= 0; jnx--) {
				tmp[t[count[order[jnx]][inx]]--] = order[jnx];
			}
			
			for (int jnx = 0; jnx < N; jnx++) {
				order[jnx] = tmp[jnx];
			}
		}
		
		// 같은값 쌍 카운트
		long ans = 0;
		int cnt = 1;
		for (int inx = 1; inx < N; inx++) {
			boolean diff = false;

			for (int j = 0; j < 26; j++) {
				if (count[order[inx-1]][j] != count[order[inx]][j]) {
					diff = true; break;
				}
			}
			
			if (diff)
				cnt = 1;
			else
				cnt++;
			
			ans += cnt-1;
		}

		return ans;
	}
}