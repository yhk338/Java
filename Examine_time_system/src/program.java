import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//make a queue out of QNode called customers
public class program {
	private static BufferedReader br; 
	//make an arryalist that keeps track of waiting list of people waiting
	static ArrayList<QNode> WaitingList= new ArrayList<QNode>();
	
	//keeps track of maximum waiting list of people
    static int maxList =0;
    
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
    String line = "";
    //start time is 9 am.
    int startTime = 32400;
    //make queue called customerList
    Queue customerList = new Queue();
    //this is one dummy QNode that will be added to customerList to keep track of other QNode's waiting time
    customerList.enqueue(0, 0);
	
	int first_arrival_time =0;
	int id=1;
	//arrayList of questions
	ArrayList<String> questionss = new ArrayList<String> ();
	
	
	try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
		line = br.readLine();
		line = br.readLine();
			//read from customersfile.txt and save idNumbers and arrival time into each QNode and add it to customerList
	        while ((line = br.readLine()) != null) {
	        		
	                // use comma as separator
	                String[] splitby = line.split(" ");
	                String[] colon = line.split(":  ");
	                line = br.readLine();
	                String[] colon2 = line.split(": ");
	                System.out.println(splitby[0]);
	                System.out.println(colon[1]);
	                System.out.println(colon2[1]);
	               
	                	String number = colon[1];
	                	String time = colon2[1];
	                	
	                	String[] times = time.split(":");
	                	System.out.println(times);
	                	//change arrival time into sec
	        	        int hour = Integer.parseInt(times[0].trim());
	        	        int min = Integer.parseInt(times[1].trim())*60;
	        	        int sec = Integer.parseInt(times[2].trim());
	        	        if (hour<8) {
	        	           first_arrival_time = (hour+12)*3600 + min + sec;
	        	        }
	        	        else {
	        	        	 first_arrival_time = hour*3600 + min + sec;
	        	        }
	        	        System.out.println(first_arrival_time);
		                QNode customer = new QNode(Integer.parseInt(number), first_arrival_time);
		                //add each customer into queue
		                customerList.enqueue(Integer.parseInt(number), first_arrival_time);
		                line = br.readLine();
		                
	                
	        }
        
        
        
        System.out.println("total break time"+ customerList.breaktime);
        System.out.println("NUMBER-OF-CUSTOMERS-SERVED "+ (customerList.size-1));
        //dequeue each customers and find out the wait time of each while dequeue each
        while (customerList.size !=0) {
        	customerList.dequeue();
        	
        	getWaitingList(customerList);
        	 
        	
        	}
        System.out.println(maxList);
        System.out.println(customerList.numberCustomers);
        
        }
        
	    
        
        
        
	    //customerList.displayList();
	    
	    
	   
	   
       
    
	
	catch (IOException e) {
        e.printStackTrace();
    }
	
	try (BufferedReader br = new BufferedReader(new FileReader(args[1]))) {
		
		//read the questions and answer according to it
        while ((line = br.readLine()) != null) {
        	questionss.add(line);
        	
        }
        
        System.out.println(Queue.savewait);
        //Integer.toString(customerList.savewait.get(i))
        System.out.println("NUMBER-OF-CUSTOMERS-SERVED: " + customerList.numberCustomers);
        System.out.println("LONGEST-BREAK-LENGTH: " +customerList.longest ); 
        System.out.println("TOTAL-IDLE-TIME: "+ customerList.breaktime);
        System.out.println("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME: "+ customerList.numberCustomers);
        
      //if customerList's info matches with the questions then print out
        for(int i =0; i<customerList.savewait.size();i++) {
        	 if (questionss.get(4).substring(16).equals(Integer.toString(i))) {
             	System.out.println("WAITING-TIME-OF " +  questionss.get(4).substring(17) + ": " + customerList.savewait.get(i-1));
             }
        	 if (questionss.get(5).substring(16).equals(Integer.toString(i))) {
        		 System.out.println("WAITING-TIME-OF " +  questionss.get(5).substring(17) + ": " + customerList.savewait.get(i-1));
             }
        	 if (questionss.get(6).substring(16).equals(Integer.toString(i))) {
        		 System.out.println("WAITING-TIME-OF " +  questionss.get(6).substring(17) + ": " + customerList.savewait.get(i-1));
             }
        	 if (questionss.get(7).substring(16).equals(Integer.toString(i))) {
        		 System.out.println("WAITING-TIME-OF " +  questionss.get(7).substring(17) + ": " + customerList.savewait.get(i-1));
             }
        }
	
      

	}
	
	catch (IOException e) {
        e.printStackTrace();
    }
	
}
	 //get the max Size of wait list and get the waitlist of people
	static void getWaitingList(Queue customerList) {
		if (customerList.front!= null) {
			//get the max
			if (customerList.front.wait_time>0) {
				WaitingList.add(customerList.front);
				if (WaitingList.size()> maxList) {
					maxList= WaitingList.size();
				}
				
			}//figure out the wait list
			for (int i =0; i<WaitingList.size(); i++) {
				if (customerList.front.arrival_time  > WaitingList.get(i).arrival_time+ WaitingList.get(i).wait_time) {
					WaitingList.remove(customerList.front);
					
				}
			}
		}
	}
	public ArrayList<QNode> getWaitingList() {
		return WaitingList;
	}
	public void setWaitingList(ArrayList<QNode> waitingList) {
		WaitingList = waitingList;
	}
}
	
	

