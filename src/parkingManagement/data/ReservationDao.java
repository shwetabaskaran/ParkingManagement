package parkingManagement.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parkingManagement.util.SQLConnection;

public class ReservationDao {
	
	static SQLConnection sqlconnection = SQLConnection.getInstance();

	public Map<Integer, Integer> getParkingAreaCountList(ArrayList<Integer> parkingAreaIdList) {
		
		Map<Integer, Integer> reservationcountMap = new HashMap<Integer, Integer>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		
	try {
		stmt = conn.createStatement();
		String queryString = null;
		
		ResultSet reservationCount = null;
		for(int id : parkingAreaIdList) {
			queryString = "select count(*) AS count from reservation where parkingArea_id=" + id ;
			reservationCount = stmt.executeQuery(queryString);
			if(reservationCount.next())
				reservationcountMap.put(id, reservationCount.getInt("count"));
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn!=null)
				conn.close();
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return reservationcountMap;

	}

}
