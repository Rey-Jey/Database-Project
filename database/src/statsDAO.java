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
@WebServlet("/statsDAO")
public class statsDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public statsDAO(Connection dbInstance) { connect = dbInstance; }
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    
	 public List<stats> Statistics() throws SQLException {
	        List<stats> listStat = new ArrayList<stats>();        
	        String sql = 
	        		"SELECT "
	        		+ "	Bill.email, "
	        		+ " COALESCE(SUM(Bill.tree_amt), 0) AS total_trees, "
	        		+ " COALESCE(SUM(price), 0) AS total_due, "
	        		+ " COALESCE(SUM(CASE WHEN status = 'Paid' THEN price ELSE 0 END),0) AS total_paid, "
	        		+ " GROUP_CONCAT(DISTINCT end_time ORDER BY end_time) AS work_dates "
	        		+ "FROM Bill "
	        		+ "GROUP BY	email;";      
	              
	        try (PreparedStatement preparedStatement = connect.prepareStatement(sql);
	        		ResultSet resultSet = preparedStatement.executeQuery()) {
	         
			        while (resultSet.next()) {
			            String email = resultSet.getString("email");
			        	int totalTrees = resultSet.getInt("total_trees");
			            double totalDue = resultSet.getDouble("total_due");
			            double totalPaid= resultSet.getDouble("total_paid");
			            String workDates = resultSet.getString("work_dates");
		
			            
			            stats stat = new stats(email, totalTrees, totalDue, totalPaid, workDates);
			            listStat.add(stat);		            
			        }
			        
			        
    		} catch (SQLException e) {
    			e.printStackTrace();
			}
	        
	        return listStat;
	    }

}
