
public class RecursionThree {
	public static void main(String[] args) {
		String a = "abccba";
		
		System.out.print(palindrome(a));
	}
	
	public static boolean palindrome(String a) {
		int n = a.length();
		//if there is nothing to compare, return true
		if (a.length()==0 || a.length()== 1) {
			return true;
		}
		
		//if the first character is same as the last, then move onto the inner parts
		else if (a.charAt(0)== a.charAt(n-1)) {
			//return substring from index 1 to 1 from the last element
			return palindrome(a.substring(1, a.length()-1));
		}
		
		//if the first character doesn't match with last, return false
		else { return false;}
	}
	
}
