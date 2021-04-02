package sw.p01;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * [기출P-0088] 화살
 * 자체풀이
 */
public class Solution {

	static int MAX = 100001;
	
	static int T, N;
	static int[][] H = new int[MAX][3]; // 건물번호, 건물높이, 힘

	static int[][] tree = new int[262144][2];
	static int len;

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(new FileInputStream("D:\\Dev\\sample_input.txt"));
		T = scan.nextInt();
		
		long start = System.currentTimeMillis();

		for (int inx = 1; inx <= T; inx++) {
			N = scan.nextInt();
			
			for (int jnx = 1; jnx <= N; jnx++) {
				H[jnx][0] = jnx;
				H[jnx][1] = scan.nextInt();
			}
			for (int jnx = 1; jnx <= N; jnx++) {
				H[jnx][2] = scan.nextInt();
			}

			long s1 = System.currentTimeMillis();
			// 정렬하여 높이 큰 빌딩부터 처리
			Arrays.sort(H, 1, N+1, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] == o2[1]) {
						// 높이가 같으면, 작은 번호부터
						return o1[0] - o2[0];
					}
					return o2[1] - o1[1];
				}
			});
			long e1 = System.currentTimeMillis();
			System.out.println("sort : " + (e1-s1));
			
			// index tree생성
			len = 1;
			while (len < N)
				len <<= 1;
			
			for (int jnx = 0; jnx < len*2; jnx++) {
				tree[jnx][0]=0;
				tree[jnx][1]=0;
			}
			

			int no, po, leftHigh;
			for (int jnx = 1; jnx <= N; jnx++) {
				no = H[jnx][0];
				po = H[jnx][2];
				
				// 왼쪽에 높은 건물 수
				leftHigh = subSum(1, no-1);
				
				updateSum(no, search(leftHigh + po + 1), 1);
				updateSum(no, 1, 0);
			}

			System.out.println("#"+inx + " " + tree[1][1]);
		}

		long end = System.currentTimeMillis();
		System.out.println("elapsed : " + (end-start));

		scan.close();
	}

	static int search(int count) {
		int pos = 1;
		if (tree[pos][0] < count)
			return 0;
		else {
			while (pos < len*2) {
				if (pos*2 >= len*2)
					break;
				if (tree[pos*2][0] >= count)
					pos *= 2;
				else {
					count -= tree[pos*2][0];
					pos = pos * 2 + 1;
				}
			}
		}
		return pos - len + 1;
	}

	static int subSum(int i, int j) {
		if (i > j || i > N || j > N)
			return 0;

		int a = len + i - 1;
		int b = len + j - 1;
		
		int sum = 0;
		while (a < b) {
			if ((a&1) == 1) {
				sum += tree[a][0];
				a++;
			}
			if ((b&1) == 0) {
				sum += tree[b][0];
				b--;
			}
			a /= 2;
			b /= 2;
		}
		
		if (a == b)
			sum += tree[a][0];
		return sum;
	}

	static void updateSum(int inx, int val, int fs) {
		int loc = len + inx - 1;
		int minus = tree[loc][fs];
		while (loc > 0) {
			tree[loc][fs] = tree[loc][fs] - minus + val;
			loc /= 2;
		}
	}
}
