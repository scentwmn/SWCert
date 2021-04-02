package pro.tree;

import java.util.Scanner;

public class Average {
	
	static int N = 0;
	static int K = 0;

	// 작은값이 우선순위 임
	static int[] MIN = new int[1000001];
	// 큰값이 우선순위 임
	static int[] MAX = new int[1000001];
	
	static int[] T_MIN = new int[1000001];
	static int[] T_MAX = new int[1000001];
	static int tMinInx = 0;
	static int tMaxInx = 0;

	static int lenN = 0;
	static int lenX = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		K = scan.nextInt();
		
		for (int inx = 1; inx <= N; inx++) {
			int x = scan.nextInt();
			pushN(x);
			pushX(x);
			
			int p = inx < K ? inx : K;
			int sumN = 0;
			int sumX = 0;
			
			tMinInx = 0;
			tMaxInx = 0;
			for (int jnx = 1; jnx <= p; jnx++) {
				int topN = topN();
				int topX = topX();
				
				popN();
				popX();
				
				T_MIN[tMinInx++] = topN;
				T_MAX[tMaxInx++] = topX;
				sumN += topN;
				sumX += topX;
			}

			System.out.println("#"+inx + " " + sumX/p + " " + sumN/p);
			for (int jnx = 0; jnx < tMinInx; jnx++) {
				pushN(T_MIN[jnx]);
			}
			for (int jnx = 0; jnx < tMaxInx; jnx++) {
				pushX(T_MAX[jnx]);
			}
		}
		
		scan.close();
	}
	
	public static void pushN(int x) {
		MIN[++lenN] = x;
		
		// 부모와 비교
		int inx = lenN;
		while (inx > 1) {
			if (MIN[inx/2] > MIN[inx]) {
				int temp = MIN[inx];
				MIN[inx] = MIN[inx/2];
				MIN[inx/2] = temp;
				inx = inx/2;
			}
			else {
				break;
			}
		}
	}
	public static void pushX(int x) {
		MAX[++lenX] = x;
		
		// 부모와 비교
		int inx = lenX;
		while (inx > 1) {
			if (MAX[inx/2] < MAX[inx]) {
				int temp = MAX[inx];
				MAX[inx] = MAX[inx/2];
				MAX[inx/2] = temp;
				inx = inx/2;
			}
			else {
				break;
			}
		}
	}
	
	public static void popN() {
		MIN[1] = MIN[lenN];
		MIN[lenN] = 0;
		lenN--;
		
		int inx = 1;
		while (inx*2 <= lenN) {
			// 좌우 둘다 있는 경우
			if (inx*2+1 <= lenN) {
				int minInx = MIN[inx*2] < MIN[inx*2+1] ? inx*2 : inx*2+1;
				
				if (MIN[inx] > MIN[minInx]) {
					int temp = MIN[inx];
					MIN[inx] = MIN[minInx];
					MIN[minInx] = temp;
					inx = minInx;
				}
				else {
					break;
				}
			}
			else if (inx*2 <= lenN) {
				if (MIN[inx*2] < MIN[inx]) {
					int temp = MIN[inx];
					MIN[inx] = MIN[inx*2];
					MIN[inx*2] = temp;
					inx = inx*2;
				}
				else {
					break;
				}
			}
			else {
				break;
			}
		}
	}

	public static void popX() {
		MAX[1] = MAX[lenX];
		MAX[lenX] = 0;
		lenX--;
		
		int inx = 1;
		while (inx*2 <= lenX) {
			// 좌우 둘다 있는 경우
			if (inx*2+1 <= lenX) {
				int maxInx = MAX[inx*2] < MAX[inx*2+1] ? inx*2+1 : inx*2;
				
				if (MAX[inx] < MAX[maxInx]) {
					int temp = MAX[inx];
					MAX[inx] = MAX[maxInx];
					MAX[maxInx] = temp;
					inx = maxInx;
				}
				else {
					break;
				}
			}
			else if (inx*2 <= lenX) {
				if (MAX[inx*2] > MAX[inx]) {
					int temp = MAX[inx];
					MAX[inx] = MAX[inx*2];
					MAX[inx*2] = temp;
					inx = inx*2;
				}
				else {
					break;
				}
			}
			else {
				break;
			}
		}
	}

	public static int topN() {
		return MIN[1];
	}
	public static int topX() {
		return MAX[1];
	}

}
