package pro.dp;

import java.util.Scanner;

public class CardGame {

	static int N = 0;
	static int[][] num = new int[100001][2];
	static int[] sel = new int[100001];
	static int[][] pq = new int[100001][2];
	static int len = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		for (int inx = 1; inx <= N; inx++) {
			int x = scan.nextInt();
			num[inx][0] = x;
			num[inx][1] = inx;
			push(x, inx);
		}
		
		for (int inx = 1; inx <= N; inx++) {
			int[] top = top();
			pop();
			int s=0, e=0;

			if (top[1] - 1 < 0) s = inx;
			else if (top[1] - 2 < 0) s = top[1] - 1;
			else s = top[1] - 2;
			
			if (top[1]+1 > N) e = N;
			else if (top[1]+2 > N) e = top[1]+1;
			else e = top[1]+2;
			
			int seq = 0;
			boolean is3 = false;
			for (int jnx = s; jnx <= e; jnx++) {
				if (jnx == top[1]) {
					seq++;
					is3 = seq >= 3;
				}
				else if (sel[jnx] == 1) {
					seq++;
					is3 = seq >= 3;
				}
				else {
					seq = 0;
				}

				if (is3)
					break;
			}

			if (!is3) {
				sel[top[1]] = 1;
			}
		}
		
		int result = 0;
		for (int inx = 1; inx <= N; inx++) {
			result += sel[inx] == 1 ? num[inx][0] : 0;
		}
		
		System.out.println(result);

		scan.close();
	}

	static void push(int v, int inx) {
		len++;
		pq[len][0] = v;
		pq[len][1] = inx;
		
		int x = len;
		while (x > 1) {
			if (pq[x][0] > pq[x/2][0]) {
				int[] temp = pq[x];
				pq[x] = pq[x/2];
				pq[x/2] = temp;
			}
			x /= 2;
		}
	}
	static void pop() {
		pq[1] = pq[len];
		len--;
		
		int x = 1;
		while (x <= len) {
			if (x*2+1 <= len) {
				// 2 children
				int maxIdx = pq[x*2][0] < pq[x*2+1][0] ? x*2+1 : x*2;
				if (pq[x][0] < pq[maxIdx][0]) {
					int[] temp = pq[x];
					pq[x] = pq[maxIdx];
					pq[maxIdx] = temp;
				}
			}
			else if (x*2 <= len) {
				if (pq[x][0] < pq[x*2][0]) {
					int[] temp = pq[x];
					pq[x] = pq[x*2];
					pq[x*2] = temp;
				}
			}
			else
				break;
			
			x *= 2;
		}
	}
	
	static int[] top() {
		return new int[]{pq[1][0], pq[1][1]};
	}
}
