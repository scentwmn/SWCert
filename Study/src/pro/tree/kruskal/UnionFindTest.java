package pro.tree.kruskal;

public class UnionFindTest {
	static int V; // vertex ¼ö
	static int E; // edge ¼ö
	static Edge[] edge;
	
//	static int[][] INIT_EDGE = {{0,1},{1,2},{1,3},{1,4},{3,6},{2,5},{5,7},{6,7}};
	static int[][] INIT_EDGE = {{0,1},{1,2},{1,3},{1,4},{3,6},{2,5},{5,7}};
	
	static class Edge {
		int src;
		int dest;
		int weight;
		@Override
		public String toString() {
			return "Edge [src=" + src + ", dest=" + dest + ", weight=" + weight + "]";
		}
	}

	public static void main(String[] args) {
		V = 8;
		E = INIT_EDGE.length;

		edge = new Edge[E];
		for (int inx = 0; inx < edge.length; inx++) {
			edge[inx] = new Edge();
			
			edge[inx].src = INIT_EDGE[inx][0];
			edge[inx].dest = INIT_EDGE[inx][1];
		}

		System.out.println("isCycle : " + isCycle());
	}

	static boolean isCycle() {
		int[] parent = new int[V]; // all vertex has parent
		for (int inx = 0; inx < parent.length; inx++) {
			parent[inx] = -1;
		}

		for (int inx = 0; inx < edge.length; inx++) {
			int x = find(parent, edge[inx].src);
			int y = find(parent, edge[inx].dest);
			
			if (x == y)
				return true;
			
			union(parent, x, y);
		}
		
		return false;
	}

	// find parent
	static int find(int[] parent, int v) {
		if (parent[v] == -1)
			return v;
		return find(parent, parent[v]);
	}
	
	static void union(int[] parent, int x, int y) {
		int xset = find(parent, x);
		int yset = find(parent, y);
		
		parent[xset] = yset;
	}
}
