package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class PreTest17023 {
	
	static int T;
	static int N;
	static int M;
	static int[] A = new int[101];
	static int[] I = new int[101];
	
	static long[] accSum = new long[101];

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\input.txt")));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for (int inx = 0; inx < T; inx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			long sum = 0;
			for (int jnx = 0; jnx < A.length; jnx++) {
				A[jnx] = 0;
				I[jnx] = 0;
			}

			for (int jnx = 1; jnx <= N; jnx++) {
				A[jnx] = Integer.parseInt(st2.nextToken());
				I[jnx] = 0; // 주사맞았는지 여부(1=맞음,0=안맞음)
				sum += A[jnx];
				accSum[jnx] = sum;
			}
			
			double avg = (double)sum / (M+1);
			long muscleSum = 0; // 근육통 합
			int muscleDay = 0;
			int tired = 0; // 피로도
			int injectCnt = 0;
//			boolean isInject = false;
			for (int jnx = 1; jnx <= N; jnx++) {
//				tired += A[jnx-1];

				if (injectCnt < M && (tired + A[jnx]) > avg) {
					if (jnx > 1 && Math.abs(avg-tired) <= Math.abs(avg-tired-A[jnx])) {
						I[jnx-1] = 1;
						injectCnt++;
						tired = 0;

						avg = (double)(sum-accSum[jnx-1]) / (M-injectCnt+1);
					}
					else {
						I[jnx] = 1;
						injectCnt++;
//						isInject = true;
						avg = (double)(sum-accSum[jnx]) / (M-injectCnt+1);
					}
				}
				
				muscleDay = tired * A[jnx];
				muscleSum += muscleDay;

				// 오늘 주사를 맞았다면, 내일 피로도=0
				tired = I[jnx] == 1 ? 0 : tired+A[jnx];
			}
			
//			if (I[0] == 1) {
//				boolean isInject = false;
//				int injectIdx = 1;
//				while (! isInject) {
//					if (I[injectIdx] == 0) {
//						I[injectIdx] = 1;
//						break;
//					}
//					injectIdx++;
//				}
//			}

			bw.write("#"+(inx+1)+ " " + muscleSum);
			int outCnt = 0;
			for (int jnx = 1; jnx <= 100; jnx++) {
				if (I[jnx] == 1) {
					bw.write(" " + jnx);
					outCnt++;
				}
				if (outCnt == M)
					break;
			}
			bw.write('\n');
			bw.flush();
		}
		
		long end = System.currentTimeMillis();
		System.out.println((end-start) + " ms");

	}
}
