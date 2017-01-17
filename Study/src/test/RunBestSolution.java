package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunBestSolution {
	
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
			
			System.out.println("#"+(inx+1) + " " + merge_sort(0,N-1));
		}

		if (br != null)
			br.close();
	}
	
	static long merge_sort(int s, int e) {
		if (s==e)
			return 0;
		
		int m = s+e >> 1;
		long ret = merge_sort(s, m) + merge_sort(m+1, e);
		for (int i=s,l=s,r=m+1; i<=e; i++) {
			if (l > m || r <= e && run[r] <= run[l]) {
				ret += l-s;
				temp[i] = run[r++];
			}
			else {
				temp[i] = run[l++];
			}
		}
		
		for (int i=s; i<=e; i++)
			run[i] = temp[i];
		
		return ret;

	}
}
