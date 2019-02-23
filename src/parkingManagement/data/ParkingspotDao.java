package parkingManagement.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
		String queryString = null;
		if (parkingArea.getParkingtype().equals("Premium"))
			queryString = "select * from parkingarea where parkingArea_name='" +parkingArea.getParkingarea_name()+ "' and"
				+ " parkingtype in  ('" +parkingArea.getParkingtype() + "', 'Midrange', 'Basic') ";
		else if (parkingArea.getParkingtype().equals("Midrange"))
			queryString = "select * from parkingarea where parkingArea_name='" +parkingArea.getParkingarea_name()+ "' and"
				+ " parkingtype in ('" +parkingArea.getParkingtype() + "', 'Basic') ";
		else
			queryString = "select * from parkingarea where parkingArea_name='" +parkingArea.getParkingarea_name()+ "' and"
					+ " parkingtype = '" +parkingArea.getParkingtype() + "' ";
		
		ResultSet parkingResultSet = stmt.executeQuery(queryString);
			
		while (parkingResultSet.next()) {
			ParkingArea parkingAreaFromdb = new ParkingArea(); 
			parkingAreaFromdb.setParkingarea_id(parkingResultSet.getInt("parkingarea_id"));
			parkingAreaFromdb.setParkingarea_name(parkingResultSet.getString("parkingarea_name"));
			parkingAreaFromdb.setParkingtype(parkingResultSet.getString("parkingtype"));
			parkingAreaFromdb.setCapacity(parkingResultSet.getInt("capacity"));
			parkingAreaFromdb.setFloor(parkingResultSet.getInt("floor")); 
			parkingList.add(parkingAreaFromdb);	
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
	return parkingList;

	}

	public ArrayList<String> getparkingareaname()
	{
		ArrayList<String> parkingAreaName = new ArrayList<String>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "select distinct parkingarea_name from `parkingarea`";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
			String name = rs.getString("parkingarea_name");
			parkingAreaName.add(name);
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
		
		return parkingAreaName;
		
	}
	
	public int number_avail_spot(String parkingareaname,String fromTime, String toTime, String permit_type){
		int count=0;
		int capacity=0;
		String parkingarea_id="";
		Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String today = dateFormat.format(calendar.getTime()).toString();
	    Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+parkingareaname+"' and `parkingtype`='"+permit_type+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
			parkingarea_id = rs.getString("parkingarea_id");
			capacity = Integer.parseInt(rs.getString("capacity")); 
		}
		String queryString2 = "select * from `reservation` where `parkingarea_id`='"+parkingarea_id+"' and `reservation_date`='"+today+"' and `from_time` >='"+fromTime+"' and `to_time`<='"+toTime+"'";
		ResultSet re = stmt.executeQuery(queryString2);
		while(re.next())
		{
			count=count+1;
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
		count = capacity-count;
		return count;
	}
	
	public void addParkingArea(ParkingArea newpark){
		 Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String queryString = "INSERT INTO `parkingarea` (`parkingarea_name`, `floor`, `capacity`, `parkingtype`) VALUES ('"+newpark.getParkingarea_name()+"','"+newpark.getFloor()+"', '"+newpark.getCapacity()+"', '"+newpark.getParkingtype()+"')";
			stmt.execute(queryString);
			conn.commit();
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
	}
}
