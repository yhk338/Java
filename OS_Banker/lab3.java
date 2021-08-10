import java.util.*;
import java.io.*; 

public class lab3 {
	//task class that represents each task
	static class task {
		//activities contain each tasks' activities such as initiate release or request statements etc
		LinkedList<String[]> activities = new LinkedList<String[]>();
		int finish_time;
		int wait_t=0;
		boolean term = false;
		boolean ok = true;
		//order keeps track of the index of the activities
		int order= 0;
		boolean deadlock_check =false;
		boolean abortt= false;
		int computenum = 0;
		int count=0;
		int[] resource_claim;
	 }
	
	//resource class that represents each resource
	static class resource {
		int unitnum;
		int requested;
		int remaining;
		int avail_cycle;
		
		
	}
	
	public static void main(String[] args)  throws Exception
	{
		
		int count =0;
		int cycle =0;
		int released_resource = 0;
		int released_task = 0;
		int released_order = 0;
		boolean released_status = false;
		boolean priority = false;
		boolean allterm = false;
			//getting all the input data first
			if(args.length > 0) {
		            File file = new File(args[0]);
		            Scanner input = new Scanner(file);
					int tasknum = Integer.parseInt(input.next());
					task[] tasks = new task[tasknum];
					for (int w=0; w<tasknum;w++) {
						tasks[w] = new task();
					}
					int resourcenum = Integer.parseInt(input.next());
					resource[] existing= new resource[resourcenum];
		
					int i=0;
					//storing resource info in 'existing' array
					while ( (i<resourcenum)) {
						resource res = new resource();
						existing[i]= res;
						existing[i].unitnum = Integer.parseInt(input.next());
						i++;
					}
					
					//storing task info in 'tasks' array
					while (input.hasNext()) {
						int a=0;
						String[] act= new String[4];
						while (a<4) {
							act[a] = input.next();
							a++;
						}
						for (int q=0; q<tasknum; q++) {
							if (Integer.parseInt(act[1])==(q+1)) {
								tasks[q].activities.add(act);
								
							}
						}
					}	
				
			 input.close();
			 
			 
			
		/////////////////////////////////////////////////actual resource allocation happening for FIFO///////////////////////	 
			//create waiting_tasks list to save the waiting tasks and change the order according to the waiting tasks
			 LinkedList<Integer> waiting_tasks= new LinkedList<Integer>();
			 for (int a = 0; a<tasks.length; a++) {
				 waiting_tasks.add(a);
			 }
			 
			 //while all tasks did not terminate
			 while (allterm ==false) {
				 int e =0;
				
				


				 int x=0;
				 	//iterating through the tasks in tasks array in order 
				 	innerloop:
				 		 
					 while( x<waiting_tasks.size()) {
						 
						 e =waiting_tasks.get(x);
						 
							if (tasks[e].term==false) {
								//if the activity of the task is to "initiate"
								if ("initiate".equals((tasks[e].activities.get(tasks[e].order)[0]))) {
									tasks[e].order++;
								}
								
								//if the activity of the task is to "request"
								else if ("request".equals((tasks[e].activities.get(tasks[e].order)[0]))){	
									
									for (int o=0; o<existing.length;o++) {
										//get the resource from the existing array matching with the task's resource
										if ((tasks[e].activities.get(tasks[e].order)[2]).equals(Integer.toString(o+1))) {

											//if the resource's unit is bigger than task's requested resource
											if (existing[o].unitnum>=Integer.parseInt(tasks[e].activities.get(tasks[e].order)[3])) {
												existing[o].unitnum-= Integer.parseInt(tasks[e].activities.get(tasks[e].order)[3]);
												//priority = true;
												tasks[e].order++;
												//e++; 
												break;
											}
											//if not
											else {
												tasks[e].deadlock_check = true;
												tasks[e].wait_t ++;
												for (int c=0;c<tasks.length;c++) {
													if (tasks[c].deadlock_check== false ) {
														
													}
													else {
														if (c== tasks.length-1 && tasks[c].deadlock_check==true) {
															for (int m=0; m<existing.length;m++) {
																existing[m].unitnum +=20;
															}
														}
													}
												}
												}
										}
									}
								}
								
								//if task's activity is to compute
								else if("compute".equals((tasks[e].activities.get(tasks[e].order)[0]))){
									//set compute number to calculate the cycle and fill up the cycle
									tasks[e].computenum = Integer.parseInt(tasks[e].activities.get(tasks[e].order)[2]);
									if (tasks[e].computenum>tasks[e].count) {
											
											tasks[e].count++;
											if (tasks[e].count == tasks[e].computenum) {
												tasks[e].count=0;
												tasks[e].computenum=0;
												tasks[e].order++;
												
											}
											
											//if the next task activity is to terminate, to do so in the same cycle.
											if ("terminate".equals((tasks[e].activities.get(tasks[e].order)[0]))) {
												
												tasks[e].order++;
												tasks[e].finish_time = cycle;
												

												tasks[e].term = true;
												 for (int m = 0; m<tasks.length; m++) {
														 if (tasks[m].term == false) {
															 break;
														 }
														 else if (m==tasks.length-1 && tasks[m].term== true) {
															 allterm = true;
															 break innerloop;
													     }
												 }
											}
									}
									
									
									
								}
								
								//if the activity of the task is to "release"
								else if ("release".equals((tasks[e].activities.get(tasks[e].order)[0]))){
									for (int p=0; p<existing.length;p++) {
										if ((tasks[e].activities.get(tasks[e].order)[2]).equals(Integer.toString(p+1))) {
											
											if ((tasks[e].activities.get(tasks[e].order+1)[0]).equals("release")) {
												
											}
											//if the task's next activity is not release then the priority goes to the next task
											else {
													priority = true;
													
											}
											
											//prepare to release the resource at the end of the cycle
											released_resource = p;
											released_task = e;
											released_order = tasks[e].order;
											released_status = true;
											
											//if the task's next activity is to terminate, then do that in the same cycle 
											if ("terminate".equals((tasks[e].activities.get(tasks[e].order+1)[0]))) {
												tasks[e].order++;
												tasks[e].finish_time = cycle;
												
												tasks[e].term = true;
												 for (int m = 0; m<tasks.length; m++) {
														 if (tasks[m].term == false) {
															 break;
														 }
														 else if (m==tasks.length-1 && tasks[m].term== true) {
															 allterm = true;
															 break innerloop;
													     }
												 }
											}
												
											}
										}
									tasks[e].order++;
									}
								
								}
						
						x++;
						
					}
				 for (int b =0; b<tasks.length;b++) {
					 if (tasks[b].deadlock_check==false) {
						 break;
					 }
					 else if (b==tasks.length-1 && tasks[b].deadlock_check== true) {
						 
					 }
				 }
				 if (priority == true) {
						
						waiting_tasks.addLast(waiting_tasks.get(0));
						waiting_tasks.remove(0);
						priority = false;
					}	
				 if (released_status==true) {
					 existing[released_resource].unitnum+= Integer.parseInt(tasks[released_task].activities.get(released_order)[3]);
					 released_status = false;
				 }
				 
				 cycle++;
				// System.out.println(cycle)
					 }
			 int totf =0;
			 int totw =0;
			 double totp =0;
			 System.out.println("Result");
			 System.out.println("FIFO");
			 double doublew =0;
			 double doublef = 0;
			 double totper =0;
			 for (int a=0; a<tasks.length; a++) {
				 System.out.print("task"+(a+1));
				 System.out.print(" finish "+ (tasks[a].finish_time+1));
				 totf+=(tasks[a].finish_time+1);
				 
				 System.out.print(" wait "+tasks[a].wait_t+"    "); 
				 totw+=tasks[a].wait_t;
				 doublew = (double)(tasks[a].wait_t);
				 doublef = (double)(tasks[a].finish_time+1);
				 totper = doublew/doublef;
				 System.out.println((int)(100*totper)+"%");
				 }
			
			totp = (100*totw/totf);
			 System.out.print( "	"+totf+"	");
			 System.out.print( totw+"	"); 
			 System.out.println((int)totp+"%");
			 
			 
			} 
			
			////////////////////////////////////////////////////////Banker's Algorithm Part////////////////////////////////
			
			cycle =0;
			released_resource = 0;
			released_task = 0;
			released_order = 0;
			released_status = false;
			priority = false;
			allterm = false;
			boolean once_released = false;
			if(args.length > 0) {
	            File file = new File(args[0]);
	            Scanner input = new Scanner(file);
				int tasknum = Integer.parseInt(input.next());
				task[] tasks = new task[tasknum];
				for (int w=0; w<tasknum;w++) {
					tasks[w] = new task();
				}
				int resourcenum = Integer.parseInt(input.next());
				resource[] existing= new resource[resourcenum];
	
				int i=0;
				//storing resource info in 'array' array
				while ( (i<resourcenum)) {
					resource res = new resource();
					existing[i]= res;
					existing[i].unitnum = Integer.parseInt(input.next());
					i++;
				}
				int number =0;
				int gett=0;
				//storing task info in 'tasks' array
				while (input.hasNext()) {
					int a=0;
					String[] act= new String[4];
					while (a<4) {
						act[a] = input.next();
						a++;
					}
					for (int q=0; q<tasknum; q++) {
						tasks[q].resource_claim = new int[resourcenum];
						if (Integer.parseInt(act[1])==(q+1)) {
							tasks[q].activities.add(act);
						}
					}
				
				}	
			 input.close();
			 for (int a=0;a<tasks.length;a++) {
				 	for (int e=0;e<tasks[a].activities.size();e++) {
						if (tasks[a].activities.get(e)[0].equals("initiate")) {
							tasks[a].resource_claim[Integer.parseInt(tasks[a].activities.get(e)[2])-1] = Integer.parseInt(tasks[a].activities.get(e)[3]);
							//if (Integer.parseInt(tasks[a].activities.get(gett)[a])==resourcenum) {
								//break haloop;
							//}
					}
				 	}
			 }
			
		
		 
	/////////////////////////////////////////////////Banker's Algorithm///////////////////////////////////////
		LinkedList<Integer> waiting_tasks= new LinkedList<Integer>();
		 LinkedList<Integer> aborted= new LinkedList<Integer>();
		 for (int a = 0; a<tasks.length; a++) {
			 waiting_tasks.add(a);
		 }
		
		 //while all tasks did not terminate
		 while (allterm ==false) {
			 int e =0;
		
			 int x=0;
			 	//iterating through the tasks in tasks array in order 
			 	innerloop:
			 		
				 while( x<waiting_tasks.size()) {
					 
					 e =waiting_tasks.get(x);
					 	if (tasks[e].abortt==true) {
					 		x++;
					 	}
					 	else if (tasks[e].term==false ) {
					 		//if the activity of the task is to "initiate"
							if ("initiate".equals((tasks[e].activities.get(tasks[e].order)[0]))) {
									
									if (existing[(Integer.parseInt(tasks[e].activities.get(tasks[e].order)[2]))-1].unitnum<Integer.parseInt(tasks[e].activities.get(tasks[e].order)[3])) {
										System.out.println("Aborted");
										aborted.add(e);
										tasks[e].abortt = true;
										System.exit(6);
										tasks[e].order++;
									}
									else {
										tasks[e].order++;
									}
								
								
								//e++;
							}
							
							//if the activity of the task is to "request"
							else if ("request".equals((tasks[e].activities.get(tasks[e].order)[0]))){									
								for (int o=0; o<existing.length;o++) {
									//check for corresponding resource
									if ((tasks[e].activities.get(tasks[e].order)[2]).equals(Integer.toString(o+1))) {
										if (existing[o].unitnum>=tasks[e].resource_claim[o]) {
											existing[o].unitnum-= Integer.parseInt(tasks[e].activities.get(tasks[e].order)[3]);
											
											tasks[e].order++;
											//e++; 
											break;
										}
										else {
											
											tasks[e].deadlock_check = true;
											tasks[e].wait_t ++;
											}
									}
								}
							}
							
							//if task's activity is to compute
							else if("compute".equals((tasks[e].activities.get(tasks[e].order)[0]))){
								//set compute number to calculate the cycle and fill up the cycle
								tasks[e].computenum = Integer.parseInt(tasks[e].activities.get(tasks[e].order)[2]);
								if (tasks[e].computenum>tasks[e].count) {
										tasks[e].count++;
										if (tasks[e].count == tasks[e].computenum) {
											tasks[e].count=0;
											tasks[e].computenum=0;
											tasks[e].order++;
											
										}
										
										//if the next task activity is to terminate, to do so in the same cycle.
										if ("terminate".equals((tasks[e].activities.get(tasks[e].order)[0]))) {
											
											tasks[e].order++;
											tasks[e].finish_time = cycle;
											
											tasks[e].term = true;
											 for (int m = 0; m<tasks.length; m++) {
													 if (tasks[m].term == false) {
														 break;
													 }
													 else if (m==tasks.length-1 && tasks[m].term== true) {
														 allterm = true;
														 break innerloop;
												     }
											 }
										}
								}
								
								
								
							}
							
							//if the activity of the task is to "release"
							else if ("release".equals((tasks[e].activities.get(tasks[e].order)[0]))){
								for (int p=0; p<existing.length;p++) {
									if ((tasks[e].activities.get(tasks[e].order)[2]).equals(Integer.toString(p+1))) {
										for (int v=0; v<tasks.length;v++) {
											if (v!=e) {
												//tasks[v].wait_t ++;
											}
										}
										if ((tasks[e].activities.get(tasks[e].order+1)[0]).equals("release")) {
											
										}
										else {
												priority = true;
												
										}
										released_resource = p;
										released_task = e;
										released_order = tasks[e].order;
										released_status = true;
										once_released = true;
									
										if ("terminate".equals((tasks[e].activities.get(tasks[e].order+1)[0]))) {
											tasks[e].order++;
											tasks[e].finish_time = cycle;
											
											tasks[e].term = true;
											 for (int m = 0; m<tasks.length; m++) {
													 if (tasks[m].term == false) {
														 break;
													 }
													 else if (m==tasks.length-1 && tasks[m].term== true) {
														 allterm = true;
														 break innerloop;
												     }
											   }
										}
											
										}
									}
								tasks[e].order++;
								}
							
							}
					
					x++;
				}
			 once_released = false;
			 for (int b =0; b<tasks.length;b++) {
				 if (tasks[b].deadlock_check==false) {
					 break;
				 }
				 else if (b==tasks.length-1 && tasks[b].deadlock_check== true) {
					 
				 }
			 }
			 if (priority == true) {
					
					waiting_tasks.addLast(waiting_tasks.get(0));
					waiting_tasks.remove(0);
					priority = false;
				}	
			 if (released_status==true) {
				 existing[released_resource].unitnum+= Integer.parseInt(tasks[released_task].activities.get(released_order)[3]);
				 released_status = false;
			 }
			 cycle++;
			 
				 }
		 
		 int totf =0;
		 int totw =0;
		 double totp =0;
		 System.out.println("");
		 System.out.println("Banker's Algorighm Result");
		 System.out.println("Banker's Algorithm");
		 double doublew =0;
		 double doublef = 0;
		 double totper =0;
		 for (int c=0; c<tasks.length; c++) {
			 if (tasks[c].abortt== true) {
				 System.out.print("Abort");
			 }
			 else {
					 System.out.print("task"+(c+1));
					 System.out.print(" finish "+ (tasks[c].finish_time+1));
					 totf+=(tasks[c].finish_time+1);
					 
					 System.out.print(" wait "+tasks[c].wait_t+"    "); 
					 totw+=tasks[c].wait_t;
					 doublew = (double)(tasks[c].wait_t);
					 doublef = (double)(tasks[c].finish_time+1);
					 totper = doublew/doublef;
					 System.out.println((int)(100*totper)+"%");
				 }
			 
			 }

			totp = (100*totw/totf);
			 System.out.print( "	"+totf+"	");
			 System.out.print( totw+"	"); 
			 System.out.println((int)totp+"%");
		 
		  
			
			}
			System.out.println("end");
	
	}		
	
	

	
	private static int stringCompare(String[] strings, String string) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

	