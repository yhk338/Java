//Tracy Kim
//yhk338
//OS Lab4

//import java.util.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
public class Lab4 {
	static int X;
	public static int randomOS() throws IOException {
		 java.io.File file1 = new java.io.File("random-numbers.txt");
		 Scanner random = new Scanner(file1);
		 while (random.hasNext()) { 
			 X= Integer.parseInt(random.nextLine());
		 }
		 random.close();
		 return X;
	}
	
	public static void main(String[] args) throws Exception {
		
		if(args.length > 0) {
			int i=0;
		
			int machine_size = Integer.parseInt(args[0]);
			int page_size = Integer.parseInt(args[1]);
			int process_size = Integer.parseInt(args[2]);
			int J = Integer.parseInt(args[3]);
			int N = Integer.parseInt(args[4]);
			String R = args[5];
		
			System.out.println("The machine size is "+ machine_size);
			System.out.println("The page size is "+ page_size);
			System.out.println("The process size is "+ process_size);
			System.out.println("The job mix number is "+ J);
			System.out.println("The number of references per process is "+ N);
			System.out.println("The replacement algorithm is "+ R);
			System.out.println("The level of debugging output is 0");
			
			double A=0;
			double B=0;
			double C=0;
			
			int process;
			int process_check=1;
			
			if (J == 1) {
				process=1;
				A=1;
				B=0;
				C=0;
			}
			
			else if (J==2) {
				process=4;
				A=1;
				B=0;
				C=0;
			}
			
			else if (J==3) {
				process=4;
				A=0;
				B=0;
				C=0;
				//fully random
			}
			
			else if (J==4) {
				process=4;
				if (process_check==1) {
					A=0.75;
					B=0.25;
					C=0;
				}
				
				else if (process_check==2) {
					A=0.75;
					B=0;
					C=0.25;
				}
				
				else if (process_check==3) {
					A=0.75;
					B=0.125;
					C=0.125;
				}
				
				else if (process_check==4) {
					A=0.5;
					B=0.125;
					C=0.125;
					//random
				}
			}
			boolean find = false;
			int[] fault = new int[4];
			for (int l=0; l<4; l++) {
				fault[l]=0;
			}
			int allfault=0;
			double allcomb=0;
			boolean reference_end = true;
			int p=0;
			int wordsize = page_size;
			int pagenum = process_size / page_size;
			int framenum = machine_size/ page_size;
			int q =3;
			int x;
			int time =0;
			//System.out.println(firstw);
			int w=0;
			
			LinkedList<Integer> lru1= new LinkedList<Integer>();
			int[][] page_array = new int[pagenum][page_size];
			for (int g=0;g<pagenum;g++) {
				for (int h=0; h<page_size;h++) {
					page_array[g][h] =w;
					w++;
				}
			}
			
			int[][] frame_array = new int[framenum][page_size];
			for (int g=0;g<framenum;g++) {
				for (int h=0; h<page_size;h++) {
					frame_array[g][h] =0;
				}
			}
			
			int[][] process_time = new int[4][pagenum];
			for (int y=0; y<4;y++) {
				for (int u=0; u<pagenum;u++) {
					process_time[y][u] = 1;
				}
			}
			int[][] frame_track = new int[framenum][4];
			
			int[] process_track = new int[4];
			if (J==1) {
				process_track[0]=N;
				for (int h=1; h<4;h++) {
					process_track[h]=0;
				}
			}
			else {	
				for (int h=0; h<4;h++) {
					process_track[h]= N;
				}
			}
			System.out.println("process num "+N);
			w=0;
			int e=0;
			double n;
			int[] total_ev= new int[4];
			int size=0;
			int num =0;
			int checkk=0;
			int[] evic= new int[4];
			int ppp=0;
			//first reference of each process
			boolean[] first = new boolean[4];
			for (int j=0; j<4;j++) {
				first[j]= true;
			}
			int frame = 0;
			//until number of references are all passed
			while(reference_end) {
				System.out.println(J);
				outer:
				for (int g=0; g<4;g++) {
					inner:
				for (int ref=0; ref<q; ref++) {
					time++;
					find = false;
					if (J==1) {
						process_track[0]--;
					}
					else {
						process_track[g]--;
					}
					if (ref==0) {
						size++;
						if (J==1) {
							
								if (first[0] ==true) {
									randomOS();
									w = (111*(1)+process_size) % process_size;
									frame = framenum-1;
									first[0] = false;
									fault[0]++;	
									for (int l=0; l<pagenum;l++) {
										for (int t=0; t<page_size;t++) {
											if (w==page_array[l][t]) {
												num=l;
											}
										}
									}
									for (int h=0; h<page_size;h++) {
										frame_array[frame][h] =page_array[num][h]; 
									
									}
									
									lru1.add(frame);
								}
								
								//until particular w is still in array
								else {
										
										for (int v=0; v<page_size;v++) {
											if (w==frame_array[0][v]) {
												find= true;
											}
										}
									
									if (w==0) {
										for (int v=0; v<page_size;v++) {
											System.out.print(frame_array[0][v]);
										}
									}
												if (find == false) {
													fault[0]++;
													if (frame>0) {
														frame--;
														num =w;
														for (int h=0; h<page_size;h++) {
															frame_array[frame][h] =num;
															num++;	
														}
														lru1.add(frame);
													}
													else {
														if (R.equals("lifo")) {
															evic[0]++;
															frame=0;
															num=w;
															for (int h=0; h<page_size;h++) {
																frame_array[frame][h] =num;
																num++;	
															}
															//getting page number of w
															for (int f=0;f<pagenum;f++) {
																for (int h=0; h<page_size;h++) {
																	if (w==page_array[f][h]) {
																		ppp=f;
																	}
																}
															}
															total_ev[0]+=time-process_time[0][ppp];
															//saving page number in frame array
															frame_track[frame][0]= ppp;
															//saving time of the page in array
															process_time[0][ppp]= time;
														}
														else if (R.equals("random")) {
															evic[0]++;
															frame = randomOS() % framenum;
															num =w;
															for (int h=0; h<page_size;h++) {
																frame_array[frame][h] =num;
																num++;	
															}
															//getting page number of w
															for (int f=0;f<pagenum;f++) {
																for (int h=0; h<page_size;h++) {
																	if (w==page_array[f][h]) {
																		ppp=f;
																	}
																}
															}
															total_ev[0]+=time-process_time[0][ppp];
															//saving page number in frame array
															frame_track[frame][0]= ppp;
															//saving time of the page in array
															process_time[0][ppp]= time;
														}
														else if (R.equals("lru")) {
															evic[0]++;
															frame =lru1.getFirst();
															num=w;
															for (int h=0; h<page_size;h++) {
																frame_array[frame][h] =num;
																num++;	
															}
															//getting page number of w
															for (int f=0;f<pagenum;f++) {
																for (int h=0; h<page_size;h++) {
																	if (w==page_array[f][h]) {
																		ppp=f;
																	}
																}
															}
															total_ev[0]+=time-process_time[0][ppp];
															//saving page number in frame array
															frame_track[frame][0]= ppp;
															//saving time of the page in array
															process_time[0][ppp]= time;
															lru1.removeFirst();
														}
													}
													
												}
												
											}
						}
						
						else { if (first[g] ==true) {
							randomOS();
							w = (111*(g+1)+process_size) % process_size;
							frame = framenum-1;
							first[g] = false;
							fault[g]++;	
							//num =w;
							for (int l=0; l<pagenum;l++) {
								for (int t=0; t<page_size;t++) {
									if (w==page_array[l][t]) {
										num=l;
									}
								}
							}
							for (int h=0; h<page_size;h++) {
								frame_array[frame][h] =page_array[num][h]; 
							
							}
							
							lru1.add(frame);
							

							
							}
							
							//until particular w is still in array
							else {
								for (int f=0;f<pagenum;f++) {
									for (int h=0; h<page_size;h++) {
										if (w==page_array[f][h]) {
											find = false;
										}
									}
								}
								if (w==0) {
									for (int v=0; v<page_size;v++) {
										System.out.print(frame_array[0][v]);
									
									}
								}
											if (find == false) {
												fault[g]++;
												for (int k=0; k<framenum;k++) {
												
												}
												if (frame>0) {
													frame--;
													num =w;
													for (int h=0; h<page_size;h++) {
														frame_array[frame][h] =num;
														num++;	
													}
													lru1.add(frame);
												}
												else {
													if (R.equals("lifo")) {
														evic[g]++;
														frame=0;
														
														for (int l=0; l<pagenum;l++) {
															for (int t=0; t<page_size;t++) {
																if (w==page_array[l][t]) {
																	num=l;
																}
															}
														}
														for (int h=0; h<page_size;h++) {
															frame_array[frame][h] =page_array[num][h]; 
														
														}
														
														//getting page number of w
														for (int f=0;f<pagenum;f++) {
															for (int h=0; h<page_size;h++) {
																if (w==page_array[f][h]) {
																	ppp=f;
																}
															}
														}
														total_ev[g]+=time-process_time[g][ppp];
														//saving page number in frame array
														frame_track[frame][g]= ppp;
														//saving time of the page in array
														process_time[g][ppp]= time;
													}
													else if (R.equals("random")) {
														evic[g]++;
														frame = randomOS() % framenum;
														for (int l=0; l<pagenum;l++) {
															for (int t=0; t<page_size;t++) {
																if (w==page_array[l][t]) {
																	num=l;
																}
															}
														}
														for (int h=0; h<page_size;h++) {
															frame_array[frame][h] =page_array[num][h]; 
														
														}
														//getting page number of w
														for (int f=0;f<pagenum;f++) {
															for (int h=0; h<page_size;h++) {
																if (w==page_array[f][h]) {
																	ppp=f;
																}
															}
														}
														total_ev[g]+=time-process_time[g][ppp];
														//saving page number in frame array
														frame_track[frame][g]= ppp;
														//saving time of the page in array
														process_time[g][ppp]= time;
													}
													else if (R.equals("lru")) {
														
														evic[g]++;
														frame =lru1.getFirst();
														for (int l=0; l<pagenum;l++) {
															for (int t=0; t<page_size;t++) {
																if (w==page_array[l][t]) {
																	num=l;
																}
															}
														}
														for (int h=0; h<page_size;h++) {
															frame_array[frame][h] =page_array[num][h]; 
														
														}
														//getting page number of w
														for (int f=0;f<pagenum;f++) {
															for (int h=0; h<page_size;h++) {
																if (w==page_array[f][h]) {
																	ppp=f;
																}
															}
														}
														total_ev[g]+=time-process_time[g][ppp];
														//saving page number in frame array
														frame_track[frame][g]= ppp;
														//saving time of the page in array
														process_time[g][ppp]= time;
														//lru1.removeFirst();
													}
												}
												
											}
											
										}
						}
						
					}
					
						
						if (J==1) {
					
							w= (w+1+process_size)%process_size;
							if (w== process_size) {
								w=0;
							}
						}	
						
				
						//else if (ref!=0) {
						else if (J!=1) {
							double y = randomOS() / (Integer.MAX_VALUE + 1);
								//get the next reference
								if (y<A) {
									w=(w+1+process_size)%process_size; //case 1 (it will occur with probability A)
								}
								else if (y<(A+B)) {
									w=(w-5+process_size)%process_size; //case 2, (it will occur with probability B)
								}
								else if (y<(A+B+C)) {
									w=(w+4+process_size)%process_size; //case 3 (it will occur with probability C)
								}
								else /* y>=A+B+C */ {
									w=randomOS()%process_size;//do case 4 (it will occur with probability 1-A-B-C)
								}
								
								/*else {
									if (J==1) {
										fault[0]++;
									}
									else {
										fault[p]++;
									}
									for (int h=0; h<page_size;h++) {
										frame_array[frame][h] =w;
										w++;	
									}
								}*/
							}
						System.out.println("w "+w);
						
						//if J==0
						//if fault happens
					//}
					
					e++;
					if (J==1) {
						if (process_track[0]==0) {
							System.out.println("fault"+"1: " +fault[0]);
							System.out.println(size);
							System.out.println(total_ev);
							System.out.println(evic);
							n = (double)total_ev[0]/evic[0];
							if (evic[0]==0) {
								System.out.println("With no evictions, the average residence is undefined.");
							}
							else {
								System.out.println("average= "+n);
								System.out.println("The total number of faults is"+ (fault[0])+" and the overall average residency is "+ n); 

								}
							reference_end=false;
							System.exit(0);
							break inner;
						}
					}
					//else {
					if (process_track[0]<=0 && process_track[1]<=0 && process_track[2]<=0 && process_track[3]<=0) {
						for (int c=0;c<fault.length;c++) {
						 System.out.print("fault"+(c+1)+": "+fault[c]+"  ");
						
						
						System.out.println(total_ev[c]);
						System.out.println(evic[c]);
						 n = (double)(total_ev[c]+1)/(evic[c]+1);
						allcomb +=n;
						if (evic[c]==0) {
							System.out.println("With no evictions, the average residence is undefined.");
						}
						else {
							System.out.println("average= "+n);
							
						}
					}
						System.out.println("The total number of faults is"+ (fault[0]+fault[1]+fault[2]+fault[3])+" and the overall average residency is "+ allcomb/(evic[0]+evic[1]+evic[2]+evic[3])); 

						reference_end=false;
						System.exit(0);
						break outer;
					}
						if (process_track[g]==0) {
							//evic[g]++;
							break inner;
						}
						
					//}
				}
				
				p++;
				if (p==3) {
					p=0;
				}
				//System. exit(0);
				}
		}
		}
		
		else {
			System.out.println("No input");
		}
		
		//int random = • a random value in 0..S-1 each with probability (1-A-B-C)/S(w-5+S)%S.

		//}
	}
}