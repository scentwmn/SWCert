package pro.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combinationpascal {
	
	static int MAX = 100;//1000000;
	static List<Integer>[] T = new ArrayList[MAX];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();
		
		make(N);
		
		int inx = 10000;
		while(T[N].get(M) % inx != 0) {
			inx /= 10;
		}

		System.out.println(String.valueOf(inx).length()-1);
		
		scan.close();
	}
	
	static void make(int n) {
		T[0] = new ArrayList<Integer>();
		T[0].add(1);

		for (int inx = 1; inx <= n; inx++) {
			T[inx] = new ArrayList<Integer>(inx+1);
			for (int jnx = 0; jnx <= inx; jnx++) {
				if (jnx == 0 || inx == jnx)
					T[inx].add(1);
				else
//					T[inx].add(T[inx-1].get(jnx-1) + T[inx-1].get(jnx));
					T[inx].add(get(T[inx-1].get(jnx-1) + T[inx-1].get(jnx)));
			}
		}
	}
	
	static int get(int num) {
		return num % 10000;
//		String a = String.valueOf(num);
//		int inx = a.length()-1;
//		while(a.charAt(inx) == '0') {
//			inx--;
//		}
//		return Integer.parseInt(a.substring(inx));
	}
	static int pascal(int n, int m) {
		if (m == 0 || n == m)
			return 1;
		
		return pascal(n-1, m-1) + pascal(n-1, m);
	}
}
