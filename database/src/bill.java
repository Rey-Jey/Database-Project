public class bill 
{
		protected int billID;
		protected int orderID;
		protected int tree_amt;
	 	protected double price;
	 	protected String start_time;
	 	protected String end_time;
	 	protected String status;
	 	protected String email;
	 	protected String contractor;//since david smith is the sole contractor, this data entry will NOT be included in any tables, this is just to keep track who is negotiating with who
	 	protected String date; //date should always be default
	 
	    //constructors
	    public bill() {}
	 
	    public bill(int billID) { this.billID = billID;}
	    
	    public bill(int orderID, double price, String end_time) {
	    	this.orderID = orderID;
	    	this.price = price;
	    	this.end_time = end_time;
	    }
	    
	    public bill(String status, int billID) {
	    	this.status = status;
	    	this.billID = billID;
	    }
	    
	    public bill(Double price, int billID) {
	    	this.price = price;
	    	this.billID = billID;
	    }
		    
	    public bill(int billID, int orderID, int tree_amt, double price, String start_time, String end_time, String status, String email, String date) {
	    	this(orderID, tree_amt, price, start_time, end_time, status, email, date);
	    	this.billID = billID;
	    }
	
	    public bill(int orderID, int tree_amt, double price, String start_time, String end_time, String status, String email, String date) {
	    	this.orderID = orderID;
	    	this.tree_amt = tree_amt;
	    	this.price = price;
	    	this.start_time = start_time;
	    	this.end_time = end_time;
	    	this.status = status;
	    	this.email = email;
	    	this.date = date;
	    }
	    
	   //getter and setter methods
	    public int getBillID() { return billID; }
	    
	    public void setBillID(int billID) { this.billID = billID; }
	    
	    public int getOrderID() { return orderID; }
	    
	    public void setOrderID(int orderID) { this.orderID = orderID; }
	    
	    public int getTree_amt() { return tree_amt; }
	    
	    public void setTree_amt(int tree_amt) { this.tree_amt = tree_amt; }
	    
	    public double getPrice() { return price; }
	    
	    public void setPrice(double price) { this.price = price; }
	    
	    public String getStart_time() { return start_time; }
	    
	    public void setStart_time(String start_time) { this.start_time = start_time; }
	    
	    public String getEnd_time() {return end_time; }
	    
	    public void setEnd_time(String end_time) { this.end_time = end_time;}
	    
	    public String getStatus() { return status; }
	    
	    public void setStatus(String status) { this.status = status; }
	    
	    public String getEmail() { return email; }
	    
	    public void setEmail(String email) { this.email = email; }
	    
	    public String getContractor() { return contractor; }
	    
	    public void setContractor(String contractor) { this.contractor = contractor; }
	    
	    public String getDate() { return date; }
	    
	    public void setDate(String date) { this.date = date; }
	    
	    
	}