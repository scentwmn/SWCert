package sw.p02;

import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args) {
//		PriorityQueue<Node> a = new PriorityQueue<Node>(new Comparator<Node>() {
//
//			@Override
//			public int compare(Node o1, Node o2) {
//				return o1.cost - o2.cost;
//			}
//		});
		PriorityQueue<Node> a = new PriorityQueue<Node>();
		
		a.add(new Node(1, 8));
		a.add(new Node(2, 6));
		a.add(new Node(3, 9));
		a.add(new Node(4, 12));
		a.add(new Node(5, 3));
		
		while (! a.isEmpty()) {
			System.out.println(a.poll());
		}
	}
	
	private static class Node implements Comparable<Node> {
		int dest;
		int cost;

		public Node(int dest, int cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost,o.cost);
		}

		@Override
		public String toString() {
			return "Node [dest=" + this.dest + ", cost=" + this.cost + "]";
		}
	}
}
