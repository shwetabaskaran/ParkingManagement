package parkingManagement.data;

import parkingManagement.util.SQLConnection;
import parkingManagement.model.User;
import java.sql.*;
import java.util.*;
public class SearchUserDao {
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	Connection conn = SQLConnection.getDBConnection();
	Statement stmt = null;
	public List<User> searchUser(String lastname)
	{
			List<User> UserList = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			String queryString = "select * from `users` where lastname LIKE '"+lastname+"%'";
			ResultSet rs = stmt.executeQuery(queryString);
			if(rs != null)
			{
				while(rs.next()){
				User search_user = new User();
				search_user.setFirstname(rs.getString("firstname"));
				search_user.setLastname(rs.getString("lastname"));
				search_user.setUsername(rs.getString("username"));
				UserList.add(search_user);
			}
		}
			else
			{
				UserList = null;
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
		return UserList;
}
	public User searchSpecificUser(String username)
	{
			User specificUser = new User();
		try {
			stmt = conn.createStatement();
			String queryString = "select * from `users` where username = '"+username+"'";
			ResultSet rs = stmt.executeQuery(queryString);
			if(rs != null)
			{
				while(rs.next()){
				specificUser.setFirstname(rs.getString("firstname"));
				specificUser.setLastname(rs.getString("lastname"));
				specificUser.setUsername(rs.getString("username"));
				specificUser.setUta_id(rs.getString("utaid"));
				specificUser.setRole(rs.getString("user_role"));
				specificUser.setPhone(rs.getString("phone"));
				specificUser.setEmail(rs.getString("email"));
				specificUser.setCity(rs.getString("city"));
				specificUser.setStreet_add(rs.getString("address"));
				specificUser.setState(rs.getString("state"));
				specificUser.setZip_code(rs.getString("zip"));
				specificUser.setCar_plate_num(rs.getString("number_plate"));
				specificUser.setPermit_id(rs.getString("permit_id"));
				specificUser.setPermit_type(rs.getString("permit_type"));
			}
		}
			else
			{
				specificUser = null;
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
		return specificUser;
}
	
}