package pro.sort;

import java.util.Scanner;

public class SubAver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		
		int[] b = new int[N];
		for (int inx = 0; inx < N; inx++) {
			b[inx] = scan.nextInt();
		}

		int[] a = new int[N];
		int accumA = 0;
		
		for (int inx = 0; inx < N; inx++) {
			a[inx] = b[inx]*(inx+1) - accumA;
			accumA += a[inx];
		}
		
		for (int inx = 0; inx < N; inx++) {
			if (inx > 0)
				System.out.print(" ");
			System.out.print(a[inx]);
		}
		System.out.println();

		scan.close();
	}
}
