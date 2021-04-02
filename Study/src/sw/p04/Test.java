package sw.p04;

import java.util.HashMap;
import java.util.Map;

public class Test {

	private static class Point {
		// 좌표, 타입
		int x;
		int y;
		char type;
		// 멀티경로 수
		int multiCnt;
		// 이동해온 경로 저장
		Map<Point, Integer> path = new HashMap<Point, Integer>();

		Point(int x, int y, char type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
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
		Point a = new Point(1, 1, 'A');
		Point b = new Point(1, 1, 'B');
		Point c = new Point(1, 1, 'A');
		
		Map<Point, Integer> map = new HashMap<Point, Integer>();
		map.put(a, 1);
		
		System.out.println(map.containsKey(a));
		System.out.println(map.containsKey(b));
		System.out.println(map.containsKey(c));
	}
}
