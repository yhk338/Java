import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class program {
	private static BufferedReader br;

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//csv file to read the file and store in an array
		String csvFile = "src/yes.csv";
        String line = "";
        String cvsSplitBy = ",";
        State[] states = new State[57];
      
        int end = states.length;
        int size = states.length;
         
			
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			
			int i =0;
            while ((line = br.readLine()) != null) {
            		
	                // use comma as separator
	                String[] splitby = line.split(cvsSplitBy);
	                int percent =0;
	                //store information inside state object and store them in an array
	                State c = new State(splitby[0], splitby[1], splitby[2], splitby[3], splitby[4], splitby[5], splitby[6], splitby[7], percent);
	                states[i] = c;
	                i ++;
	                

            	
            
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		
		//take user inputs
		Scanner input = new Scanner(System.in);
		System.out.println("Insert a year that you want to see the percentage difference of (ex: 2010): ");
		String firstyear = input.next();
		System.out.println("Insert another year that you want to see the percentage difference of(ex: 2011): ");
		String secondyear = input.next();
		System.out.println("Insert what kind of sorting method you want to use out of 'merge', 'bubble', 'plainquick', 'improvedquick' (has to be same as listed, WITHOUT spaces) (ex: plainquick): ");
		String sort = input.next();
		System.out.println("Insert the specific state( to find the most similar state to state X in terms of percent change in population for a given year)(insert state name WITHOUT spaces");
		while (input.nextLine().contains(" ")) {
			System.out.println("ddd");
			System.out.println("Insert the specific state( to find the most similar state to state X in terms of percent change in population for a given year)(insert state name WITHOUT spaces");
		}
		String similar = input.nextLine().replaceAll("\\s","");
		System.out.println("");
	     
		
		//print out the percentage change of all the regions and states
		System.out.println("Percentage change of all the regions and states: ");
		for (int i =0; i<states.length; i++) {
			
			int year1 = Integer.parseInt(states[i].takeInput(firstyear)); 
			int year2 = Integer.parseInt(states[i].takeInput(secondyear)); 
			
			states[i].setPercent(states[i].calculate(year1, year2));
			
			System.out.println(states[i].getName() + " " + states[i].getPercent());
			
		}
		
		System.out.println("");
		
		//when user chooses merge, sort by merge sort
		if (sort.equals("merge")) {
			 long starts = System.currentTimeMillis();
			  program ob = new program(); 
		        ob.sort(states, 0, states.length-1); 
		        System.out.println("sort from hightest to lowest using merge: " );
		        for (int i=0; i<states.length; ++i) 
		            System.out.println(states[i].getName() + " "+states[i].getPercent()+", ");
		        System.out.println("");
		        long ends = System.currentTimeMillis();
		        long difference = (ends-starts);
		        System.out.println("");
		        System.out.println("Running time is (in milliseconds )" + difference);
		        System.out.println("");
		        System.out.println("The state that has the most close percentage difference from " + similar +" is: ");
		        StateSimilar(states, similar);
		}
		
		//when user chooses bubble, sort by bubble sort
		else if (sort.equals("bubble")) {
			long a = System.currentTimeMillis();
			State[] arrays = bubbleSort(states, size);
			System.out.println("sort from hightest to lowest using bubble: ");
			for (int z=0; z<arrays.length; z++) 
				System.out.print( arrays[z].getName() +" "+ states[z].getPercent()+", ");
			long b= System.currentTimeMillis();
			System.out.println("");
			System.out.println("Running time is (in milliseconds )" + (b-a));
			System.out.println("");
			 System.out.println("The state that has the most close percentage difference from " + similar +" is: ");
			 StateSimilar(states, similar);
			
		}
		
		//when user chooses plain quick sort, sort by plain quick sort
		else if (sort.equals("plainquick")) {
			long c = System.currentTimeMillis();
			int n= states.length;
			program temp = new program(); 
	        temp.sort(states, 0, n-1); 
	        System.out.println("sort from hightest to lowest using plain quick sort: ");
	        for (int i=0; i<n; ++i) 
	            System.out.print(states[i].getName() + " "+states[i].getPercent()+", "); 
	        
	        long d = System.currentTimeMillis();
	        System.out.println("");
			System.out.println("Running time is (in milliseconds) " + (d-c));
			System.out.println("");
			 System.out.println("The state that has the most close percentage difference from " + similar +" is: ");
			 StateSimilar(states, similar);
		}
		
		//when user chooses improved quick sort, sort by improved quick sort
		else if (sort.equals("improvedquick")) {
			long e = System.currentTimeMillis();
			int n= states.length;
			improvedquick(states, 0, n-1);
			System.out.println("sort from hightest to lowest using improved quick sort: ");
	        for (int i=0; i<n; ++i) 
	            System.out.print(states[i].getName() +" "+ states[i].getPercent()+", "); 
	        long f = System.currentTimeMillis();
	        System.out.println("");
			System.out.println("Running time is (in milliseconds) " + (f-e));
			System.out.println("");
			 System.out.println("The state that has the most close percentage difference from " + similar +" is: ");
			 StateSimilar(states, similar);
		}
		
		else {System.out.print("wrong input");
		
		}
		
		input.close();
            }
	
	//get the similar state using the sort method
	public static void StateSimilar(State[] A, String similar) {
		int s = 0;
		for (int i =0; i<A.length;i++) {
			if (similar.toLowerCase().equals(A[i].getName().toLowerCase())) {
				s = i;
			}
				
		}
		
		if (Math.abs(A[(s-1)].getPercent()-A[s].getPercent()) < Math.abs(A[(s+1)].getPercent()-A[s].getPercent())) {
			System.out.println(A[s-1].getName() + " " + A[s-1].getPercent());
		}
		
		else if (Math.abs(A[(s-1)].getPercent()-A[s].getPercent()) > Math.abs(A[(s+1)].getPercent()-A[s].getPercent())) {
			System.out.println(A[s-1].getName() + " " + A[s+1].getPercent());
		}
		else if (s == 0){
			System.out.println(A[s+1].getName() + " " + A[s+1].getPercent());
		}
		else if (s == A.length-1) {
			System.out.println(A[s-1].getName() + " " + A[s-1].getPercent());
		}
		else {
			System.out.println("Your state input may be wrong; check if you have spaces(has to be no space)");
		}
	}
	
	
	public static State[] bubbleSort(State[] A, int size) { 
	        // Base case 
	        if (size == 1) 
	            return A; 
	       
	        else {
	        	//compare two elements to see who is bigger and make the bigger one to the right side
	        	for (int i=0; i<size-1; i++) 
		            if (A[i].getPercent() > A[i+1].getPercent()) { 
		                // swap A[i], A[i+1] 
		                State temp = A[i]; 
		                A[i] = A[i+1]; 
		                A[i+1] = temp;  
		                //at the end, biggest will move to the end
		            } 
	        	//once biggest is decided, then exclude that out of the array and sort the remainders
	        	return bubbleSort(A, size-1);
	        }
	        
	    
	    } 
	    
	    
		
	
	 public static void merge(State[] A, int left, int middle, int right) 
	    { 
	    	//get size of left side and right side
	        int n1 = middle - left + 1; 
	        int n2 = right - middle; 
	  
	        //make arrays of left and right
	        State[] l = new State [n1]; 
	        State[] r = new State [n2]; 
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
	            if (l[i].getPercent() >= r[j].getPercent()) 
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
	    void sort(State[] A, int left, int right) { 
	        if (left < right) { 
	            
	            int middle = (left+right)/2; 
	  
	           //using sort methods devide the array into two and sort it
	            sort(A, left, middle); 
	            sort(A , middle+1, right); 
	  
	            // Merge the sorted halves 
	            merge(A, left, middle, right); 
	        } 
	    }
	    //////////////////////////////////////////////////////////////////////////////////
	    
	    int quickChange(State[] array, int start, int end) 
	    { 
	        State pivot = array[end];  
	        int i = (start-1);
	        
	        for (int j=start; j<end; j++) { 
	        	//if the pivot is bigger than start
	            if (array[j].getPercent() <= pivot.getPercent()) { 
	                i++; 
	  
	                // switch j and i
	                State temp = array[i]; 
	                array[i] = array[j];                 
	                array[j] = temp; 
	            } 
	        } 
	  
	       //switch i+1 index with end 
	        State temp = array[i+1]; 
	        array[i+1] = array[end]; 
	        array[end] = temp; 
	  
	        return i+1; 
	    } 
	  
	  
	   
	    void sortQuick(State[] array, int start, int end) { 
	        if (start < end) { 
	           //pivott to be partitioned
	            int pivott = quickChange(array, start, end); 
	  
	           //recursively sort
	            sortQuick(array, start, pivott-1); 
	            sortQuick(array, pivott+1, end); 
	        } 
	    }
	    
	    ///////////////////////////////////////////////////////////////////////
	    
	    static int improved(State[] array, int start, int end) 
	    { 
	        State pivot = array[end];  
	        int i = (start-1);
	        
	        for (int j=start; j<end; j++) { 
	        	//if the pivot is bigger than start
	            if (array[j].getPercent() <= pivot.getPercent()) { 
	                i++; 
	  
	                // switch j and i
	                State temp = array[i]; 
	                array[i] = array[j];                 
	                array[j] = temp; 
	            } 
	        } 
	  
	       //switch i+1 index with end 
	        State temp = array[i+1]; 
	        array[i+1] = array[end]; 
	        array[end] = temp; 
	  
	        return i+1; 
	    } 
	  
	  
	    //for better quick sort, I make it into one recursive call instead of two by changing the loop
	   //to while instead of if loop
	   //called tail call elimination
	    static void improvedquick(State[] array, int start, int end) { 
	    
	    
	        while (start < end) { 
	           //pivott to be partitioned
	            int pivott = improved(array, start, end); 
	  
	           //recursively sort
	            improvedquick(array, start, pivott-1); 
	            start = pivott+1;
	        } 
	    }
}



