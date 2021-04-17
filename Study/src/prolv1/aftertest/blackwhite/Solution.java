package prolv1.aftertest.blackwhite;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [����A-0026] �����
 * �׷���, DFS
 */
@SuppressWarnings("unchecked")
public class Solution {

	static int N_MAX = 1001;
	static int T, N, M;
	static int[] cat = new int[N_MAX]; // 0=������, 1=���, 2=ȸ��
	static List<Integer>[] road = new List[N_MAX];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int inx = 1; inx < N_MAX; inx++) {
			road[inx] = new ArrayList<Integer>();
		}

		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// �ʱ�ȭ
			for (int inx = 1; inx <= N; inx++) {
				road[inx].clear();
			}

			// ����� ����
			st = new StringTokenizer(br.readLine());
			for (int inx = 1; inx <= N; inx++) {
				cat[inx] = Integer.parseInt(st.nextToken());
			}
			for (int inx = 1; inx <= M; inx++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				road[s].add(e);
				road[e].add(s);
			}
			
			// ó��
			Map<Integer, Integer> notVisit = new HashMap<Integer, Integer>();
			for (int inx = 1; inx <= N; inx++) {
				notVisit.put(inx, inx);
			}
			int result = 0;
			while (! notVisit.isEmpty()) {
				int start = notVisit.get(notVisit.keySet().iterator().next());
				int r = bfs(notVisit, start);
				if (result < r) {
					result = r;
				}
			}

			System.out.println("#"+tnx + " " + result);
		}
	}
	
	static int bfs(Map<Integer, Integer> notVisit, int start) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		
		int cnt = 0;
		
		Map<Integer, Integer> visited = new HashMap<Integer, Integer>();
		visited.put(start, start);

		while (!queue.isEmpty()) {
			int n = queue.poll();
			notVisit.remove(n);
			cnt++;

			for (int inx = 0; inx < road[n].size(); inx++) {
				int e = road[n].get(inx);
				// ���� �ϳ� ȸ���̰ų�, �Ѵ� ���� ���̸� �̵� ����
				if (! visited.containsKey(e)) {
					if (cat[n] == 2 || cat[e] == 2 || (cat[n] == cat[e]) ) {
						queue.add(e);
						visited.put(e, e);
					}
				}
			}
		}

		return cnt;
	}
}
