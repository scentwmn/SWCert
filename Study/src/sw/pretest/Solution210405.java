package sw.pretest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 컬러 판을 올리기
 */
public class Solution210405 {

	static int T, N, Q;
	static char[][][] panel = new char[11][8][8];
	
	static char[][][] q_panel = new char[6][8][8];
	
	static List<Integer> result = new ArrayList<Integer>();
	
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
				List<Integer> r = process(inx);
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

	static List<Integer> process(int qn) {
		int[] qResult = new int[11];
		int pCnt = 0;
		List<Integer> aList= new ArrayList<Integer>();

		// N부터 일치여부 확인
		for (int jnx = 1; jnx <= N; jnx++) {
			for (int inx = N; inx >= 1; inx--) {
				if (qResult[inx] == 1)
					continue;

				int rotate = getMatchRotate(q_panel[qn], panel[inx]);
				// 매칭된 곳을 회색처리함.
				if (rotate != -1) {
					setGray(q_panel[qn], panel[inx], rotate);
					qResult[inx] = 1;
					pCnt++;
					aList.add(inx);
				}
			}
			// 성공 체크
			if (isDone(q_panel[qn]))
				break;
		}

		List<Integer> qrList= new ArrayList<Integer>();
		qrList.add(pCnt);
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
	
	static void setGray(char[][] q, char[][] p, int rotate) {
		char[][] r = p;
		if (rotate >= 90)
			r = rotate(r);
		if (rotate >= 180)
			r = rotate(r);
		if (rotate >= 270)
			r = rotate(r);

		for (int inx = 0; inx < 8; inx++) {
			for (int jnx = 0; jnx < 8; jnx++) {
				if (r[inx][jnx] != '.' && q[inx][jnx] != 'G' && r[inx][jnx] == q[inx][jnx]) {
					// 회색처리
					q[inx][jnx] = 'G';
				}
			}
		}
	}
	
	static int getMatchRotate(char[][] q, char[][] p) {
		int rotate = -1; // 회전각도 저장.
		if (isMatch(q, p)) {
			rotate = 0;
		}
		else if (isMatch(q, rotate(p)) ) {
			rotate = 90;
		}
		else if (isMatch(q, rotate(rotate(p))) ) {
			rotate = 180;
		}
		else if (isMatch(q, rotate(rotate(rotate(p))))) {
			rotate = 270;
		}

		return rotate;
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
