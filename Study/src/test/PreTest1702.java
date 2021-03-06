package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PreTest1702 {
	
	static int T;
	static int N;
	static int M;
	static int[] E = new int[100];
	static int[] I = new int[11];
	
	static double sum;
	static int muscle;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for (int inx = 0; inx < T; inx++) {
			String[] nm = br.readLine().split(" ");
			N = Integer.parseInt(nm[0]); M = Integer.parseInt(nm[1]);
			
			sum = 0;
			String[] estr = br.readLine().split(" ");
			for (int jnx = 0; jnx < N; jnx++) {
				E[jnx] = Integer.parseInt(estr[jnx]);
				sum += E[jnx];
			}
			
			muscle = 0;
			process();
			System.out.print("#"+(inx+1) + " " + muscle);
			for (int jnx = 0; jnx < M; jnx++) {
				System.out.print(" " + (I[jnx]+1));
			}
			System.out.println();
		}

		if (br != null)
			br.close();
	}

	static void process() {
		final double avg = sum / (M+1);
		
		int tired = 0; // 누적 피로도
		int i_idx = 0; // 주사맞은 횟수

//		boolean isInjection = false;
		for (int inx = 0; inx < N; inx++) {
			muscle += tired * E[inx];

			if (inx > 0 && tired <= avg && tired+E[inx] >= avg) {
				if (Math.abs(tired-avg) <= Math.abs(tired+E[inx]-avg)) {
					I[i_idx++] = inx-1;

					if (inx < N-1)
						muscle -= tired * E[inx];
					tired = E[inx];
				} else {
					I[i_idx++] = inx;
					tired = 0;
				}
//				isInjection = true;
			}
			else {
				tired += E[inx];
//				isInjection = false;
			}
		}
	}

}
