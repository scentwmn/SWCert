package pro.sort;

public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = new int[]{14,4,8,23,11,5,3,2,4,9};
		
		int i = 1;
		while (i < arr.length) {
			for (int inx = i; inx > 0; inx--) {
				if (arr[inx] < arr[inx-1]) {
					int temp = arr[inx];
					arr[inx] = arr[inx-1];
					arr[inx-1] = temp;
				}
				else
					break;
			}
			i++;
		}
		
		for (int inx = 0; inx < arr.length; inx++) {
			System.out.print(arr[inx] + " ");
		}
	}
}
