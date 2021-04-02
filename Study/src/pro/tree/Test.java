package pro.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
	}

	static void test3() {
		int[][] a = new int[5][2];
		a[0][0] = 5; a[0][1] = 0;
		a[1][0] = 9; a[1][1] = 1;
		a[2][0] = 4; a[2][1] = 2;
		a[3][0] = 1; a[3][1] = 3;
		a[4][0] = 3; a[4][1] = 4;
		
		Arrays.sort(a);
		for (int inx = 0; inx < a.length; inx++) {
			System.out.println(a[inx][0] + ", " + a[inx][1]);
		}
	}

	static void test2() {
		int[] a = {3, 6, 8, 1, 2, 10};
		int[] b = Arrays.copyOf(a, a.length);
		int[] c = Arrays.copyOfRange(a, 0, 3);
		
		for (int inx = 0; inx < a.length; inx++) {
			System.out.print(a[inx]);
			System.out.print(" ");
		}
		System.out.println();
		for (int inx = 0; inx < b.length; inx++) {
			System.out.print(b[inx]);
			System.out.print(" ");
		}
		System.out.println();
		for (int inx = 0; inx < c.length; inx++) {
			System.out.print(c[inx]);
			System.out.print(" ");
		}
	}
	
	static void test1() {
		List<Integer> list = new ArrayList<Integer>(10);
		
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(9);
		list.add(7);
		list.add(3);
		list.add(5);
		list.add(7);
		
		int N = list.size();
		List<Integer> left = list.subList(0, N/2-1);
		List<Integer> right = list.subList(N/2-1, list.size());

		System.out.println(left);
		System.out.println(right);

		Collections.sort(left, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.intValue() - o1.intValue();
			}
		});
		Collections.sort(right);
		System.out.println(left);
		System.out.println(right);
		
		System.out.println(list);
	}
}
