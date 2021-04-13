package sw.pretest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * ø˙»¶ Ω√∞£ ¥‹√‡ »Æ¿Œ
 */
public class Solution210406_2 {

	static int T, N, M, W, S;
	static List<Route> graph = new ArrayList<Route>();
	
	static boolean isGoPast = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			graph.clear();
			// µµ∑Œ
			for (int inx = 1; inx <= M; inx++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				graph.add(new Route(s, e,t));
				graph.add(new Route(e, s,t));
			}
			// ø˙»¶
			for (int inx = 1; inx <= W; inx++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				graph.add(new Route(s, e,t*-1));
				S = s;
			}

			// √≥∏Æ
			bellmanford();

			System.out.println("#"+tnx + " " + (isGoPast ? "YES" : "NO"));
		}
	}

	static void bellmanford() {
		int edgeCnt = M*2 + W;

		int[] time = new int[N+1];
		Arrays.fill(time, Integer.MAX_VALUE);

		time[S] = 0;
		for (int inx = 1; inx <= N-1; inx++) {
			for (int jnx = 0; jnx < edgeCnt; jnx++) {
				Route r = graph.get(jnx);

				int u = r.start;
				int v = r.end;
				int weight = r.time;
				if (time[u] != Integer.MAX_VALUE && time[u] + weight < time[v])
					time[v] = time[u] + weight;
			}
		}

		isGoPast = false;
		for (int inx = graph.size()-1; inx >= graph.size()-W; inx--) {
			if (time[S] < 0) {
				isGoPast = true;
			}
		}
	}

	static class Route {
		int start;
		int end;
		int time;
		
		Route(int s, int e, int t) {
			this.start = s;
			this.end = e;
			this.time = t;
		}
	}
}
