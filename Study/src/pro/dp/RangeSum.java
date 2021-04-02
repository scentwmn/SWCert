package pro.dp;

import java.util.Scanner;

public class RangeSum {

	static int N = 0;
	static int max = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		
		int num = 0;
		int sum = 0;
		for (int inx = 1; inx <= N; inx++) {
			num = scan.nextInt();
			
			if (num + sum < num) {
				if (max < num) {
					max = num;
				}
				sum = num;
			}
			else {
				if (max < num + sum) {
					max = num + sum;
				}
				sum = num + sum;
			}
		}
		
		System.out.println(max);
		
		scan.close();
	}
}
