package sw.p05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [����P-0091][2021��02��07��] �������� ���
 */
public class Solution {

	static int T, N, K;
	static Num[] num = new Num[100];
	
	static int result = 0;
	
	private static class Num implements Comparable<Num> {
		int idx;
		int num;
		int ness;
		int use;

		@Override
		public int compareTo(Num o) {
			if (this.ness == 1 && o.ness == 1)
				return Integer.compare(o.num, this.num);
			else if (this.ness == 1)
				return -1;
			else if (o.ness == 1)
				return 1;
			return Integer.compare(o.num, this.num);
		}

		@Override
		public String toString() {
			return "Num [idx=" + idx + ", num=" + num + ", ness=" + ness + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer nkST = new StringTokenizer(br.readLine());
			N = Integer.parseInt(nkST.nextToken());
			K = Integer.parseInt(nkST.nextToken());
			
			StringTokenizer numST = new StringTokenizer(br.readLine());
			for (int inx = 0; inx < K; inx++) {
				num[inx] = new Num();
				num[inx].idx = inx;
				num[inx].num = Integer.parseInt(numST.nextToken());
			}

			StringTokenizer nessST = new StringTokenizer(br.readLine());
			while (nessST.hasMoreTokens()) {
				num[Integer.parseInt(nessST.nextToken())-1].ness = 1;
			}

			// ó��
			process();
			
			System.out.println("#"+tnx + " " + result);
		}
		
		br.close();
	}
	
	static void process() {
		result = 0;
		
		// ū���ڷ� ����
		Arrays.sort(num, 0, K);
		
		// ������� �̾���̱�
		int sum = 0;
		int idx = 0;
		while (sum < N) {
			
		}
	}
}
