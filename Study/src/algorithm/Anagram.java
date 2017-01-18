package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Anagram {
	
	static int T;
	static int N;
	static String[] str;
	static int[][] count;
	static int W;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int inx = 1; inx <= T; inx++) {
			N = Integer.parseInt(br.readLine());
			
			str = new String[N];
			count = new int[N][26];
			for (int jnx = 0; jnx < str.length; jnx++) {
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

	static int process() {
		int[] order = new int[N];
		for (int inx = 0; inx < order.length; inx++) {
			order[inx] = inx;
		}
		
		for (int inx = 0; inx < 26; inx++) {
			int[] t = new int[W+1];
			for (int jnx = 0; jnx < count.length; jnx++) {
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
		int acc = 1;
		int sum = 0;
		for (int inx = 1; inx < order.length; inx++) {
			boolean isSame = true;

			for (int jnx = 0; jnx < 26; jnx++) {
				if (count[order[inx]][jnx] != count[order[inx-1]][jnx]) {
					isSame = false;
					break;
				}
			}
			
			if (isSame)
				acc++;
			else {
				sum += acc * (acc-1) / 2;
				acc = 1;
			}
		}
		sum += acc * (acc-1) / 2;

		return sum;
	}
}