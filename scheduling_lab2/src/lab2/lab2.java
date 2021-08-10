package lab2;
import java.util.*;
import java.io.*; 

public class lab2 {
	static int X;
	public static int randomOS(int B) throws IOException {
		 java.io.File file1 = new java.io.File("random-numbers.txt");
		 Scanner random = new Scanner(file1);
		 while (random.hasNext()) {
			 X= Integer.parseInt(random.nextLine());
		 }
		 random.close();
		 return (1 + (X % B));
	}
	
	 static class process {
			int A;
			int B;
			int totalCPUneed;
			int C;
			int M;
			int cpu_b=0;
			int cpu_proc_need=0;
			int io_b=0;
			int totalio=0;
			boolean runningbefore=false;
			int finishtime=0;
			int turnaroundtime=0;
			int waittime=0;
			boolean unstart = true;
	
	 }
	
	 public static void bubbleSort(process[] arrayy) {
			   for (int i = (arrayy.length - 1); i >= 0; i--)
			   {
			      for (int j = 1; j <= i; j++)
			      {
			         if (arrayy[j-1].A > arrayy[j].A)
			         {
			              process temp = arrayy[j-1];
			              arrayy[j-1] = arrayy[j];
			              arrayy[j] = temp;
			         }
			       } 
			    }
	}
	 
	 
	public static void main(String[] args) throws Exception {
		boolean notfinished = true;
		boolean cpu_check= true;
		boolean readyprev = false;
		boolean blockprev = false;
		if(args.length > 0) {
	            File file = new File(args[0]);
	            Scanner input = new Scanner(file);
	           
	            int num = Integer.parseInt(input.next());
	            process[] array= new process[num];
	            System.out.print("The original input was: ");
	            System.out.print(num +" ");
	            while (input.hasNext()) {
			   			int i=0;
			   			while ( (i<num)) {
			   				process proc = new process();
			   				array[i]= proc;
			   				proc.A = Integer.parseInt(input.next());
			   				System.out.print("("+proc.A + " ");
			   				proc.B = Integer.parseInt(input.next());
			   				System.out.print(proc.B+" ");
			   				proc.totalCPUneed = Integer.parseInt(input.next());
			   				proc.C = proc.totalCPUneed;
			   				System.out.print(proc.totalCPUneed+" ");
			   				proc.M = Integer.parseInt(input.next());
			   				System.out.print(proc.M+") ");
			   				//proc.toString();
			   				i++;
			   			}
	            }
	   			
	   			bubbleSort(array);
	   			
	   			System.out.println("");
	   			System.out.print("The sorted input is: ");
	   			System.out.print(num +" ");
	   			for (int i=0;i<array.length;i++) {
	   				System.out.print("("+array[i].A+" ");
	   				System.out.print(array[i].B+" ");
	   				System.out.print(array[i].totalCPUneed+" ");
	   				System.out.print(array[i].M+") ");
	   			}
	   			
	   			System.out.println("");
	   			System.out.println("");
	   			System.out.println("This detailed printout gives the state and remaining burst for each process");
	   			System.out.println("");
	   			
	   			int cpu=0;
	   			//Arrays.sort(array,0,array.length);
	   			//System.out.println(Arrays.toString(array));
	   			int cycle =0;
	   			//while (ru)
	   			//int array_check = 0;
	   			while (notfinished==true)  {
			   				for (int i =0; i<array.length;i++) {
					   				if (array[i].C>0) {
					   					
							   			
					   					//blockprev= true;
					   					//System.out.println("");
					   					System.out.print("before cycle "+cycle+":    ");
					   					if (cpu_check==false) {
					   						cpu-=1;
					   						cpu_check= true;
					   					}
								   				for (int q =0; q<array.length; q++) {
								   			
								   					if (cycle<=array[q].A) {
								   							System.out.print(" unstarted 0");
								   							if (cycle ==array[q].A ) {
								   								array[q].unstart = false;
								   							}
								   					} 
								   				
								   					//System.out.println("cpu "+cpu+"  ");
								   					//System.out.println("array.cpu_b "+array[q].cpu_b+"  ");
								   					//System.out.println("runningbefore "+array[q].runningbefore+"  ");
								   					else {
										   				if ( cpu==0 && array[q].cpu_b==0 && array[q].runningbefore ==false && readyprev == false && array[q].unstart==false ) {//cpu not occupied
										   					if (randomOS(array[q].B)>array[q].C) {
									   								array[q].cpu_b = array[q].C;
									   							}
									   						else {
									   								
									   								array[q].cpu_b = randomOS(array[q].B);
									   							}
									   						array[q].io_b = array[q].cpu_b * array[q].M;
									   						array[q].runningbefore= true;
									   						cpu+=array[q].cpu_b;
									   						array[q].C-=array[q].cpu_b;
									   						System.out.print(" running   "+array[q].cpu_b);
									   						array[q].cpu_b-=1;
									   						cpu_check= false;
									   						//readyprev= true;
								   					}
								   					
								   					//array[q].io_b==0 && array[q].cpu_b==0 && array[q].runningbefore==false && runprev == true){
												   		
								   				
								   					
								   					else if (cpu!=0 && array[q].cpu_b!=0) {
								   						System.out.print(" running   "+array[q].cpu_b);
								   						array[q].cpu_b-=1;
								   						cpu_check= false;
								   				
								   					}
										   				
								   					else if ( cpu==0 && array[q].cpu_b==0 && array[q].runningbefore ==false && readyprev == true && array[q].unstart==false ) {//cpu not occupied
										   					if (randomOS(array[q].B)>array[q].C) {
									   								array[q].cpu_b = array[q].C;
									   							}
									   						else {
									   								
									   								array[q].cpu_b = randomOS(array[q].B);
									   							}
									   						array[q].io_b = array[q].cpu_b * array[q].M;
									   						array[q].runningbefore= true;
									   						cpu+=array[q].cpu_b;
									   						array[q].C-=array[q].cpu_b;
									   						System.out.print(" running   "+array[q].cpu_b);
									   						array[q].cpu_b-=1;
									   						cpu_check= false;
									   						readyprev= false;
								   					}
										   				
								   				
								   					else //cpu occupied by other process
								   					{
								   						
								   						if (array[q].C<=0) {
								   								System.out.print(" terminated 0");
								   								array[q].C-=1;
								   							}
								   						
								   							else if(array[q].cpu_b==0 && array[q].io_b!=0 && array[q].runningbefore==true) {
								   									System.out.print(" blocked   "+array[q].io_b);
								   									array[q].totalio+=1;
								   									array[q].io_b-=1;
								   									blockprev= true;
								   									if (array[q].io_b==0) {
								   										array[q].runningbefore=false;
								   										blockprev = false;
								   									}
								   									
								   							}
								   			
								   							else if (cpu!=0 && array[q].io_b==0 && array[q].cpu_b==0 && array[q].runningbefore==false &&array[q].unstart==false){
								   		
								   								System.out.print(" ready     0");
								   								array[q].waittime +=1;
								   								readyprev=true;
								   								//blockprev = true;
								   							}
								   							
								   							else if (cpu==0 && array[q].cpu_b==0 && array[q].io_b==0 && array[q].runningbefore ==false && readyprev == true ) {
								   								System.out.print(" ready     0");
								   								array[q].waittime +=1;
								   								//blockprev = true;
								   								//readyprev = false;
								   							}
								   							
								  
								   						}
								   					}
								   					
								   					if (array[i].C==0) {
								   						array[i].finishtime= cycle;
														array[i].turnaroundtime = array[i].finishtime-array[i].A;
								   					}
								   					
								   					
								   					
								   				}
								   				System.out.println("");
								   				cycle+=1;
							   					/*if (cycle==20) {
							   						System.exit(0);
							   					}*/
							   					
								   			}}
					   			
	   			loop:
		   			for (int h=0; h<array.length;h++) {
		   				if (array[h].C>0) {
		   					break loop;
		   				}
		   				if (h==array.length-1 && array[h].C<=0) {
		   					notfinished = false;
		   				}
		   			}
								   		
								   						   			
		}
	   			System.out.println("The scheduling algorithm used was First Come First Served");
	   			for (int t=0; t<array.length; t++) {
	   				System.out.println("Process "+t+": ");
	   				System.out.println("\t(A,B,C,M) = ("+array[t].A+","+array[t].B+","+array[t].totalCPUneed+","+array[t].M+")");
	   				System.out.println("Finishing time: "+array[t].finishtime);
	   				System.out.println("Turnaround time: "+array[t].turnaroundtime);
	   				System.out.println("I/O time: "+array[t].totalio);
	   				System.out.println("Waiting time: "+array[t].waittime);
	   				System.out.println("");

	   			}
	   			
	   		
	   			System.out.println("Summary Data: ");
   				System.out.println("\tFinishing time: "+array[array.length-1].finishtime);
   				System.out.println("\tCPU Utilization: ");
   				System.out.println("\tI/O Utilization: ");
   				System.out.println("\tThroughput: "+ 100*array.length/(array[array.length-1].finishtime));
   				int totalturn=0;
   				for (int i =0; i<array.length; i++) {
   					totalturn+= array[i].turnaroundtime;
   				}
   				System.out.println("\tAverage turnaround time: "+ totalturn/array.length+" processes per hundred cycles");
   				int totalwait =0;
   				for (int i =0; i<array.length; i++) {
   					totalwait+=array[i].waittime;
   				}
   				System.out.println("\tAverage waiting time: "+ totalwait/array.length);
	   			input.close();
		
		
			   						} 
	   				
	   			
					   			}
	   			
		
	}
		
  

