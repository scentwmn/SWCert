package prolv1.aftertest.baloon;

import java.util.TreeMap;

public class Test {

	static class Node {
		String a;
		String b;

		Node(String a, String b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((a == null) ? 0 : a.hashCode());
			result = prime * result + ((b == null) ? 0 : b.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (a == null) {
				if (other.a != null)
					return false;
			} else if (!a.equals(other.a))
				return false;
			if (b == null) {
				if (other.b != null)
					return false;
			} else if (!b.equals(other.b))
				return false;
			return true;
		}

	}

	public static void main(String[] args) {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		map.put(5, 3);
		map.put(8, 2);
		map.put(3, 1);
		
		System.out.println(map.ceilingKey(9));
		System.out.println(map.floorKey(6));
	}
}
