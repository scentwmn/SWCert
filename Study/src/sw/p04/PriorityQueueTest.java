package sw.p04;

import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args) {
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		
		pq.add(new Point(5, 20));
		pq.add(new Point(4, 10));
		pq.add(new Point(3, 80));
		pq.add(new Point(2, 70));
		pq.add(new Point(1, 30));
		
		while(! pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return this.y - o.y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
}
