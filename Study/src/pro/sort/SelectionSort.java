package pro.sort;

public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = new int[]{4,8,4,9,11,2,5,3,23,14};
		
		int i = 0;
		while (i < arr.length-1) {
			int minIdx = i;
			int minVal = arr[i];

			for (int inx = i+1; inx < arr.length; inx++) {
				if (arr[inx] < minVal) {
					minVal = arr[inx];
					minIdx = inx;
				}
			}
			
			int temp = arr[i];
			arr[i] = minVal;
			arr[minIdx] = temp;
			i++;
		}

		for (int inx = 0; inx < arr.length; inx++) {
			System.out.print(arr[inx]+" ");
		}
	}
}
