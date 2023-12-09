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
@WebServlet("/orderDAO")
public class orderDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public orderDAO(Connection dbInstance) { connect = dbInstance; }
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    
    public List<order> listAllOrders() throws SQLException {
        List<order> listOrder = new ArrayList<order>();        
        String sql = "SELECT * FROM qOrder";      
              
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int orderID = resultSet.getInt("orderID");
            int quoteID = resultSet.getInt("quoteID");
            int tree_amt = resultSet.getInt("tree_amt");
            double price = resultSet.getDouble("price");
            String email = resultSet.getString("email");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
             
            order orders = new order(orderID, quoteID, tree_amt, price, start_time, end_time, status, email);
            listOrder.add(orders);
            
        }        
        resultSet.close();
        return listOrder;
    }
    
    public List<order> listUserOrders(String email) throws SQLException {
        List<order> listUserOrder = new ArrayList<order>();        
        String sql = "SELECT * FROM qOrder WHERE email=?";      
              
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	int orderID = resultSet.getInt("orderID");
            int quoteID = resultSet.getInt("quoteID");
            int tree_amt = resultSet.getInt("tree_amt");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            
            order orders = new order(orderID, quoteID, tree_amt, price, start_time, end_time, status, email);
            listUserOrder.add(orders);
            
        }        
        resultSet.close();
        return listUserOrder;
    }
    
    public List<order> listPendingOrders(String email) throws SQLException {
        List<order> listPendingOrder = new ArrayList<order>();        
        String sql = "SELECT * FROM qOrder WHERE email=? and status='Pending'";      
              
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	int orderID = resultSet.getInt("orderID");
            int quoteID = resultSet.getInt("quoteID");
            int tree_amt = resultSet.getInt("tree_amt");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            
            order orders = new order(orderID, quoteID, tree_amt, price, start_time, end_time, status, email);
            listPendingOrder.add(orders);
            
        }        
        resultSet.close();
        return listPendingOrder;
    }
    
    public void insertFromQuote(order orders) throws SQLException {
    	            
		String sql = "insert into qOrder (quoteID, tree_amt, price, email, contractor, start_time, end_time) select quoteID, tree_amt, price, email, contractor, start_time, end_time from Quote where quoteID=?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);	
		preparedStatement.setInt(1, orders.getQuoteID());		


		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    //public void ()
    
    public boolean delete(int orderID) throws SQLException {
        String sql = "DELETE FROM qOrder WHERE orderID = ?";        
        
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, orderID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(order orders) throws SQLException {
        String sql = "update qOrder set quoteID=?, tree_amt=?, price=?, start_time=?, end_time=?, status=? where quoteID=?";
        
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, orders.getQuoteID());
        preparedStatement.setInt(2, orders.getTree_amt());
		preparedStatement.setDouble(3, orders.getPrice());
		preparedStatement.setString(4, orders.getStart_time());
		preparedStatement.setString(5, orders.getEnd_time());
		preparedStatement.setString(6, orders.getStatus());
        preparedStatement.setInt(7, orders.getQuoteID());

		//preparedStatement.setString(7, quotes.getEmail());		
	
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public boolean updateStatus(int orderID, String status) throws SQLException {
        String sql = "update Quote set status=? where quoteID=?";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, status);
		preparedStatement.setInt(2, orderID);
				
	
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public order getOrder(int orderID) throws SQLException {
    	order currentOrder = null;
        String sql = "SELECT * FROM Quote WHERE quoteID = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, orderID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	int quoteID = resultSet.getInt("quoteID");
        	int tree_amt = resultSet.getInt("tree_amt");
        	double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            String email = resultSet.getString("email");

            currentOrder = new order(orderID, quoteID, tree_amt, price, start_time, end_time, status, email);
        }
         
        resultSet.close();
        statement.close();
         
        return currentOrder;
    }
    
    public void init() throws SQLException, FileNotFoundException, IOException {
    	
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = { "drop table if exists qOrder; ",
					        ("CREATE TABLE if not exists qOrder( " +
					        	"orderID INTEGER NOT NULL AUTO_INCREMENT, " + 
					            "quoteID INTEGER NOT NULL, " +
					        	"tree_amt INTEGER DEFAULT 0, " +
					        	"price DECIMAL(7,2) DEFAULT 0, " +
					        	"email VARCHAR(50) NOT NULL, " +
					        	"contractor VARCHAR(50) DEFAULT 'davidsmith@gmail.com', " +
					            "start_time DATE DEFAULT '0001-01-01', " +
					            "end_time DATE DEFAULT '0001-01-01', " +
					            "status VARCHAR(20) DEFAULT 'In Progress', " +
					            "PRIMARY KEY (orderID), " +
					            "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID)," +
					            "FOREIGN KEY (email) REFERENCES User(email)," +
					            "FOREIGN KEY (contractor) REFERENCES User(email));" )
					        
        					};
        String[] TUPLES = {("insert into qOrder(orderID, quoteID, tree_amt, price, start_time, end_time, status, email)" +
        					"values (1, 1, default, default, '2023-10-10', '2023-10-11', default, 'rey@gmail.com'), " +
        					"(2, 2, default, default, '2023-11-15', '2023-12-01', default, 'j@gmail.com'), " +
        					"(3, 3, default, default, '2023-12-17', '2024-01-01', default, 'wallace@gmail.com'), " +
        					"(4, 4, default, default, default, default, 'Rejected', 'amelia@gmail.com'), " +
        					"(5, 5, default, default, '2023-12-10', '2023-12-11', default, 'rey@gmail.com'), " +
        					"(6, 6, default, default, '2023-12-10', '2023-12-11', default, 'j@gmail.com'), " +
        					"(7, 7, default, default, '2023-12-10', '2023-12-11', default, 'wallace@gmail.com'), " +
        					"(8, 8, default, default, '2023-12-10', '2023-12-11', default, 'wallace@gmail.com'), " +
        					"(9, 9, default, default, '2023-12-10', '2023-12-11', default, 'wallace@gmail.com'), " +
        					"(10, 10, default, default, '2023-01-10', '2023-02-11', default, 'sophie@gmail.com');"
        					
    		)
		};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        
    }
    
    
   
    
    
    
    
    
	
	

}
