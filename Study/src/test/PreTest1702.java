package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PreTest1702 {
	
	static int T;
	static int N;
	static int M;
	static int[] E = new int[100];
	static int[] I = new int[10];
	
	static double sum;
	static int result;
	
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
			
			result = 0;
			process();
			System.out.print("#"+(inx+1) + " " + result);
			for (int jnx = 0; jnx < M; jnx++) {
				System.out.print(" " + (I[jnx]+1));
			}
			System.out.println();
		}

		if (br != null)
			br.close();
	}
	
	static void process() {
		double avg = sum / (M+1);
		
		int acc = E[0];
		int i_idx = 0;
//		int tired = E[0]; // 피로도 누적
		int muscle = 0; // 근육통 누적

		for (int inx = 1; inx < N-1; inx++) {
			
			if (i_idx < M && acc < avg && acc+E[inx] > avg) {
				if (Math.abs(acc-avg) < Math.abs(acc+E[inx]-avg)) {
					I[i_idx++] = inx-1;
				} else {
					I[i_idx++] = inx;
					inx++;
				}
				acc = 0;
			}
			muscle += acc * E[inx];
			acc += E[inx];
		}

		System.out.println();
	}
}

//날짜	0	1	2	3	4	5	6	7
//피로도	 - 	 6 	 15 		 11 	 13 		 4 
//운동량	 6 	 9 	 7 	 11 	 2 	 16 	 4 	 10 
//근육통(당일)	 - 	 54 	 105 	 - 	 22 	 208 	 - 	 40 
//근육통(누적)	 - 	 54 	 159 	 159 	 181 	 389 	 389 	 429 

