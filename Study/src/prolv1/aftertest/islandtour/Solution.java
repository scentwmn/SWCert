package prolv1.aftertest.islandtour;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [기출A-0007] 섬나라 여행
 * 그래프 BFS
 */
public class Solution {

	static int T, N, L;
	static int[][] loc = new int[2][1001];
	static int[] days = new int[1001];
	static boolean[] visited = new boolean[1001];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			for (int inx = 1; inx <= N; inx++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				loc[0][inx] = x;
				loc[1][inx] = y;
			}
			
			Arrays.fill(visited, false);
			Arrays.fill(days, -1);
			process();
			
			System.out.println("#"+tnx + " " + days[N]);
		}
	}
	
	static void process() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		days[1] = 0;
		visited[1] = true;
		
		while(! queue.isEmpty()) {
			int curr = queue.poll();
			
			if (curr == N)
				break;
			
			for (int inx = 1; inx <= N; inx++) {
				if (isMovable(curr, inx)) {
					visited[inx] = true;
					if (days[inx] == -1 || days[curr]+1 < days[inx]) {
						days[inx] = days[curr]+1;
						queue.add(inx);
					}
				}
			}
		}
	}
	
	static boolean isMovable(int fromIdx, int toIdx) {
		if (fromIdx == toIdx)
			return false;

		int x1 = loc[0][fromIdx];
		int y1 = loc[1][fromIdx];

		int x2 = loc[0][toIdx];
		int y2 = loc[1][toIdx];

		if ((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) <= L*L)
			return true;
		
		return false;
	}
}
