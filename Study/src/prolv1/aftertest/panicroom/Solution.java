package prolv1.aftertest.panicroom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [±‚√‚A-0011] ∆–¥–∑Î
 * DP
 */
public class Solution {
	
	static int MAX = 51;

	static int T, N, M;
	static Node[][] room = new Node[MAX][MAX];
	static int result = 0;

	static class Node implements Comparable<Node> {
		int n;
		int m;
		int c;
		int sum;
		List<int[]> next = new ArrayList<int[]>();
		Node(int n, int m, int c) {
			this.n = n;
			this.m = m;
			this.c = c;
			this.sum = -1;
		}
		@Override
		public String toString() {
			return "Node [n=" + n + ", m=" + m + ", c=" + c + ", sum=" + sum + "]";
		}
		@Override
		public int compareTo(Node o) {
			return this.sum - o.sum;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			for (int inx = 1; inx <= N; inx++) {
				st = new StringTokenizer(br.readLine());
				for (int jnx = 1; jnx <= M; jnx++) {
					int c = Integer.parseInt(st.nextToken());
					room[inx][jnx] = new Node(inx, jnx, c);
					if (inx < N)
						room[inx][jnx].next.add(new int[]{inx+1,jnx});
					if (jnx < M)
						room[inx][jnx].next.add(new int[]{inx,jnx+1});
				}
			}

			dijkstra();

			System.out.println("#"+tnx + " " + room[N][M].sum);
		}

		br.close();
	}

	static void dijkstra() {
		room[1][1].sum = room[1][1].c;
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(room[1][1]);
		
		while (! pq.isEmpty()) {
			Node node = pq.poll();
			
			for (int inx = 0; inx < node.next.size(); inx++) {
				int[] n1 = node.next.get(inx);
				int n = n1[0];
				int m = n1[1];

				Node next = room[n][m];
				if (next.sum == -1 || node.sum + next.c < next.sum) {
					next.sum = node.sum + next.c;
					pq.add(next);
				}
			}
		}
	}
}
