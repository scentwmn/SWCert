package pro.greedy;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MaxInclination {
	
	static int MAX = 4;//100000
	static int N;
	static int[][] P = new int[MAX][2];

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		
		for (int inx = 0; inx < N; inx++) {
			P[inx][0] = scan.nextInt();
			P[inx][1] = scan.nextInt();
		}
		
		Arrays.sort(P, 0, N, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		double maxIncline = 0d;
		for (int inx = 0; inx < N-1; inx++) {
			double incline =
					(double)((double)P[inx+1][1]-(double)P[inx][1]) / (double)((double)P[inx+1][0]-(double)P[inx][0]);
			incline = incline < 0 ? incline * -1 : incline;
			
			if (maxIncline < incline)
				maxIncline = incline;
		}

		DecimalFormat df = new DecimalFormat("#.000");

		System.out.println(df.format((double)Math.round(maxIncline*1000)/1000));
		scan.close();
	}
}
