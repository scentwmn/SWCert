package pro.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StringDistance {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		byte[] strA = new byte[2000];
		byte[] strAInput = br.readLine().getBytes();
		byte[] strB = new byte[2000];
		byte[] strBInput = br.readLine().getBytes();
		
		final int lenA = strAInput.length;
		final int lenB = strBInput.length;

		for (int inx = 0; inx < strAInput.length; inx++) {
			strA[inx] = strAInput[inx];
			strB[inx] = strBInput[inx];
		}
		
		int lena = lenA-1;
		int lenb = lenB-1;
		while(lena >= 0 && lenb >= 0) {
			
		}

		br.close();
	}
}
