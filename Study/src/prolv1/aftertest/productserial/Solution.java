package prolv1.aftertest.productserial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [����A-0033] ��ǰ�� �Ϸù�ȣ 1
 * ����, ����
 */
public class Solution {

	static int T, N;
	static String str1, str2;
	
	static int[] fact = new int[11];
	static int[] use = new int[11];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		factorial();
		
		for (int tnx = 1; tnx <= T; tnx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			str1 = st.nextToken();
			str2 = st.nextToken();

			int a = getOrder(str1);
			int b = getOrder(str2);
			
			int result = a > b ? a-b-1 : b-a-1;

			System.out.println("#"+tnx + " " + result);
		}
	}

	static int getOrder(String str) {
		Arrays.fill(use, 1);
		// ���� ����
		// str���ڿ��� ���°���� Ȯ��.
		int result = 0;
		for (int inx = 0; inx < str.length(); inx++) {
			char c = str.charAt(inx);
			int r = remain(c);
			result += r * fact[N-(inx+1)];
	
			use[(int)c - (int)'a' + 1] = 0;
		}

		return result;
	}

	static int remain(char c) {
		// c ���ں��� ���� ���ڵ���. ������ �ʴ� �� ī��Ʈ
		int sum = 0;
		for (int inx = 1; inx <= ((int)c-(int)'a'); inx++) {
			sum += use[inx];
		}
		return sum;
	}

	static void factorial() {
		int result = 1;
		for (int inx = 1; inx <= 10; inx++) {
			result *= inx;
			fact[inx] = result;
		}
	}
}
