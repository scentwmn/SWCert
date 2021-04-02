package pro.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Shooting {

	static int N;
	static List<int[]> NUM = new ArrayList<int[]>(500);
	static List<int[]> ORIGIN = new ArrayList<int[]>(500);
	static int result = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		for (int inx = 0; inx < N; inx++) {
			int v = scan.nextInt();
			
			int[] num = {v, inx, 0};
			NUM.add(num);

			int[] num2 = {v, inx, 0};
			ORIGIN.add(num2);
		}
		for (int inx = 0; inx < N; inx++) {
			int l=0, r=0, c=0;
			if (inx == 0) {
				l = 1;
				r = N > 1 ? NUM.get(inx+1)[0] : 1;
			}
			if (inx == N-1) {
				l = N > 1 ? NUM.get(inx-1)[0] : 1;
				r = 1;
			}
			if (inx > 0 && inx < N-1) {
				l = NUM.get(inx-1)[0];
				r = NUM.get(inx+1)[0];
			}
			c = NUM.get(inx)[0];
			
			int rslt = c * l * r;
			NUM.get(inx)[2] = rslt;
			ORIGIN.get(inx)[2] = rslt;
		}

		process();
		System.out.println(result);
		
		scan.close();
	}
	
	static void process() {
		int[] first = NUM.get(0);
		int[] last = NUM.get(NUM.size()-1);
		
		NUM.remove(NUM.size()-1);
		NUM.remove(0);
		
		Collections.sort(NUM, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		while (! NUM.isEmpty()) {
			int[] num = NUM.get(0);
			result += ORIGIN.get(num[1])[2];

			NUM.remove(0);
			for (int inx = 0; inx < NUM.size(); inx++) {
				if (NUM.get(inx)[1] > num[1])
					NUM.get(inx)[1]--;
			}
			ORIGIN.remove(num[1]);

			int l=0, r=0, c=0;
			if (num[1] == 0) {
				l = 1;
				r = ORIGIN.size() > 1 ? ORIGIN.get(num[1]+1)[0] : 1;
			}
			if (num[1] == ORIGIN.size()-1) {
				l = ORIGIN.size() > 1 ? ORIGIN.get(num[1]-1)[0] : 1;
				r = 1;
			}
			if (num[1] > 0 && num[1] < ORIGIN.size()-1) {
				l = ORIGIN.get(num[1]-1)[0];
				r = ORIGIN.get(num[1]+1)[0];
			}
			c = ORIGIN.get(num[1])[0];

			int rslt = c * l * r;
			ORIGIN.get(num[1])[2] = rslt;
		}
		
		result += first[0] * last[0];
		result += first[0] < last[0] ? last[0] : first[0];

	}
}
