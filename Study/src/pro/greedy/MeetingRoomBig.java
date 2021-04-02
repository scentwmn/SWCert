package pro.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MeetingRoomBig {

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
		
		
		E.add(T[0][1]);
		for (int inx = 1; inx < N; inx++) {
			int fkIdx = binarySearch(T[inx][0]);
			while (fkIdx >= E.size()) {
				fkIdx--;
			}
			if (fkIdx != -1 && E.get(fkIdx) <= T[inx][0]) {
				int nextIdx = fkIdx;
				while (nextIdx >= 0) {
					int v = E.get(nextIdx);
					if (v < T[inx][0])
						break;
					fkIdx = nextIdx;
					nextIdx--;
				}

				E.remove(fkIdx);
				E.add(T[inx][1]);
				Collections.sort(E);
			}
			else {
				E.add(T[inx][1]);
				Collections.sort(E);
			}
		}

		System.out.println(E.size());
		scan.close();
	}
	
	static int binarySearch(Integer key) {
		int idx = Collections.binarySearch(E, key);
		if (idx >= 0)
			return idx;
		else if (idx == -1 && E.size() >= 0)
			return 0;
		else if (idx * -1 - 1 <= E.size())
			return idx * -1 - 1;
		else
			return -1;
	}
}
