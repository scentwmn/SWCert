package sw.p02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [����P-0089] ������ �׷�
 * ������ Ǯ��
 * Djikstra ���
 * PriorityQueue�� ����Ͽ�, ��ü ���κ����� �����ϵ��� ��.(WrapperŬ������ compareTo �Լ��� ����ؾ� ��.)
 */
public class Solution2 {

	static int N_MAX = 100_001;
	static int M_MAX = 200_001;

	static int T, N, M, K;
	static List<Integer>[] route = new ArrayList[N_MAX];
	static List<Integer>[] routeW = new ArrayList[N_MAX];
	static Map<Integer, Integer> food = new HashMap<Integer, Integer>(N_MAX);

	static int[] minroute = new int[N_MAX];
	static PriorityQueue<Island> minrouteList = new PriorityQueue<Island>();
	
	private static class Island implements Comparable<Island> {
		int dest;
		int cost;
		
		Island(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Island o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

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
			// N=��, M=�ط�, K=�ķ�
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			for (int jnx = 1; jnx <= N; jnx++) {
				minroute[jnx] = Integer.MAX_VALUE;
			}
			for (int jnx = 1; jnx <= N; jnx++) {
				route[jnx].clear();
				routeW[jnx].clear();
			}

			// �ط� ����
			for (int jnx = 1; jnx <= M; jnx++) {
				st = new StringTokenizer(br.readLine(), " ");
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				weight = Integer.parseInt(st.nextToken());
				
				// �Ųٷ� ����.
				route[end].add(start);
				routeW[end].add(weight);
			}

			// �ķ�
			food.clear();
			for (int jnx = 1; jnx <= K; jnx++) {
				st = new StringTokenizer(br.readLine(), " ");
				foodLoc = Integer.parseInt(st.nextToken());
				foodW = Integer.parseInt(st.nextToken());

				food.put(foodLoc, foodW);
			}

			minrouteList.clear();

			// Djikstra
			djikstra();
			
			System.out.println("#"+inx + " " + minroute[1]);
		}

		br.close();
	}

	// N ���� 1���� ���� �ִܰ�� ����.
	static void djikstra() {
		minroute[N] = 0;
		minrouteList.add(new Island(N, 0));

		int minIdx, minValue;
		int next, f, nextC;
		
		Island is = null;
		while (! minrouteList.isEmpty()) {

			is = minrouteList.poll();
			minIdx = is.dest;
			minValue = is.cost;

			for (int jnx = 0; jnx < route[minIdx].size(); jnx++) {
				next = route[minIdx].get(jnx);

				f = food.containsKey(next) ? food.get(next) : 0;
				if (minValue == Integer.MAX_VALUE)
					nextC = Integer.MAX_VALUE;
				else
					nextC = routeW[minIdx].get(jnx) + minValue <= f ? 0 : routeW[minIdx].get(jnx) + minValue - f;
				if (minroute[next] == Integer.MAX_VALUE || nextC < minroute[next]) {
					minroute[next] = nextC;
					minrouteList.add(new Island(next, nextC));
				}
			}
		}
	}

}
