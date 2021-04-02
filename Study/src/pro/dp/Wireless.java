package pro.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Wireless {

	static int N;
	static int[] NUM = new int[10000];
	static int[] RESULT = new int[10000];
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>(10000);
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		for (int inx = 0; inx < N; inx++) {
			NUM[inx] = scan.nextInt();
			map.put(NUM[inx], inx);
		}

		process(0, N);
		for (int inx = 0; inx < N; inx++) {
			System.out.print(RESULT[inx]);
			if (inx < N-1)
				System.out.print(" ");
		}
		System.out.println();
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
				for (int inx = pLeft; inx < left.length; inx++) {
					RESULT[map.get(left[inx])]++;
				}
				pRight++;
			}
			else {
				pLeft++;
			}
		}
		
		process(start, mid);
		process(mid, end);
	}
}
