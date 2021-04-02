package sw.p06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [기출P-0092][2021년 02월 20일] 약속
 * LCA(Lowest Common Ancestor)
 */
public class Solution {

	static int MAX_N = 300_001;
	static int MAX_D = 50_001;
	static int T, N, D;
	static List<Integer>[] CITY = new ArrayList[MAX_N];
	static int[][] DAY = new int[2][MAX_D];
	
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int inx = 1; inx <= MAX_N; inx++) {
			CITY[inx] = new ArrayList();
		}

		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			// 도시간 거리
			for (int inx = 1; inx <= N; inx++) {
				CITY[inx].clear();
			}
			st = new StringTokenizer(br.readLine());
			for (int inx = 1; inx <= N; inx++) {
				int dest = Integer.parseInt(st.nextToken());
				CITY[inx].add(dest);
				CITY[dest].add(inx);
			}
			// 여행지
			for (int inx = 1; inx <= D; inx++) {
				st = new StringTokenizer(br.readLine());
				DAY[0][inx] = Integer.parseInt(st.nextToken());
				DAY[1][inx] = Integer.parseInt(st.nextToken());
			}
			
			// 처리
			process();
			
			System.out.println("#"+tnx+" " + result);
		}
	}
	
	static void process() {
		result = 0;
		
		
	}
}
