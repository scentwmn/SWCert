import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int T;
	static int N;
	static int M;
	static List<int[]> A;
	static int[] I = new int[101];

//	static int[] accSum = new int[101];

	public static void main(String[] args) throws Exception {
//		long start = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for (int inx = 0; inx < T; inx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int sum = 0;
			A = new LinkedList<int[]>();
			for (int jnx = 0; jnx < N; jnx++) {
				I[jnx] = 0;
			}

			for (int jnx = 0; jnx < N; jnx++) {
				int inp = Integer.parseInt(st2.nextToken());
				A.add(new int[]{jnx, inp});
				I[jnx] = 0; // 주사맞았는지 여부(1=맞음,0=안맞음)
				sum += inp;
//				accSum[jnx] = sum;
			}
			
			double avg = (double)sum / (M+1);
			long muscleSum = 0; // 근육통 합
			int muscleDay = 0;
			int tired = 0; // 피로도
			int injectCnt = 0;

			while (injectCnt < M) {
				int curVal = 0;
				int prevVal = 0;
				tired = 0;

				for (int jnx = 0; jnx < A.size(); jnx++) {
					prevVal = curVal;
					curVal = A.get(jnx)[1];

					if (jnx > 0 && tired >= avg) {
						if (Math.abs(avg-tired) > Math.abs(tired+curVal-avg)) {
							I[A.get(jnx)[0]-1] = 1;
							injectCnt++;

							sum -= curVal;
						}
						else {
							I[A.get(jnx)[0]-2] = 1;
							injectCnt++;

							sum -= prevVal;
						}
						avg = (double)sum / (M-injectCnt+1);
						break;
					}

					if (I[jnx] == 0) {
						tired += curVal;
					} else {
						tired = curVal;
					}
				}
			}

			tired = 0;
			muscleSum  = 0;
			for (int jnx = 0; jnx < A.size(); jnx++) {
				muscleSum += tired * A.get(jnx)[1];
				
				tired += A.get(jnx)[1];
				if (I[jnx]==1)
					tired = 0;
			}

			bw.write("#"+(inx+1)+ " " + muscleSum);
			int outCnt = 0;
			for (int jnx = 1; jnx <= 100 && outCnt < M; jnx++) {
				if (I[jnx] == 1) {
					bw.write(" " + (jnx+1));
					outCnt++;
				}
			}
			bw.write('\n');
			bw.flush();
		}
		
//		long end = System.currentTimeMillis();
//		System.out.println((end-start) + " ms");

	}
}
