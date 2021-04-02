package pro.tree.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsSort {
	
	static class Weight {
		int id;
		int weight;

		public Weight(int id, int weight) {
			this.id = id;
			this.weight = weight;
		}
//		@Override
//		public int compare(Weight o1, Weight o2) {
//			return o1.weight - o2.weight;
//		}
		@Override
		public String toString() {
			return "Sort [id=" + id + ", weight=" + weight + "]";
		}
	}

	public static void main(String[] args) {
		List<String> strList = new ArrayList<String>(5);
		strList.add("abc");
		strList.add("bbb");
		strList.add("ddd");
		strList.add("eee");
		strList.add("zzz");
		
		Collections.sort(strList, Collections.reverseOrder());
//		Collections.reverse(strList);
		for (int inx = 0; inx < strList.size(); inx++) {
			System.out.println(strList.get(inx));
		}
		
		System.out.println("\n\n");
		
		// custom class sort
		List<Weight> weightList = new ArrayList<Weight>(5);
		weightList.add(new Weight(0, 30));
		weightList.add(new Weight(1, 10));
		weightList.add(new Weight(2, 20));
		weightList.add(new Weight(3, 35));
		weightList.add(new Weight(4, 5));
		
		Collections.sort(weightList, new Comparator<Weight>() {
			@Override
			public int compare(Weight o1, Weight o2) {
				return o2.weight - o1.weight;
			}
		});
		for (int inx = 0; inx < weightList.size(); inx++) {
			System.out.println(weightList.get(inx));
		}
	}
}
