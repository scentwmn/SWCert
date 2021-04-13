package sw.p06;

import java.util.Arrays;

public class LCATest {
	
	static final int MAX_NODE = 16;
	static final int MAX_LEVEL = (int)(Math.log(MAX_NODE) / Math.log(2));
	static Node[] nodes = new Node[MAX_NODE];

	static class Node {
		int no;
		int[] parent = new int[MAX_LEVEL]; // root의 parent = 0
		int level;	// root의 level = 1

		Node(int no, int parent) {
			this.no = no;

			if (parent == 0)
				this.level = 1;
			else
				this.level = nodes[parent].level+1;

			Arrays.fill(this.parent, -1);

			if (parent == 0)
				this.parent[0] = parent;
			else
				setParent(parent);
		}

		public void setParent(int p) {
			parent[0] = p;

			for (int inx = 1; inx < MAX_LEVEL; inx++) {
				// 6 -> parent 13.
				// nodes[13].parent[0] 은  6의 2^1의 parent임.
				if (parent[inx-1] <= 0)
					break;
				int tmp = nodes[parent[inx-1]].no;
				
				if (nodes[tmp].parent[inx-1] <= 0)
					break;
				this.parent[inx] = nodes[tmp].parent[inx-1];
			}
		}

		@Override
		public String toString() {
			return "Node [no=" + no + ", parent=" + parent + ", level=" + level + "]";
		}
	}

	public static void main(String[] args) {
		nodes[1] = new Node(1, 0);
		nodes[3] = new Node(3, 1);
		nodes[5] = new Node(5, 1);
		nodes[12] = new Node(12, 3);
		nodes[2] = new Node(2, 12);
		nodes[14] = new Node(14, 12);
		nodes[9] = new Node(9, 2);
		nodes[11] = new Node(11, 2);
		nodes[13] = new Node(13, 2);
		nodes[6] = new Node(6, 13);
		
		nodes[8] = new Node(8, 5);
		nodes[4] = new Node(4, 8);
		nodes[15] = new Node(15, 8);
		nodes[7] = new Node(7, 15);
		nodes[10] = new Node(10, 15);

		findLCA(6, 7);

		System.out.println();
	}
	
	static void findLCA(int a, int b) {
		
	}
}
