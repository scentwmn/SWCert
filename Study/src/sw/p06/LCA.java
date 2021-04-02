package sw.p06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LCA {
	static int MAX_NODE = 16;//100_001;

	// depth :: ����� ����(����)
	// ac[x][y] :: x�� 2^y��° ������ �ǹ�
	static int[] depth = new int[MAX_NODE];
	static int[][] ac = new int[MAX_NODE][20];

	static List<Integer>[] graph = new ArrayList[MAX_NODE];
	static int max_level;

	public static int log2(int N) {
		// calculate log2 N indirectly
		// using log() method
		int result = (int) (Math.log(N) / Math.log(2));

		return result;
	}

	// DP(ac)�迭 ����� ����
	static void getTree(int here, int parent) {
		// here�� ���̴� �θ������ + 1
		depth[here] = depth[parent] + 1;

		// here�� 1��° ������ �θ���
		ac[here][0] = parent;

		// MAX_NODE�� log2�� ���� ������ ���ش�.
		max_level = (int) Math.floor(log2(MAX_NODE));

		for (int i = 1; i <= max_level; i++) {
			// tmp :: here�� 2^(i-1)��° ����
			/*
			 * ��, ac[here][i] = ac[tmp][i-1]�� here�� 2^i��° ������ tmp�� 2^(i-1)��° ������
			 * 2^(i-1)��° ����� ���ٴ� �ǹ� ������� i = 3�϶� here�� 8��° ������ tmp(here�� 4��°
			 * ����)�� 4��° ����� ����. i = 4�϶� here�� 16��° ������ here�� 8��° ����(tmp)�� 8��°��
			 * ����.
			 */
			int tmp = ac[here][i - 1];
			ac[here][i] = ac[tmp][i - 1];
		}

		// dfs �˰���
		for (int inx = 0; inx < graph[here].size(); inx++) {
			int there = graph[here].get(inx);
			if (there != parent)
				getTree(there, here);
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		for (int inx = 0; inx < MAX_NODE; inx++) {
			graph[inx] = new ArrayList<Integer>();
		}

		// ����� �׷��� ����
		for (int inx = 0; inx < n; inx++) {
			int from = scan.nextInt();
			int to = scan.nextInt();
			graph[from].add(to);
			graph[to].add(from);
		}

		// make_tree�� 1,0�� ���⶧���� 0�� -1�� ����
		depth[0] = -1;

		// ��Ʈ ����� 1�� ������ Ʈ�� ����
		getTree(1, 0);

		int m = scan.nextInt();

		// ������ ����
		while (m > 0) {
			m--;
			int a = scan.nextInt();
			int b = scan.nextInt();

			if (depth[a] != depth[b]) {
				// depth[b] >= depth[a]�� �ǵ��� swap
				if (depth[a] > depth[b]) {
					int t = a;
					a = b;
					b = t;
				}

				// b�� �÷��� depth�� �����ش�.
				/*
				 * �̷����ϸ� ���� max_level�� 4��� 2^4 -> 2^3 -> 2^2 -> 2^1 -> 2^0�������
				 * ã�ư��ٵ� �ᱹ 1, 2, 3, 4, 5 ..., 31���� ��� ã�� ��İ� ��������. �������, i��
				 * 4�϶��� 1�϶� �����ߴ� ġ�� depth[a] <= depth[ac[b][4]]�� ���� b =
				 * ac[b][4];�� �Ǿ� b�� b�� 16��° ������ ���� ���� ���̰� depth[a] <=
				 * depth[ac[b][1]]�� ����(���� b�� ó�� b�� 16��° ������ b�� �ٲ����.) b =
				 * ac[b][1];�� �Ǿ� b�� b�� 2��° ������ ���� �ȴ�. ��, b�� 16��° ������ 2��° ������ ����
				 * ���̴� b�� 18��° ������ ���� �ȴ�. (�ϰ��� �ϴ� ���� 3��°, 7��°, 11��° �̷� ����鵵 ��� ��
				 * �� �ִٴ� �ǹ��̴�.)
				 */
				for (int i = max_level; i >= 0; i--) {
					// b�� 2^i��° ������ a�� depth���� ũ�ų� ������ ��� ������ Ÿ����.
					if (depth[a] <= depth[ac[b][i]])
						b = ac[b][i];
				}
			}

			int lca = a;

			// a�� b�� �ٸ��ٸ� ���� ���̰� ������
			// ���̸� ��� �÷� ���� �ٸ� ����� ������ ������ ������ �ݺ��Ѵ�.
			// ��, ���� �ٸ� ���(2,3)�� ������ 1�� ���ٸ� lca = ac[2][0]�� ���� 2�� ������ 1���� �� �� �ְ�
			// ���������� ac[3][0] ���� 3�� ������ 1���� �˰ԵǸ� �˰����� ���̳���.
			if (a != b) {
				for (int i = max_level; i >= 0; i--) {
					if (ac[a][i] != ac[b][i]) {
						a = ac[a][i];
						b = ac[b][i];
					}
					lca = ac[a][i];
				}
			}

			System.out.println(lca);
		}

		scan.close();
	}
}
