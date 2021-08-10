import java.util.Scanner;

public class ArraysOne {
	public static int reverse(String haha, String hahaha) {
		//ignore space and case problems
		String haha1 = haha.toUpperCase();
		String hahaha1 = hahaha.toUpperCase();
		
		String temp = haha1.replaceAll(" ", "");
		String tempo = hahaha1.replaceAll(" ", "");
		
		//make reversed string out of the original and compare it with the actual reversed
		String turn = ""; 
		for(int i = temp.length() - 1; i >= 0; i--)
	        {
	            turn = turn + temp.charAt(i);
	        }
		// if the made one is equal to the actual reverse, return 1
		if (turn.equals(tempo)) {
			return 1;
		}
		//if not return 0
		else {return 0;}
	}
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("Put the phrase you want: ");
		String haha = input.nextLine();
		
		System.out.print("Put another phrase you want: ");
		String hahaha = input.next();
		
		System.out.print(reverse(haha, hahaha));
		input.close();
		
	}
	
	
		
	
	
}
