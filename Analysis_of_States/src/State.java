
public class State {
	//make a state class to store all the years and name and percentage change
	//state class can be region or state
	private String name;
	private String yr2010;
	private String yr2011;
	private String yr2012;
	private String yr2013;
	private String yr2014;
	private String yr2015;
	private String yr2016;
	private double percent;
	
	public State() {
		
	}
	//constructor to pass all the variables and percentage change
	public State(String name, String yr2010, String yr2011, String yr2012, String yr2013, String yr2014,
			String yr2015, String yr2016, double percent) {
		this.name = name;
		this.yr2010 = yr2010;
		this.yr2011 = yr2011;
		this.yr2012 = yr2012; 
		this.yr2013 = yr2013;
		this.yr2014 = yr2014;
		this.yr2015 = yr2015;
		this.yr2016 = yr2016;
		this.percent = percent;
	}
	
	//getters and setters to access the private variables
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYr2010() {
		return yr2010;
	}

	public void setYr2010(String yr2010) {
		this.yr2010 = yr2010;
	}

	public String getYr2011() {
		return yr2011;
	}

	public void setYr2011(String yr2011) {
		this.yr2011 = yr2011;
	}

	public String getYr2012() {
		return yr2012;
	}

	public void setYr2012(String yr2012) {
		this.yr2012 = yr2012;
	}

	public String getYr2013() {
		return yr2013;
	}

	public void setYr2013(String yr2013) {
		this.yr2013 = yr2013;
	}

	public String getYr2014() {
		return yr2014;
	}

	public void setYr2014(String yr2014) {
		this.yr2014 = yr2014;
	}

	public String getYr2015() {
		return yr2015;
	}

	public void setYr2015(String yr2015) {
		this.yr2015 = yr2015;
	}

	public String getYr2016() {
		return yr2016;
	}

	public void setYr2016(String yr2016) {
		this.yr2016 = yr2016;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
	public double calculate(int year1, int year2) {
	
		return (double)(year2-year1)/year1*100;
		
	}
	
	
	//this method is to take user inputs and compare with the each state object
	public String takeInput(String firstyear) {
		 if (firstyear.equals("2010")) {
			 return yr2010;
		 }
		 else if (firstyear.equals("2011")) {
			 return yr2011;
		 }
		 else if (firstyear.equals("2012")) {
			 return yr2012;
		 }
		 else if (firstyear.equals("2013")) {
			 return yr2013;
		 }
		 else if (firstyear.equals("2014")) {
			 return yr2014;
		 }
		 else if (firstyear.equals("2015")) {
			 return yr2015;
		 }
		 else if (firstyear.equals("2016")) {
			 return yr2016;
		 }
		 else {
			 return "Error occured";
		 }
	}
	 
}
