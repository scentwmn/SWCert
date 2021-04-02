package pro.sort;

import java.util.Scanner;

public class Combinationzero2 {

	/**
	 * n���� ����� m���� ������ ������� �̴� ����� ���� �����̶�� �ϸ� nCm���� ��Ÿ����.
	 *
	 * �� ������ �Ľ�Į�� �ﰢ���� ���� ������ ������ �ִٰ� �Ѵ�.
	 *
	 * n�� m�� �־������� nCm�� ���� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
	 *
	 * nCm�� �������� n!/m!(n-m)! ���� ���� �� �ִ�. (5! = 12345)
	 *
	 * ù° �ٿ� ���� n, m(0��m��n��30)�� ���´�. ù° �ٿ� nCm�� ���� ����Ѵ�.
	 *
	 * 5 2
	 *
	 * 10
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		int result = (combinationDigits(n, m));

		System.out.println(result);
	}

	private static int combinationDigits(int n, int m) {

		int topFives = 0, botFives = 0;
		int topTwos = 0, botTwos = 0;

		// top: n!
		// bot: m! + (n-m)!

		topFives = findTrailingZeros(n, 5);
		botFives = findTrailingZeros(m, 5) + findTrailingZeros((n - m), 5);

		topTwos = findTrailingZeros(n, 2);
		botTwos = findTrailingZeros(m, 2) + findTrailingZeros((n - m), 2);

		// when are 0z made?
		// System.out.println("top: " + top);
		// System.out.println("bot: " + bot);

		int trailingZerosFives = topFives - botFives;
		int trailingZerosTwos = topTwos - botTwos;

		return Math.min(trailingZerosFives, trailingZerosTwos);
	}

	private static int findTrailingZeros(int n, int num) {
		int count = 0;
		while (n != 0) {
			n /= num;
			count += n;
		}

		return count;
	}

}