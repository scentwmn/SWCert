package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Height {
	
	static int T;
	static int N;
	static int I;
	static int H;
	static int R;
	static int[] E = new int[10001];
	static Map<String, String> map = new HashMap<String, String>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());
		for (int inx = 0; inx < T; inx++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			I = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			for (int jnx = 1; jnx <= N; jnx++) {
				E[jnx] = H;
			}

			map.clear();
			for (int jnx = 0; jnx < R; jnx++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st2.nextToken());
				int b = Integer.parseInt(st2.nextToken());
				
				int min = Math.min(a, b);
				int max = Math.max(a, b);
				
				if (! map.containsKey(min+"_"+max)) {
	
					for (int knx = min+1; knx <= max-1; knx++) {
						E[knx]--;
					}
					
					map.put(min+"_"+max, "");
				}
			}
			
			bw.write("#"+(inx+1));
			for (int jnx = 1; jnx <= N; jnx++) {
				bw.write(" " + E[jnx]);
			}
			bw.write('\n');
			bw.flush();
		}

	}

}
