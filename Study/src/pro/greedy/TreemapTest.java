package pro.greedy;

import java.util.TreeMap;

public class TreemapTest {

	public static void main(String[] args) {
		TreeMap tm = new TreeMap();
		tm.put("E", "EV");
		tm.put("C", "CV");
		tm.put("A", "AV");
		tm.put("D", "DV");
		
		Object key = tm.ceilingKey("B");
		System.out.println(key);
		
		tm.floorKey(key);
	}
}
