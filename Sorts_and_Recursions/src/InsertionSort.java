
public class InsertionSort {
	public static void main(String[] args) {
		int[] A = {2, 0, 1, 5, 1, 7};
		
		for (int z =0; z<A.length; z++) {
			System.out.print(insertSort(A)[z]);
		}
	}
	
	public static int[] insertSort(int[] A) {
		int temp;
		int size = A.length;
		//starting from end to start
		for (int i = size-2; i > -1; i--){
			int j = i;
			temp = A[i];
			//compare element with the element after that, and if the left is bigger than right, switch each other
			while (j < size-2 && temp > A[j + 1]){
				A[j] = A[j + 1];
				j++;
			}
			A[j] = temp;
		}
		return A;
	}
}
