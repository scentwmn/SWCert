package pro.tree;

import java.util.Scanner;

public class EyeHeight {
	static int N = 0;
	static int K = 0;
	
	static int[] NUM = new int[100001];
	static int len = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt();
		K = scan.nextInt();
		
		long sum = 0;

		for (int inx = 1; inx <= N; inx++) {
			int r = scan.nextInt();
			sum += (long)r;
			push(r);
		}
		
		System.out.println(sum/(long)N + " " + sum()/(long)N);
		
		scan.close();
	}
	
	static void push(int x) {
		NUM[++len] = x;
		
		// 큰값을 위로 보냄
		int inx = len;
		while (inx/2 >= 1) {
			if (NUM[inx/2] < NUM[inx]) {
				int temp = NUM[inx/2];
				NUM[inx/2] = NUM[inx];
				NUM[inx] = temp;
			} else {
				break;
			}
			
			inx = inx / 2;
		}
	}
	
	static void pop() {
		NUM[1] = NUM[len];
		NUM[len] = 0;
		len--;
		
		int inx = 1;
		while (inx*2 <= len) {
			// 두 자식 모두 있는 경우
			if (inx*2+1 <= len) {
				int maxIdx = NUM[inx*2] < NUM[inx*2+1] ? inx*2+1 : inx*2;
				if (NUM[inx] < NUM[maxIdx]) {
					int temp = NUM[inx];
					NUM[inx] = NUM[maxIdx];
					NUM[maxIdx] = temp;
					inx = maxIdx;
				}
				else {
					break;
				}
			}
			else if (inx*2 <= len) {
				if (NUM[inx] < NUM[inx*2]) {
					int temp = NUM[inx];
					NUM[inx] = NUM[inx*2];
					NUM[inx*2] = temp;
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
	
	static long top() {
		return (long)NUM[1];
	}
	
	static long sum() {
		long sum = 0;
		int inx = 1;
		while (len >= 1) {
			sum += inx <= K ? (long)top()/(long)3 : (long)top();
			pop();
			inx++;
		}

		return sum;
	}

}
