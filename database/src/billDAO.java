import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/billDAO")
public class billDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public billDAO(Connection dbInstance) { connect = dbInstance; }
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    
    public List<bill> listAllBills() throws SQLException {
        List<bill> listBill = new ArrayList<bill>();        
        String sql = "SELECT * FROM Bill";      
              
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int billID = resultSet.getInt("billID");
            int orderID = resultSet.getInt("orderID");
            int tree_amt = resultSet.getInt("tree_amt");
            double price = resultSet.getDouble("price");
            String email = resultSet.getString("email");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            String date = resultSet.getString("date");
             
            bill bills = new bill(billID, orderID, tree_amt, price, start_time, end_time, status, email, date);
            listBill.add(bills);
            
        }        
        resultSet.close();
        return listBill;
    }
    
    public List<bill> listUserBills(String email) throws SQLException {
        List<bill> listUserBill = new ArrayList<bill>();        
        String sql = "SELECT * FROM Bill WHERE email=?";      
              
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int billID = resultSet.getInt("billID");
            int orderID = resultSet.getInt("orderID");
            int tree_amt = resultSet.getInt("tree_amt");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            String date = resultSet.getString("date");
            
            bill bills = new bill(billID, orderID, tree_amt, price, start_time, end_time, status, email, date);
            listUserBill.add(bills);
            
        }        
        resultSet.close();
        return listUserBill;
    }
    
    public void insertFromOrder(bill bills) throws SQLException {
        
 		String sql = "insert into Bill (orderID, tree_amt, email, contractor, start_time) select orderID, tree_amt, email, contractor, start_time from qOrder where orderID=?";
 		
 		
 		try(PreparedStatement preparedStatement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
 			preparedStatement.setInt(1, bills.getOrderID());
 			
 			int affectedRows = preparedStatement.executeUpdate();
 			
 			if(affectedRows > 0) {
 				try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	 					if(generatedKeys.next()) {
	 					int billID = generatedKeys.getInt(1);
	 					bills.setBillID(billID);
	 					
	 				}
 				}
 			}
 		}
     }
    
    
    
    public boolean delete(int quoteID) throws SQLException {
        String sql = "DELETE FROM Quote WHERE quoteID = ?";        
        
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean insertIntoBill(bill bills) throws SQLException {
        String sql = "update Bill set price=?, end_time=? where orderID=?";
        
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        //preparedStatement.setInt(1, bills.getTree_amt());
		preparedStatement.setDouble(1, bills.getPrice());
		//preparedStatement.setString(3, bills.getStart_time());
		preparedStatement.setString(2, bills.getEnd_time());
		//preparedStatement.setString(5, bills.getStatus());
        preparedStatement.setInt(3, bills.getOrderID());

		//preparedStatement.setString(7, quotes.getEmail());		
	
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public boolean updateBill(bill bills) throws SQLException {
    	String sql = "update Bill set price=? where billID = ?";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	
    	preparedStatement.setDouble(1, bills.getPrice());
    	preparedStatement.setInt(2, bills.getBillID());
    	
    	boolean rowUpdated = preparedStatement.executeUpdate() > 0;
    	preparedStatement.close();
    	return rowUpdated;
    }
    
    
    
    public boolean payBill(int billID, String status) throws SQLException {
        String sql = "update Bill set status=?, paydate = CURRENT_TIMESTAMP where billID=?";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, status);
		preparedStatement.setInt(2, billID);
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public quote getQuote(int quoteID) throws SQLException {
    	quote currentQuote = null;
        String sql = "SELECT * FROM Quote WHERE quoteID = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	int tree_amt = resultSet.getInt("tree_amt");
        	double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            String email = resultSet.getString("email");

            currentQuote = new quote(quoteID, tree_amt, price, start_time, end_time, status, email);
        }
         
        resultSet.close();
        statement.close();
         
        return currentQuote;
    }
    
    public List<bill> OverdueBills() throws SQLException {
        List<bill> listBill = new ArrayList<bill>();        
        String sql = "Select * from Bill where date <= NOW() - INTERVAL 1 WEEK;\r\n"
        		+ "";      
              
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int billID = resultSet.getInt("billID");
            int orderID = resultSet.getInt("orderID");
            int tree_amt = resultSet.getInt("tree_amt");
            double price = resultSet.getDouble("price");
            String email = resultSet.getString("email");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            String date = resultSet.getString("date");
             
            bill bills = new bill(billID, orderID, tree_amt, price, start_time, end_time, status, email, date);
            listBill.add(bills);
            
        }        
        resultSet.close();
        return listBill;
    }
    
    public void init() throws SQLException, FileNotFoundException, IOException {
    	
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = { "drop table if exists Bill; ",
					        ("CREATE TABLE if not exists Bill( " +
					        	"billID INTEGER NOT NULL AUTO_INCREMENT, " + 
					            "orderID INTEGER NOT NULL, " +
					        	"tree_amt INTEGER DEFAULT 0, " +
					        	"price DECIMAL(7,2) DEFAULT 0, " +
					        	"email VARCHAR(50) NOT NULL, " +
					        	"contractor VARCHAR(50) DEFAULT 'davidsmith@gmail.com', " +
					            "start_time DATE DEFAULT '0001-01-01', " +
					            "end_time DATE DEFAULT '0001-01-01', " +
					            "status VARCHAR(10) DEFAULT 'Pending', " +
					            "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
					            "paydate TIMESTAMP, " +
					            "PRIMARY KEY (billID), " +
					            "FOREIGN KEY (orderID) REFERENCES qOrder(orderID), " +
					            "FOREIGN KEY (email) REFERENCES User(email)," +
					            "FOREIGN KEY (contractor) REFERENCES User(email));" )
					        
        					};
        String[] TUPLES = {("insert into Bill(orderID, tree_amt, price, start_time, end_time, status, email)" +
        					"values (1, default, default, '2023-10-10', '2023-10-11', default, 'rey@gmail.com'), " +
        					"(2, default, default, '2023-11-15', '2023-12-01', default, 'j@gmail.com'), " +
        					"(3, default, default, '2023-12-17', '2024-01-01', default, 'wallace@gmail.com'), " +
        					"(4, default, default, default, default, default, 'amelia@gmail.com'), " +
        					"(5, default, default, '2023-12-10', '2023-12-11', default, 'rey@gmail.com'), " +
        					"(6, default, default, '2023-12-10', '2023-12-11', default, 'j@gmail.com'), " +
        					"(7, default, default, '2023-12-10', '2023-12-11', default, 'wallace@gmail.com'), " +
        					"(8,default, default, '2023-12-10', '2023-12-11', default, 'wallace@gmail.com'), " +
        					"(9, default, default, '2023-12-10', '2023-12-11', default, 'wallace@gmail.com'), " +
        					"(10, default, default, '2023-01-10', '2023-02-11', default, 'sophie@gmail.com');"
        					
    		)
		};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        
    }
    
    
   
    
    
    
    
    
	
	

}
