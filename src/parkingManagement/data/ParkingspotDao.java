package parkingManagement.data;

import java.sql.Connection;
import parkingManagement.model.UnavailableSpot;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.*;

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
	public ArrayList<ParkingArea> fetch_parking_details(String parkingname)
	{
		
		ArrayList<ParkingArea> parkarealist = new ArrayList<ParkingArea>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+parkingname+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
			ParkingArea parkarea = new ParkingArea();
			parkarea.setParkingarea_name(parkingname);
			parkarea.setFloor(rs.getInt("floor"));
			parkarea.setParkingtype(rs.getString("parkingtype"));
			parkarea.setCapacity(Integer.parseInt(rs.getString("capacity")));
			parkarealist.add(parkarea);
			
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
		
		
		return parkarealist; 
	}
	
	public void updateParkarea(ParkingArea parkarea){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "UPDATE `parkingarea` SET `capacity` = '"+parkarea.getCapacity()+"', `parkingtype` = '"+parkarea.getParkingtype()+"' WHERE `parkingarea_name`='"+parkarea.getParkingarea_name()+"' and `parkingtype`='"+parkarea.getParkingtype()+"' and `floor` ="+parkarea.getFloor();
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
	public void updateName(String oldname, String newname)
	{
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		stmt2 = conn.createStatement(); 
		String query = "select * from `parkingarea` where `parkingarea_name` ='"+oldname +"'";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
		String queryString = "UPDATE `parkingarea` SET `parkingarea_name` ='"+newname+"' WHERE `parkingarea_id`='"+rs.getString("parkingarea_id")+"'";
		stmt2.execute(queryString);
		conn.commit();
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
		
	}
	
	public void setParkspotunavail(UnavailableSpot unavailspot)
	{
		
		Statement stmt = null;
		String parkingarea_id="";
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+unavailspot.getParkingName()+"' and `parkingtype`='"+unavailspot.getType()+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
			parkingarea_id = rs.getString("parkingarea_id"); 
		}
		String query2 = "INSERT INTO `unavailablespots`(`parking_id`,`spot_no`) VALUES ('"+parkingarea_id+"',"+unavailspot.getSpot_no()+")";
		stmt.execute(query2);
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
	
	public ArrayList<UnavailableSpot> fetch_unavail_spots()
	{
		ArrayList<UnavailableSpot> unlist = new ArrayList<UnavailableSpot>();
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		stmt2 = conn.createStatement();
		String queryString = "select * from `unavailablespots`";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
			UnavailableSpot unavail = new UnavailableSpot();
			String query2 ="select * from `parkingarea` where `parkingarea_id` ='"+rs.getString("parking_id")+"'";
			ResultSet re = stmt2.executeQuery(query2);
			while(re.next()){
			unavail.setParkingName(re.getString("parkingarea_name"));
			unavail.setType(re.getString("parkingtype"));
			}
			unavail.setSpot_no(rs.getInt("spot_no"));
			unlist.add(unavail);
		 
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
		
		
		return unlist;
		}
	
	public void remove_unavailable(UnavailableSpot unavail)
	{
		Statement stmt = null;
		Statement stmt2 = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		stmt2 = conn.createStatement();
		String queryString = "select * from `parkingarea` where `parkingarea_name`='"+unavail.getParkingName()+"' and `parkingtype`='"+unavail.getType()+"'";
		ResultSet rs = stmt.executeQuery(queryString);
		while(rs.next())
		{
		 String query2 ="Delete from `unavailablespots` where `parking_id`='"+rs.getString("parkingarea_id")+"' and `spot_no`="+unavail.getSpot_no();
		 stmt2.execute(query2);
		 conn.commit();
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
		
		
	}
}
