package prolv1.aftertest.connect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * [기출A-0002] 일렬 연결망
 * 구현
 */
public class Solution {

	static int T, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());

			int num = 0;

			List<Integer> list = new ArrayList<Integer>();
			int zeroSum = 0;
			long totalSum = 0;
			
			int[] uf = new int[N+1];
			Arrays.fill(uf, -1);

			for (int inx = 1; inx <= N; inx++) {
				num = Integer.parseInt(st.nextToken());
				
				if (num == 0) {
					zeroSum += list.size();
					zeroSum += 1;
					list.add(inx);

					if (list.size() > 5) {
						int d = list.get(0);
						list.remove(0);
						zeroSum -= inx-d+1;
					}
				}
				else {
					totalSum += zeroSum;
					zeroSum += list.size();

					if (! list.isEmpty()) {
						int f = list.get(0);
						if (uf[f] != -1) {
							uf[inx] = uf[f];
							for (int jnx = 0; jnx < list.size(); jnx++) {
								uf[list.get(jnx)] = uf[f];
							}
						}
						else {
							uf[inx] = inx;
							for (int jnx = 0; jnx < list.size(); jnx++) {
								uf[list.get(jnx)] = inx;
							}
						}
					}
				}
			}
			int group = 0;
			Map<Integer, Integer> checked = new HashMap<Integer, Integer>();
			for (int inx = 1; inx <= N; inx++) {
				if (uf[inx] == -1)
					group++;
				else
					checked.put(uf[inx], uf[inx]);
			}
			group += checked.size();

			System.out.println("#"+tnx + " " + totalSum + " " + group);
		}
		
		br.close();
	}
}
