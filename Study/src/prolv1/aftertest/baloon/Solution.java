package prolv1.aftertest.baloon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * [연습A-0007] 풍선 맞추기
 * Ad-hoc
 */
public class Solution {

	static int T, N;
	
	static class Baloon {
		int num;
		TreeMap<Integer, Integer> loc = new TreeMap<Integer, Integer>();
		Baloon(int num, int inx) {
			this.num = num;
			loc.put(inx, inx);
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((loc == null) ? 0 : loc.hashCode());
			result = prime * result + num;
			return result;
		}
		@Override
		public String toString() {
			return "Baloon [num=" + num + ", loc=" + loc + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			N = Integer.parseInt(br.readLine());

			TreeMap<Integer, Baloon> map = new TreeMap<Integer, Baloon>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int inx = 1; inx <= N; inx++) {
				int a = Integer.parseInt(st.nextToken());
				if (map.containsKey(a)) {
					map.get(a).loc.put(inx, inx);
				} else {
					map.put(a, new Baloon(a, inx));
				}
			}

			int arrCnt = 0;
			while (! map.isEmpty()) {
				Entry<Integer, Baloon> last = map.lastEntry();
				int key = last.getKey();
				Baloon baloon = last.getValue();
				int inx = baloon.loc.firstKey();
				arrCnt++;
				
				map.remove(key);
				while (true) {
					key--;
					if (map.isEmpty() || ! map.containsKey(key))
						break;
					if (map.get(key).loc.ceilingEntry(inx) == null)
						break;
					
					int r = map.get(key).loc.ceilingKey(inx);
					map.get(key).loc.remove(r);
					
					inx = r;

					if (map.get(key).loc.isEmpty())
						map.remove(key);
				}
			}
			System.out.println("#"+tnx + " " + arrCnt);
		}

		br.close();
	}
}
