package sw.pretest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 컬러 판을 올리기
 */
public class Solution210405_2 {

	static int T, N, Q;
	static char[][][] panel = new char[11][8][8];
	
	static char[][][] q_panel = new char[6][8][8];
	
	static List<Integer> result = new ArrayList<Integer>();
	
	private static class Color {
		char[][] q;
		List<Integer> list = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		boolean isDone = false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tnx = 1; tnx <= T; tnx++) {
			N = Integer.parseInt(br.readLine());
			
			// 입력 판 데이터 읽음
			for (int inx = 1; inx <= N; inx++) {
				for (int jnx = 0; jnx < 8; jnx++) {
					String line = br.readLine();
					for (int knx = 0; knx < 8; knx++) {
						panel[inx][jnx][knx] = line.charAt(knx);
					}
				}
			}

			// 문제의 수
			Q = Integer.parseInt(br.readLine());
			// 문제 읽음
			for (int inx = 1; inx <= Q; inx++) {
				for (int jnx = 0; jnx < 8; jnx++) {
					String line = br.readLine();
					for (int knx = 0; knx < 8; knx++) {
						q_panel[inx][jnx][knx] = line.charAt(knx);
					}
				}
			}
			
			result.clear();
			for (int inx = 1; inx <= Q; inx++) {
				List<Integer> r = process2(inx);
				result.addAll(r);
			}
			
			// 결과 출력
			System.out.print("#"+tnx);
			for (int inx = 0; inx < result.size(); inx++) {
				System.out.print(" "+result.get(inx));
			}
			System.out.println();
		}
	}

	static List<Integer> process2(int qn) {
		List<Color> cList = new ArrayList<Color>();

		for (int inx = 1; inx <= N; inx++) {
			char[][] ro = getMatchRotate(q_panel[qn], panel[inx]);
			if (ro != null) {
				Color c = new Color();
				c.list.add(inx);
				c.map.put(inx, inx);
				c.q = setGray(q_panel[qn], ro);

				cList.add(c);
			}
		}

		List<Color> doneList = new ArrayList<Color>();

		while(! cList.isEmpty()) {
			Color c = cList.get(cList.size()-1);

			for (int inx = 1; inx <= N; inx++) {
				if (c.map.containsKey(inx))
					continue;

				char[][] ro = getMatchRotate(c.q, panel[inx]);
				if (ro != null) {
					c.list.add(inx);
					c.map.put(inx, inx);
					c.q = setGray(c.q, ro);
					
					if (isDone(c.q)) {
						c.isDone = true;
						doneList.add(c);
						cList.remove(cList.size()-1);
						break;
					}
				}
			}
		}

		int minLen = 9999;
		for (int inx = 0; inx < doneList.size(); inx++) {
			if (doneList.get(inx).list.size() < minLen)
				minLen = doneList.get(inx).list.size();
		}
		
		List<String> strList = new ArrayList<String>();
		for (int inx = 0; inx < doneList.size(); inx++) {
			if (doneList.get(inx).list.size() == minLen) {
				List<Integer> l = doneList.get(inx).list;
				StringBuffer str = new StringBuffer();
				for (int jnx = l.size()-1; jnx >= 0 ; jnx--) {
					if (l.get(jnx) < 10) {
						str.append("0");
					}
					str.append(String.valueOf(l.get(jnx)));
				}
				strList.add(str.toString());
			}
		}
		
		Collections.sort(strList);

		List<Integer> rtnList = new ArrayList<Integer>();
		rtnList.add(minLen);
		for (int inx = 0; inx < strList.get(0).length(); inx+=2) {
			rtnList.add(Integer.parseInt(strList.get(0).substring(inx, inx+2)));
		}
		
		return rtnList;
	}

	static List<Integer> process(int qn) {
		Map<Integer, Integer> checked = new HashMap<Integer, Integer>();
		List<Integer> aList= new ArrayList<Integer>();

		while(! isDone(q_panel[qn])) {
			int inx = 1;
			for (inx = 1; inx <= N; inx++) {
				if (checked.containsKey(inx))
					continue;

				char[][] ro = getMatchRotate(q_panel[qn], panel[inx]);
				if (ro != null) {
					setGray(q_panel[qn], ro);
					aList.add(inx);
					checked.put(inx, inx);
				}
			}
		}

		List<Integer> qrList= new ArrayList<Integer>();
		qrList.add(checked.size());
		for (int inx = aList.size()-1; inx >= 0; inx--) {
			qrList.add(aList.get(inx));
		}

		return qrList;

//		System.out.println("-----------------------------------------");
//		System.out.println(print(panel[1]));
//		System.out.println("-----------------------------------------");
//		System.out.println("90도 시계 반대방향 회전");
//		System.out.println("-----------------------------------------");
//		System.out.println(print(rotate(panel[1])));
//		System.out.println("-----------------------------------------");
//
//		System.out.println("\n\n");
//		System.out.println("-----------------------------------------");
//		System.out.println(print(panel[2]));
//		System.out.println("-----------------------------------------");
//		System.out.println("90도 시계 반대방향 회전");
//		System.out.println("-----------------------------------------");
//		System.out.println(print(rotate(panel[2])));
//		System.out.println("-----------------------------------------");
	}

	static boolean isDone(char[][] q) {
		boolean isDone = true;
		for (int inx = 0; inx < 8; inx++) {
			for (int jnx = 0; jnx < 8; jnx++) {
				if (q[inx][jnx] == 'R' || q[inx][jnx] == 'Y' || q[inx][jnx] == 'B') {
					isDone = false;
					break;
				}
			}
		}

		return isDone;
	}
	
	static char[][] setGray(char[][] qi, char[][] p) {
		char[][] q = copy(qi);
		for (int inx = 0; inx < 8; inx++) {
			for (int jnx = 0; jnx < 8; jnx++) {
				if (p[inx][jnx] != '.' && q[inx][jnx] != 'G' && p[inx][jnx] == q[inx][jnx]) {
					// 회색처리
					q[inx][jnx] = 'G';
				}
			}
		}
		return q;
	}
	
	static char[][] getMatchRotate(char[][] q, char[][] p) {
		char[][] ro = copy(p);
		if (isMatch(q, ro)) {
			return ro;
		}
		else {
			ro = rotate(ro);
			if (isMatch(q, ro))
				return ro;
			ro = rotate(ro);
			if (isMatch(q, ro))
				return ro;
			ro = rotate(ro);
			if (isMatch(q, ro))
				return ro;
		}

		return null;
	}
	// 매치여부 리턴
	static boolean isMatch(char[][] q, char[][] p) {
		int matchCnt = 0;
		int notMatchCnt = 0;
		for (int inx = 0; inx < 8; inx++) {
			for (int jnx = 0; jnx < 8; jnx++) {
				if (p[inx][jnx] == '.')
					continue;
				if (q[inx][jnx] == 'G')
					continue;
				if (p[inx][jnx] == q[inx][jnx]) {
					matchCnt++;
				}
				else {
					notMatchCnt++;
				}
			}
		}

		return matchCnt > 0 && notMatchCnt == 0;
	}

	// 90도 회전(시계방향)
	static char[][] rotate(char[][] p) {
		char[][] r = new char[8][8];
		
		for (int inx = 0; inx < 8; inx++) {
			for (int jnx = 0; jnx < 8; jnx++) {
				r[jnx][7-inx] = p[inx][jnx];
			}
		}
		return r;
	}
	static char[][] copy(char[][] p) {
		char[][] r = new char[8][8];
		
		for (int inx = 0; inx < 8; inx++) {
			for (int jnx = 0; jnx < 8; jnx++) {
				r[inx][jnx] = p[inx][jnx];
			}
		}
		return r;
	}

	// 출력용 문자열
	static String print(char[][] c) {
		StringBuffer sb = new StringBuffer();
		for (int inx = 0; inx < 8; inx++) {
			for (int jnx = 0; jnx < 8; jnx++) {
				if (jnx >= 1)
					sb.append(" ");
				sb.append(c[inx][jnx]);
			}
			if (inx < 7)
				sb.append("\n");
		}
		return sb.toString();
	}
}
