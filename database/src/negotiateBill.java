public class negotiateBill 
{
		protected int negotiateBID;
		protected int billID;
	 	protected String email;
	 	protected String msg;
	 	protected String date;
	 	
	    //constructors
	    public negotiateBill() {}
	 
	    public negotiateBill(int negotiateBID) { this.negotiateBID = negotiateBID;}
	    
	    public negotiateBill(int negotiateBID, int billID, String email, String msg, String date) {
	    	this(billID, email, msg);
	    	this.negotiateBID = negotiateBID;
	    	this.date = date;
	    }
	    
	    public negotiateBill(int billID, String email, String msg) {
	    	this.billID = billID;
	    	this.email = email;
	    	this.msg = msg;
    	}
	    
	   //getter and setter methods
	    
	    public int getNegotiateBID() { return negotiateBID;}
	    
	    public void setNegotiateBID(int negotiateBID) { this.negotiateBID = negotiateBID; }
	    
	    public int getBillID() { return billID;}
	    
	    public void setBillID(int billID) { this.billID = billID; }
	    
	    public String getEmail() { return email; }
	    
	    public void setEmail(String email) { this.email = email; }
	    
	    public String getMsg() { return msg; }
	    
	    public void setMsg(String msg) { this.msg = msg; }
	    
	    public String getDate() { return date; }
	    
	    public void setDate(String date) { this.date = date; }
	    
	}