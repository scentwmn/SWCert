package pro.tree.kruskal;

import java.util.Arrays;

public class ArraysSortTest {

	static class Sort implements Comparable<Sort> {
		int id;
		int weight;

		@Override
		public int compareTo(Sort o) {
			return o.weight - weight;
		}
		@Override
		public String toString() {
			return "ToBeSort [id=" + id + ", weight=" + weight + "]";
		}
	}
	
	public static void main(String[] args) {
		Sort[] sort = new Sort[5];
		for (int inx = 0; inx < sort.length; inx++) {
			sort[inx] = new Sort();
		}
		sort[0].id = 0;
		sort[0].weight = 15;
		
		sort[1].id = 1;
		sort[1].weight = 10;
		
		sort[2].id = 2;
		sort[2].weight = 8;
		
		sort[3].id = 3;
		sort[3].weight = 30;
		
		sort[4].id = 4;
		sort[4].weight = 20;
		
		Arrays.sort(sort);
		
		for (int inx = 0; inx < sort.length; inx++) {
			System.out.println(sort[inx]);
		}
	}
}
