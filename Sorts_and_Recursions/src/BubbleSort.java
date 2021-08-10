
public class BubbleSort {
	public static void main(String[] args) {
		int[] A = {2, 2, 5, 1, 11, 9, 0};
		int[] arrays = bubbleSort(A);
		for (int z=0; z<arrays.length; z++) {
			System.out.print(arrays[z]+" ");
		}
		
	}
	
	public static int[] bubbleSort(int[] A) {
		int temp;
		int length = A.length;
		//check this all over one more time
		for (int i = 0; i<length-1; i++) {
			//starting from the end, if the element is bigger than the last element before that, switch each other
			for (int j = length-1; j>i; j--) {
				if (A[j] < A[j-1]) {
					temp = A[j];
					A[j] = A[j-1];
					A[j-1] = temp;
				}
			}
		}
		return A;
	}
}
