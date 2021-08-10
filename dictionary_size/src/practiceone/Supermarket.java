package practiceone;

public class Supermarket {
	public static void main(String[] args) {
		Dictionary<String, Integer> dict = new Dictionary<String, Integer> ();
		dict.add("Fruit", 5);
		dict.add("Vegetables", 9);
		dict.add("Meat", 14);
		dict.add("Dairy", 3);
		dict.add("Fish", 12);
		dict.add("Grains", 4);
		dict.add("Else", -1);
		dict.add("Household goods", 99);
		
		Integer code =(Integer)dict.get("Grains");
		
		System.out.println("dictionary size is " + dict.size());
		System.out.print("grain is " + code);
		
	}
}
