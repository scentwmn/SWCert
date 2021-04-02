package pro.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SkyLine {
	static int N = 0;
	static int LAST = 0;
	static List<int[]> RANGE = new ArrayList<int[]>(100000);
	static int[] H = new int[100001];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		for (int inx = 0; inx < N; inx++) {
			int[] range = new int[3];
			range[0] = scan.nextInt(); // from
			range[1] = scan.nextInt(); // to
			range[2] = scan.nextInt(); // height
			
			if (LAST < range[1])
				LAST = range[1];

			RANGE.add(range);
		}
		
		process();

		int prev = 1;
		for (int inx = 1; inx <= LAST; inx++) {
			if (inx==1 || H[inx] != prev) {
				System.out.println(inx + " " + H[inx]);
				prev = H[inx];
			}
			if (inx == LAST) {
				System.out.println(inx + " " + 0);
			}
		}

		scan.close();
	}
	
	static void process() {
		Collections.sort(RANGE, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		for (int inx = 0; inx < N; inx++) {
			int[] range = RANGE.get(inx);
			for (int jnx = range[0]; jnx <= range[1]; jnx++) {
				H[jnx] = range[2];
			}
		}
	}
}
