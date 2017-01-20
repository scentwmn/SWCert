package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxSubArray {
	
	static int T;
	static int N;
	static long sum;
	static long ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for (int inx = 0; inx < T; inx++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			sum = 0;
			ans = Long.MIN_VALUE;
			for (int jnx = 0; jnx < N; jnx++) {
				sum += Long.parseLong(st.nextToken());
				ans = Math.max(ans, sum);
				sum = Math.max(sum, 0);
			}

			System.out.println("#"+(inx+1) + " " + ans);
		}

	}

}
