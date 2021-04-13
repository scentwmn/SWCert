package sw.pretest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * ø˙»¶ Ω√∞£ ¥‹√‡ »Æ¿Œ
 */
public class Solution210406 {

	static int MAX = 501;
	static int T, N, M, W;
	static List<Route>[] graph = new ArrayList[MAX];
	static List<Route>[] w_graph = new ArrayList[MAX];
	
	static boolean isGoPast = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			for (int inx = 1; inx <= N; inx++) {
				graph[inx] = new ArrayList<Route>();
				w_graph[inx] = new ArrayList<Route>();
			}

			// µµ∑Œ
			for (int inx = 1; inx <= M; inx++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				graph[s].add(new Route(e,t));
				graph[e].add(new Route(s,t));
			}
			// ø˙»¶
			for (int inx = 1; inx <= W; inx++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				w_graph[s].add(new Route(e,t));
			}
			
			for (int inx = 1; inx <= N; inx++) {
				Collections.sort(graph[inx], new Comp());
			}

			// √≥∏Æ
			process();

			System.out.println("#"+tnx + " " + (isGoPast ? "YES" : "NO"));
		}
	}
	
	static void process() {
		// ø˙»¶¿« ∞πºˆ∏∏≈≠ ∞Ê∑Œ≈Ωªˆ «œø©, ∫Ò±≥
		isGoPast = false;
		
		for (int inx = 1; inx <= N; inx++) {
			if (w_graph[inx].isEmpty())
				continue;

			Collections.sort(graph[inx], new Comp());
			PriorityQueue<Route> pq = new PriorityQueue<Route>();
			pq.add(new Route(inx, 0));

//			Map<Integer, Integer> visited = new HashMap<Integer, Integer>();
			int[] time = new int[N+1];
			Arrays.fill(time, 987987987);
			time[inx] = 0;
			while(! pq.isEmpty()) {
				Route r = pq.poll();
				int minIdx = r.end;
				int minValue = r.time;

				for (int jnx = 0; jnx < graph[minIdx].size(); jnx++) {
					Route next = graph[minIdx].get(jnx);
					if (minValue + next.time < time[next.end]) {
						time[next.end] = minValue + next.time;
						pq.add(new Route(next.end, time[next.end]));
					}
				}
			}
			
			// w_graph : inx -> w_graph[inx]
			for (int jnx = 0; jnx < w_graph[inx].size(); jnx++) {
				Route r = w_graph[inx].get(jnx);
				// Ω√∞£ø©«‡ ∞°¥….
				if (time[r.end] < r.time) {
					isGoPast = true;
				}
			}
		}
	}
	
	static class Comp implements Comparator<Route> {
		@Override
		public int compare(Route o1, Route o2) {
			return Integer.compare(o1.time, o2.time);
		}
	}

	static class Route implements Comparable<Route> {
		int end;
		int time;
		
		Route(int e, int t) {
			this.end = e;
			this.time = t;
		}
		@Override
		public String toString() {
			return "Route [end=" + end + ", time=" + time + "]";
		}

		@Override
		public int compareTo(Route o) {
			return Integer.compare(this.time, o.time);
		}
	}
}
