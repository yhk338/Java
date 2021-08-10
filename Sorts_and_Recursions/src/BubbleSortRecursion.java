

public class BubbleSortRecursion {
	public static void main(String[] args) {
		int[] A = {2, 7, 2, 5, 100, 11, 9};
		int size = A.length;
		for (int i =0; i<size ;i++) {
			System.out.print(bubbleSort(A, size )[i] +" ");
		}
	}
	
	public static int[] bubbleSort(int[] A, int size) { 
        // Base case 
        if (size == 1) 
            return A; 
       
        else {
        	//compare two elements to see who is bigger and make the bigger one to the right side
        	for (int i=0; i<size-1; i++) 
	            if (A[i] > A[i+1]) { 
	                // swap A[i], A[i+1] 
	                int temp = A[i]; 
	                A[i] = A[i+1]; 
	                A[i+1] = temp;  
	                //at the end, biggest will move to the end
	            } 
        	//once biggest is decided, then exclude that out of the array and sort the remainders
        	return bubbleSort(A, size-1);
        }
        
    
    } 
}
	

