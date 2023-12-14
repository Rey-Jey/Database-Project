public class stats 
{
		protected int totalTrees;
		protected double totalDue;
		protected double totalPaid;
		protected String workDates;
	 	protected String email;

	 
	    //constructors
	    public stats() {}
	    
	    public stats(String email, int totalTrees, double totalDue, double totalPaid, String workDates) {
	    	this.email = email;
	    	this.totalTrees = totalTrees;
	    	this.totalDue = totalDue;
	    	this.totalPaid = totalPaid;
	    	this.workDates = workDates;
	    }

	    
	   //getter and setter methods
	    public String getEmail() {return email;}
	    
	    public void setEmail(String email) {this.email = email;}
	    
	    public int getTotalTrees() {return totalTrees;}
	    
	    public void setTotalTrees(int totalTrees) {this.totalTrees = totalTrees;}

	    public double getTotalDue() {return totalDue;}
	    
	    public void setTotalDue(double totalDue) {this.totalDue= totalDue;}
	    
	    public double getTotalPaid() {return totalPaid;}
	    
	    public void setTotalPaid(double totalPaid) {this.totalPaid = totalPaid;}
	    
	    public String getWorkDates() {return workDates;}
	    
	    public void setWorkDates(String workDates) {this.workDates = workDates;}
	    
	}