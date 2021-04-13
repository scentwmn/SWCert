package sw.p04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [����P-0094][2021�� 02�� 27��] ����Ʈ
 * ==> ����
 */
public class Solution2 {

	static int MAX = 500;
	static int T, N, M;
	static int SX, SY;
	static int EX, EY;
	
	static Point[][] matrix = new Point[MAX][MAX];
	
	private static class Point implements Comparable<Point> {
		// ��ǥ, Ÿ��
		int x;
		int y;
		char type;
		// �����Ƿε�
		int labor = 999_999_999;
		// ��Ƽ��� ��
		int multiCnt = 0;

		Point(int x, int y, char type, int multiCnt) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.multiCnt = multiCnt;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", type=" + type + ", labor=" + labor + ", multiCnt=" + multiCnt
					+ "]";
		}

		@Override
		public int compareTo(Point o) {
			if (this.labor == o.labor) {
				if (this.type == '.')
					return -1;
				if (o.type == '.')
					return 1;
			}
			return this.labor - o.labor;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int inx = 1; inx <= T; inx++) {
			StringTokenizer nmST = new StringTokenizer(br.readLine());
			N = Integer.parseInt(nmST.nextToken());
			M = Integer.parseInt(nmST.nextToken());
			
			for (int jnx = 0; jnx < N; jnx++) {
				String line = br.readLine();
				for (int knx = 0; knx < M; knx++) {
					matrix[jnx][knx] = new Point(jnx, knx, line.charAt(knx), 0);
					if (line.charAt(knx) == 'S') {
						SX = jnx;
						SY = knx;
					}
					if (line.charAt(knx) == 'E') {
						EX = jnx;
						EY = knx;
					}
				}
			}

			// ó�� ����
			process();

			// ���
			int answer = matrix[EX][EY].labor;
			if (answer == 999_999_999) {
				answer = -1;
			}
			System.out.print("#"+inx + " " + answer);
			if (answer != -1) {
				System.out.print(" " + matrix[EX][EY].multiCnt);
			}
			System.out.println();
		}
	}

	static void process() {
		// ������ �ʱ�ȭ
		matrix[SX][SY].labor = 0;
		matrix[SX][SY].multiCnt = 1;
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.add(matrix[SX][SY]);
		
		while (! pq.isEmpty()) {
			
			Point p = pq.poll();

			// 8�� ���� �̵����� ��ġ Ȯ��.
			// 1) �� 2ĭ, �¿� 1ĭ
			if (isMovable(p, p.x-2, p.y-1)) {
				int r = 0; if (matrix[p.x-2][p.y-1].type == 'R') r = -1;
				boolean isAdd = false;
				if (p.labor+1+r < matrix[p.x-2][p.y-1].labor) {
					isAdd = true;
				}
				move(p, matrix[p.x-2][p.y-1]);
				if (isAdd)
					pq.add(matrix[p.x-2][p.y-1]);
			}
			if (isMovable(p, p.x-2, p.y+1)) {
				int r = 0; if (matrix[p.x-2][p.y+1].type == 'R') r = -1;
				boolean isAdd = false;
				if (p.labor+1+r < matrix[p.x-2][p.y+1].labor) {
					isAdd = true;
				}
				move(p, matrix[p.x-2][p.y+1]);
				if (isAdd)
					pq.add(matrix[p.x-2][p.y+1]);
			}
			// 2) �� 1ĭ, �¿� 2ĭ
			if (isMovable(p,  p.x-1, p.y-2)) {
				int r = 0; if (matrix[p.x-1][p.y-2].type == 'R') r = -1;
				boolean isAdd = false;
				if (p.labor+1+r < matrix[p.x-1][p.y-2].labor) {
					isAdd = true;
				}
				move(p, matrix[p.x-1][p.y-2]);
				if (isAdd)
					pq.add(matrix[p.x-1][p.y-2]);
			}
			if (isMovable(p,  p.x-1, p.y+2)) {
				int r = 0; if (matrix[p.x-1][p.y+2].type == 'R') r = -1;
				boolean isAdd = false;
				if (p.labor+1+r < matrix[p.x-1][p.y+2].labor) {
					isAdd = true;
				}
				move(p, matrix[p.x-1][p.y+2]);
				if (isAdd)
					pq.add(matrix[p.x-1][p.y+2]);
			}
			// 3) �Ʒ� 1ĭ, �¿� 2ĭ
			if (isMovable(p,  p.x+1, p.y-2)) {
				int r = 0; if (matrix[p.x+1][p.y-2].type == 'R') r = -1;
				boolean isAdd = false;
				if (p.labor+1+r < matrix[p.x+1][p.y-2].labor) {
					isAdd = true;
				}
				move(p, matrix[p.x+1][p.y-2]);
				if (isAdd)
					pq.add(matrix[p.x+1][p.y-2]);
			}
			if (isMovable(p,  p.x+1, p.y+2)) {
				int r = 0; if (matrix[p.x+1][p.y+2].type == 'R') r = -1;
				boolean isAdd = false;
				if (p.labor+1+r < matrix[p.x+1][p.y+2].labor) {
					isAdd = true;
				}
				move(p, matrix[p.x+1][p.y+2]);
				if (isAdd)
					pq.add(matrix[p.x+1][p.y+2]);
			}
			// 4) �Ʒ� 2ĭ, �¿� 1ĭ
			if (isMovable(p, p.x+2, p.y-1)) {
				int r = 0; if (matrix[p.x+2][p.y-1].type == 'R') r = -1;
				boolean isAdd = false;
				if (p.labor+1+r < matrix[p.x+2][p.y-1].labor) {
					isAdd = true;
				}
				move(p, matrix[p.x+2][p.y-1]);
				if (isAdd)
					pq.add(matrix[p.x+2][p.y-1]);
			}
			if (isMovable(p, p.x+2, p.y+1)) {
				int r = 0; if (matrix[p.x+2][p.y+1].type == 'R') r = -1;
				boolean isAdd = false;
				if (p.labor+1+r < matrix[p.x+2][p.y+1].labor) {
					isAdd = true;
				}
				move(p, matrix[p.x+2][p.y+1]);
				if (isAdd)
					pq.add(matrix[p.x+2][p.y+1]);
			}
		}
	}

	// �̵� ���� ��ġ Ȯ��
	static boolean isMovable(Point bef, int x, int y) {
		if (x < 0 || x >= N)
			return false;
		if (y < 0 || y >= M)
			return false;
		// ���� ���� ��
		if (matrix[x][y].type == '#')
			return false;
		if (matrix[x][y].type == 'R' && bef.type == 'R')
			return false;
		if (matrix[x][y].type == 'R' && bef.labor > matrix[x][y].labor) {
			// �ްԼ��� ���, ���� �Ƿε��� ū ���̸� �̵��Ұ�
			return false;
		}
		else if (matrix[x][y].type != 'R' && bef.labor+1 > matrix[x][y].labor) {
			return false;
		}

		return true;
	}

	static void move(Point bef, Point aft) {
		int r = 0;
		// �Ƿ� 1 ����. �ްԼҴ� ������ ����.
		if (aft.type == 'R') {
			r = -1;
		}
//		if (aft.labor == bef.labor + 1 + r) {
			aft.multiCnt = aft.multiCnt + bef.multiCnt;
//		} else {
//			aft.multiCnt = bef.multiCnt;
//		}
		aft.multiCnt = aft.multiCnt % 1_000_000_007;
		aft.labor = bef.labor + 1 + r;
	}
}
