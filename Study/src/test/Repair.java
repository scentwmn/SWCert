package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Repair {
	
	static int T;
	static int C;
	static int N;
	static int[] E = new int[1000000];
	static String result;
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
				process();
				if ("yes".equals(result))
					System.out.println("#"+(inx+1) + " " + result + " " + L1 + " " + L2);
				else
					System.out.println("#"+(inx+1) + " " + result);
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
		
		result = "danger";
		
		while (begin <= end) {
			if (E[begin] + E[end] < C)
				begin++;
			else if (E[begin] + E[end] > C)
				end--;
			else {
				result = "yes";
				if (Math.abs(L2-L1) < Math.abs(E[begin]-E[end])) {
					L1 = E[begin];
					L2 = E[end];
				}

				begin++;
			}
		}
		
		if (L1 > L2) {
			long temp = L1;
			L1 = L2;
			L2 = temp;
		}
	}
}
