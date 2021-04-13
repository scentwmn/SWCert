package prolv1.aftertest.onecm;

import java.util.Scanner;

public class Nqueen {
	static int N;
	static boolean[][] grid;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		grid = new boolean[N][N];
		System.out.println(dfs(0));

		scan.close();
	}

	static int dfs(int x){
		if (x == N)
			return 1;

		int cnt = 0;
		for (int inx = 0; inx < N; inx++) {
			if (isValid(x, inx)) {
				grid[inx][x] = true;
				cnt += dfs(x+1);
				grid[inx][x] = false;
			}
		}

		return cnt;
	}

	static boolean isValid(int x, int y) {
		int diff = 0;
		for (int inx = x-1; inx >= 0; inx--) {
			diff++;
			if (grid[y][inx]
				   || (y-diff >= 0 && grid[y-diff][inx])
				   || (y+diff < N && grid[y+diff][inx]))
			{
				return false;
			}
		}
		
		return true;
	}
}
