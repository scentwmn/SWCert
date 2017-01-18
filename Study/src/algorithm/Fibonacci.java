package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fibonacci {
	
	static int T;
	static int N;
	static long[] FN = new long[20000000];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int inx = 0; inx < T; inx++) {
			N = Integer.parseInt(br.readLine());

			System.out.println("#"+(inx+1) + " " + process());
		}
		
		if (br != null)
			br.close();
	}
	
	static long process() {
		if (N % 500 == 0 && FN[N / 500] != 0) {
			return FN[N / 500];
		}

		long ret = getFibonacci(N);
		if (N % 500 == 0) {
			FN[N / 500] = ret;
		}

		return ret;
	}
	
	static long getFibonacci(int num) {
		if (num == 0)
			return 0;
		if (num == 1)
			return 1;
		
		if (num % 500 == 0 && FN[num / 500] != 0)
			return FN[num / 500];
		
		return getFibonacci(num-1) + getFibonacci(num-2);
	}
}
