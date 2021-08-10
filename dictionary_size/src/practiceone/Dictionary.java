package practiceone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Dictionary <A  , B extends Number> {
	private ArrayList<A> keys;
	private ArrayList<B> values;
	public Dictionary() {
		this.keys = new ArrayList<A> ();
		this.values = new ArrayList<B> ();
	}
	
	/*Class X {
		Class X extends Y;
		class Y extends Z;
	}*/
	
	
	public Dictionary(ArrayList<A> keys, ArrayList<B> values) {
		if (keys.size()!= values.size()) System.out.println("error");
		if (!isUnique(keys)) System.out.print("error");
		this.keys = keys;
		this.values = values;
	}
	
	public ArrayList<A> getKeys() {
		return this.keys;
	}
	
	public ArrayList<B> getValues() {
		return this.values;
	}
	
	public void setKeys(ArrayList<A> keys) {
		this.keys = keys;
	}
	
	public void setValues(ArrayList<B> vlaues) {
		this.values = values;
	}
	
	public boolean add(A key, B value) {
		if (hasKey(key)) return false;
		this.keys.add(key);
		this.values.add(value);
		return true;
	}
	
	public boolean update(A key, B value) {
		int idx = this.keys.indexOf(key);
		if (idx == -1) return false;
		else {
			this.values.set(idx, value);
			return true;
		}
	}
	public boolean hasKey(A key) {
		for (int i = 0; i<this.keys.size()-1; i++) {
			if (keys.get(i).equals(key)) return true;
		}
		return false;
	}
	
	public boolean remove(A key) {
		int idx = this.keys.indexOf(key);
		if (idx == -1) return false;
		else {
			this.keys.remove(idx);
			this.values.remove(idx);
			return true;
		}
	}
	
	public int size() {
		return this.keys.size();
	}
	
	public boolean isUnique(ArrayList<A> arr) {
		Set<A> set = new HashSet<A>(arr);
		if (set.size() != arr.size()) return true;
		return false;
	}

	public B get(A keys) {
		// TODO Auto-generated method stub
		for (int i= 0; i<this.size(); i++) {
			if (this.keys.get(i).equals(keys)) {
				return this.values.get(i);
			}
		}
		return null;
		
	}
}

