import java.util.ArrayList;

public class Queue {
	// Queue represents a pile of QNode which is each customer
	    QNode front, rear; 
	    int size=0;
	    int breaktime=0;
	    int longest = 0;
	    int numberCustomers=0;
	    //this saves wait list
	    static ArrayList<Integer> savewait= new ArrayList<Integer> ();
	    int maxSize=0;
	    
	    public Queue() { 
	        this.front = this.rear = null; 
	    } 
	    
	    //a method to figure out the total break time and the longest break time 
	    int longestBreaktime(int waitTime, QNode temp) {
	    	
	    	if (this.front.wait_time==0) {
	    		int eachBreak = this.front.arrival_time- temp.service_timeEnding;
	    		breaktime+= eachBreak;
	    		if (longest < eachBreak) {
	    			longest= eachBreak;
	    		}
	    		return breaktime;
	    	}
	    	
			return breaktime;
	    }
	    
	   //a method to get the wait time of each person
	     int getWaitTime(int arrival_time, QNode temp) {
	    	 //1. this is the case when the first person arrives before 9 a.m.
	    	 if (arrival_time <32400) {
	    		 //for the wait time of the first person
		    	 if (size== maxSize) {
		    			 this.front.wait_time = 32400-arrival_time;
		    			 this.front.service_timeEnding = 32700;
		    			 savewait.add(this.front.wait_time);
		    			 return this.front.wait_time;
		 
		    	 }
		    	 //for the rest of them
		    	 else {
		    		 //if there is no waiting
		    		 if (temp.service_timeEnding< arrival_time) {
		    			 this.front.wait_time= 0;
		    			 this.front.service_timeEnding = arrival_time +300;
		    			 if (this.front.arrival_time+this.front.wait_time+300  !=this.front.service_timeEnding) {
		    				 numberCustomers++;
		    			 }
		    			 savewait.add(this.front.wait_time);
		    			 return this.front.wait_time;
		    			 
		    		 }
		    		 //if there is waiting and there is person before you
		    		 else {
				    	this.front.wait_time = temp.service_timeEnding - arrival_time;
				    	this.front.service_timeEnding = arrival_time +this.front.wait_time +300;
				    	if (this.front.service_timeEnding< this.front.wait_time) {
		    				 numberCustomers++;
		    			 }
				    	//save wait time
				    	savewait.add(this.front.wait_time);
				    	return this.front.wait_time;
		    		 
		    		 }
		    		 
		    	 }
	    	 }
	    	 
	    	 //this is the case when the first person arrives after 9
	    	 else {
	    		 //for the first person arriving
		    	 if (size ==maxSize ) {
		    			 this.front.wait_time = 0;
		    			 this.front.service_timeEnding = arrival_time +300;
		    			 savewait.add(this.front.wait_time);
		    			 return this.front.wait_time;
		    	 }
		    	 //for the rest of them
		    	 else {
		    		 //if there is no waiting
		    		 if (temp.service_timeEnding< arrival_time) {
		    			 this.front.wait_time= 0;
		    			 this.front.service_timeEnding = arrival_time +300;
		    			 if (this.front.service_timeEnding!= this.front.wait_time) {
		    				 numberCustomers++;
		    			 }
		    			 savewait.add(this.front.wait_time);
		    			 return this.front.wait_time;
		    			 
		    		 }
		    		 //if there is a waiting
		    		 else {
				    	this.front.wait_time = temp.service_timeEnding - arrival_time;
				    	this.front.service_timeEnding = arrival_time +this.front.wait_time +300;
				    	if (this.front.service_timeEnding!= this.front.wait_time) {
		    				 numberCustomers++;
		    			 }
				    	savewait.add(this.front.wait_time);
				    	return this.front.wait_time;
		    		
		    		 
		    		 }
		    	 
	    	 
	    	
	    	 }
	    	 }
	     }
	    	
	     
	     
	     
	    // Method to add customers to queue 
	    void enqueue(int idNumber, int arrival_time) 
	    { 
	          
	        // Create a new LL node 
	        QNode temp = new QNode(idNumber, arrival_time); 
	       
	        // If queue is empty, then new node is front and rear both 
	        if (this.rear == null) 
	        { 
	           this.front = this.rear = temp; 
	           size++;
	           maxSize++;
	           return; 
	        } 
	       
	        // Add the new node at the end of queue and change rear 
	        else {
	        	this.rear.next = temp;
	        	this.rear = temp; 
	        	size++;
	        	maxSize++;
	        }
	        
	    } 
	       
	    // Method to remove a customer from queue.   
	    QNode dequeue() 
	    { 
	    	
	        // If queue is empty, return NULL. 
	        if (this.front == null) {
	        	size=0;
	        	this.rear = null;
	           return null; 
	        }
	        
	        // Store previous front and move front one node ahead 
	        else {
		        QNode temp = this.front; 
		        this.front = this.front.next; 
		      
		        if(temp!= null && this.front!= null) {
		        	//every time it dequeues figure out the wait time of each customers
			        this.front.wait_time = getWaitTime(this.front.arrival_time, temp);
			        longestBreaktime(this.front.wait_time, temp);
			        System.out.println(this.front.wait_time);
			        //decrease size by 1 whenever it deques
			        size--;
			        System.out.println("break "+ breaktime);
			        System.out.println("longest " + longest);
			        
			        return temp;
		        }
		        else {
		        	return temp;
		        }
		        
	        }
	        }
	    
	    
}  

