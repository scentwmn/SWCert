package prolv1.aftertest.divisor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * [����A-0041] ����� ��� 
 * �׷���, DFS
 */
public class Solution {

	static int MAX = 1_000_001;
	static int T, N;
	static int[] num = new int[MAX]; // idx=����, ���� ����
	static int[] pa = new int[MAX];
	static List<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			N = Integer.parseInt(br.readLine());

			Arrays.fill(num, 0);
			Arrays.fill(pa, 0);
			list.clear();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int inx = 1; inx <= N; inx++) {
				int n = Integer.parseInt(st.nextToken());
				num[n]++;
				pa[n] = n;
				if (num[n] == 1)
					list.add(n);
			}

			// ó��
			Collections.sort(list, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
				
			});

			for (int inx = 0; inx < list.size(); inx++) {
				int a = list.get(inx);
				for (int jnx = inx+1; jnx < list.size(); jnx++) {
					int b = list.get(jnx);
					if (a % b == 0) {
						union(a, b);
					}
				}
			}

			// root ����� ���� ū ������ ����
			Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>();
			for (int inx = 0; inx < list.size(); inx++) {
				int n = list.get(inx);
				find(n);
				// pa[n] �� root��.
				// num[n] �� n�� ����
				if (cntMap.containsKey(pa[n]))
					cntMap.put(pa[n], cntMap.get(pa[n])+num[n]);
				else
					cntMap.put(pa[n], num[n]);
			}
			
			// ū ���� ����.
			int result = 0;
			Iterator<Integer> iter = cntMap.keySet().iterator();
			while (iter.hasNext()) {
				int k = iter.next();
				if (result < cntMap.get(k)) {
					result = cntMap.get(k);
				}
			}
			
			System.out.println("#"+tnx + " " + result);
		}
	}

	static void union(int a, int b) {
		pa[find(a)] = find(b);
	}
	static int find(int a) {
		if (pa[a] == a)
			return a;
		return pa[a] = find(pa[a]);
	}

}
