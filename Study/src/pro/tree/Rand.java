package pro.tree;

public class Rand {

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			double d = Math.random();
			if (i > 0)
				System.out.print(" ");
			System.out.print((int)(d*1000)%100);
		}
		System.out.println();
	}
}
