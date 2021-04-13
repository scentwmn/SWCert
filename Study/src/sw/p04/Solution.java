package sw.p04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [����P-0094][2021�� 02�� 27��] ����Ʈ
 * ==> ����
 */
public class Solution {

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
		int labor = -1;
		// ��Ƽ��� ��
		int multiCnt = 0;
		// �̵��ؿ� ��� ����
		Map<String, Integer> path = new HashMap<String, Integer>();
//		int[] befPoint = new int[2];

		Point(int x, int y, char type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", type=" + type + "]";
		}

		@Override
		public int compareTo(Point o) {
			if (this.type == 'R')
				return -1;
			else if (o.type == 'R')
				return 1;
			return Integer.compare(this.labor, o.labor);
		}

		@Override
		public boolean equals(Object obj) {
			if (! (obj instanceof Point) )
				return false;
			if (this.x == ((Point)obj).x && this.y == ((Point)obj).y)
				return true;

			return false;
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
					matrix[jnx][knx] = new Point(jnx, knx, line.charAt(knx));
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
			System.out.print("#"+inx + " " + matrix[EX][EY].labor);
			if (matrix[EX][EY].labor != -1) {
				System.out.print(" " + matrix[EX][EY].multiCnt);
			}
			System.out.println();
		}
	}

	static void process() {
		// ������ �ʱ�ȭ
		matrix[SX][SY].labor = 0;
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.add(matrix[SX][SY]);
		
		final int max = N * M;
		int moveCnt = 0;
		
		while (! pq.isEmpty() && moveCnt < max) {
			moveCnt++;
			
			Point p = pq.poll();

			// 8�� ���� �̵����� ��ġ Ȯ��.
			// 1) �� 2ĭ, �¿� 1ĭ
			if (isMovable(p, p.x-2, p.y-1)) {
				move(p, matrix[p.x-2][p.y-1]);
				pq.add(matrix[p.x-2][p.y-1]);
			}
			if (isMovable(p, p.x-2, p.y+1)) {
				move(p, matrix[p.x-2][p.y+1]);
				pq.add(matrix[p.x-2][p.y+1]);
			}
			// 2) �� 1ĭ, �¿� 2ĭ
			if (isMovable(p,  p.x-1, p.y-2)) {
				move(p, matrix[p.x-1][p.y-2]);
				pq.add(matrix[p.x-1][p.y-2]);
			}
			if (isMovable(p,  p.x-1, p.y+2)) {
				move(p, matrix[p.x-1][p.y+2]);
				pq.add(matrix[p.x-1][p.y+2]);
			}
			// 3) �Ʒ� 1ĭ, �¿� 2ĭ
			if (isMovable(p,  p.x+1, p.y-2)) {
				move(p, matrix[p.x+1][p.y-2]);
				pq.add(matrix[p.x+1][p.y-2]);
			}
			if (isMovable(p,  p.x+1, p.y+2)) {
				move(p, matrix[p.x+1][p.y+2]);
				pq.add(matrix[p.x+1][p.y+2]);
			}
			// 4) �Ʒ� 2ĭ, �¿� 1ĭ
			if (isMovable(p, p.x+2, p.y-1)) {
				move(p, matrix[p.x+2][p.y-1]);
				pq.add(matrix[p.x+2][p.y-1]);
			}
			if (isMovable(p, p.x+2, p.y+1)) {
				move(p, matrix[p.x+2][p.y+1]);
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
		// ������ �뵿������ ū ���̸�.. �̵� �Ұ�
		if (matrix[x][y].labor != -1) {
			if (matrix[x][y].type == 'R' && bef.labor > matrix[x][y].labor) {
				// �ްԼ��� ���, ���� �Ƿε��� ū ���̸� �̵��Ұ�
				return false;
			}
			else if (matrix[x][y].type != 'R' && bef.labor+1 > matrix[x][y].labor) {
				return false;
			}
		}
		// �ݺ� ���
		if (matrix[x][y].path.containsKey(bef.x+","+bef.y))
			return false;

		return true;
	}

	static void move(Point bef, Point aft) {
		if (aft.x == 1 && aft.y == 2)
			System.out.print("");

		int r = 0;
		// �Ƿ� 1 ����. �ްԼҴ� ������ ����.
		if (aft.type == 'R') {
			r = -1;
		}
		if (aft.labor == -1) {
			aft.multiCnt = 1;
		}
		else if (aft.labor == bef.labor + 1 + r) {
			aft.multiCnt += bef.multiCnt;
		}
		aft.labor = bef.labor + 1 + r;

		// �̵���� ������ �߰�
		aft.path.putAll(bef.path);
	}
}
