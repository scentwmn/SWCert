package pro.greedy;

import java.util.Scanner;

public class ReturnChange {

	static int N;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		
		int cnt = N/500;
		cnt += N%500/100;
		cnt += N%500%100/50;
		cnt += N%500%100%50/5;
		cnt += N%500%100%50%5;
		
		System.out.println(cnt);
		
		scan.close();
	}
}
