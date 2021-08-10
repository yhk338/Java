import java.util.Scanner;

public class ArraysTwo {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.print("Insert more than three words; the number of strings in your sentence has to be a multiple of 3: ");
		String haha = input.nextLine();
		System.out.print(Consecutive(haha));
		input.close();
	}
	
	public static String Consecutive(String haha) {
		//make an array out of the original string
		String yes = "";
		String[] words = haha.split(" ");
		
		//for every three elements in the array
		for (int i = 0; i< words.length; i+=3) {
			//if second element is bigger than first,
			if (words[i].length() <= words[i+1].length()) {
				//then check if third one is bigger than first and if yes, store the first one which is the smallest
				if (words[i].length()<= words[i+2].length()) {
					yes = yes+ words[i];
				}
				//if not store the third one which is the smallest
				else {yes = yes+ words[i+2];}
			}	
			
			else {
				//if first one is bigger than second
				if (words[i+1].length()<= words[i+2].length()) {
					//store second one which is the smallest
					yes = yes+ words[i+1];
				}
				else { yes = yes+ words[i+2];}
			}
		}
		//do this for every three elements until the end of array and return the stored values
		return yes;
	}
	}

