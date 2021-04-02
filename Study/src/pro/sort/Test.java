package pro.sort;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();
			
			if (a == 0 && b == 0 && c == 0)
				break;
			
			if (b - a == c - b) {
				System.out.println("AP " + (c + (c - b)));
			}
			else {
				System.out.println("GP " + (c * (c / b)));
			}
		}
		
		scan.close();
	}
}
