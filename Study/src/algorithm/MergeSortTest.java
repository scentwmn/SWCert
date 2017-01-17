package algorithm;

import java.util.Arrays;

public class MergeSortTest {
	
	static final int length = 100;
	static int[] data;
	static int[] temp;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		makeInitialData();
		
		split(0, data.length);
		
		// ∞À¡ı
//		for (int inx = 1; inx < data.length; inx++) {
//			if (data[inx] < data[inx-1]) {
//				System.err.println("data["+(inx-1)+"]="+data[inx-1]);
//				System.err.println("data["+(inx)+"]="+data[inx]);
//			}
//		}
		System.out.println(Arrays.toString(data));
		
		long end = System.currentTimeMillis();
		System.out.println((end-start) + " ms elapsed.");
	}
	
	static void makeInitialData() {
		data = new int[length];
		for (int inx = 0; inx < data.length; inx++) {
			data[inx] = (int)(Math.random() * 10000);
		}
		
//		data = new int[] {1, 5, 2, 3, 6, 0, 4};

		temp = Arrays.copyOf(data, data.length);
	}
	
	static void split(int begin, int end) {
		
		if (end-begin < 2)
			return;

		int mid = (begin + end) / 2;
		
		split(begin, mid);
		split(mid, end);
		merge(begin, mid, end);
	}
	
	static void merge(int begin, int mid, int end) {
		int inx = begin;
		int jnx = mid;
		
		for (int knx = begin; knx < end; knx++) {
			temp[knx] = data[knx];
		}

		for (int knx = begin; knx < end; knx++) {
			if (inx < mid && (jnx >= end || temp[inx] >= temp[jnx])) {
				data[knx] = temp[inx];
				inx++;
			}
			else {
				data[knx] = temp[jnx];
				jnx++;
			}
		}
	}
}
