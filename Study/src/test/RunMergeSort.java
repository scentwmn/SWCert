package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunMergeSort {
	
	static int T;
	static int N;
	static int[] run = new int[100000];
	static int[] temp = new int[100000];
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int inx = 0; inx < T; inx++) {
			N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			String[] ele = str.split(" ");
			for (int jnx = 0; jnx < ele.length; jnx++) {
				run[jnx] = Integer.parseInt(ele[jnx]);
				temp[jnx] = run[jnx];
			}
			
			process();
			System.out.println("#"+(inx+1) + " " + result);
		}

		if (br != null)
			br.close();
	}
	
	static void process() {
		result = 0;
		mergeSplit(0, N);
	}
	
	static void mergeSplit(int begin, int end) {
		if (end-begin < 2)
			return;
		
		int mid = (begin + end) / 2;
		mergeSplit(begin, mid);
		mergeSplit(mid, end);
		merge(begin, mid, end);
	}
	
	static void merge(int begin, int mid, int end) {
		int inx = begin;
		int jnx = mid;
		
		for (int knx = begin; knx < end; knx++) {
			temp[knx] = run[knx];
		}
		
		for (int knx = begin; knx < end; knx++) {
			if (inx < mid && (jnx >= end || temp[inx] >= temp[jnx])) {
				run[knx] = temp[inx];
				inx++;
			}
			else {
//				if (temp[inx] < temp[jnx])
					result += jnx-knx;

				run[knx] = temp[jnx];
				jnx++;
			}
		}
	}
}
