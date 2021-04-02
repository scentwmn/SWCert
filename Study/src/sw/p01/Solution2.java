package sw.p01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [기출P-0088] 화살
 * Hint 참고 풀이
 * IndexTree는 부분합을 계산하기 위함.
 * IndexTree를 활용하는 방법 및 sum값의 노드를 찾아가기 사용.
 */
public class Solution2 {

	private static class Tower implements Comparable<Tower> {
		int idx;
		int height;
		int power;

		@Override
		public int compareTo(Tower o) {
			if (this.height == o.height)
				return this.idx - o.idx;
			return o.height - this.height;
		}

		@Override
		public String toString() {
			return "Tower [idx=" + idx + ", height=" + height + ", power=" + power + "]";
		}
	}
	
	static int T, N;
	static StringTokenizer bdST, poST;
	static Tower[] tower = new Tower[100_001];
	
	static int leafStart;
	static int[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int inx = 1; inx <= T; inx++) {
			N = Integer.parseInt(br.readLine());
			bdST = new StringTokenizer(br.readLine(), " ");
			poST = new StringTokenizer(br.readLine(), " ");
			
			for (int jnx = 1; jnx <= N; jnx++) {
				tower[jnx] = new Tower();
				tower[jnx].idx = jnx;
				tower[jnx].height = Integer.parseInt(bdST.nextToken());
				tower[jnx].power = Integer.parseInt(poST.nextToken());
			}
			
			Arrays.sort(tower, 1, N+1);
			
			// index tree 생성
			leafStart = 1;
			while(leafStart < N)
				leafStart <<= 1;
			tree = new int[leafStart*2];

			long sum = 0L;
			for (int jnx = 1; jnx <= N; jnx++) {
				int find = sum(1, tower[jnx].idx-1) + tower[jnx].power + 1;
				sum += search(find);
				update(tower[jnx].idx);
			}
			System.out.println("#"+inx + " " + sum);
		}
	}

	static void update(int inx) {
		int loc = leafStart + inx - 1;
		int minus = tree[loc];
		while (loc > 0) {
			tree[loc] = tree[loc] - minus + 1;
			loc >>= 1;
		}
	}

	static int sum(int start, int end) {
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
	
	static int search(int count) {
		int loc = 1;
		if (tree[loc] < count)
			return 0;

		while (loc < leafStart*2) {
			if (loc*2 >= leafStart*2)
				break;

			if (tree[loc*2] >= count)
				loc = loc*2;
			else {
				count -= tree[loc*2];
				loc = loc*2+1;
			}
		}

		return loc-leafStart+1;
	}
}
