package algorithm;

public class MaxSubarraySum {

	public static void main(String[] args) throws Exception {

		int[] input = new int[20];
		for (int inx = 0; inx < input.length; inx++) {
			input[inx] = (int)(Math.random() * 10);
		}
		
		for (int inx = 0; inx < input.length; inx++) {
			input[(int)(Math.random() * 100) % input.length] *= -1;;
		}
		
//		input = new int[] {9, -9, 3, -9, 5, 8, -2, 7, 5, 2, -5, 8, 0, -8, 8, 5, -6, -2, -2, 5};
//		input = new int[] {-2, 5, 3, -4, 5};

		long ans = Long.MIN_VALUE;
		int sum = 0;
		for (int inx = 0; inx < input.length; inx++) {
			sum += input[inx];
			ans = Math.max(ans, sum);
			sum = Math.max(sum, 0);
		}
		
		System.out.println(ans);
	}
}
