package parkingManagement.data;

import parkingManagement.util.SQLConnection;
import parkingManagement.model.User;
import java.sql.*;
import java.util.*;

public class UserStatusDao {
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	Connection conn = SQLConnection.getDBConnection();
	Statement stmt = null;
	
	public List<User> getActiveUsers()
	{
			List<User> ActiveUserList = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			String queryString = "select * from `users` user_status='Active'";
			ResultSet rs = stmt.executeQuery(queryString);
			if(rs != null)
			{
				while(rs.next()){
				User activeUsers = new User();
				activeUsers.setFirstname(rs.getString("firstname"));
				activeUsers.setLastname(rs.getString("lastname"));
				activeUsers.setUsername(rs.getString("username"));
				activeUsers.setNoshows(rs.getInt("noshows"));
				activeUsers.setOverdue(rs.getInt("overdue"));
				ActiveUserList.add(activeUsers);
			}
		}
			else
			{
				ActiveUserList = null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return ActiveUserList;
} 
	
	public String getUserStatus(String username)
	{
			String userStatus = "";
		try {
			stmt = conn.createStatement();
			String queryString = "select user_status from `users` where username = '"+username+"'";
			ResultSet rs = stmt.executeQuery(queryString);
			if(rs != null)
			{
				while(rs.next()){
				userStatus = rs.getString("user_status");
			}
		}
			else
			{
				userStatus = "";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return userStatus;
} 
	
	
	public void changeUserStatus(String user_status, String username){
		try {
			stmt = conn.createStatement();
			String queryString ="Update `users` set user_status='"+user_status+"' where `username`='"+username+"'";
			stmt.execute(queryString);
			conn.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
	}
	
		public void resetViolations(String username){
			try {
				stmt = conn.createStatement();
				String queryString ="Update `users` set noshows=0, overdue=0 where `username`='"+username+"'";
				stmt.execute(queryString);
				conn.commit();
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(conn!=null)
						conn.close();
					if(stmt!=null)
						stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				};
			}
		}


}
