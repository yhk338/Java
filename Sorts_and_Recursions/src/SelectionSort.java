
public class SelectionSort {
	public static void main(String[] args) {
		int[] A = {2, 6, 3, 7, 100, 1, 1};
		int n = A.length;
		int[] array = selectionSort(A, n);
		for (int z =0; z< array.length; z++ ) {
			System.out.print(selectionSort(A, n)[z] +" ");
		}
	}
	
	public static int[] selectionSort(int[] A, int n) {
		//starting from the end to start
		for (int i = n-1; i> -1; i--) {
			int max = i;
			//if last element is smaller than the one prior to that, switch them
			for (int j = i-1; j> -1; j--) {
				if (A[max]< A[j]) {
					max = j;
				}
			}
			int temp = A[max];
			A[max] = A[i];
			A[i]= temp; 
			//repeat for the whole array
			
		}
		return A;
	}
}
