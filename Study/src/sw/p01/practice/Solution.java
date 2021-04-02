package sw.p01.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int MAX = 100_001;
	static int T, N;
	static Tower[] towers = new Tower[MAX];
	
	static int result = 0;
	static int[] tree;
	static int leafStart;

	private static class Tower implements Comparable<Tower> {
		int idx;
		int height;
		int power;

		@Override
		public int compareTo(Tower o) {
			// 높이가 같으면, 왼쪽 빠른 순서로 정렬
			if (this.height == o.height) {
				return Integer.compare(this.idx, o.idx);
			}
			return Integer.compare(o.height, this.height);
		}

		@Override
		public String toString() {
			return "Tower [idx=" + idx + ", height=" + height + ", power=" + power + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			N = Integer.parseInt(br.readLine());
			
			// 타워 높이
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int inx = 1; inx <= N; inx++) {
				towers[inx] = new Tower();
				towers[inx].idx = inx;
				towers[inx].height = Integer.parseInt(st.nextToken());
			}
			// 화살 힘
			st = new StringTokenizer(br.readLine());
			for (int inx = 1; inx <= N; inx++) {
				towers[inx].power = Integer.parseInt(st.nextToken());
			}

			// 처리
			process();
			
			System.out.println("#"+tnx + " " + result);
		}
	}
	
	static void process() {
		result = 0;
		
		// 인덱스 트리 생성
		leafStart = 1;
		while(leafStart < N)
			leafStart <<= 1;
		
		// 트리 생성
		tree = new int[leafStart*2];
		
		// 높이 큰 순서로 정렬
		Arrays.sort(towers, 1, N+1);
		
		// 인덱스 트리에 값 하나씩 넣으면서.. 계산
		for (int inx = 1; inx <= N; inx++) {
			// 점수 먼저 계산 후, 트리에 1 업데이트함.

			// 높은 빌딩수 의.
			int higher = inx - 1;
			int leftHigh = subsum(1, towers[inx].idx-1);
//			int rightHigh = higher = leftHigh;
			if (towers[inx].power+leftHigh < higher) {
				int s = search(towers[inx].power+leftHigh+1);
				result += s;
			}
			
			// 트리에 없데이트
			update(towers[inx].idx, 1);
		}
	}
	
	// 특정 값까지 찾음.
	static int search(int value) {
		int loc = 1;
		
		if (tree[1] < value)
			return 0;
		
		int sum = 0;
		
		while (loc < leafStart * 2) {
			if (loc*2 >= leafStart*2-1)
				break;
			if (value <= sum+tree[loc*2]) {
				loc *= 2;
			}
			else {
				sum += tree[loc*2];
				loc = loc * 2 + 1;
			}
		}
		
		return loc - leafStart + 1;
	}
	
	// 인덱스 트리 값 업데이트
	static void update(int idx, int value) {
		// idx : leafStart ~ N 까지
		if (idx < 1 || idx > N)
			return;

		// 트리에서의 위치
		int loc = leafStart + idx - 1;
		int minus = tree[loc];
		while (loc > 0) {
			tree[loc] = tree[loc] - minus + value;
			loc >>= 1;
		}
	}
	
	// 트리 부분합
	static int subsum(int start, int end) {
		if (start > end)
			return 0;
		
		if (start < 1)
			start = leafStart;
		if (end > N)
			end = N;
		
		// 트리에서의 위치
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
