package sw.p01;

/**
 * 부분합을 구하기 위함.
 */
public class IndexTree {
	static int N = 20;
	static int len = 1;

	static int[] num = null;
	
	public static void main(String[] args) {
		
		while (len < N)
			len <<= 1;

		num = new int[len*2];
		for (int inx = 0; inx < N; inx++) {
			num[len+inx] = (int)(Math.random()*1000) % 2;
		}
		
		initSum();
		
//		System.out.println();
//		updateSum(1, 1);
//		System.out.println();
//		int sub = subSum(1, 5);
//		System.out.println(sub);
		
		int next = binSearch(1, 1, N, 4);
		System.out.println(next);

		int sub = subSum(1, next);
		System.out.println(sub);
	}
	
	// inxS : sub sum시작 위치(고정)
	// inxEA : sub sum 끝 위치 범위시작
	// inxEB : sub sum 끝 위치 범위끝
	// po : 찾는 sub sum값
	static int binSearch(int inxS, int inxEA, int inxEB, int po) {
		int a = subSum(inxS, inxEA);
		int b = subSum(inxS, inxEB);
		
		if (a == po)
			return inxEA;
		else if (b == po)
			return inxEB;
		else if (po < a)
			return 0;
		else if (a < po && po < b) {
			int inxC = inxEA+(inxEB-inxEA)/2;
			int c = subSum(inxS, inxC);
			if (c == po)
				return inxC;
			else if (c < po)
				return binSearch(inxS, inxC, inxEB, po);
			else if (c > po)
				return binSearch(inxS, inxEA, inxC, po);
		}
		else if (b < po) {
			return 0;
		}

		return 0;
	}

	static void initSum() {
		int inx = len;
		
		while (inx >= 1) {
			for(int jnx = inx; jnx < inx*2; jnx+=2) {
				num[jnx/2] = num[jnx]+num[jnx+1];
			}
			inx /= 2;
		}
	}

	static void updateSum(int inx, int val) {
		int loc = inx+len-1;
		do {
			num[loc] = val;
			loc /= 2;
			val = num[loc*2]+num[loc*2+1];
		} while (loc > 0);
	}

	static int subSum(int i, int j) {
		int a = i + len - 1;
		int b = j + len - 1;

		int sum = 0;
		while (a < b) {
			if ((a&1) == 1) {
				// 시작이 홀수라면
				sum += num[a];
				a++;
			}
			if ((b&1) == 0) {
				// 끝이 짝수라면
				sum += num[b];
				b--;
			}
			a /= 2;
			b /= 2;
		}
		
		if (a == b)
			sum += num[a];
		return sum;
	}
}
