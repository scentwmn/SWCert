package pro.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Maze {

	static int N, M;
	static int[][] graph = new int[1000][1000];
	static int[][] dist = new int[1000][1000];
	static int[][] visited = new int[1000][1000];
	static Queue<String> stack = new LinkedList<String>();
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		for (int inx = 0; inx < N; inx++) {
			for (int jnx = 0; jnx < M; jnx++) {
				graph[inx][jnx] = scan.nextInt();
				dist[inx][jnx] = 5000000;
			}
		}

		dist[N-1][0] = 0;
		stack.add(getLoc(N-1,0));
		BFS();
		
		System.out.println(dist[0][M-1]);
		scan.close();
	}
	
	static void BFS() {
		while(! stack.isEmpty()) {
			int[] loc = getLoc(stack.poll());
			int inx = loc[0];
			int jnx = loc[1];
			
			if (visited[inx][jnx] == 1)
				continue;

			// 위
			if (inx > 0 && graph[inx-1][jnx] == 0) {
				dist[inx-1][jnx] = dist[inx][jnx]+1 < dist[inx-1][jnx] ? dist[inx][jnx]+1 : dist[inx-1][jnx];
				stack.add(getLoc(inx-1,jnx));
			}
			// 아래
			if (inx < N-1 && graph[inx+1][jnx] == 0) {
				dist[inx+1][jnx] = dist[inx][jnx]+1 < dist[inx+1][jnx] ? dist[inx][jnx]+1 : dist[inx+1][jnx];
				stack.add(getLoc(inx+1,jnx));
			}
			// 왼쪽
			if (jnx > 0 && graph[inx][jnx-1] == 0) {
				dist[inx][jnx-1] = dist[inx][jnx]+1 < dist[inx][jnx-1] ? dist[inx][jnx]+1 : dist[inx][jnx-1];
				stack.add(getLoc(inx,jnx-1));
			}
			// 오른쪽
			if (jnx < M-1 && graph[inx][jnx+1] == 0) {
				dist[inx][jnx+1] = dist[inx][jnx]+1 < dist[inx][jnx+1] ? dist[inx][jnx]+1 : dist[inx][jnx+1];
				stack.add(getLoc(inx,jnx+1));
			}
			visited[inx][jnx] = 1;
		}
	}
	
	static String getLoc(int inx, int jnx) {
		return String.valueOf(inx)+","+String.valueOf(jnx);
	}
	static int[] getLoc(String loc) {
		int inx = Integer.parseInt(loc.substring(0, loc.indexOf(',')));
		int jnx = Integer.parseInt(loc.substring(loc.indexOf(',')+1));
		
		return new int[]{inx,jnx};
	}
}
