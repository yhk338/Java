import java.util.*;

public class RecursionTwo {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Put the inter: ");
		int n = input.nextInt();
		System.out.println(numberOfZeros(n));
		input.close();
	}
	
	public static int numberOfZeros(int n) {
		//base case, do it until it reaches the number 1
		if (n==1) {
			return 0;
		}
		
		//if it has zero, then add one and return the remainder to find out if there is more zeros
		else if (n%2 ==0) {
			return 1+ numberOfZeros((n/2));
		}
		
		//if not, just return the remainder 
		else {
			return numberOfZeros((n/2));  
		}
	}
	
}
