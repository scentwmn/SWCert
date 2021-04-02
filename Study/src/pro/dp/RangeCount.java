package pro.dp;

import java.util.Scanner;

public class RangeCount {
	static int[] NUM = new int[100000];
	static int N = 0;
	static int LO = 0;
	static int HI = 0;
	
	static int CNT = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		LO = scan.nextInt();
		HI = scan.nextInt();
		
		for (int inx = 0; inx < N; inx++) {
			int n = scan.nextInt();
			
			for (int jnx = 0; jnx <= inx; jnx++) {
				NUM[jnx] += n;
				
				if (NUM[jnx] >= LO && NUM[jnx] <= HI)
					CNT++;
			}
		}
		
		System.out.println(CNT);
		
		scan.close();
	}
}
