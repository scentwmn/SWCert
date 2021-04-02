package pro.graphalgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShortestPath {
	
	static int MAX = 10000;

	static int N, M;
	static List<Integer>[] graph = new ArrayList[MAX];
	static int[] T = new int[MAX];
	static Map<Integer, Integer> checked = new HashMap<Integer, Integer>();
	
	static int start, end;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		
		for (int inx = 0; inx < N; inx++) {
			graph[inx] = new ArrayList();
		}
		
		for (int inx = 0; inx < M; inx++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			
			graph[a].add(b);
			graph[b].add(a);
		}
		start = scan.nextInt();
		end = scan.nextInt();

		for (int inx = 0; inx < N; inx++) {
			T[inx] = Integer.MAX_VALUE;
		}

		T[start] = 0;
		for (int inx = 0; inx < N; inx++) {
			
			int minValue = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int jnx = 0; jnx < N; jnx++) {
				if (! checked.containsKey(jnx) && T[jnx] < minValue) {
					minValue = T[jnx];
					minIndex = jnx;
				}
			}

			checked.put(minIndex, minIndex);

			for (int jnx = 0; jnx < graph[minIndex].size(); jnx++) {
				// minIndex -- node
				int node = graph[minIndex].get(jnx);
				if (T[node] > T[minIndex]+1) {
					T[node] = T[minIndex]+1;
				}
			}
		}

		System.out.println(T[end]);
		scan.close();
	}
}
