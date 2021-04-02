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
        // 1. �ʱ�ȭ
		int[][] initData = new int[][]{{1,1},{2,1},{3,2},{4,3},{5,4}
		                              ,{6,6},{7,6},{8,6},{9,6},{10,6}
		                              ,{11,11},{12,11}
                          			};

		for (int inx = 0; inx < initData.length; inx++) {
			parent.put(initData[inx][0], initData[inx][1]);
		}
    }

	// 3. Find (ã��) ����
    // u �� ���� Ʈ���� ��Ʈ ��� ��ȣ�� ��ȯ�Ѵ�.
	static int find (int u) {

    	// ��Ʈ ���� �θ� ��� ��ȣ�� �ڱ� �ڽ��� ������.
        if (u == parent.get(u))
        	return u;

        // �� ����� �θ� ��带 ã�� �ö󰣴�.
        return find(parent.get(u));
    }

    // 2. Union (��ġ��) ����
    // u �� ���� Ʈ���� v �� ���� Ʈ���� ��ģ��.
    static void merge(int u, int v){
        // �� ���Ұ� ���� Ʈ���� ��Ʈ ��带 ã�´�.
        int rootU = find(u);
        int rootV = find(v);

        // u �� v �� �̹� ���� Ʈ���� ���ϴ� ��쿡�� ��ġ�� �ʴ´�.
        if (rootU == rootV)
        	return;

        parent.put(u, v);
    }
}
