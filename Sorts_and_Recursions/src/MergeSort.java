public class MergeSort { 
    public static void main(String args[]) 
    { 
        int A[] = {2, 0, 100, 7, 15, 2};
  
        MergeSort temp = new MergeSort(); 
        temp.sort(A, 0, A.length-1); 
         
        for (int i=0; i<A.length; ++i) 
            System.out.print(A[i] + " ");
         
    } 
    
    public static void merge(int A[], int left, int middle, int right) 
    { 
    	//get size of left side and right side
        int n1 = middle - left + 1; 
        int n2 = right - middle; 
  
        //make arrays of left and right
        int l[] = new int [n1]; 
        int r[] = new int [n2]; 
        for (int i=0; i<n1; ++i) 
            l[i] = A[left + i];
        
        for (int j=0; j<n2; ++j) 
            r[j] = A[middle + 1+ j]; 
  
  
     //indexs of the two arrays
        int i = 0, j = 0; 
  
        //if right one is bigger, then put that into the original
        int k = left; 
        while (i < n1 && j < n2) 
        { 
            if (l[i] <= r[j]) 
            { 
                A[k] = l[i]; 
                i++; 
            } 
            else
            { 
                A[k] = r[j]; 
                j++; 
            } 
            k++; 
        } 
  
        
        while (i < n1) 
        { 
            A[k] = l[i]; 
            i++; 
            k++; 
        } 
  
        
    } 
  
   //take left and right elements and take the middle one out of those two 
    void sort(int A[], int left, int right) { 
        if (left < right) { 
            
            int middle = (left+right)/2; 
  
           //using sort methods devide the array into two and sort it
            sort(A, left, middle); 
            sort(A , middle+1, right); 
  
            // Merge the sorted halves 
            merge(A, left, middle, right); 
        } 
    } 
  
   
  
   
} 

