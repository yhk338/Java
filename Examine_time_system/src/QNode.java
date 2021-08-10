public class QNode {
	//QNode is each customer
	 	final int service_time = 300;
		int idNumber;
	    int arrival_time;
	    QNode next;
	    int yes =0;
	    int wait_time;
	    int service_timeEnding;
	    
	      
	    // constructor 
	    public QNode(int idNumber, int arrival_time) { 
	        this.idNumber = idNumber;
	        wait_time=0;
	        service_timeEnding=0;
	        
	        this.next = null; 
	 
	        this.arrival_time = arrival_time;
	        
	    }
}
	

