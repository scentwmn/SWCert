package pro.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class DfsBfs {

	static int N;
	static int M;

	@SuppressWarnings("unchecked")
	static List<Integer>[] graph = new ArrayList[1000];
//	static Stack<Integer> stack = new Stack<Integer>();
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	static Map<Integer, Integer> dfsVisited = new HashMap<Integer, Integer>();
	static Map<Integer, Integer> bfsVisited = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		
		for (int inx = 0; inx < N; inx++) {
			graph[inx] = new ArrayList<Integer>();
		}
		for (int inx = 0; inx < M; inx++) {
			int start = scan.nextInt();
			int end = scan.nextInt();
			graph[start].add(end);
			graph[end].add(start);
		}
		for (int inx = 0; inx < N; inx++) {
			Collections.sort(graph[inx]);
		}

		DFS(0);

		System.out.println();

		System.out.print(0);
		bfsVisited.put(0, 0);
		queue.add(0);
		while(! queue.isEmpty()) {
			BFS();
		}
		System.out.println();

		scan.close();
	}
	
	static void DFS(int elem) {
		System.out.print(elem);
		dfsVisited.put(elem, elem);
		for (int inx = 0; inx < graph[elem].size(); inx++) {
			if (! dfsVisited.containsKey(graph[elem].get(inx))) {
				System.out.print(" ");
				DFS(graph[elem].get(inx));
			}
		}
	}

	static void BFS() {
		int e = queue.poll();
		for (int inx = 0; inx < graph[e].size(); inx++) {
			if (! bfsVisited.containsKey(graph[e].get(inx))) {
				System.out.print(" " + graph[e].get(inx));
				queue.add(graph[e].get(inx));
				bfsVisited.put(graph[e].get(inx), graph[e].get(inx));
			}
		}
	}
}
