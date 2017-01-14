package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RadixSort {
	
	static int T;
	static int N;
	static String[] str;
	static int[][] count;

	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(System.in);
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
			
			System.out.println("#"+inx + " " + process());
		}
	}

	static int process() {
		return 1;
	}
}
