package sw.p04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [기출P-0094][2021년 02월 27일] 나이트
 * ==> 실패
 */
public class Solution {

	static int MAX = 500;
	static int T, N, M;
	static int SX, SY;
	static int EX, EY;
	
	static Point[][] matrix = new Point[MAX][MAX];
	
	private static class Point implements Comparable<Point> {
		// 좌표, 타입
		int x;
		int y;
		char type;
		// 누적피로도
		int labor = -1;
		// 멀티경로 수
		int multiCnt = 0;
		// 이동해온 경로 저장
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

			// 처리 시작
			process();

			// 결과
			System.out.print("#"+inx + " " + matrix[EX][EY].labor);
			if (matrix[EX][EY].labor != -1) {
				System.out.print(" " + matrix[EX][EY].multiCnt);
			}
			System.out.println();
		}
	}

	static void process() {
		// 시작점 초기화
		matrix[SX][SY].labor = 0;
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.add(matrix[SX][SY]);
		
		final int max = N * M;
		int moveCnt = 0;
		
		while (! pq.isEmpty() && moveCnt < max) {
			moveCnt++;
			
			Point p = pq.poll();

			// 8개 방향 이동가능 위치 확인.
			// 1) 위 2칸, 좌우 1칸
			if (isMovable(p, p.x-2, p.y-1)) {
				move(p, matrix[p.x-2][p.y-1]);
				pq.add(matrix[p.x-2][p.y-1]);
			}
			if (isMovable(p, p.x-2, p.y+1)) {
				move(p, matrix[p.x-2][p.y+1]);
				pq.add(matrix[p.x-2][p.y+1]);
			}
			// 2) 위 1칸, 좌우 2칸
			if (isMovable(p,  p.x-1, p.y-2)) {
				move(p, matrix[p.x-1][p.y-2]);
				pq.add(matrix[p.x-1][p.y-2]);
			}
			if (isMovable(p,  p.x-1, p.y+2)) {
				move(p, matrix[p.x-1][p.y+2]);
				pq.add(matrix[p.x-1][p.y+2]);
			}
			// 3) 아래 1칸, 좌우 2칸
			if (isMovable(p,  p.x+1, p.y-2)) {
				move(p, matrix[p.x+1][p.y-2]);
				pq.add(matrix[p.x+1][p.y-2]);
			}
			if (isMovable(p,  p.x+1, p.y+2)) {
				move(p, matrix[p.x+1][p.y+2]);
				pq.add(matrix[p.x+1][p.y+2]);
			}
			// 4) 아래 2칸, 좌우 1칸
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

	// 이동 가능 위치 확인
	static boolean isMovable(Point bef, int x, int y) {
		if (x < 0 || x >= N)
			return false;
		if (y < 0 || y >= M)
			return false;
		// 갈수 없는 곳
		if (matrix[x][y].type == '#')
			return false;
		// 현재의 노동값보다 큰 값이면.. 이동 불가
		if (matrix[x][y].labor != -1) {
			if (matrix[x][y].type == 'R' && bef.labor > matrix[x][y].labor) {
				// 휴게소인 경우, 이전 피로도가 큰 값이면 이동불가
				return false;
			}
			else if (matrix[x][y].type != 'R' && bef.labor+1 > matrix[x][y].labor) {
				return false;
			}
		}
		// 반복 경로
		if (matrix[x][y].path.containsKey(bef.x+","+bef.y))
			return false;

		return true;
	}

	static void move(Point bef, Point aft) {
		if (aft.x == 1 && aft.y == 2)
			System.out.print("");

		int r = 0;
		// 피로 1 쌓임. 휴게소는 쌓이지 않음.
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

		// 이동경로 정보에 추가
		aft.path.putAll(bef.path);
	}
}
