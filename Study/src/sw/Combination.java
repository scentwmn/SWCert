package sw;

/**
 * Combination 확률 계산 로직
 * nCr = n-1Cr + n-1Cr-1 의 공식을 사용하여 계산함.
 * 그리고 2^60 의 숫자를 최대값으로 사용하므로 long 타입이면 됨.
 * 하지만, nCr 을 그대로 적용하면 너무 큰 숫자가 나오므로 long 타입을 넘치는 경우가 있으므로 그런경우는 MAX+1 값을 일괄 저장하도록 함.
 * 2^60     : 1,152,921,504,606,846,976
 * long MAX : 9,223,372,036,854,775,807 = 2^63-1
 */
public class Combination {

	static long MAX_X = 1024L * 1024L * 1024L * 1024L * 1024L * 1024L;
	static long[][] combi = new long[1001][1001];

	public static void main(String[] args) {
		
		combi[0][0] = 1;
		combi[1][0] = 1;
		combi[1][1] = 1;
		for (int inx = 2; inx <= 1000; inx++) {
			combi[inx][0] = 1;
			for (int jnx = 1; jnx <= inx; jnx++) {
				combi[inx][jnx] = combi[inx-1][jnx-1] + combi[inx-1][jnx];
				if (combi[inx][jnx] > MAX_X) {
					combi[inx][jnx] = MAX_X + 1;
				}
			}
		}

//		combi[0][0] = 1;
//		combi[1][0] = 1;
//		combi[1][1] = 1;
//		for (int inx = 2; inx <= 1000; inx++) {
//			combi[inx][0] = 1;
//			for (int jnx = 1; jnx <= inx; jnx++) {
//				combi[inx][jnx] = combi[inx-1][jnx-1] + combi[inx-1][jnx];
//				if (combi[inx][jnx] > MAX_X) {
//					combi[inx][jnx] = MAX_X + 1;
//				}
//			}
//		}

	}
}
