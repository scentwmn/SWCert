package sw.p02;

import java.util.LinkedList;

public class Test {

	public static void main(String[] args) {
		LinkedList<Integer> minrouteList = new LinkedList<Integer>();
		minrouteList.add(1);
		minrouteList.add(5);
		minrouteList.add(2);
		minrouteList.add(3);
		
		System.out.println(minrouteList.get(0));
		minrouteList.remove(0);
		System.out.println(minrouteList.get(0));
		minrouteList.remove(0);
		System.out.println(minrouteList.get(0));
		minrouteList.remove(0);
		System.out.println(minrouteList.get(0));
		minrouteList.remove(0);

		System.out.println();
	}
}
