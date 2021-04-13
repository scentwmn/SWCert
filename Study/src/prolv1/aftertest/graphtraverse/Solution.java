package prolv1.aftertest.graphtraverse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [연습A-0015] 그래프 순회
 * DFS, BFS
 */
public class Solution {
	
	static int MAX = 501;
	static int T, V, E, S;
	static List<Integer>[] edge = new List[MAX];
	static Map<Integer, Integer> visited = new HashMap<Integer, Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int inx = 1; inx < MAX; inx++) {
			edge[inx] = new ArrayList<Integer>();
		}
		
		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			
			for (int inx = 1; inx <= V; inx++) {
				edge[inx].clear();
			}

			for (int inx = 1; inx <= E; inx++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				edge[x].add(y);
				edge[y].add(x);
			}
			
			for (int inx = 1; inx <= V; inx++) {
				Collections.sort(edge[inx]);
			}

			System.out.print("#"+tnx);

			visited.clear();
			dfs(S);
			
			System.out.println();

			visited.clear();
			bfs(S);
			System.out.println();
		}

		br.close();
	}

	static void dfs(int v) {
		System.out.print(" " + v);
		visited.put(v, v);

		for (int inx = 0; inx < edge[v].size(); inx++) {
			if (! visited.containsKey(edge[v].get(inx)))
				dfs(edge[v].get(inx));
		}
	}

	static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		visited.put(v, v);

		int cnt = 0;
		while(! queue.isEmpty()) {
			int a = queue.poll();
			if (cnt > 0) {
				System.out.print(" ");
			}
			System.out.print(a);
			cnt++;
			
			for (int inx = 0; inx < edge[a].size(); inx++) {
				if (! visited.containsKey(edge[a].get(inx))) {
					queue.add(edge[a].get(inx));
					visited.put(edge[a].get(inx), edge[a].get(inx));
				}
			}
		}
	}
}
