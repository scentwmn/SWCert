package pro.graphalgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ViaShortest {

	static int N, M;
	static int MAX = 1001;
	static List<Integer>[] graph = new List[MAX];
	static List<Integer>[] cost = new List[MAX];
	static Map<Integer, Integer> checked = new HashMap<Integer, Integer>();
	
	static int[] T = new int[MAX];
	
	static int viaA, viaB;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		M = scan.nextInt();
		
		for (int inx = 1; inx <= N; inx++) {
			graph[inx] = new ArrayList<Integer>();
			cost[inx] = new ArrayList<Integer>();
		}
		for (int inx = 1; inx <= M; inx++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();
			
			graph[a].add(b);
			graph[b].add(a);
			cost[a].add(c);
			cost[b].add(c);
		}
		
		viaA = scan.nextInt();
		viaB = scan.nextInt();
		
		// 1 -> viaA -> viaB -> N
		int p1 = 0;
		shortest(1, viaA);
		p1 += T[viaA];
		shortest(viaA, viaB);
		p1 += T[viaB];
		shortest(viaB, N);
		p1 += T[N];

		// 1 -> viaB -> viaA -> N
		int p2 = 0;
		shortest(1, viaB);
		p2 += T[viaB];
		shortest(viaB, viaA);
		p2 += T[viaA];
		shortest(viaA, N);
		p2 += T[N];

		System.out.println(p1 > p2 ? p2 : p1);
		scan.close();
	}

	static void shortest(int start, int end) {
		for (int inx = 1; inx <= N; inx++) {
			T[inx] = -1;			
		}
		T[start] = 0;
		
		checked.clear();
		
		for (int inx = 1; inx <= N; inx++) {

			int minValue = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int jnx = 1; jnx <= N; jnx++) {
				if (! checked.containsKey(jnx) && T[jnx] != -1 && minValue > T[jnx]) {
					minValue = T[jnx];
					minIndex = jnx;
				}
			}
			
			checked.put(minIndex, minIndex);

			for (int jnx = 0; jnx < graph[minIndex].size(); jnx++) {
				int node = graph[minIndex].get(jnx);
				int nodeCost = cost[minIndex].get(jnx);
				
				if (T[node] == -1) {
					T[node] = T[minIndex] + nodeCost;
				}
				else if (T[node] > T[minIndex] + nodeCost) {
					T[node] = T[minIndex] + nodeCost;
				}
			}
		}
	}
}
