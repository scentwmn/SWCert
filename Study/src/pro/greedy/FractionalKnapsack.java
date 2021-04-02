package pro.greedy;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class FractionalKnapsack {

	static int N, M;
	static int[][] WC = new int[100000][2];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		M = scan.nextInt();

		for (int inx = 0; inx < N; inx++) {
			WC[inx][0] = scan.nextInt();
			WC[inx][1] = scan.nextInt();
		}
		
		Arrays.sort(WC, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				double d1 = (double)o1[1]/(double)o1[0];
				double d2 = (double)o2[1]/(double)o2[0];
				return d2 - d1 < 0 ? -1 : 1;
			}
		});
		
		int WI = 0;
		double CI = 0d;
		for (int inx = 0; inx < N; inx++) {
			if (WC[inx][0] <= M-WI) {
				CI += WC[inx][1];
				WI += WC[inx][0];
			} else {
				CI += ((double)WC[inx][1] / (double)(WC[inx][0])) * (double)(M-WI);
				WI += M-WI;
			}
			
			if (M <= WI)
				break;
		}

		DecimalFormat df = new DecimalFormat("###.000");
		System.out.println(df.format((double)Math.round(CI*1000)/1000));

		scan.close();
	}
}
