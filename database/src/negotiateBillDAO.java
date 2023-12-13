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
@WebServlet("/negotiateBillDAO")
public class negotiateBillDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public negotiateBillDAO(Connection dbInstance) { connect = dbInstance; }
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    
    public List<negotiateBill> listAllNB() throws SQLException {
        List<negotiateBill> listNegB = new ArrayList<negotiateBill>();        
        String sql = "SELECT * FROM NegotiateBill";   
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int negotiateBID = resultSet.getInt("negotiateBID");
            int billID = resultSet.getInt("billID");
            String email = resultSet.getString("email");
            String msg = resultSet.getString("msg"); 
            String date = resultSet.getString("date"); 

            negotiateBill negBs = new negotiateBill(negotiateBID, billID, email, msg, date);
            listNegB.add(negBs);
            
        }        
        resultSet.close();
        
                
        return listNegB;
    }
    
    public List<negotiateBill> listConvo(int billID) throws SQLException {
        List<negotiateBill> listConvo = new ArrayList<negotiateBill>();        
        String sql = "SELECT * FROM NegotiateQuote where quoteID=?";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, billID);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int negotiateBID = resultSet.getInt("negotiateBID");
            String email = resultSet.getString("email");
            String msg = resultSet.getString("msg"); 
            String date = resultSet.getString("date"); 

            negotiateBill billConvo = new negotiateBill(negotiateBID, billID, email, msg, date);
            listConvo.add(billConvo);
            
        }        
        resultSet.close();
        
                
        return listConvo;
    }
    
    public void insert(negotiateBill negBs) throws SQLException {
    	
		String sql = "insert into NegotiateBill(billID, email, msg) values (?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			//preparedStatement.setInt(1, negQs.getNegotiateID());
			preparedStatement.setInt(1, negBs.getBillID());
			preparedStatement.setString(2, negBs.getEmail());	
			preparedStatement.setString(3, negBs.getMsg());		
			//preparedStatement.setString(8, negQs.getDate());		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(int billID) throws SQLException {
        String sql = "DELETE FROM NegotiateBill WHERE billID = ?";        
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, billID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
  
    public void init() throws SQLException, FileNotFoundException, IOException{
    	
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = { "drop table if exists NegotiateBill; ",
		        ("CREATE TABLE if not exists NegotiateBill( " +
		        	"negotiateBID INTEGER NOT NULL AUTO_INCREMENT, " +
		            "billID INTEGER NOT NULL, " +
		        	"email VARCHAR(50) NOT NULL, " +
		            "msg VARCHAR(500)," +
		            "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
		            "PRIMARY KEY (negotiateBID), " +
		            "FOREIGN KEY (billID) REFERENCES Bill(billID)," +
		            "FOREIGN KEY (email) REFERENCES User(email));" )
		        
				};
        String[] TUPLES = {("insert into NegotiateBill(billID, email, msg, date)"+
        			"values (4, 'davidsmith@gmail.com', 'I am not cutting these trees!', default), " +
        			"(4, 'amelia@gmail.com', 'Please cut my trees!', default), " +
        			"(4, 'davidsmith@gmail.com', 'No', default), " +
        			"(4, 'amelia@gmail.com', 'Please cut my trees!', default), " +
        			"(4, 'davidsmith@gmail.com', 'I already said no.', default), " +
        			"(4, 'amelia@gmail.com', 'Please cut my trees!', default), " +
        			"(4, 'davidsmith@gmail.com', 'NO', default), " +
        			"(4, 'amelia@gmail.com', 'Please cut my trees!', default), " +
        			"(4, 'davidsmith@gmail.com', 'FINE! I will cut your trees', default), " +
        			"(4, 'amelia@gmail.com', 'Thank you!', default); ")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        
    }
    
    
   
    
    
    
    
    
	
	

}
