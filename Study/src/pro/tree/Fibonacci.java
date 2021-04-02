package pro.tree;

import java.util.Scanner;

public class Fibonacci {

	static long N = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextLong();

		long a = 0, b = 1;
		int na=1, nb=1; // a, b가 몇개씩 사용됐는지 확인
		for (int inx = 2; inx <= N; inx++) {
			if (inx%2 == 0) {
				a = (a+b)%1000000;
				na++;
			} else {
				b = (a+b)%1000000;
				nb++;
			}
		}
		
		if (N % 2 == 0) {
			System.out.println(a);
		} else {
			System.out.println(b);
		}
		
		System.out.println("na : " + na);
		System.out.println("nb : " + nb);
		
		scan.close();
	}
}
