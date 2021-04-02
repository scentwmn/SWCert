package pro.graphalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MaximumCost {

	static int N, M, K;
	// cost : 번호,start,end,weight
	static List<String> cost;
	// parent
	static Map<Integer,Integer> parent = new HashMap<Integer,Integer>();
	static Map<Integer,Integer> rank = new HashMap<Integer,Integer>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		M = scan.nextInt();
		K = scan.nextInt();

		for (int inx = 1; inx <= N; inx++) {
			parent.put(inx, inx);
			rank.put(inx, 0);
		}

		cost = new ArrayList<String>(M);

		for (int inx = 1; inx <= M; inx++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();

			cost.add(inx+","+a+","+b+","+c);
		}

		// cost 정렬 : 내림차순 (Maximum을 구하기 위함)
		Collections.sort(cost, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String[] o1a = o1.split(",");
				String[] o2a = o2.split(",");

				return Integer.parseInt(o2a[3]) - Integer.parseInt(o1a[3]);
			}
		});

		int succCnt = 0;
		int failCnt = 0;
		List<Integer> succList = new ArrayList<Integer>();

		for (int inx = 0; inx < cost.size(); inx++) {
			String[] ca = cost.get(inx).split(",");
			int idx = Integer.parseInt(ca[0]);
			int a = Integer.parseInt(ca[1]);
			int b = Integer.parseInt(ca[2]);

			boolean isSuccess = union(a, b);
			if (isSuccess || failCnt >= K) {
				succCnt++;
				succList.add(idx);
			} else {
				failCnt++;
			}
		}

		Collections.sort(succList);
		System.out.println(succCnt);
		for (int inx = 0; inx < succList.size(); inx++) {
			if (inx > 0)
				System.out.print(" ");
			System.out.print(succList.get(inx));
		}
		System.out.println();
		scan.close();
	}

	// 성공여부 리턴
	static boolean union(int u, int v) {
		int rootU = find(u);
		int rootV = find(v);

		if (rootU == rootV)
			return false;


        // If x's rank is less than y's rank
        if (rank.get(rootU) < rank.get(rootV)) {

            // Then move x under y  so that depth
            // of tree remains less
            parent.put(rootU, rootV);

        // Else if y's rank is less than x's rank
        } else if (rank.get(rootV) < rank.get(rootU)) {

        	// Then move y under x so that depth of
            // tree remains less
            parent.put(rootV, rootU);

        } else { // if ranks are the same
            // Then move y under x (doesn't matter
            // which one goes where)
            parent.put(rootV, rootU);

            // And increment the result tree's
            // rank by 1
            rank.put(rootU, rank.get(rootU)+1);
        }

        return true;
	}

	static int find(int u) {
		if (u == parent.get(u))
			return u;

		return find(parent.get(u));
	}
}
