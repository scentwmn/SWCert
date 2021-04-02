package sw.p01;

public class Test2 {

	public static void main(String[] args) {
		int N = 100001;
		int len = 1;
		while (len < N)
			len <<= 1;
		
		System.out.println(len*2);
		
	}
}
