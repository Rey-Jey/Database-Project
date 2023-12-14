public class stats 
{
		protected int total_trees;
		protected double total_due;
		protected double total_paid;
		protected String work_dates;
	 	protected String email;

	 
	    //constructors
	    public stats() {}
	    
	    public stats(String email, int total_trees, double total_due, double total_paid, String work_dates) {
	    	this.email = email;
	    	this.total_trees = total_trees;
	    	this.total_due = total_due;
	    	this.total_paid = total_paid;
	    	this.work_dates = work_dates;
	    }

	    
	   //getter and setter methods
	    public String getEmail() {return email;}
	    
	    public void setEmail(String email) {this.email = email;}
	    
	    public int getTotalTrees() {return total_trees;}
	    
	    public void setTotalTrees(int total_trees) {this.total_trees = total_trees;}

	    public double getTotalDue() {return total_due;}
	    
	    public void setTotalDue(double total_due) {this.total_due= total_due;}
	    
	    public double getTotalPaid() {return total_paid;}
	    
	    public void setTotalPaid(double total_paid) {this.total_paid = total_paid;}
	    
	    public String getWorkDates() {return work_dates;}
	    
	    public void setWorkDates(String work_dates) {this.work_dates = work_dates;}
	    
	}