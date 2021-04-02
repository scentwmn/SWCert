package sw.p03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * [기출P-0090] [2021년 02월 06일] 부품공장
 * 자체풀이
 */
public class Solution {

	static int MAX = 10;//100_000
	static int T, N;
	static int X, Y;
	static int[][] num = new int[MAX][2];
	static long result = 0L;
	
	static int[] tree = null;
	static int leafStart = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int inx = 1; inx <= T; inx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int jnx = 0; jnx < N; jnx++) {
				num[jnx][0] = Integer.parseInt(st1.nextToken());
			}
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int jnx = 0; jnx < N; jnx++) {
				num[jnx][1] = Integer.parseInt(st2.nextToken());
			}
			
			process();
		
			System.out.println("#"+inx + " " + result);
		}
	}

	static void process() {
		result = 0;
		leafStart = 1;
		
		while (leafStart < N)
			leafStart <<= 1;
		
		tree = new int[leafStart*2];

		// 배열 정렬
		Arrays.sort(num, 0, N, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int sum = 0;
		for (int inx = 0; inx < N; inx++) {
			// 범위 : inx ~ inx + X;
			if (inx == 0) {
				for (int jnx = inx; jnx <= inx + X && jnx < N; jnx++) {
					update(num[jnx][1], 1);
				}
			}
			else {
				if (inx + X < N)
					update(num[inx + X][1], 1);
			}

			// 합산할 위치 결정
			int less = num[inx][1] - Y;
			int greater = num[inx][1] + Y;
			result += subSum(1, less);
			result += subSum(greater, N);

			// 지나간 데이터 삭제처리
			update(num[inx][1], -1);
		}
	}

	// 트리에 데이터 업데이트
	// inx : 1 ~ N
	static void update(int inx, int value) {
		int loc = leafStart+inx-1;
		
		if (loc < leafStart || loc >= leafStart * 2)
			return;

		while (loc > 0) {
			tree[loc] = tree[loc] + value;
			loc >>= 1;
		}
	}

	// 부분합 구하기
	// 1 ~ N
	static int subSum(int start, int end) {
		if (start > end)
			return 0;

		int a = leafStart + start - 1;
		int b = leafStart + end - 1;
		
		int sum = 0;
		while (a <= b) {
			if ((a&1) == 1) {
				sum += tree[a];
				a++;
			}
			if ((b&1) == 0) {
				sum += tree[b];
				b--;
			}
			a >>= 1;
			b >>= 1;
		}

		return sum;
	}
}
