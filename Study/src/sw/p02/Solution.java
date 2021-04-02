package sw.p02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * [기출P-0089] 위대한 항로
 * 자체 풀이
 */
public class Solution {

	static int N_MAX = 100_001;
	static int M_MAX = 200_001;

	static int T, N, M, K;
	static List<Integer>[] route = new ArrayList[N_MAX];
	static List<Integer>[] routeW = new ArrayList[N_MAX];
	static Map<Integer, Integer> food = new HashMap<Integer, Integer>(N_MAX);

	static int[] minroute = new int[N_MAX];
	static LinkedList<Integer> minrouteList = new LinkedList<Integer>();

	static int[] fromCnt = new int[N_MAX];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				new FileInputStream("D:\\Dev\\git\\SWCert\\Study\\src\\sw\\p02\\sample_input.txt")));
		T = Integer.parseInt(br.readLine());

		for (int jnx = 1; jnx < N_MAX; jnx++) {
			route[jnx] = new ArrayList<Integer>();
			routeW[jnx] = new ArrayList<Integer>();
		}

		int start, end, weight, foodLoc, foodW;
		for (int inx = 1; inx <= T; inx++) {
			// N=섬, M=해류, K=식량
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			for (int jnx = 1; jnx <= N; jnx++) {
				minroute[jnx] = -1;//Integer.MAX_VALUE;
				fromCnt[jnx] = 0;
			}
			for (int jnx = 1; jnx <= N; jnx++) {
				route[jnx].clear();
				routeW[jnx].clear();
			}

			// 해류 정보
			for (int jnx = 1; jnx <= M; jnx++) {
				st = new StringTokenizer(br.readLine(), " ");
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				weight = Integer.parseInt(st.nextToken());
				
				// 거꾸로 구함.
				route[end].add(start);
				routeW[end].add(weight);

				// 거꾸로 했을 때.. 들어오는 것 카운트.
				fromCnt[start]++;
			}

			// 식량
			food.clear();
			for (int jnx = 1; jnx <= K; jnx++) {
				st = new StringTokenizer(br.readLine(), " ");
				foodLoc = Integer.parseInt(st.nextToken());
				foodW = Integer.parseInt(st.nextToken());

				food.put(foodLoc, foodW);
			}

			minrouteList.clear();

			// topological sort
			topologicalSort();

			// Djikstra
			djikstra();
			
			System.out.println("#"+inx + " " + minroute[1]);
		}

		br.close();
	}

	// N 에서 1까지 가는 최단경로 구함.
	static void djikstra() {
		minroute[N] = 0;

		int minIdx, minValue;
		int next, f, nextC;
		
		while (! minrouteList.isEmpty()) {

			minIdx = minrouteList.remove(0);// .get(0);
			minValue = minroute[minIdx];
			
			for (int jnx = 0; jnx < route[minIdx].size(); jnx++) {
				next = route[minIdx].get(jnx);
				fromCnt[next]--;
				if (fromCnt[next] == 0)
					minrouteList.add(next);

				f = food.containsKey(next) ? food.get(next) : 0;
				if (minValue == -1)
					nextC = -1;
				else
					nextC = routeW[minIdx].get(jnx) + minValue <= f ? 0 : routeW[minIdx].get(jnx) + minValue - f;
				if (minroute[next] == -1 || nextC < minroute[next]) {
					minroute[next] = nextC;
				}
				
			}
		}
	}

	static void topologicalSort() {
		for (int inx = N; inx >= 1; inx--) {
			if (fromCnt[inx] == 0) {
				minrouteList.add(inx);
			}
		}
	}

}
