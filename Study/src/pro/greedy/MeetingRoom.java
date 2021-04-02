package pro.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MeetingRoom {

	static int N;
	static int MAX = 100000;
	static int[][] T = new int[MAX][2];
	static List<Integer> E = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		for (int inx = 0; inx < N; inx++) {
			T[inx][0] = scan.nextInt();
			T[inx][1] = scan.nextInt();
		}

		Arrays.sort(T, 0, N, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		
		for (int inx = 0; inx < N; inx++) {
			if (E.isEmpty()) {
				E.add(T[inx][1]);
			}
			else {
				boolean isAvail = false;
				int timeDiff = Integer.MAX_VALUE;
				int availIdx = 0;
				for (int jnx = 0; jnx < E.size(); jnx++) {
					if (E.get(jnx) <= T[inx][0]) {
						isAvail = true;
						availIdx = jnx;
						if (T[inx][0] - E.get(jnx) < timeDiff) {
							timeDiff = T[inx][0] - E.get(jnx);
						}
					}
				}
				
				if (isAvail) {
					E.remove(availIdx);
					E.add(T[inx][1]);
				}
				else {
					E.add(T[inx][1]);
				}
			}
		}

		System.out.println(E.size());
		scan.close();
	}
}
