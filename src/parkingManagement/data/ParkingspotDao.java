package parkingManagement.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import parkingManagement.model.ParkingArea;
import parkingManagement.util.*;

public class ParkingspotDao {
	
	static SQLConnection sqlconnection = SQLConnection.getInstance();

	public ArrayList<ParkingArea> getParkingAreaList(ParkingArea parkingArea) {
		ArrayList<ParkingArea> parkingList = new ArrayList<ParkingArea>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "select * from parkingarea where parkingArea_name='" +parkingArea.getParkingarea_name()+ "' and"
				+ " parkingtype = '" +parkingArea.getParkingtype() + "' ";
		System.out.println("Query string is : "+queryString);
		ResultSet parkingResultSet = stmt.executeQuery(queryString);
		while (parkingResultSet.next()) {
			ParkingArea parkingAreaFromdb = new ParkingArea(); 
			parkingAreaFromdb.setParkingarea_name(parkingResultSet.getString("parkingarea_name"));
			parkingAreaFromdb.setParkingtype(parkingResultSet.getString("parkingtype"));
			parkingAreaFromdb.setCapacity(parkingResultSet.getInt("capacity"));
			parkingAreaFromdb.setAvailability(parkingResultSet.getInt("availability"));  
			parkingAreaFromdb.setFloor(parkingResultSet.getInt("floor")); 
			System.out.println(parkingAreaFromdb.getParkingarea_name()+" "+parkingAreaFromdb.getParkingtype());
			parkingList.add(parkingAreaFromdb);	
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return parkingList;

	}

}
