package pro.greedy;

import java.util.Iterator;
import java.util.TreeSet;

public class TreesetTest {

	public static void main(String[] args) {
		TreeSet<Integer> ts = new TreeSet<Integer>();
		ts.add(1);
		ts.add(5);
		ts.add(3);
		ts.add(4);
		ts.add(2);
		ts.add(6);
		ts.add(6);
		
		System.out.println(ts.higher(3));
		System.out.println(ts.lower(3));
		System.out.println(ts.ceiling(3));
		System.out.println(ts.floor(3));
		
		System.out.println(ts.size());
		Iterator<Integer> i = ts.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
	}
}
