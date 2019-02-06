package parkingManagement.data;

import parkingManagement.util.SQLConnection;
import parkingManagement.model.User;
import java.sql.*;
import java.util.*;
public class SearchUserDao {
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	Connection conn = SQLConnection.getDBConnection();
	Statement stmt = null;
	public List<User> searchUser(String firstname,String lastname)
	{
			List<User> UserList = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			String queryString = "select * from `users` where firstname = '"+firstname+"' and lastname = '"+lastname+"'";
			ResultSet rs = stmt.executeQuery(queryString);
			if(rs != null)
			{
				while(rs.next()){
				User search_user = new User();
				search_user.setFirstname(rs.getString("firstname"));
				search_user.setLastname(rs.getString("lastname"));
				search_user.setUsername(rs.getString("username"));
				search_user.setPassword(rs.getString("password"));
				search_user.setUta_id(rs.getString("utaid"));
				search_user.setRole(rs.getString("user_role"));
				search_user.setPhone(rs.getString("phone"));
				search_user.setEmail(rs.getString("email"));
				search_user.setCity(rs.getString("city"));
				search_user.setStreet_add(rs.getString("address"));
				search_user.setState(rs.getString("state"));
				search_user.setCar_plate_num(rs.getString("car_plate_number"));
				search_user.setPermit_id(rs.getString("permit_id"));
				search_user.setPermit_type(rs.getString("permit_type"));
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
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return UserList;
}
}