package pro.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TwoGroupGraph {

	static int N,M;
	
	static List<Integer>[] graph = new ArrayList[1001];
	static Map<Integer,Integer> visited = new HashMap<Integer,Integer>();
	
	static boolean isAllColor = true;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		
		for (int inx = 1; inx <= N; inx++) {
			graph[inx] = new ArrayList<Integer>();
		}
		for (int inx = 1; inx <= M; inx++) {
			int start = scan.nextInt();
			int end = scan.nextInt();
			
			graph[start].add(end);
			graph[end].add(start);
		}
		for (int inx = 1; inx <= N; inx++) {
			Collections.sort(graph[inx]);
		}
		
		visited.clear();
		isAllColor = true;

		DFS(1, 1);
		
		System.out.println(isAllColor ? "Yes":"No");

		scan.close();
	}
	
	static void DFS(int v, int col) {
		if (!isAllColor)
			return;

		visited.put(v, col);
		
		int nextCol = col == 1 ? 2 : 1;
		
		for (int inx = 0; inx < graph[v].size(); inx++) {
			int next = graph[v].get(inx);
			// 이미 방문했다면 색깔 비교
			if (visited.containsKey(next)) {
				if (nextCol != visited.get(next)) {
					isAllColor = false;
				}
			}
			else {
				DFS(next, col==1?2:1);
			}
		}
	}
}
