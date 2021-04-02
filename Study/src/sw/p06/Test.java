package sw.p06;

public class Test {

	public static void main(String[] args) {

		final int MAX_NODE = 100_001;
		System.out.println(log2(MAX_NODE));
		System.out.println((int) Math.floor(log2(MAX_NODE)));
		
		System.out.println(Math.sqrt(11));
		System.out.println(MAX_NODE);
		System.out.println(Math.pow(2, 16));
		System.out.println(Math.pow(2, 17));
	}

	public static double log2(int N) {
		// calculate log2 N indirectly
		// using log() method
		double result = (double) (Math.log(N) / Math.log(2));

		return result;
	}

}
