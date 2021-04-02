package pro.tree;

import java.util.Scanner;

/*
 * Divide & Conquer
 */
public class PartialSum {
	
	static int N = 0;
	static int[] NUM = new int[1000000];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		N = scan.nextInt();
		for (int inx = 0; inx < N; inx++) {
			NUM[inx] = scan.nextInt();
		}

		System.out.println(getSubSum(0, N-1));
		
		scan.close();
	}
	
	static int getSubSum(int start, int end) {
		if (start >= end)
			return NUM[start];
		
		int mid = (start + end) / 2;
		int leftV = getSubSum(start, mid);
		int rightV = getSubSum(mid+1, end);
		
		int leftSub=0, leftMax=0;
		for (int inx = mid; inx >= 0; inx--) {
			leftSub += NUM[inx];
			if (leftMax < leftSub) {
				leftMax = leftSub;
			}
		}
		
		int rightSub=0, rightMax=0;
		for (int inx = mid; inx <= end; inx++) {
			rightSub += NUM[inx];
			if (rightMax < rightSub) {
				rightMax = rightSub;
			}
		}
		int midV = leftMax > rightMax ? leftMax : rightMax;
		
		if (leftV >= rightV && leftV >= midV)
			return leftV;
		else if (rightV >= leftV && rightV >= midV)
			return rightV;
		else
			return midV;
	}
}
