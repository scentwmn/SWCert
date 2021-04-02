package pro.sort;

import java.util.Scanner;

public class Hive {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		
		if (N == 1)
			System.out.println(1);
		else if (N <= 7)
			System.out.println(2);
		else {
		
//		19 = 7 + (6*2)
//		37 = 19 + (6*3)
//		61 = 37 + (6*4)
				
			int c = 7;
			int i = 2;
			while (true) {
				if (N <= (c+6*i)) {
					System.out.println(i+1);
					break;
				}
				c = c + 6*i;
				i++;
			}
		}
		
		scan.close();
	}
}
