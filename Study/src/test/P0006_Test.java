package test;

import java.util.ArrayList;
import java.util.List;

/**
 * 아름다운 비트문자열
 */
public class P0006_Test {
	
	static final long MOD = 1000000007;
	static long[][] combi = new long[1001][1001];

	public static void main(String[] args) {
//		System.out.println(combination(4,2));
//		System.out.println(combination(3,2));
		
		long start = System.currentTimeMillis();
		for (int inx = 1; inx <= 1000; inx++) {
			for (int jnx = 1; jnx <= 1000; jnx++) {
				combi[inx][jnx] = combi(inx, jnx);
			}
		}
		System.out.println(System.currentTimeMillis() - start);

//		for (int inx = 2; inx < 20; inx++) {
//			long c1 = combination(40, inx);
//			long c2 = combi(40, inx);
//			
//			if (c1 != c2) {
//				System.out.println("combination : " + c1);
//				System.out.println("combi : " + c2);
//			}
//		}
//		
//		System.out.println("END");
	}


	static long combination(int a, int b) {
		// 카운트, 3C2 구하기
		long temp = 1L;
		for (int jnx = 1; jnx <= b; jnx++) {
			long up = ((long)a-(long)jnx+(long)1);
			long down = (long)jnx;

			temp = temp * up / down;
		}
		
		return temp;
	}
	
	static long combi(int n, int r) {
		long a = 1;
		for (int i = 1; i <= n; i++) {
			a *= i;
			a %= MOD;
		}
		long b = 1;
		for (int i = 1; i <= r; i++) {
			b *= i;
			b %= MOD;
		}
		for (int i = 1; i <= n - r; i++) {
			b *= i;
			b %= MOD;
		}
		
		long m = MOD - 2;
		long Ans = 1;
		while (m > 0) {
			if (m%2 == 1) {
				Ans = (Ans * b) % MOD;
			}
			b = (b*b) % MOD;
			m /= 2;
		}
		Ans = (Ans*a) % MOD;
		return Ans;
	}

	public static List<int[]> generate(int n, int r) {
	    List<int[]> combinations = new ArrayList<>();
	    helper(combinations, new int[r], 0, n - 1, 0);
	    return combinations;
	}

	private static void helper(List<int[]> combinations, int data[], int start, int end, int index) {
	    if (index == data.length) {
	        int[] combination = data.clone();
	        combinations.add(combination);
	    } else {
	        int max = Math.min(end, end + 1 - data.length + index);
	        for (int i = start; i <= max; i++) {
	            data[index] = i;
	            helper(combinations, data, i + 1, end, index + 1);
	        }
	    }
	}
}
