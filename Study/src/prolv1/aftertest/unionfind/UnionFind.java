package prolv1.aftertest.unionfind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UnionFind {

	static int MAX = 11;//100_001;
	static int T, N, Q;
	static int[] pa = new int[MAX];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int tnx = 1; tnx <= T; tnx++) {
			
			N = Integer.parseInt(br.readLine());
			Q = Integer.parseInt(br.readLine());

			// �ڱ��ڽ����� �ʱ�ȭ.
			for (int inx = 1; inx <= N; inx++) {
				pa[inx] = inx;
			}

			int result = 0;
			for (int inx = 1; inx <= Q; inx++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int q = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (q == 0) {
					union(a, b);
				}
				else {
					if (find(a) == find(b))	// �߿�
						result++;
				}
			}

			System.out.println("#"+tnx + " " + result);
		}
	}

	static void union(int a, int b) {
		pa[find(a)] = find(b); // ������
	}

	// parent ����
	static int find(int a) {
		if (pa[a] == a)
			return a;
		return pa[a] = find(pa[a]); // ������
	}
}
