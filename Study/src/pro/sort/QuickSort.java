package pro.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuickSort {

	static int MAX = 10;//100_000;
	static int N;
	static int[] num = new int[MAX];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int inx = 0; inx < N; inx++) {
			num[inx] = Integer.parseInt(st.nextToken());
		}
		
		quicksort(0, N-1);
		
		for (int inx = 0; inx < N; inx++) {
			if (inx > 0)
				System.out.print(" ");
			System.out.print(num[inx]);
		}
		System.out.println();
		br.close();
	}

	static void quicksort(int start, int end) {
		if (start >= end)
			return;
		
		// pivot ����
		int pivot = num[start];
		// left ��� ��ȸ : pivot���� �۰ų� ���� ��.
		int[] left = new int[end-start+1];
		int leftCnt = getLeft(pivot, start+1, end, left);
		// right ��� ��ȸ : pivot���� ū ��.
		int[] right = new int[end-start+1];
		int rightCnt = getRight(pivot, start+1, end, right);
		
		// 2�� �迭�� ����
		// 5 2 3 4 9 8
		// p
		// 0 1 2 3 4 5
		for (int inx = 0; inx < leftCnt; inx++) {
			num[start+inx] = left[inx];
		}
		num[start+leftCnt] = pivot;
		for (int inx = 0; inx < rightCnt; inx++) {
			num[inx+start+leftCnt+1] = right[inx];
		}

		quicksort(start, start+leftCnt-1);
		quicksort(start+leftCnt+1, end);
	}

	static int getLeft(int pivot, int start, int end, int[] left) {
		int cnt = 0;
		for (int inx = start; inx <= end; inx++) {
			if (num[inx] <= pivot)
				left[cnt++] = num[inx];
		}
		return cnt;
	}
	static int getRight(int pivot, int start, int end, int[] right) {
		int cnt = 0;
		for (int inx = start; inx <= end; inx++) {
			if (num[inx] > pivot)
				right[cnt++] = num[inx];
		}
		return cnt;
	}
}
