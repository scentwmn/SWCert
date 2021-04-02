package pro.tree;

import java.util.Arrays;
import java.util.Scanner;

public class InversionCount {

	static int N;
	static int[] NUM = new int[1000000];
	
	static int CNT;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		CNT = 0;
		N = scan.nextInt();
		for (int inx = 0; inx < N; inx++) {
			NUM[inx] = scan.nextInt();
		}

		process(0, N);
		System.out.println(CNT);
		scan.close();
	}

	static void process(int start, int end) {
		if (start >= end-1)
			return;

		int mid = (end+start) / 2;
		int[] left = Arrays.copyOfRange(NUM, start, mid);
		int[] right = Arrays.copyOfRange(NUM, mid, end);

		Arrays.sort(left);
		Arrays.sort(right);
		
		int pLeft = 0;
		int pRight = 0;
		
		while (pLeft < left.length && pRight < right.length) {
			if (left[pLeft] > right[pRight]) {
				CNT += left.length - pLeft;
				pRight++;
			}
			else
				pLeft++;
		}
		
		process(start, mid);
		process(mid, end);
	}
}
