package pro.graphalgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Party {
	
	static int MAX = 1001;

	static int N, M, K;
	static List<Integer>[] graph = new List[MAX];
	static List<Integer>[] cost = new List[MAX];

	static Map<Integer, Integer> checked = new HashMap<Integer, Integer>();
	static int[] T = new int[MAX];
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		K = scan.nextInt();
		
		for (int inx = 1; inx <= N; inx++) {
			graph[inx] = new ArrayList<Integer>();
			cost[inx] = new ArrayList<Integer>();
		}
		for (int inx = 1; inx <= M; inx++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();
			
			graph[a].add(b);
			cost[a].add(c);
		}
		
		int result = 0;
		
		// K 에서 각 집으로 이동
		shortest(K);
		for (int inx = 1; inx <= N; inx++) {
			result += T[inx];
		}
		
		// 각 집에서 K로 이동
		for (int inx = 1; inx <= N; inx++) {
			if (inx == K)
				continue;
			
			shortest(inx);
			result += T[K];
		}

		System.out.println(result);
		scan.close();
	}
	
	static void shortest(int start) {
		for (int inx = 1; inx <= N; inx++) {
			T[inx] = -1;
		}
		checked.clear();
		
		T[start] = 0;
		
		for (int inx = 1; inx <= N; inx++) {
			int minValue = Integer.MAX_VALUE;
			int minIndex = start;
			for (int jnx = 1; jnx <= N; jnx++) {
				if (! checked.containsKey(jnx) && T[jnx] != -1 && T[jnx] < minValue) {
					minIndex = jnx;
					minValue = T[jnx];
				}
			}
			
			checked.put(minIndex, minIndex);
			
			for (int jnx = 0; jnx < graph[minIndex].size(); jnx++) {
				int node = graph[minIndex].get(jnx);
				int nodeCost = cost[minIndex].get(jnx);
				
				if (T[node] == -1 || T[node] > T[minIndex] + nodeCost) {
					T[node] = T[minIndex] + nodeCost;
				}
			}
		}
	}
}
