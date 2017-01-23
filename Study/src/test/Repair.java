package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Repair {
	
	static int T;
	static int C;
	static int N;
	static int[] E = new int[1000000];
	static int result;
	static long L1, L2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int inx = 0; inx < T; inx++) {
			C = Integer.parseInt(br.readLine()) * 10000000;
			N = Integer.parseInt(br.readLine());
			
			if (N != 0) {
				for (int jnx = 0; jnx < N; jnx++) {
					E[jnx] = Integer.parseInt(br.readLine());
				}
	
				L1 = 0;
				L2 = 0;
				result = 0;
				process();
				if (2==result)
					System.out.println("#"+(inx+1) + " yes " + L1 + " " + L2);
				else
					System.out.println("#"+(inx+1) + " danger");
			}
			else {
				System.out.println("#"+(inx+1) + " danger");
			}
		}

	}

	static void process() {
		Arrays.sort(E, 0, N);
		
		int begin = 0;
		int end = N-1;
		
		result = 1;
		
		int sum = 0;
		
		while (begin < end) {
			sum = E[begin] + E[end];
			if (sum < C)
				begin++;
			else if (sum > C)
				end--;
			else {
				result = 2;
				L1 = E[begin];
				L2 = E[end];

				break;
			}
		}
	}
}
