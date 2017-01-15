package test;

public class CountTest {

	public static void main(String[] args) {
		int[] cnt = {1, 1, 1, 2, 3, 3, 3, 3, 3};
		
		int acc = 0;
		int sum = 0;
		for (int i = 0; i < cnt.length; i++) {
			if (i == 0) {
				acc++;
				continue;
			}

			if (cnt[i] == cnt[i-1]) {
				acc++;
			}
			else {
				sum += acc * (acc-1) / 2;
				acc = 1;
			}
		}
		sum += acc * (acc-1) / 2;
		
		System.out.println(sum);
	}
}
