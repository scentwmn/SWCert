package pro.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSort {

	static int MAX = 100_000;
	static int N;
	static int[] num = new int[MAX];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int inx = 0; inx < N; inx++) {
			num[inx] = Integer.parseInt(st.nextToken());
		}
		
		mergeSort(0, N-1);
		
		for (int inx = 0; inx < N; inx++) {
			if (inx > 0)
				System.out.print(" ");
			System.out.print(num[inx]);
		}
	}
	
	static void mergeSort(int start, int end) {
		if (start >= end)
			return;
		
		int mid = (start + end) / 2;
		mergeSort(start, mid);
		mergeSort(mid+1, end);
		
		merging(start, mid, mid+1, end);
	}
	
	static void merging(int s1, int e1, int s2, int e2) {
		int[] temp = new int[e2-s1+1];
		int tempIdx = 0;
		
		int p = s1;
		int q = s2;
		while(p <= e1 && q <= e2) {
			if (num[p] <= num[q]) {
				temp[tempIdx++] = num[p];
				p++;
			}
			else {
				temp[tempIdx++] = num[q];
				q++;
			}
		}
		if (p <= e1) {
			for (int inx = p; inx <= e1; inx++) {
				temp[tempIdx++] = num[inx];
			}
		}
		if (q <= e2) {
			for (int inx = q; inx <= e2; inx++) {
				temp[tempIdx++] = num[inx];
			}
		}
		
		for (int inx = s1; inx <= e2; inx++) {
			num[inx] = temp[inx-s1];
		}
	}
}
