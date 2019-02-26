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
	
//	public List<User> getRevokedUsers()
//	{
//			List<User> RevokedUserList = new ArrayList<User>();
//		try {
//			stmt = conn.createStatement();
//			String queryString = "select * from `users` where user_status='Revoked'";
//			ResultSet rs = stmt.executeQuery(queryString);
//			if(rs != null)
//			{
//				while(rs.next()){
//				User revokedUsers = new User();
//				revokedUsers.setFirstname(rs.getString("firstname"));
//				revokedUsers.setLastname(rs.getString("lastname"));
//				revokedUsers.setUsername(rs.getString("username"));
//				revokedUsers.setNoshows(rs.getInt("noshows"));
//				revokedUsers.setOverdue(rs.getInt("overdue"));
//				RevokedUserList.add(revokedUsers);
//			}
//		}
//			else
//			{
//				RevokedUserList = null;
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if(conn!=null)
//					conn.close();
//				if(stmt!=null)
//					stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			};
//		}
//		return RevokedUserList;
//} 
	
	public String getUserStatus(String username)
	{
			String userStatus = null;
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
				userStatus = null;
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

}
