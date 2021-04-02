package sw.p02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class TopologicalSort {

	static int N_MAX = 100_001;
	static int M_MAX = 200_001;

	static int T, N, M, K;
	static List<Integer>[] route = new ArrayList[M_MAX];
	static List<Integer> ts = new ArrayList<Integer>(N_MAX);
	
	static int[] fromCnt = new int[N_MAX];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		int start, end, weight, foodLoc, foodW;
		for (int inx = 1; inx <= T; inx++) {
			// N=섬, M=해류, K=식량
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for (int jnx = 1; jnx <= N; jnx++) {
				fromCnt[jnx] = 0;
			}
			for (int jnx = 1; jnx <= M; jnx++) {
				route[jnx] = new ArrayList<Integer>();
			}

			// 해류 정보
			for (int jnx = 1; jnx <= M; jnx++) {
				st = new StringTokenizer(br.readLine(), " ");
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				weight = Integer.parseInt(st.nextToken());
				
				// 거꾸로 구함.
				route[end].add(start);
				
				// 거꾸로 했을 때.. 들어오는 것 카운트.
				fromCnt[start]++;
			}
			
			// Djikstra
			topologicalSort();
			
			for (int jnx = 0; jnx < args.length; jnx++) {
				System.out.println(ts.get(jnx));
			}
		}
		
		br.close();
	}

	static void topologicalSort() {
		
		Queue<Integer> pq = new LinkedList<Integer>();
		Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>(N);

		for (int inx = N; inx >= 1; inx--) {
			if (fromCnt[inx] == 0) {
				pq.add(inx);
			}
		}
		
		while(! pq.isEmpty()) {
			int node = pq.poll();
			ts.add(node);
			for (int inx = 0; inx < route[node].size(); inx++) {
				if (!visited.containsKey(route[node].get(inx))) {
					pq.add(route[node].get(inx));
					visited.put(route[node].get(inx), true);
				}
			}
		}
		System.out.println();
	}
}
