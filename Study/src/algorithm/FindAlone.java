package algorithm;

public class FindAlone {
	
	static int T;
	static int N;

	public static void main(String[] args) throws Exception {
		
		int[] array = new int[101];
		array[0] = (int)(Math.random() * 1000000);

		for (int inx = 1; inx < array.length; inx+=2) {
			int num = (int)(Math.random() * 1000000);
			array[inx] = num;
			array[inx+1] = num;
		}



		int a = 0;
		for (int inx = 0; inx < array.length; inx++) {
			a ^= array[inx];
		}
		System.out.println("result : " + a);
		System.out.println("last array : " + array[0]);
	}
}
