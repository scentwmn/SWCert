package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FindAlone {
	
	static int T;
	static int N;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		long ans = 0;
		for (int inx = 0; inx < T; inx++) {
			N = Integer.parseInt(br.readLine());

			ans = 0;
			for (int jnx = 0; jnx < 2*N+1; jnx++) {
				ans ^= Integer.parseInt(br.readLine());
			}

			System.out.println("#"+(inx+1) + " " + ans);
		}
	}
}
