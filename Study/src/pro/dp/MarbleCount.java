package pro.dp;

import java.util.Scanner;

public class MarbleCount {
	
	static int[] four = new int[10];
	static int N = 0;

	public static void main(String[] args) {
		int f = 1;
		for (int inx = 0; inx < 10; inx++) {
			f *= 4;
			four[inx] = f;
		}
		
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		int inx = 9;
		while(true) {
			if (N > four[inx]) {
				N -= four[inx];
			}
			else {
				inx--;
			}
			if (inx < 0)
				break;
		}
		
		if (N == 1 || N == 2 || N == 3)
			System.out.println("YES");
		else
			System.out.println("NO");

		scan.close();
	}
}
