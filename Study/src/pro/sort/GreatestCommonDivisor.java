package pro.sort;

/**
 * �ִ����� ���ϱ�
 */
public class GreatestCommonDivisor {

	public static void main(String[] args) {
		// 20
		
		int a = 20;
		for (int inx = 1; inx < a; inx++) {
			if (a % inx == 0)
				System.out.println(inx);
		}
	}
}
