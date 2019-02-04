package parkingManagement.data;

import parkingManagement.util.SQLConnection;
import parkingManagement.model.User;
import java.sql.*;

public class LoginUserDao {
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	Connection conn = SQLConnection.getDBConnection();
	Statement stmt = null;
	public User searchUser(String username)
	{
			User user = new User();
		try {
			stmt = conn.createStatement();
			String queryString = "select * from `users` where username = '"+username+"'";
			ResultSet rs = stmt.executeQuery(queryString);
				while(rs.next()){
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("user_role"));
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
		return user;
	}
}

	
