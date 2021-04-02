package pro.tree.kruskal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UnionFind2 {

	static int N = 12;
	static Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) {
		init();
		
		Iterator<Integer> iter = parent.keySet().iterator();
		int preKey = -1;
		while(iter.hasNext()) {
			int key = iter.next();
			if (preKey < 0)
				preKey = key;

			merge(key, preKey);
		}

		System.out.println();
	}

	static void init() {
        // 1. 초기화
		int[][] initData = new int[][]{{1,1},{2,1},{3,2},{4,3},{5,4}
		                              ,{6,6},{7,6},{8,6},{9,6},{10,6}
		                              ,{11,11},{12,11}
                          			};

		for (int inx = 0; inx < initData.length; inx++) {
			parent.put(initData[inx][0], initData[inx][1]);
		}
    }

	// 3. Find (찾기) 연산
    // u 가 속한 트리의 루트 노드 번호를 반환한다.
	static int find (int u) {

    	// 루트 노드는 부모 노드 번호로 자기 자신을 가진다.
        if (u == parent.get(u))
        	return u;

        // 각 노드의 부모 노드를 찾아 올라간다.
        return find(parent.get(u));
    }

    // 2. Union (합치기) 연산
    // u 가 속한 트리와 v 가 속한 트리를 합친다.
    static void merge(int u, int v){
        // 각 원소가 속한 트리의 루트 노드를 찾는다.
        int rootU = find(u);
        int rootV = find(v);

        // u 와 v 가 이미 같은 트리에 속하는 경우에는 합치지 않는다.
        if (rootU == rootV)
        	return;

        parent.put(u, v);
    }
}
