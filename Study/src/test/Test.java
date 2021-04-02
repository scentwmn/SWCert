package test;

public class Test {

	public static void main(String[] args) {

		double d = 1.5345d;
		System.out.println((double)Math.round(d*1000)/1000);
	}
	
	static void swap1(int max) {

		int a, b = 0;
		int temp = 0;
		for (int inx = 0; inx < max; inx++) {
			a = (int)(Math.random()*10);
			b = (int)(Math.random()*10);
			
//			System.out.println(a + ", " + b);
			
			temp = a;
			a = b;
			b = temp;

//			System.out.println(a + ", " + b);
		}
	}

	static void swap2(int max) {

		int a, b = 0;
		for (int inx = 0; inx < max; inx++) {
			a = (int)(Math.random()*10);
			b = (int)(Math.random()*10);
			
//			System.out.println(a + ", " + b);
			
			a = a ^ b;
			b = a ^ b;
			a = a ^ b;

//			System.out.println(a + ", " + b);
		}
	}
}
