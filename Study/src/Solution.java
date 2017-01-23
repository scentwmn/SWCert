import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	
	static int T;
	static int N;
	static int M;
	static double[] E = new double[100];
	static int[] I = new int[11];
	
	static double sum;
	static double muscle;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for (int inx = 0; inx < T; inx++) {
			String[] nm = br.readLine().split(" ");
			N = Integer.parseInt(nm[0]);
			M = Integer.parseInt(nm[1]);
			
			sum = 0;
			String[] estr = br.readLine().split(" ");
			for (int jnx = 0; jnx < N; jnx++) {
				E[jnx] = Double.parseDouble(estr[jnx]);
				sum += E[jnx];
			}
			
			muscle = 0;

			process();
			System.out.print("#"+(inx+1) + " " + (long)muscle);
			for (int jnx = 0; jnx < M; jnx++) {
				System.out.print(" " + (I[jnx]+1));
			}
			System.out.println();
		}
	}

	static void process() {
		final double avg = (double)sum / (M+1);
		
		double tired = 0; // 누적 피로도
		int i_idx = 0; // 주사맞은 횟수
		
		double nextTired = 0;

		for (int inx = 0; inx < N; inx++) {
			muscle += tired * E[inx];
			nextTired = tired+E[inx];

			if (nextTired >= avg) {
				if ((avg-tired) <= (nextTired-avg)) {
					I[i_idx++] = inx-1;

					if (inx < N-1)
						muscle -= tired * E[inx];

					tired = E[inx];
				} else {
					I[i_idx++] = inx;
					tired = 0;
				}
			}
			else {
				tired += E[inx];
			}

		}
	}

}
