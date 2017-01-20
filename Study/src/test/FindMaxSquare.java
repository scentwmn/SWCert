package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class FindMaxSquare {
	
	static int T;
	static int N;
	static long[] E = new long[100000];
	static int[] L = new int[100000];
	static int[] R = new int[100000];

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		long ans = 0;
		for (int inx = 0; inx < T; inx++) {
			N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			Stack<Integer> stk = new Stack<Integer>();
			for (int jnx = 0; jnx < N; jnx++) {
				E[jnx] = Integer.parseInt(st.nextToken());

				while(!stk.isEmpty() && E[stk.peek()] >= E[jnx]) {
					stk.pop();
				}
				L[jnx] = stk.isEmpty() ? 0 : stk.peek()+1;
				stk.push(jnx);
			}
			
			stk = new Stack<Integer>();
			for (int jnx = N-1; jnx >= 0; jnx--) {
				while(!stk.isEmpty() && E[stk.peek()] >= E[jnx]) {
					stk.pop();
				}
				R[jnx] = stk.isEmpty() ? N-1 : stk.peek()-1;
				stk.push(jnx);
			}
			
			for (int jnx = 0; jnx < N; jnx++) {
				ans = Math.max(ans, (long)(R[jnx]-L[jnx]+1)*E[jnx]);
			}

			System.out.println("#"+(inx+1) + " " + ans);
		}
	}
}
