package pro.tree;

import java.util.Scanner;

public class Average2 {
	
	static int N = 0;
	static int K = 0;

	// 작은값이 우선순위 임
	static int[] MIN = new int[1000001];
	// 큰값이 우선순위 임
	static int[] MAX = new int[1000001];
	
	static int lenN = 0;
	static int lenX = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		K = scan.nextInt();
		
		int sumN = 0;
		int sumX = 0;

		for (int inx = 1; inx <= N; inx++) {
			int x = scan.nextInt();
			
			if (lenN < K) {
				pushN(x);
				pushX(x);
				sumN += x;
				sumX += x;
			}
			else {
				// 작은 것들 K개만 남김
				if (topX() > x) {
					sumN = sumN - topX() + x;
					popX();
					pushX(x);
				}
				// 큰 숫자 K개만 남김
				if (topN() < x) {
					sumX = sumX - topN() + x;
					popN();
					pushN(x);
				}
			}
			
			int p = inx < K ? inx : K;

			System.out.println(sumX/p + " " + sumN/p);
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
