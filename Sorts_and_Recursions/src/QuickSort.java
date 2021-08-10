import java.util.Arrays;
public class QuickSort {
	

    public static void main(String args[]) 
    { 
        int array[] = {7, 1, 1, 6, 100}; 
        int n = array.length; 
   
  
        QuickSort temp = new QuickSort(); 
        temp.sort(array, 0, n-1); 
  
        for (int i=0; i<n; ++i) 
            System.out.print(array[i]+" "); 
      
    } 

    int change(int array[], int start, int end) 
    { 
        int pivot = array[end];  
        int i = (start-1);
        
        for (int j=start; j<end; j++) { 
        	//if the pivot is bigger than start
            if (array[j] <= pivot) { 
                i++; 
  
                // switch j and i
                int temp = array[i]; 
                array[i] = array[j];                 
                array[j] = temp; 
            } 
        } 
  
       //switch i+1 index with end 
        int temp = array[i+1]; 
        array[i+1] = array[end]; 
        array[end] = temp; 
  
        return i+1; 
    } 
  
  
   
    void sort(int array[], int start, int end) { 
        if (start < end) { 
           //pivott to be partitioned
            int pivott = change(array, start, end); 
  
           //recursively sort
            sort(array, start, pivott-1); 
            sort(array, pivott+1, end); 
        } 
    } 
  
  

	 } 
