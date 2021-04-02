package sw;

/**
 * Combination Ȯ�� ��� ����
 * nCr = n-1Cr + n-1Cr-1 �� ������ ����Ͽ� �����.
 * �׸��� 2^60 �� ���ڸ� �ִ밪���� ����ϹǷ� long Ÿ���̸� ��.
 * ������, nCr �� �״�� �����ϸ� �ʹ� ū ���ڰ� �����Ƿ� long Ÿ���� ��ġ�� ��찡 �����Ƿ� �׷����� MAX+1 ���� �ϰ� �����ϵ��� ��.
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
