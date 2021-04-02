package pro.sort;

import java.util.Scanner;

public class FindKthMax {

	static int N,K;
	static int[] arr = new int[100000];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		K = scan.nextInt();
		
		for (int inx = 0; inx < N; inx++) {
			arr[inx] = scan.nextInt();
		}
		
		int i = 0;
		while (i < K) {
			int maxIdx = i;
			for (int inx = i+1; inx < N; inx++) {
				if (arr[maxIdx] < arr[inx]) {
					maxIdx = inx;
				}
			}

			int temp = arr[i];
			arr[i] = arr[maxIdx];
			arr[maxIdx] = temp;
			
			i++;
		}
		
		System.out.println(arr[K-1]);
		scan.close();
	}
}
