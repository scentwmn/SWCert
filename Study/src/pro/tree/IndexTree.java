package pro.tree;

import java.util.Scanner;

public class IndexTree {

	static int N = 0;
	static int M = 0;
	static int K = 0;
	
	static long[] NUM = null;
	static int len = 1; // 자식 갯수이면서, 원소 시작지점
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		K = scan.nextInt();

		while(len < N)
			len <<= 1;

		NUM = new long[len * 2];
		for (int inx = len; inx < len+N; inx++) {
			NUM[inx] = scan.nextLong();
		}
		
		// 부모에 합을 구함.
		updateAllParent();

		int a=0, b=0, c=0;
		for (int inx = 1; inx <= M+K; inx++) {
			a = scan.nextInt();
			b = scan.nextInt();
			c = scan.nextInt();
			
			if (a == 1) {
				updateParent(b, c);
			}
			else {
				System.out.println(sum(b, c));
			}
		}
		scan.close();
	}
	
	static void updateAllParent() {
		int levelIdx = len;
		while (levelIdx >= 1) {

			for (int inx = levelIdx; inx < levelIdx*2; inx+=2) {
				NUM[inx/2] = NUM[inx] + NUM[inx+1];
			}
			
			levelIdx /= 2;
		}
	}
	
	static void updateParent(int idx, int val) {
		NUM[len+idx-1] = val;
		
		int p = (len+idx-1) / 2;
		while (p >= 1) {
			NUM[p] = NUM[p*2] + NUM[p*2+1];
			p /= 2;
		}
	}
	
	static long sum(int start, int end) {
		
		return 0L;
	}
}
