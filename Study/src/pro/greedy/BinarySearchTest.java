package pro.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTest {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(15);
		
		Collections.sort(list);
		System.out.println(list);
		
		int idx = Collections.binarySearch(list, 2);
		System.out.println(idx);
	}
}
