package parkingManagement.data;

import parkingManagement.util.SQLConnection;
import parkingManagement.model.Reservation;
import parkingManagement.model.User;
import java.sql.*;
import java.util.*;
public class ReservedSpotsDao {
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	Connection conn = SQLConnection.getDBConnection();
	Statement stmt = null;
	public List<Reservation> viewReservedSpots(String username)
	{
			List<Reservation> ReservationsList = new ArrayList<Reservation>();
		try {
			stmt = conn.createStatement();
			String queryString = "select * from `reservation` where username = '"+username+"'";
			ResultSet rs = stmt.executeQuery(queryString);
			if(rs != null)
			{
				while(rs.next()){
				Reservation reservedspot = new Reservation();
				reservedspot.setReservation_id(rs.getInt("reservation_id"));
				reservedspot.setUsername(rs.getString("username"));
				reservedspot.setParkingarea_id(rs.getInt("parkingarea_id"));
				reservedspot.setReservation_date(rs.getDate("reservation_date"));
				reservedspot.setFrom_time(rs.getTime("from_time"));
				reservedspot.setTo_time(rs.getTime("to_time"));
				reservedspot.setParkingslot_no(rs.getInt("parkingslot_no"));
				reservedspot.setCart(rs.getBoolean("cart"));
				reservedspot.setCamera(rs.getBoolean("camera"));
				reservedspot.setHistory(rs.getBoolean("history"));
				ReservationsList.add(reservedspot);
			}
		}
			else
			{
				ReservationsList = null;
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
		return ReservationsList;
}
}