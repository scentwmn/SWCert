package pro.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class HouseGroup {

	static int N;
	static int[][] graph = new int[25][25];
	
	static Queue<String> queue = new LinkedList<String>();
	static Map<String, String> unVisited = new HashMap<String, String>();
	static List<Integer> cntList = new ArrayList<Integer>();
	static int cnt = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		String firstLoc = "";
		for (int inx = 0; inx < N; inx++) {
			String line = scan.next();
			for (int jnx = 0; jnx < N; jnx++) {
				int p = line.charAt(jnx)=='0' ? 0 : 1;
				graph[inx][jnx] = p;
				if (p==1) {
					unVisited.put(getLoc(inx,jnx), "");
					if ("".equals(firstLoc)) {
						firstLoc = getLoc(inx, jnx);
					}
				}
			}
		}

		while(unVisited.size() > 0) {
			cnt = 1;
			String first = unVisited.keySet().iterator().next();
			queue.add(first);
			unVisited.remove(first);
			while(! queue.isEmpty()) {
				BFS();
			}

			cntList.add(cnt);
		}

		Collections.sort(cntList);
		System.out.println(cntList.size());
		for (int inx = 0; inx < cntList.size(); inx++) {
			System.out.println(cntList.get(inx));
		}

		scan.close();
	}
	
	static void BFS() {
		String loc = queue.poll();
		int[] xy = getLoc(loc);
		int inx = xy[0];
		int jnx = xy[1];
		// 왼쪽
		if (inx > 0 && graph[inx-1][jnx] == 1 && unVisited.containsKey(getLoc(inx-1,jnx))) {
			String next = getLoc(inx-1,jnx);
			unVisited.remove(next);
			queue.add(next);
			cnt++;
		}
		// 오른쪽
		if (inx < N-1 && graph[inx+1][jnx] == 1 && unVisited.containsKey(getLoc(inx+1,jnx))) {
			String next = getLoc(inx+1,jnx);
			unVisited.remove(next);
			queue.add(next);
			cnt++;
		}
		// 위
		if (jnx > 0 && graph[inx][jnx-1] == 1 && unVisited.containsKey(getLoc(inx,jnx-1))) {
			String next = getLoc(inx,jnx-1);
			unVisited.remove(next);
			queue.add(next);
			cnt++;
		}
		// 아래
		if (jnx < N-1 && graph[inx][jnx+1] == 1 && unVisited.containsKey(getLoc(inx,jnx+1))) {
			String next = getLoc(inx,jnx+1);
			unVisited.remove(next);
			queue.add(next);
			cnt++;
		}
	}
	
	static String getLoc(int inx, int jnx) {
		return String.valueOf(inx)+","+String.valueOf(jnx);
	}
	static int[] getLoc(String loc) {
		int inx = Integer.parseInt(loc.substring(0, loc.indexOf(',')));
		int jnx = Integer.parseInt(loc.substring(loc.indexOf(',')+1));
		return new int[]{inx, jnx};
	}
}
