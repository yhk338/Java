import java.util.Arrays;

public class RecursionOne {
	public static void main(String[] args) {
		int[] A = {1, 2, 100, 4, 5};
		int n = A.length;
		
		
		System.out.println(RecursiveMax(A, n));
	}
	
	public static int RecursiveMax(int[] A, int n) {
		//split the original array into two arrays
		int[] left = Arrays.copyOfRange(A, 0, (n+1)/2);
		int[] right = Arrays.copyOfRange(A, (n+1)/2, n);
		//base case, return bigger out of two
		if (n ==2) {
			return Math.max(A[0], A[1]);
		}
		
		//if there is only one element, return that
		else if (n==1) {
			return A[0];
		}
		
		//compare max of two arrays until it reaches an array with only two element
		else {
			return Math.max(RecursiveMax(left, left.length), RecursiveMax(right, right.length));
		}
	}
}
