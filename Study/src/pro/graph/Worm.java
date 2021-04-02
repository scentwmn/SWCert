package pro.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Worm {

	static int N,M;
	
	static List<Integer>[] graph = new ArrayList[101];
	static Map<Integer,Integer> visited = new HashMap<Integer,Integer>();
	
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

		DFS(1);
		
		System.out.println(visited.size()-1);

		scan.close();
	}
	
	static void DFS(int v) {
		visited.put(v, v);
		
		for (int inx = 0; inx < graph[v].size(); inx++) {
			int next = graph[v].get(inx);
			// 이미 방문했다면 색깔 비교
			if (!visited.containsKey(next)) {
				DFS(next);
			}
		}
	}
}
