package algorithm;

public class BitCount {

	public static void main(String[] args) throws Exception {

		int[] num = {0xffffffff, 0x00ffffff, 0x0000ffff, 0x11111111, 0x22222222, 0x33333333};
		
		for (int inx = 0; inx < num.length; inx++) {
			int x = num[inx];

			x = (x & 0x55555555) + ((x >>  1) & 0x55555555);
			x = (x & 0x33333333) + ((x >>  2) & 0x33333333);
			x = (x & 0x0f0f0f0f) + ((x >>  4) & 0x0f0f0f0f);
			x = (x & 0x00ff00ff) + ((x >>  8) & 0x00ff00ff);
			x = (x & 0x0000ffff) + ((x >> 16) & 0x0000ffff);

			System.out.println(x);
		}
		
	}
}
