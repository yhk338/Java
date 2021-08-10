//Tracy Kim
//yhk338
//OS Lab1

import java.util.*;
import java.util.Scanner;
public class OSLab1 {

	public static void main(String[] args)  {
		
		Scanner input =new Scanner(System.in);
		int num = Integer.parseInt(input.next());
		System.out.println(num);
		class module {
			String[] def;
			String[] use;
			String[] text;
			String[] defelem;
		}
		module[] array= new module[num];
		
		int i=0;
		while ( (i<num)) {
			module mod = new module();
			array[i]= mod;
			
			int a=0;
			int one = Integer.parseInt(input.next());
			array[i].def= new String[one*2];
			while (a<array[i].def.length) {
				array[i].def[a]=input.next();
				a++;
			}
			
			int b=0;
			int two = Integer.parseInt(input.next());
			array[i].use= new String[two*2];
			while (b<array[i].use.length) {
				array[i].use[b]=input.next();
				b++;
			}
		
			int c=0;
			int three = Integer.parseInt(input.next());
			array[i].text= new String[three];
			while (c<array[i].text.length) {
				array[i].text[c]=input.next();
				c++;
			}
			i++;
		}
		
		for (int v=0; v<num; v++) {
			System.out.println(Arrays.toString(array[v].def));
			System.out.println(Arrays.toString(array[v].use));
			System.out.println(Arrays.toString(array[v].text));
		}
		
		System.out.println("Symbol Table");
		
		
		int n=0;
		int base = 0;
		//to get symbol table, each module in array
		while (n<array.length) {
			
			if (n==0) { 
				if (array[0].def.length!=0) {
					int m =0;
					int p=0;
					array[0].defelem= new String[array[0].def.length];
					
					while(m<array[0].def.length) {
						if (Integer.parseInt(array[0].def[m+1])<array[0].text.length) {
							System.out.println(array[0].def[m]+"="+array[0].def[m+1]);
							array[0].defelem[p]= array[0].def[m];
							array[0].defelem[p+1]= array[0].def[m+1];
							p+=2;
							m+=2;
						}
						
						else {
							
							System.out.println("Error: An address appearing in a definition exceeds the size of the module; address is set as 0");
							System.out.println(array[0].def[m]+"="+"0");
							array[0].defelem[p]= array[0].def[m];
							array[0].defelem[p+1]= "0";
							p+=2;
							m+=2;
						}
					}
					n++;
				}
				
				else {
					n++;
				}
				
			}
			
			
			else {
				base +=array[n-1].text.length;
				array[n].defelem= new String[array[n].def.length];
				
				int l=0;
				int k=0;
				while(l<array[n].def.length) {
					if (array[n].def.length!=0) {
						boolean found = false;
						for (int h=0; h<array.length;h++) {
							if (array[h].defelem!=null) {
								for (int j=0;j<array[h].defelem.length;j++) {
									if (array[h].defelem[j]!=null) {
									int compare = (array[h].defelem[j]).compareTo( array[n].def[l] );
									if (compare==0) {
										System.out.println(array[n].def[l]+" -> Error: This variable is multiply defined; first value used.");
										
										found = true;
										break;
									}
									}
								}
							}
						}
						
						if (found == true) {
							l+=2;
						}
						else {
							if (Integer.parseInt(array[n].def[l+1])<array[n].text.length) {
								System.out.println(array[n].def[l]+"="+(Integer.parseInt(array[n].def[l+1])+base));
								array[n].defelem[k]= array[n].def[l];
								array[n].defelem[k+1]= Integer.toString(Integer.parseInt(array[n].def[l+1])+base);
								k+=2;
								l+=2;
						}
							else {
								
								System.out.println("Error: An address appearing in a definition exceeds the size of the module; address is set as 0");
								System.out.println(array[n].def[l]+"="+"0");
								array[n].defelem[k]= array[n].def[l];
								array[n].defelem[k+1]= "0";
								k+=2;
								l+=2;
							}
						}
					}
					
				}
				n++;
			}
		}
		for (int d =0; d<array.length;d++) {
		System.out.println(Arrays.toString(array[d].defelem));
		}
		System.out.println("");
		System.out.println("Memory Map");
		
		int basee=0;
		for (int h=0; h<array.length;h++) {
			int count=0;
				if (h==0) {
					basee+= 0;
				}
				else {
					basee+= array[h-1].text.length;
				}
				int c=array[h].use.length-1;
				
				for (int v=0; v<array[h].text.length;v++) {
					
					char[] chars= array[h].text[v].toCharArray();
					
					if (chars[chars.length-1]=='1' || chars[chars.length-1]=='2' ) {
						System.out.println("");
						System.out.print(count+": ");
						for (int f=0; f<chars.length-1;f++) { 
							System.out.print(chars[f]);
						}
						count++;
					}
					
					else if (chars[chars.length-1]=='3') {
						String str= "";
						for (int f=0; f<chars.length-1;f++) { 
							str+=chars[f];
						}
						int numb= Integer.parseInt(str)+basee;
						System.out.println("");
						System.out.print(count+": ");
						System.out.print(numb);
						
						count++;
					}
					
					
					else if (chars[chars.length-1]=='4') {
						boolean check = false;
						System.out.println("");
						System.out.print(count+": ");
						String str="";
						int t=0;
						for (int g=1; g<chars.length-1;g++) {
							str+= chars[g];
						}
						
						if (check==false) {
							for (int r=0; r<array.length;r++) {
						
									for (int y=0; y<array[r].defelem.length; y+=2 ) {
											if ((array[r].defelem[y]).compareTo(array[h].use[t])==0) {
												int numb1= Character.getNumericValue(chars[0])*1000+Integer.parseInt(array[r].defelem[y+1]);
												System.out.print(numb1);
												count++;
											}
											else {
												check= true;
											}
									}
							}
						}
						
						
						else if (check ==true){
							while (c>=0) { 
							if (Integer.parseInt(str) == 777) {
								
								
								for (int r=0; r<array.length;r++) {
									for (int y=0; y<array[r].defelem.length; y+=2 ) {
										if ((array[r].defelem[y]).compareTo(array[h].use[c-1])==0) {
											int numb1= Character.getNumericValue(chars[0])*1000+Integer.parseInt(array[h].defelem[y+1]);
											System.out.print(numb1);
											count++;
											c-=2;
											break;
										}
									}
								}
								
								
								
								 
							}}}
						
						else {
							System.out.println("External address is not on the list, so treated as an immediate address");
							System.out.print(count+": ");
							for (int f=0; f<chars.length-1;f++) { 
								System.out.print(chars[f]);
							}
							count++;
						}
							}
						
						
						
							}
						}
								
		input.close();	
						
									
					}
		}
