package sw.p07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [기출P-0085] 외환 관리
 */
public class Solution {

	static int MAX = 6;//201;
	static int T, N, M, D, K;
	static List<int[]> ex = new ArrayList<int[]>();
	static Route[][] route = new Route[MAX][MAX];
	
	static final int INF = 987_987_987;

	static class Route {
		int x, y, z;
		int maxWeight;
		Route(int x, int y, int z){
			this.x = x;
			this.y = y;
			this.z = z;
			this.maxWeight = z;
		}
		@Override
		public String toString() {
			return "Route [x=" + x + ", y=" + y + ", z=" + z + ", maxWeight=" + maxWeight + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int inx = 1; inx < MAX; inx++) {
			for (int jnx = 1; jnx < MAX; jnx++) {
				route[inx][jnx] = new Route(inx, jnx, INF);
			}
		}

		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			ex.clear();
			for (int jnx = 1; jnx <= M; jnx++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());

				route[x][y].z = z;
				route[x][y].maxWeight = z;
				route[y][x].z = z;
				route[y][x].maxWeight = z;

				ex.add(new int[]{x,y,z});
			}

			// Floyd-Warshall 실행
			floydWarshall();

			int result = 0;
			for (int jnx = 1; jnx <= D; jnx++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				int r = route[s][e].z - route[s][e].maxWeight + K;
				result += r;
			}

			System.out.println("#"+tnx + " " + result);
		}
	}

	static void floydWarshall() {
		for (int inx = 1; inx <= N; inx++) {
			for (int jnx = 1; jnx <= N; jnx++) {
				for (int knx = 1; knx <= N; knx++) {
					int mw = Math.max(route[inx][jnx].maxWeight, route[jnx][knx].maxWeight);
					if (route[inx][jnx].z+route[jnx][knx].z-mw+K < route[inx][knx].z-route[inx][knx].maxWeight+K) {
						route[inx][knx].z = route[inx][jnx].z+route[jnx][knx].z;
						route[inx][knx].maxWeight = Math.max(route[inx][jnx].maxWeight, route[jnx][knx].maxWeight);
					}
				}
			}
		}
	}
}
